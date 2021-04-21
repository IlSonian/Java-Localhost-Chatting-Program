import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class Users {
      
	  ArrayList<String> group = new ArrayList<String>();
	  
	  private String username;
	  private String psswd;
	  public Users(String username, String psswd) {
    	  this.username = username;
    	  this.psswd = psswd;
    	   
      }
	  public Users() {
		  
	  }
	  
	  public String getUsername() {
		  return username;
	  }
	  
	  public String getPsswd() {
		  return psswd;
	  }
	  
	  public void writeDataTofile() {	  

		      try (PrintWriter writer = new PrintWriter(new File("data.csv"))) {

		        StringBuilder sb = new StringBuilder();
		        String headerForStuff = "username, password";
		        sb.append(headerForStuff);
		        sb.append('\n');
		        sb.append("bob2, 2215249148");
		        sb.append('\n');

		        writer.write(sb.toString());

		        System.out.println("done!");

		      } catch (FileNotFoundException e) {
		        System.out.println(e.getMessage());
		      }

	
	
	  }
      
}
