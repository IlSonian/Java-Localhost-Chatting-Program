import java.net.Socket;

public class ReceiverFromUser {
	
	
	
	 static String[] array; 
	 
	 static String[] buarray; 
	 
	 static Socket socket;
	 static boolean in = false;
	 static String myUsername = "";
	 static String mypassword = "";
	 
	 static public void assingSocket(Socket socket) {
		 ReceiverFromUser.socket = socket;
	 }
	 
	 
	 static public Socket getSocket() {
		 return socket;
	 }
	 static int i = 0;
	 static String[] getAllUsers() {
		 if (i==0) {
		 buarray = array;
         i = 1;		 
		 }
		 return array;
	 }
	 
	 static void importUserfromServer(String totalUser) {
	        // adding dummy data to list		    
	        totalUser = totalUser.replace("[", "");
	        totalUser = totalUser.replace("]", "");
	        System.out.println(totalUser);
	        array = totalUser.split(",");
	        for (int i = 0; i < array.length; i++) {
	        	 array[i] =  array[i].trim();
	        }
	        
	    }
}
