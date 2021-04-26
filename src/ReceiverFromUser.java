
public class ReceiverFromUser {
	
	
	
	 static String[] array; 
	 
	 
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
	            array[i].trim();
	        }
	        
	    }
}
