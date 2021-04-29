import java.io.*;
import java.net.*;


public class UserThread extends Thread {
	private Socket socket;
	private ChatServer server;
	private PrintWriter writer;
	String userName;
	String userNameAndPassword;
	String password;
	String up;
	public UserThread(Socket socket, ChatServer server) {
		this.socket = socket;
		this.server = server;
	}

	public void setUsername(String name) {
		userName = name;
	}

	public String getUsername() {
		return userName;
	}
   // login and check credentials
	public void login(BufferedReader reader) throws Exception {

		while (up.equals("in") && !server.checkUserNameAndPassword(userName, password)) {
			userNameAndPassword = reader.readLine();
			String[] splited = userNameAndPassword.split("\\s+");

			up = splited[0];
			userName = splited[1];
			password = splited[2];
			server.wrongPass("Wrong pass", this);

		}  

		if (up.equals("up")) {
			signup(reader);
		} else {
			server.correctPass("Correct pass", this);   
			String serverMessage = "#" + server.getUserNames();
			Thread.sleep(1000);
			server.giveListOfUsers(serverMessage);
		}
	}

	//sing up 
	public void signup (BufferedReader reader) throws Exception {
		System.out.println("sign up");
		while(server.checkUserNameDuplication(userName) && up.equals("up")) { 
			userNameAndPassword = reader.readLine();
			String[] splited = userNameAndPassword.split("\\s+");                 
			up = splited[0];
			userName = splited[1];
			password = splited[2];
			System.out.println("Duuplicates");
			server.wrongPass("Duplicate username", this);
		}
		if (up.equals("in")) {
			login(reader);
		} else {
			server.correctPass("Good", this);   
			server.signUp(userName, password);
			String serverMessage = "#" + server.getUserNames();
			Thread.sleep(1000);
			server.giveListOfUsers(serverMessage);
		}

	}

	@SuppressWarnings("deprecation")
	public void run() {
		try {
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);

			userNameAndPassword = reader.readLine();
			
			String[] splited = userNameAndPassword.split("\\s+");

			up = splited[0];
			userName = splited[1];
			password = splited[2];

			// once check for sign up and sign in procceed to add username to the list
			if (up.equals("in")) {
				login(reader);
			} else if (up.equals("up")) {
				signup(reader);
			}

			server.addUserName(userName);
			String serverMessage = "#" + server.getUserNames();
			server.giveListOfUsers(serverMessage);


			String clientMessage;

			// listen to the client
			do {
				clientMessage = reader.readLine();

				if (clientMessage == null)
					break;
				else {
					// if client request changing username
					if (clientMessage.substring(0,2).equals("!#")) {
						String oldName = userName;
						setUsername(clientMessage.substring(2));
						server.changeUserName(oldName, clientMessage.substring(2), this);
					}
					// if client request changing password
					else if (clientMessage.substring(0,2).equals("$#")) {
						server.changePassword(clientMessage.substring(2), userName);
					} else if (clientMessage.substring(0,2).equals("##")) {

						if(clientMessage.substring(2,3).equals("[")) {
							server.loadUserData2(userName, clientMessage.substring(2), this);
							System.out.println("Gotta do");
						} else               	
							server.loadUserData(userName, clientMessage.substring(2), this);
						// if user request delete an account
					} else if (clientMessage.substring(0,2).equals("!!")) {
						server.deleteAccount(userName);
					}
					 else if (clientMessage.substring(0,2).equals("**")) {
							server.writeToFileForceChange(userName, clientMessage.substring(2));
					}
					 else if (clientMessage.substring(0,2).equals("*!")) {
							server.setUsetDontAppear(userName, clientMessage.substring(2));
					}
					else {
						// if client does not request anything, just send nad broadcast message
						serverMessage = "[" + userName + "]: " + clientMessage;
						server.broadcast(serverMessage, this);
					}
				}


			} while (true);
		   
			socket.close();
       
		} catch (Exception ex) {
            System.out.println("user exited");
			ex.printStackTrace();

		}
	}

    // send message to client
	void sendMessage(String message) {
		writer.println(message);
	}
}