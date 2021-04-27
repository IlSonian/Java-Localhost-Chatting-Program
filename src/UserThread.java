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
    
    public void login(BufferedReader reader) throws IOException {
    	 
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
            server.giveListOfUsers(serverMessage, this);
        }
    }
    
    public void signup (BufferedReader reader) throws IOException {
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
            server.giveListOfUsers(serverMessage, this);
        	}
        
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
            
            if (up.equals("in")) {
            	login(reader);
            } else if (up.equals("up")) {
            	signup(reader);
            }
            /*
            do {
            userNameAndPassword = reader.readLine();
            String[] splited = userNameAndPassword.split("\\s+");
            
            up = splited[0];
            userName = splited[1];
            password = splited[2];
             if (up.equals("in") && !server.checkUserNameAndPassword(userName, password))
             server.wrongPass("Wrong pass", this);
             
            } while (up.equals("in") && !server.checkUserNameAndPassword(userName, password)); 
            	
            if (up.equals("in"))
            if(server.checkUserNameAndPassword(userName, password)) {
            	server.correctPass("Correct pass", this);   
                String serverMessage = "#" + server.getUserNames();
                server.giveListOfUsers(serverMessage, this);
             }
             
             */
            
            /*
            if(up.equals("up") ) {
            	System.out.println("sign up");
            	while(server.checkUserNameDuplication(userName)) { 
            		  userNameAndPassword = reader.readLine();
                      String[] splited = userNameAndPassword.split("\\s+");                 
                      up = splited[0];
                      userName = splited[1];
                      password = splited[2];
                      System.out.println("Duuplicates");
                      server.wrongPass("Duplicate username", this);
                }
               	server.signUp(userName, password);
                String serverMessage = "#" + server.getUserNames();
                server.giveListOfUsers(serverMessage, this);
            }
            */
            
            server.addUserName(userName);
            String serverMessage = "#" + server.getUserNames();
            server.giveListOfUsers2(serverMessage, this);

      
            String clientMessage;
 
            do {
                clientMessage = reader.readLine();
                if (clientMessage == null)
                	break;
                else {
                serverMessage = "[" + userName + "]: " + clientMessage;
                server.broadcast(serverMessage, this);
                }
                
               
            } while (true);
             
        } catch (IOException ex) {
        	this.stop();
        	ex.printStackTrace();
        	
        }
    }
    
    
    void sendMessage(String message) {
        writer.println(message);
    }
}