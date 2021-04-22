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
    
    public String getUsername() {
    	return userName;
    }
    
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
            
            if (up.equals("in") && server.checkUserNameAndPassword(userName, password)) {
            	socket.close();
            } else {
            	server.signUp(userName, password);
            }
            
            server.addUserName(userName);
 
            String serverMessage = "New user connected: " + userName;
            server.broadcast(serverMessage, this);
 
            String clientMessage;
 
            do {
                clientMessage = reader.readLine();
                serverMessage = "[" + userName + "]: " + clientMessage;
                server.broadcast(serverMessage, this);
            } while (true);
             
        } catch (IOException ex) {
        	try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	ex.printStackTrace();
        	
        }
    }
    void sendMessage(String message) {
        writer.println(message);
    }
}