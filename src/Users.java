
public class Users {
      	 
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
	        
}
