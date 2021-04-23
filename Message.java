import java.util.Date;
import java.util.Objects;

public class Message {
    Date timeStamp;
    User sender;
    String content;

    public Message(Date timeStamp, User sender, String content) {
        this.timeStamp = timeStamp;
        this.sender = sender;

        Objects.requireNonNull(content, "Empty message cannot be created!");
        this.content = content;
    } //constructor

    public String toString() {
        return String.format("%s: %s     - %d", sender.getUserName(),
                content, timeStamp.getTime());
    } //method toString

    public Date getTimeStamp() {
        return this.timeStamp;
    } //method getTimeStamp

    public User getSender() {
        return this.sender;
    } //method getSender

    public String getContent() {
        return this.content;
    } //method getContent

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    } //method setTimeStamp

    public void setContent(String content) {
        this.content = content;
    } //method setContent

} //class
