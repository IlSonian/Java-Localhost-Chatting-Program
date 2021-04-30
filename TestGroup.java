import java.util.ArrayList;

/**
 * This class includes test cases for every methods in Group.java. This class runs the tests
 * for each method and announces the result of each tests on terminal.
 *
 * CS 180 SP21 Project 5
 *
 * @author Project 5 Group
 * @version April 30th, 2021
 */

public class TestGroup {
    public static void main(String[] args) {
        System.out.println(runTest());
    } //main

    public static String runTest() {
        return String.format("%s\n%s\n%s\n%s\n%s\n%s\n", testConstructor(), testGetGroupName(),
                testGetUserList(), testSetGroupName(), testSetGroupMember(), testToString());
    } //method runTest

    public static String testConstructor() {
        ArrayList<String> userList = new ArrayList<>();
        userList.add("test1");
        userList.add("test2");

        Group group = new Group("Group", userList);

        if (group.getGroupName().equals("Group")) {
            if (group.getUserList().equals(userList)) {
                return "Constructor working properly!";
            } //if userList matches
        } //if group name matches

        return "Constructor failed..";
    } //method testConstructor

    public static String testGetGroupName() {
        Group group = new Group("Group A", new ArrayList<>());

        if (group.getGroupName().equals("Group A")) {
            return "Method getGroupName() working properly!";
        } //if group name matches

        return "Method getGroupName() failed..";
    } //method testGetGroupName

    public static String testGetUserList() {
        ArrayList<String> userList = new ArrayList<>();
        userList.add("user0");
        userList.add("user1");
        userList.add("user2");

        Group group = new Group("Group", userList);

        if (group.getUserList().equals(userList)) {
            return "Method getUserList() working properly!";
        } //if user list matches

        return "Method getUserList() failed..";
    } //method testGetUserList

    public static String testSetGroupName() {
        Group group = new Group("Group A", new ArrayList<>());
        group.setGroupName("Group B");

        if (group.getGroupName().equals("Group B")) {
            return "Method setGroupName(String groupName) working properly!";
        } //if group name changed

        return "Method setGroupName(String groupName) failed..";
    } //method testSetGroupName

    public static String testSetGroupMember() {
        ArrayList<String> userList1 = new ArrayList<>();
        userList1.add("1");
        userList1.add("2");
        userList1.add("3");

        Group group = new Group("Group", userList1);

        ArrayList<String> userList2 = new ArrayList<>();
        userList2.add("4");
        userList2.add("5");
        userList2.add("6");

        group.setGroupMember(userList2);

        if (group.getUserList().equals(userList2)) {
            return "Method setGroupMember(ArrayList<String> userList) working properly!";
        } //if user list changed

        return "Method setGroupMember(ArrayList<String> userList) failed..";
    } //method testSetGroupMember

    public static String testToString() {
        ArrayList<String> userList = new ArrayList<>();
        userList.add("John Doe");
        userList.add("Jane Doe");

        Group group = new Group("Group", userList);

        String toString = group.toString();
        String answer = "John Doe; Jane Doe";

        if (toString.equals(answer)) {
            return "Method toString() working properly!";
        } //if toString matches with the answer

        return "Method toString() failed..";
    } //method testToString
} //class
