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


	//start a port
	public ChatServer(int port) {
		this.port = port;
	}

	//default port is 8989
	public static void main(String[] args) {

		int port = 8989;
		ChatServer server = new ChatServer(port);
		server.execute();

	}

	//sign up by create new users and add users to the arraylist of users with username and password
	void signUp(String username, String password) {
		userData.add(new Users(username, password));
	}

	// execute the server

	public void execute() {
		try  {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Chat Server is listening on port " + port);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("New user connected");        
				// create and start a new server thread 
				UserThread newUser = new UserThread(socket, this);
				userThreads.add(newUser);
				newUser.start();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// create a new group the sender passed usually appears like [username]
	void createGroup(String sender, String[] arr, UserThread excludeUser) {
		ArrayList<String> groupMemberUsername = new ArrayList<String>();
		// remove the brackets
		sender = sender.replace("[", "");
		sender = sender.replace("]", "");
		//groupMemberUsername.add(sender);
		for (int i =0; i < arr.length; i++) {
			groupMemberUsername.add(arr[i]);
		}

		// create group

		Group grouppy = new Group("Group"+(group.size()+1), groupMemberUsername);
		group.add(grouppy);
		/*
    	   for (UserThread user : userThreads) {
               if (user == excludeUser) {
                   user.sendMessage("#{"+ grouppy.toString()+ "}, "+ getUserNames() );

               }
           } */
		
		

		// for all the users currently connected to the server 
		//for (UserThread user : userThreads) {
			// send the data to client with the list of users and servers in this form: {name; of; users}, {another; group}, [users, present]
			
			giveListOfUsers("#"+getUserNames() );
			//user.sendMessage("#"+getAllGroup(user) +", "+getUserNames());
		//}
	}

	// loop through the array list to see if the username exist before
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

	// check the username and password to see if it matches anything on the server 
	boolean checkUserNameAndPassword(String username, String password) {
		for (int i = 0; i < userData.size(); i++) {

			if (username.equals(userData.get(i).getUsername()) && password.equals(userData.get(i).getPsswd()) ) {
				return true;
			}
		}
		return false;
	}

	// get all the group that user is in
	String getAllGroup(UserThread user) {
		String allgrou = "";
		//goes through to see if the users in the list connected to the server is in a group
		for (int i =0; i < group.size(); i++) {
			for (int j = 0 ; j < group.get(i).getUserList().size(); j++) {
				if (user.getUsername().equals(group.get(i).getUserList().get(j))) {
					allgrou += allgrou +"{"+ group.get(i).toString() +"}, ";
				}
			}
			//if found in group, add the group to the string, which is presented in this form {group; member; name}
		}

		// basically sort the list of groups
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
		// remove bracked ([ and ]) because array ususally has this form ([some, element, smt])
		c = c.replace("[", "");
		c = c.replace("]", "");
		allgrou = c;

		return allgrou;
	}

	void setUsetDontAppear(String sender, String listUsers) {
		sender = sender.replace("[","");
		sender = sender.replace("]","");
		for (int i = 0; i< userData.size(); i++) {
			if (sender.equals(userData.get(i).getUsername())) {
				userData.get(i).addHiddenUsers(listUsers);
			}
		}
	}

	// give a list of users to  clients 

	void giveListOfUsers(String message) {
		for (UserThread user : userThreads) {
			/*
			if (user == excludeUser) {
				user.sendMessage(message);
			}
			 */
			String toSend = message +", "+ getAllGroup(user);
            String second = toSend;
			toSend = toSend.replace("[","");
			toSend = toSend.replace("]","");
			toSend = toSend.replace("#","");
			
			String[] split = toSend.split(",");

			for (int i = 0; i< userData.size(); i++) {
				if (user.getUsername().equals(userData.get(i).getUsername())) {
					for (int j = 0; j < split.length; j++) {
						split[j] = split[j].trim();
						if (userData.get(i).getHiddenUser().contains(split[j])) {
							split[j] = "";
						}
					}
				}
			}
			

			user.sendMessage("#"+Arrays.toString(split));
		}

	}

	// tell the clients that the password is wrong () send to the clients some thing like !

	void wrongPass(String message, UserThread excludeUser) {
		for (UserThread user : userThreads) {
			if (user == excludeUser) {
				user.sendMessage("!"+message);
			}
		}
	}

	// tell the clients that the password is correct send something like $
	void correctPass(String message, UserThread excludeUser) {
		for (UserThread user : userThreads) {
			if (user == excludeUser) {
				user.sendMessage("$"+message);
			}
		}
	}

	//change the user name of a client
	void changeUserName(String oldName, String newName, UserThread excludeUser) {   	
		userNames.remove(oldName);
		userNames.add(newName);

		for (int i = 0; i < userData.size(); i++) {
			System.out.println("CHANGE username "+oldName+" "+userData.get(i).getUsername());
			if (oldName.equals(userData.get(i).getUsername()) ) {
				userData.get(i).setUsername(newName);
			}
		}
	}

	// change the password of a client
	void changePassword(String newPass, String username) {
		for (int i = 0; i < userData.size(); i++) {
			System.out.println("CHANGE passwoed "+username+" "+userData.get(i).getUsername());
			if (username.equals(userData.get(i).getUsername()) ) {
				userData.get(i).setPsswd(newPass);
			}
		}
	}


	// check if the group already exist

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


	// send message

	void broadcast(String message, UserThread excludeUser) {

		// private conversation will have a form of @username message here
		// group conversation will have a form of @username1 @Username2 @username3 message here
		// this basically is formmat the string and interpret the string base on the information above
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

		// send message to group
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

			// write to file 

			writeToFile2(whosent, (userGroup), time+" "+whosent + " -> "  + d+": "+c);
		}

		// send to dm
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

	// load user conversation from a group
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
		File[] filer = new File[array.length];

		for (int i = 0; i < filer.length; i++) {
			filer[i] = new File(array[i]+"->"+Arrays.toString(array)+".txt");
			File forcechange = new File(array[i]+"->"+Arrays.toString(array)+"1.txt");
			try {

				if (forcechange.exists()) {
					FileInputStream fstream = new FileInputStream(forcechange);
					BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

					String strLine;

					//Read File Line By Line
					while ((strLine = br.readLine()) != null)   {
						user.sendMessage(strLine);
					}

					//Close the input stream
					fstream.close();		
				}
				else if (filer[i].exists()) {

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
		File forcechange = new File(sender+"->"+receiver+"1.txt");

		try {
			if (forcechange.exists()) {

				FileInputStream fstream = new FileInputStream(forcechange);
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

				String strLine;

				//Read File Line By Line
				while ((strLine = br.readLine()) != null)   {
					user.sendMessage(strLine);
				}

				//Close the input stream
				fstream.close();

			} else {
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
			}


		}catch (Exception e) {

		}

	}

	void writeToFileForceChange(String sender, String receiver) {
		sender = sender.replace("[","");
		sender = sender.replace("]","");
		String[] arr = receiver.split("_");
		//System.out.println(sender + " "+ receiver);

		boolean privateConverse = false;
		boolean groupconversation = false;

		int count = 0;
		for (int i = 0; i < arr[0].length(); i++) {
			if (arr[0].charAt(i) == '@') {
				count++;
			}
		}
		if (count == 1) privateConverse = true;
		else if (count > 1) groupconversation = true;

		if (privateConverse) {
			File ff = new File(sender+"->"+arr[0].replace("@","").trim()+"1.txt");
			ff.delete();

			try(FileWriter fw = new FileWriter(sender+"->"+arr[0].replace("@","").trim()+"1.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw))
			{
				String[] no = arr[1].split("\\\\n");
				for (int i = 0 ; i < no.length; i++)
					out.println(no[i]);
				//more code
			} catch (IOException e) {

			}
		}

		if (groupconversation) {
			String[] userGroup = new String[count];
			String[] splited = arr[0].split("\\s+");

			for (int ii = 0; ii < splited.length; ii++) {
				if (splited[ii].indexOf("@") != -1)  {
					userGroup[ii] =  splited[ii].replace("@", "");
				}
			}
			Arrays.sort(userGroup);
			File ff = new File(sender+"->"+Arrays.toString(userGroup)+"1.txt");
			ff.delete();
			try(FileWriter fw = new FileWriter(sender+"->"+Arrays.toString(userGroup)+"1.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw))
			{
				String[] no = arr[1].split("\\\\n");
				for (int i = 0 ; i < no.length; i++)
					out.println(no[i]);
				//more code
			} catch (IOException e) {

			}

		}

	}
	// write to file where all users are in a group
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

	// write to file to store dm between users
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

	// delete an account
	void deleteAccount(String username) {
		userNames.remove(username); 
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

	// add user name
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