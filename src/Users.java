import java.util.ArrayList;

public class Users {
      	 
      Group grouppy;
      
	  private String username;
	  private String psswd;
	  ArrayList<String> hiddenUsers = new ArrayList<>();
	  public Users(String username, String psswd) {
    	  this.username = username;
    	  this.psswd = psswd;
    	  hiddenUsers.add("__"); 
      }
	  public Users() {
		  
	  }
	  
	  public void addHiddenUsers(String hide) {
		  hiddenUsers.add(hide);
	  }
	  
	  public ArrayList<String> getHiddenUser() {
		  return hiddenUsers;
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
	        
}
