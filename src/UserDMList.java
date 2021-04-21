import java.util.ArrayList;

public class UserDMList {
	 private String sender;
	 ArrayList<String> userList;
	 public UserDMList(String sender, String toUser ) {
	     this.sender = sender;
	     userList =  new ArrayList<String>();
	     userList.add(toUser);
   }

   public String getSenderName() {
	    return sender;
   }
   
   public ArrayList<String>  getUserList() {
	   return userList;
   }
   
   public void setSenderName(String sender) {
	   this.sender = sender;
   }
   
   public void addDmToList(String toUser) {
	   userList.add(toUser);
   }
   
   public void removeDmToList(String toUser) {
	   for(int i = 0; i < userList.size(); i ++) {
		   if (userList.get(i).equals(toUser)) {
			   userList.remove(i);
			   break;
		   }
	   }
   }
}
