import java.util.ArrayList;

/**
 * This class includes test cases for every methods in Users.java. This class runs the tests
 * for each method and announces the result of each tests on terminal.
 *
 * CS 180 SP21 Project 5
 *
 * @author Project 5 Group
 * @version April 30th, 2021
 */

public class TestUsers {
    public static void main(String[] args) {
        System.out.println(TestUsers.runTest());
    } //main

    public static String runTest() {
        return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", testConstructor1(),
                testConstructor2(), testAddHiddenUsers(), testSetHiddenUsers(), testGetHiddenUser(),
                testGetUserGroup(), testSetUserGroup(), testSetUsername(), testGetUsername(),
                testSetPsswd(), testGetPsswd());
    } //method runTest

    public static String testConstructor1() {
        Users user = new Users("John Doe", "1234");

        if (user.getUsername().equals("John Doe")) {
            if (user.getPsswd().equals("1234")) {
                if (user.getHiddenUser().get(0).equals("__")) {
                    return "Constructor1 working properly!";
                } //if hiddenUser = __
            } //if password = 1234
        } //if username = John Doe

        return "Constructor1 failed..";
    } //method testConstructor1

    public static String testConstructor2() {
        Users user = new Users();

        if (user.getUsername() == null) {
            if (user.getPsswd() == null) {
                if (user.getHiddenUser().isEmpty()) {
                    return "Constructor2 working properly!";
                } //if hiddenUser = null
            } //if password = null
        } //if username = null

        return "Constructor2 failed..";
    } //method testConstructor2

    public static String testAddHiddenUsers() {
        Users user = new Users();
        user.addHiddenUsers("John Doe");

        if (user.getHiddenUser().get(0).equals("John Doe")) {
            return "Method addHiddenUsers(String hide) working properly!";
        } //if hidden user added

        return "Method addHiddenUsers(String hide) failed..";
    } //method testAddHiddenUsers

    public static String testSetHiddenUsers() {
        Users user = new Users();
        user.addHiddenUsers("John Doe");

        ArrayList<String> hiddenUsers = new ArrayList<>();
        hiddenUsers.add("Jane Doe");

        user.setHiddenUsers(hiddenUsers);

        if (user.getHiddenUser().equals(hiddenUsers)) {
            return "Method setHiddenUsers(ArrayList<String> hiddenUsers) working properly!";
        } //if hidden user changed

        return "Method setHiddenUsers(ArrayList<String> hiddenUsers) failed..";
    } //method testSetHiddenUsers

    public static String testGetHiddenUser() {
        Users user = new Users();
        user.addHiddenUsers("John Doe");

        ArrayList<String> hiddenUsers = new ArrayList<>();
        hiddenUsers.add("John Doe");

        if (user.getHiddenUser().equals(hiddenUsers)) {
            return "Method getHiddenUser() working properly!";
        } //if hidden user matches

        return "Method getHiddenUser() failed..";
    } //method testGetHiddenUser

    public static String testGetUserGroup() {
        Users user = new Users();

        ArrayList<String> userList = new ArrayList<>();

        userList.add("user1");
        userList.add("user2");

        Group group = new Group("test", userList);

        user.setUserGroup(group);

        if (user.getUserGroup().equals(group)) {
            return "Method getUserGroup() working properly!";
        } //if groups match

        return "Method getUserGroup() failed..";
    } //method testGetUserGroup

    public static String testSetUserGroup() {
        Users user = new Users();

        ArrayList<String> userList1 = new ArrayList<>();
        userList1.add("user1");
        userList1.add("user2");

        Group group1 = new Group("test0", userList1);

        user.setUserGroup(group1);

        ArrayList<String> userList2 = new ArrayList<>();
        userList2.add("user3");
        userList2.add("user4");

        Group group2 = new Group("test1", userList2);

        user.setUserGroup(group2);

        if (user.getUserGroup().equals(group2)) {
            return "Method setUserGroup(Group grouppy) working properly!";
        } //if group changed

        return "Method setUserGroup(Group grouppy) failed..";
    } //method testSetUserGroup

    public static String testSetUsername() {
        Users user = new Users("John Doe", "1234");
        user.setUsername("Jane Doe");

        if (user.getUsername().equals("Jane Doe")) {
            return "Method setUsername(String username) working properly!";
        } //if username changed

        return "Method setUsername(String username) failed..";
    } //method testSetUsername

    public static String testGetUsername() {
        Users user = new Users("John Doe", "1234");

        if (user.getUsername().equals("John Doe")) {
            return "Method getUsername() working properly!";
        } //if username matches

        return "Method getUsername() failed..";
    } //method testGetUsername

    public static String testSetPsswd() {
        Users user = new Users("John Doe", "1234");

        user.setPsswd("5678");

        if (user.getPsswd().equals("5678")) {
            return "Method setPsswd(String psswd) working properly!";
        } //if password changed

        return "Method setPsswd(String psswd) failed..";
    } //method testSetPsswd

    public static String testGetPsswd() {
        Users user = new Users("John Doe", "1234");

        if (user.getPsswd().equals("1234")) {
            return "Method getPsswd() working properly!";
        } //if password matches

        return "Method getPsswd() failed..";
    } //method testGetPsswd
} //class
