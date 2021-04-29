import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Group.java
 * <p>
 * group class
 * <p>
 * a list of your sources of help (if any)
 *
 * @author Project 5 group
 * @version 4/29/2021
 */
public class Group {
    private String groupName;
    ArrayList<String> userList;

    public Group(String groupName, ArrayList<String> userList) {
        this.groupName = groupName;
        this.userList = userList;
    }

    public String getGroupName() {
        return groupName;
    }

    public ArrayList<String> getUserList() {
        return userList;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupMember(ArrayList<String> userList) {
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(userList);

        ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);
        this.userList = listWithoutDuplicates;
    }

    public String toString() {
        String c = "";
        for (int i = 0; i < userList.size(); i++) {
            c += userList.get(i) + "; ";
        }
        c = c.substring(0, c.length() - 2);
        return c;
    }

}
