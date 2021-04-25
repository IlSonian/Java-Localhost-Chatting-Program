import java.util.ArrayList;

public class Conversation {
    private String name;
    private ArrayList<User> participants;
    private ArrayList<Message> chatHistory;

    public Conversation(String name, ArrayList<User> participants,
                        ArrayList<Message> chatHistory) {
        this.name = name;
        this.participants = participants;
        this.chatHistory = chatHistory;
    } //constructor

    public String getName() {
        return this.name;
    } //method getName

    public ArrayList<User> getParticipants() {
        return this.participants;
    } //method getParticipants

    public ArrayList<Message> getChatHistory() {
        return this.chatHistory;
    } //method getChatHistory

    public void setName(String name) {
        this.name = name;
    } //method setName

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    } //method setParticipants

    public void setChatHistory(ArrayList<Message> chatHistory) {
        this.chatHistory = chatHistory;
    } //method setChatHistory

    public void addParticipant(User user) {
        this.participants.add(user);
    } //method addParticipant

    public void removeParticipant(User user) {
        this.participants.remove(user);
    } //method removeParticipant

} //class
