import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListenServer extends Thread{
	  private BufferedReader reader;
	    private Socket socket;
	    private Login client;
	 
	    public ListenServer(Socket socket, Login client) {
	        this.socket = socket;
	        this.client = client;
	 
	        try {
	            InputStream input = socket.getInputStream();
	            reader = new BufferedReader(new InputStreamReader(input));
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	    public static boolean isNumeric(String str) { 
	    	  try {  
	    	    Integer.parseInt(str);  
	    	    return true;
	    	  } catch(NumberFormatException e){  
	    	    return false;  
	    	  }  
	    	}
	    
	    public void run() {
	        while (true) {
	            try {
	                String response = reader.readLine();
	                System.out.println(response);
	                if (isNumeric(response.substring(0,4))) {
		                Chat.setSendMessage(response);

	                }
	                String td = response.substring(0,1);
	                if (td.equals("!")) {
	                	System.out.println("Wrong password");
	                	Login.logintrue(false);
	                }
	                else if (td.equals("$")){
	                	Login.logintrue(true);
	                	System.out.println("Correct password");
	                	ReceiverFromUser.importUserfromServer(response.substring(response.indexOf("#")+1));
	                    System.out.println("\n" + response);     
	               }
	                else if (td.equals("#")) {
	                	ReceiverFromUser.importUserfromServer(response.substring(response.indexOf("#")+1));
	                    System.out.println("\n" + response); 
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	                break;
	            }
	        }
	    }
}
