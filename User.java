import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private ArrayList<Conversation> conversations;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.conversations = null;
    } //constructor

    public String getUserName() {
        return this.userName;
    } //method getUserName

    public String getPassword() {
        return this.password;
    } //method getPassword

    public void setUserName(String userName) {
        this.userName = userName;
    } //method setUserName

    public void setPassword(String password) {
        this.password = password;
    } //method setPassword

    public void joinChat(Conversation conversation) {
        for (int i = 0; i < conversations.size(); i++) {
            if (conversations.get(i).equals(conversation)) {
                return;
            } //if already exists
        } //for loop

        conversations.add(conversation);
    } //method joinChat

    public void leaveChat(Conversation conversation) {
        conversations.remove(conversation);
    } //method leaveChat

    public void writeDataToFile(Conversation conversation) {
        for (int i = 0; i < conversations.size(); i++) {
            if (conversations.get(i).equals(conversation)) {
                try (PrintWriter writer = new PrintWriter(new File("data.csv"))) {
                    writer.append(String.format("Username: %s\n", this.getUserName()));

                    writer.append(String.format("Conversation: %s\n", conversation.getName()));

                    writer.append("Participants: ");

                    for (int j = 0; j < conversation.getParticipants().size() - 1; j++) {
                        writer.append(String.format("%s, ", conversation.getParticipants().get(j).getUserName()));
                    } //for loop

                    writer.append(String.format("%s\n", conversation.getParticipants().get(
                            conversation.getParticipants().size() - 1)));

                    writer.append("Chat History:\n");

                    for (int j = 0; j < conversation.getChatHistory().size(); j++) {
                        writer.append(String.format("%s\n", conversation.getChatHistory().get(j).toString()));
                    } //for loop

                } catch (Exception e) {
                    e.printStackTrace();
                } //try catch
            } //if
        } //for loop
    } //method writeDataToFile

    public void readDataFromFile(String fileName) {
        try {
            File f = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(f));
            ArrayList<String> data = new ArrayList<>();

            while (true) {
                String line = reader.readLine();

                if (line == null) {
                    break;
                } //if null

                data.add(line);
            } //while loop

            if (!data.get(0).equals(String.format("Username: %s", userName))) {
                JOptionPane.showMessageDialog(null,
                        "You cannot import a file from another user!",
                        "Error", JOptionPane.ERROR_MESSAGE);

                return;
            } //if

            String conversationName = data.get(1);
            conversationName = conversationName.substring(conversationName.indexOf(':' + 2));

            //TODO: Need Server to locate the group chat from its list and add it to user's conversation list

            //TODO: Use Data list to update the chat history of the group chat

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "File that you were trying to import from was not found!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Import Failure!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } //try catch
    } //method readDataFromFile

} //class
