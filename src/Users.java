import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class Users {
      
      private ArrayList<Group> group = new ArrayList<Group>();
	 
      Group grouppy;
      
	  private String username;
	  private String psswd;
	  public Users(String username, String psswd) {
    	  this.username = username;
    	  this.psswd = psswd;
    	   
      }
	  public Users() {
		  
	  }
	  
	  public Group getUserGroup() {
		  
		  return grouppy;
	  }
	  
	  public void setUserGroup(Group grouppy) {
		  this.grouppy = grouppy;
	  }
	  
	  public void setUsername(String username) {
		  this.username = username;
	  }
	  
	  public String getUsername() {
		  return username;
	  }
	  
	  public void setPsswd(String psswd) {
		  this.psswd = psswd;
	  }
	  
	  public String getPsswd() {
		  return psswd;
	  }
	  
	  public void writeDataTofile() {	  
// sombody try to finish writting data to file please
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
