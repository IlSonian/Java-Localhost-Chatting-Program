import java.util.ArrayList;

public class Group {
   private String groupName;
   ArrayList<String> userList;
   public Group(String groupName, ArrayList<String> userList ) {
	     this.groupName = groupName;
	     this.userList = userList;
   }

   public String getGroupName() {
	    return groupName;
   }
   public ArrayList<String>  getUserList() {
	   return userList;
   }
   public void setGroupName(String groupName) {
	   this.groupName = groupName;
   }
   public void setGroupMember(ArrayList<String> userList) {
	   this.userList = userList;
   }
 
}
