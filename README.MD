## Project: 
   + Duong Dinh submits the report 
   +            submits the source code on vocareum


## Client: 

   + Written in java
   + Run the server first before running the client
   + To run the server, just complie all the files and run it by doing java ChatServer
   

### Class Description for the client:


### Class Description for the server:

```ChatServer.java``` This class contains main method for the server. All the methods include

```UserThread.java``` This class is a class that runs parallel with the chat server. It is a runnable. All the methods include

```Users.java``` This class contains the data for all the users.

```Group.java``` This class contains the data for all the groups that a user is in.

## Server:

   + Listening on port 8989
   
   + Written in java
   
   + The GUI and implementation of the server is at main
   
## Data Server accept (how the GUI should implement the server):

   + To login, send to server ```in username password``` (username cannot have a space or special character)
   
   + to sign up, send to server ```up username password``` (username cannot have a space or special character)
   
   + To request changing username, send to server ```!# [String new username]```
   
   + To change password, send to server ```$# [String new password]```
   
   + To request loading user data, send to server ```##```
   
   + To delete account, send to server ```!!```
   
   + To tell server to write data and save data, send to server ```** [String Receiver]```
   
   + Send to server ```*! [String list of users]``` to send the list of removed user 
   
   + The server will send to client ```!``` in case of wrong password and ```$``` in case of correct password
   
 Most of the operation should be done automatically in the implementation of GUI
   
## Limitations

   + password is not encrypted because of time limitations
   
   + there might be more bugs, but we don't have enough time to debug and test all the combinations
   
## Other:

   + server will create data for every chat and chat history, deleting user account will not delete their chat history
   
   + file will be created in the form of ```sender->receiver1.txt and sender->receiver.txt(this file is a backup file in case the first file is edited)```
   
   + the server send DM in a form of ```@username [message here]```
   
   + the server send to group in a form of ```@username1 @username3 @username4 [message here]```
   
   + the server will create a group with group name default to ```{username1; username2; username3}``` to avoid confusion from the client and better parsing from server
   
   + server also stores all the file relate to users and groups in case the server shutdown. 
   
   + any changes to the chat area will be updated to the server (only the user who edit)