import java.net.Socket;
import java.util.ArrayList;

public class ReceiverFromUser {



	static String[] array; 


	static Socket socket;
	static boolean in = false;
	static String myUsername = "";
	static String mypassword = "";
	static ArrayList<String> removedList = new ArrayList<>();

	static public void assingSocket(Socket socket) {
		ReceiverFromUser.socket = socket;
	}


	static public Socket getSocket() {
		return socket;
	}
	static String[] getAllUsers() {

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
