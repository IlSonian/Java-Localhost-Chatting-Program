import java.io.*;
import java.net.*;
import java.util.*;
 
public class ChatServer {
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
    private ArrayList<Users> userData = new ArrayList<Users>();
    
    public ChatServer(int port) {
        this.port = port;
    }
    
    public static void main(String[] args) {
        int port = 8989;
        ChatServer server = new ChatServer(port);
        server.execute();
    }
 
    public void execute() {
        try  {
        	@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Chat Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");        
                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    void broadcast(String message, UserThread excludeUser) {
        boolean privateConverse = false;
        boolean groupconversation = false;
        int count = 0;
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == '@') {
                count++;
            }
        }
        if (count == 1) privateConverse = true;
        else if (count > 1) groupconversation = true;
        String toUser = "";
        String meee = message;
        String whosent = "";
        meee = meee.substring(meee.indexOf(":")+2);
        whosent = message.substring(0, message.indexOf(":"));
       
        if (groupconversation) {   	
        	String[] splited = meee.split("\\s+");
        	String c = "";
        	String[] userGroup = new String[count];
        	for (int ii = 0; ii < splited.length; ii++) {
        		if (splited[ii].indexOf("@") != -1)  {
        			 userGroup[ii] =  splited[ii].replace("@", "");
        		} else {
        		   c += splited[ii] + " ";
        		}
        	}
        	 for (UserThread user : userThreads) {
              for (int ii = 0; ii < userGroup.length; ii++)
        		 if (user.getUsername().equals(userGroup[ii])) {
        			 user.sendMessage(whosent + ": "+c);
                 }
                 
               }
        	
        }
        
        if (privateConverse) {       
        if (meee.substring(0,1).equals("@")) {
        	 privateConverse = true;
        	 toUser = meee.replace("@", "");
        	 String[] splited = toUser.split("\\s+");
        	  for (UserThread user : userThreads) {
                  if (user.getUsername().equals(splited[0])) {
                	  String c = "";
                	  for (int ii = 1; ii < splited.length; ii++) {
                		  c += splited[ii] + " ";
                	  }
                      user.sendMessage(whosent + ": "+c);
                  }
              }
         }
        }
               
        //broadcast to the entire server and whoever connected to the server.
        if (!privateConverse && !groupconversation)
        for (UserThread user : userThreads) {
            if (user != excludeUser) {
                user.sendMessage(message);
            }
        }
    }
    void addUserName(String userName) {
        userNames.add(userName);
    }

}