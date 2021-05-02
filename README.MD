## Project: 
   + Duong Dinh submits the report 
   + Saif Alkhoori submits the source code on vocareum


### Class Description for the client:

```Login.java```

- Makes sure that the program is connected to the default server

- Checks that the user input are valid characters else it would display a popup error.

- Has mainly two buttons, login and sign up.

- Login button check if the user is details entered exists in the server.

- Sign up button opens a new GUI Signup.java and asks the user for some details to create a new user for him

- Once logged in / signed up successfully, user will be directed to the Messagelist.java UI

```Messagelist.java```

- Contains all existing users on a DefaultListModel.

- Any user clicked once from the Messagelist.java will open the chat window with that user.

- A user can also use the search button to search directly for a user and open the chat window with them

- A user can also delete another user from his own UI

- The account button will open a new window to the account details with some features

- The create group button will open a new window to create a group

```Chat.java```

- A total of 5 buttons on the GUI and a scrollable chat area.

- This window will include the implementations of importing and exporting the chat

- This class will include the logic that makes sure that messages are sent properly with a time stamp and all other
  requirements

- Export button will create a new csv file called data.csv that includes all the chat

- Import button will ask the user to choose a csv file to import the chat to the current opened chat

```createGroup.java```

- Once the create group button is clicked in the Messagelist GUI, the createGroup window is opened.

- The window will practically have the same look as the `Messagelist.java` as I found it the most efficient way to do
  so, and would only include options that the user needs to create a group.

- The users listed will be in bold so that it's more obvious that the user opened a new window.

- After selecting the group members, the create group will create the group with the name of the users as the name of
  the group neatly organized by brackets and semi-colons. The group will be added to the Messagelist from here.

```Account.java```

- Offers the user the capability of changing his username by changing the name in the textfield and clicking on the edit
  button. It also checks if the user already exists in the server to avoid unnecessary errors.

- The user can change his password by typing his old password, and the new desired password, if the old password is
  wrong, the user won't be able to change his password

- The user can also delete his account using the delete button which will literally delete everything related to the
  account and pushes the user back to the Login page.
  
```Signup.java```

- Write something here.
  
```ListenServer.java```

- This class is a thread which runs parallel with the program. This class listen to what servers send and notify the chat client.

```ReveiverFromUser.java```

- This class basically to stores data and static variables. The point of the class is to store variable where every class have
access to and update the variables if there are any changes. A few data stored there include the sockets, the user's username and password, and parsing the list of current users on the server.
  
### Testing done on the client:

Write stuff here

### Class Description for the server:

```ChatServer.java``` This class contains main method for the server. All the methods include create a group, check login credentials, delete an account, give the lists of users to the client, change username and password, broadcast messages, load and write file to server as well as clients. This class will start a thread class which is UserThread which listens to what the client says. ChatServer is also responsible for creating new Users class and Group class.

```UserThread.java``` This class is a class that runs parallel with the chat server. It is a runnable. All the methods include add the usernames to the list, listens to what the client response and decide appropriate actions. If the user requests some actions needed to be done, this class will direct that to the ChatServer class which contains most of the main fucntions of the server.

```Users.java``` This class contains the data for all the users. This class will be created inside of the ChatServer class if there is a new user sign up to the system, which is notified by UserThread class to the ChatServer.

```Group.java``` This class contains the data for all the groups that a user is in. This class will be created inside the ChatServer class if there is a new group messages which is notified by UserThread to the ChatServer.

### Testing done on the server 

Write stuff here

## Client: 

   + Written in java
   + Run the server first before running the client
   + To run the server, just compile all the files and run it by doing java ChatServer
   

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