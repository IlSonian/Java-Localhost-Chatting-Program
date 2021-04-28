import java.io.*;
import java.net.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
import java.util.*;
import java.util.Arrays;

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
    
    void createGroup(String sender, String[] arr, UserThread excludeUser) {
    	ArrayList<String> groupMemberUsername = new ArrayList<String>();
    	sender = sender.replace("[", "");
    	sender = sender.replace("]", "");
    	//groupMemberUsername.add(sender);
    	for (int i =0; i < arr.length; i++) {
    		groupMemberUsername.add(arr[i]);
    	}
    	
    	Group grouppy = new Group("Group"+(group.size()+1), groupMemberUsername);
    	group.add(grouppy);
    	/*
    	   for (UserThread user : userThreads) {
               if (user == excludeUser) {
                   user.sendMessage("#{"+ grouppy.toString()+ "}, "+ getUserNames() );
                   
               }
           } */
    	for (UserThread user : userThreads) {
        String allgrou = "";
        boolean check = false;
        for (int i =0; i < group.size(); i++) {
        	check = false;
        	for (int j = 0 ; j < group.get(i).getUserList().size(); j++) {
        		if (excludeUser.getUsername().equals(group.get(i).getUserList().get(j))) {
        			check = true;	
        		}
        	}
        	if (check) {
        		allgrou += allgrou +"{"+ group.get(i).toString() +"}, ";
        	}
        }
    	String[] splited = allgrou.split(",");
  
    	ArrayList<String> yourList = new ArrayList<>();
      	for (int i = 0; i < splited.length; i++) {
    		splited[i] = splited[i].trim();
    		yourList.add(splited[i]);
    	}
      	
    	Set<String> set = new HashSet<>(yourList);
    	yourList.clear();
    	yourList.addAll(set);
    	String c = yourList.toString();
    	c = c.replace("[", "");
    	c = c.replace("]", "");
    	allgrou = c;

        user.sendMessage("#"+allgrou +", "+getUserNames());
    	}
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
    
    boolean checkUserNameDuplication(String username) {
	for (int i = 0; i < userData.size(); i++) {
            System.out.println("check dups "+username+" "+userData.get(i).getUsername());
    		if (username.equals(userData.get(i).getUsername()) ) {
    			System.out.println(" dups");
    			return true;
    		}
    		
    	}
	   return false;
    }
    
    boolean checkUserNameAndPassword(String username, String password) {
    	for (int i = 0; i < userData.size(); i++) {
            
    		if (username.equals(userData.get(i).getUsername()) && password.equals(userData.get(i).getPsswd()) ) {
    			return true;
    		}
    		
    	}
    	
    	return false;
    }
    
    void giveListOfUsers2(String message, UserThread excludeUser) {
  	   for (UserThread user : userThreads) {
            if (user != excludeUser) {
                user.sendMessage(message);
            }
           
            String allgrou = "";
            for (int i =0; i < group.size(); i++)
            	for (int j = 0 ; j < group.get(i).getUserList().size(); j++) {
            		if (user.getUsername().equals(group.get(i).getUserList().get(j))) {
            			allgrou += allgrou+ "{"+ group.get(i).toString() + "}, ";
            			
            		}
            	}
        	String[] splited = allgrou.split(",");
        	  
        	ArrayList<String> yourList = new ArrayList<>();
          	for (int i = 0; i < splited.length; i++) {
        		splited[i] = splited[i].trim();
        		yourList.add(splited[i]);
        	}
          	
        	Set<String> set = new HashSet<>(yourList);
        	yourList.clear();
        	yourList.addAll(set);
        	String c = yourList.toString();
        	c = c.replace("[", "");
        	c = c.replace("]", "");
        	allgrou = c;
        	
            user.sendMessage(message +", "+ allgrou);
              
            
        }
     }
    
    void giveListOfUsers(String message, UserThread excludeUser) {
 	   for (UserThread user : userThreads) {
           if (user == excludeUser) {
               user.sendMessage(message);
            
           }
           
           String allgrou = "";
           for (int i =0; i < group.size(); i++)
           	for (int j = 0 ; j < group.get(i).getUserList().size(); j++) {
           		if (user.getUsername().equals(group.get(i).getUserList().get(j))) {
           			allgrou += allgrou+ "{"+ group.get(i).toString() + "}, ";
           			
           		}
           	}
       	String[] splited = allgrou.split(",");
        
    	ArrayList<String> yourList = new ArrayList<>();
      	for (int i = 0; i < splited.length; i++) {
    		splited[i] = splited[i].trim();
    		yourList.add(splited[i]);
    	}
      	
    	Set<String> set = new HashSet<>(yourList);
    	yourList.clear();
    	yourList.addAll(set);
    	String c = yourList.toString();
    	c = c.replace("[", "");
    	c = c.replace("]", "");
    	allgrou = c;
    	
           user.sendMessage(message +", "+ allgrou);
             
             
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
    
    void changeUserName(String oldName, String newName, UserThread excludeUser) {   	
    	for (String c : userNames) {
    		if (c.equals(oldName)) {
    			userNames.remove(c);
    			userNames.add(newName);
    		}
    	}
    	for (int i = 0; i < userData.size(); i++) {
            System.out.println("CHANGE username "+oldName+" "+userData.get(i).getUsername());
    		if (oldName.equals(userData.get(i).getUsername()) ) {
    			userData.get(i).setUsername(newName);
    		}
    		}
    }
    
    void changePassword(String newPass, String username) {
      	for (int i = 0; i < userData.size(); i++) {
            System.out.println("CHANGE passwoed "+username+" "+userData.get(i).getUsername());
    		if (username.equals(userData.get(i).getUsername()) ) {
    			userData.get(i).setPsswd(newPass);
    		}
    		}
    }
    
 
    boolean checkGroupExist(String sender, String[] users) {
    	//sender = sender.replace("[", "");
    	//sender = sender.replace("]", "");
    	ArrayList<String> all = new ArrayList<>();
    	//all.add(sender);
    	for (int i = 0; i< users.length; i++) {
    		all.add(users[i]);
    	}
    	Collections.sort(all);
    	System.out.println( "Sent contains" +all);
    	for (int i = 0; i <group.size(); i++ ) {
    			Collections.sort(group.get(i).getUserList());
    			System.out.println("Check user contain "+ group.get(i).getUserList()  );
    			if (group.get(i).getUserList().equals(all)) {
    				System.out.println("It equals");
    				return true;
    			}
    		}
    	return false;
    	
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
        	
        	if (!checkGroupExist(whosent, userGroup))
            	createGroup(whosent, userGroup, excludeUser);
        	
        	 for (UserThread user : userThreads) {
              for (int ii = 0; ii < userGroup.length; ii++)
        		 if (user.getUsername().equals(userGroup[ii])) {
        			 user.sendMessage(time+" "+whosent + " -> "  + d+": "+c);      			 
                     
                 }                
               }
        	 
        	 
        	 writeToFile2(whosent, (userGroup), time+" "+whosent + " -> "  + d+": "+c);
        }
        
        if (privateConverse) {       
        if (meee.substring(0,1).equals("@")) {
        	 privateConverse = true;
        	 toUser = meee.replace("@", "");
        	 String[] splited = toUser.split("\\s+");
        	  for (UserThread user : userThreads) {
        		
                  if (user.getUsername().equals(splited[0])) {
                	                 	  
                	  //loadUserData(whosent, user.getUsername(), user);
                	  
                	  String c = "";
                	  for (int ii = 1; ii < splited.length; ii++) {
                		  c += splited[ii] + " ";
                	  }
                	  
                      user.sendMessage(time+" "+whosent + ": "+c);
                      writeToFile(whosent, user.getUsername(), time+" "+whosent + ": "+c);
                  }
              }
         }
          
        
        }  
        
        /*
        //broadcast to the entire server and whoever connected to the server.
        if (!privateConverse && !groupconversation)
        for (UserThread user : userThreads) {
            if (user != excludeUser) {
                user.sendMessage(time+" "+message);
            }
        }
        */
    }

    void loadUserData2(String sender, String receiver, UserThread user) {
    	sender = sender.replace("[","");
    	sender = sender.replace("]","");
    	
    
    	receiver = receiver.replace("[", "");
    	receiver = receiver.replace("]", "");
    	String[] array = receiver.split(",");
        for (int i = 0; i < array.length; i++) {
            array[i] =  array[i].trim();
        }
    	Arrays.sort(array);
    	File readfrom1 = new File(sender+"->"+Arrays.toString(array)+".txt");
    	File[] filer = new File[array.length];
    	
    	for (int i = 0; i < filer.length; i++) {
    		filer[i] = new File(array[i]+"->"+Arrays.toString(array)+".txt");
        	try {
        		if (filer[i].exists()) {
            		
            		FileInputStream fstream = new FileInputStream(filer[i]);
            		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            		String strLine;

            		//Read File Line By Line
            		while ((strLine = br.readLine()) != null)   {
            			 user.sendMessage(strLine);
            		}
                    
            		//Close the input stream
            		fstream.close();		
            	  }
        		
            	} catch (Exception e) {
            		
            	}
    	}
    	

    }
    
    
    void loadUserData(String sender, String receiver, UserThread user) {
    	sender = sender.replace("[","");
    	sender = sender.replace("]","");
    	File readfrom1 = new File(sender+"->"+receiver+".txt");
    	File readfrom2 = new File(receiver+"->"+sender+".txt");
    	try {
    	if (readfrom1.exists()) {
    		
    		FileInputStream fstream = new FileInputStream(readfrom1);
    		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

    		String strLine;

    		//Read File Line By Line
    		while ((strLine = br.readLine()) != null)   {
    			 user.sendMessage(strLine);
    		}
            
    		//Close the input stream
    		fstream.close();		
    	  }
    	
    	if (readfrom2.exists()) {
    		
    		FileInputStream fstream = new FileInputStream(readfrom2);
    		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

    		String strLine;

    		//Read File Line By Line
    		while ((strLine = br.readLine()) != null )   {
    			 user.sendMessage(strLine);
    		}

    		//Close the input stream
    		fstream.close();	
    	
    		
    	  }
    	
    	
    	}catch (Exception e) {
    		
    	}
    	
    }
 
    void writeToFile2(String sender, String[] receiver, String content) {
    	sender = sender.replace("[","");
    	sender = sender.replace("]","");
    	Arrays.sort(receiver);
    	try(FileWriter fw = new FileWriter(sender+"->"+Arrays.toString(receiver)+".txt", true);
    		    BufferedWriter bw = new BufferedWriter(fw);
    		    PrintWriter out = new PrintWriter(bw))
    		{
    		    out.println(content);
    		    //more code
    		} catch (IOException e) {
    			
    		}
    }
    
    
    void writeToFile(String sender, String receiver, String content) {
    	sender = sender.replace("[","");
    	sender = sender.replace("]","");
    	try(FileWriter fw = new FileWriter(sender+"->"+receiver+".txt", true);
    		    BufferedWriter bw = new BufferedWriter(fw);
    		    PrintWriter out = new PrintWriter(bw))
    		{
    		    out.println(content);
    		    //more code
    		} catch (IOException e) {
    			
    		}
    }
    
    
    void deleteAccount(String username) {
    	for (String one : userNames) {
    	    if (username.equals(one)) {
    	    	userNames.remove(username);
    	    }
    	  }
    	
    	for (int i = 0; i < userData.size(); i++) {
            System.out.println("delete username "+username+" "+userData.get(i).getUsername());
    		if (username.equals(userData.get(i).getUsername()) ) {
    			userData.remove(i);
    			break;
    		  }
    		}
    }
    
    
    ArrayList<Group>  getGroup() {
    	return this.group;
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