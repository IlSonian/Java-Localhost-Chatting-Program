import java.io.*;
import java.net.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
import java.util.*;
 
public class ChatServer {
   
	private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
    private ArrayList<Users> userData = new ArrayList<Users>();
    private ArrayList<Group> group = new ArrayList<Group>();
    private ArrayList<UserDMList> userDmList = new ArrayList<UserDMList>();


    public ChatServer(int port) {
        this.port = port;
    }
    
    public static void main(String[] args) {

        int port = 8989;
        ChatServer server = new ChatServer(port);
        server.execute();
        
    }
    
    void loadUserData() {
    	// read the cvs file and add new users to the userData stuff
    }
 
    void signUp(String username, String password) {
    	userData.add(new Users(username, password));
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
    
    void createGroup(String sender, String[] arr) {
    	ArrayList<String> groupMemberUsername = new ArrayList<String>();
    	groupMemberUsername.add(sender);
    	for (int i =0; i < arr.length; i++) {
    		groupMemberUsername.add(arr[i]);
    	}
    	group.add(new Group("Group"+(group.size()+1), groupMemberUsername));
    }
    
    void createDM(String sender, String toReceive) {
    	userDmList.add(new UserDMList(sender,toReceive));
    }
    
    void removeConversation(String sender, String userNameToRemove) {
    	for (int i = 0; i < userDmList.size(); i++) {
    			 if (userDmList.get(i).getSenderName().equals(sender)) {
    				 userDmList.get(i).removeDmToList(userNameToRemove);
    			 }

    	}
    }
    
    boolean checkUserNameAndPassword(String username, String password) {
    	for (int i = 0; i < userData.size(); i++) {
            
    		if (username.equals(userData.get(i).getUsername()) && password.equals(userData.get(i).getPsswd()) ) {
    			return true;
    		}
    		
    	}
    	
    	return false;
    }
    
    void giveListOfUsers(String message, UserThread excludeUser) {
 	   for (UserThread user : userThreads) {
           if (user == excludeUser) {
               user.sendMessage(message);
           }
       }
    }
    
    void wrongPass(String message, UserThread excludeUser) {
    	   for (UserThread user : userThreads) {
               if (user == excludeUser) {
                   user.sendMessage("!"+message);
               }
           }
    }
    
    void correctPass(String message, UserThread excludeUser) {
    	   for (UserThread user : userThreads) {
               if (user == excludeUser) {
                   user.sendMessage("$"+message);
               }
           }
    }
 
    void broadcast(String message, UserThread excludeUser) {
        boolean privateConverse = false;
        boolean groupconversation = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        
		String time = dtf.format(now);

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
        	String d = "";
        	for (int ii = 0; ii < splited.length; ii++) {
        		if (splited[ii].indexOf("@") != -1)  {
        			 userGroup[ii] =  splited[ii].replace("@", "");
        			 d += "["+ userGroup[ii] + "] ";
        		} else {
        		   c += splited[ii] + " ";
        		}
        	}
        	 for (UserThread user : userThreads) {
              for (int ii = 0; ii < userGroup.length; ii++)
        		 if (user.getUsername().equals(userGroup[ii])) {
        			 user.sendMessage(time+" "+whosent + " -> "  + d+": "+c);
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
                      user.sendMessage(time+" "+whosent + ": "+c);
                  }
              }
         }
        }           
        //broadcast to the entire server and whoever connected to the server.
        if (!privateConverse && !groupconversation)
        for (UserThread user : userThreads) {
            if (user != excludeUser) {
                user.sendMessage(time+" "+message);
            }
        }
    }
    
    
    
    
    Set<String> getUserNames() {
        return this.userNames;
    }
    
    
    boolean addUserName(String userName) {
    	for (String one : userNames) {
    	    if (one.equals(userName)) {
    	      return false;
    	    }
    	  } 
        userNames.add(userName);
        return true;
    }

}