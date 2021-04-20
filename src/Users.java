import java.util.ArrayList;

public class Users {
      
	  ArrayList<String> group = new ArrayList<String>();
	  
	  private String username;
	  private String psswd;
	  public Users(String username, String psswd) {
    	  this.username = username;
    	  this.psswd = psswd;
      }
	  
	  public String getUsername() {
		  return username;
	  }
	  
	  public String getPsswd() {
		  return psswd;
	  }
	  
	  public void writeDataTofile() {
		  
	  }
      
}
