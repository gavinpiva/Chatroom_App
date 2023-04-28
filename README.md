# Chatroom
This is a Java program that creates a GUI-based chat client.

This program uses 6 files to compile and create a working chat room application.

## Classes 

-ChatScreen

-ReaderThread

-BroadcastThread

-Connection

-Handler

-Server

# Client Side
## ChatScreen

Creates a pop up window for users to see usernames of other memeber in the chat room as well as all responses that are typed.

ChatScreen extends JFrame and implements the ActionListener and KeyListener interfaces. The class has several private fields, including JButtons, a JTextField, and a JTextArea. It also has a static string field for storing the user's name and a static BufferedWriter for sending messages to a chat server.

The constructor initializes the GUI components and registers listeners for the send button, exit button, and keyboard. It then adds the components to a panel and adds the panel to the south of the JFrame. It also creates a JTextArea for displaying chat messages and adds it to the center of the JFrame.

The displayMessage() method is called by the ReaderThread class to display chat messages received from the server. The displayText() method sends the message entered by the user to the server.

The main() method creates a socket connection to the chat server and starts a new ReaderThread to read messages from the server. It also creates an instance of the ChatScreen class and sends a JOIN request to the server with the user's name.

## ReaderThread
This code defines a class called ReaderThread that implements the Runnable interface. It takes a socket and a ChatScreen as parameters, and reads input from the socket using a BufferedReader. Whenever it receives input, it uses the ChatScreen's displayMessage() method to write the input to the chat screen. This class is used to handle reading messages from the server in a chat application.

# Server Side

## Broadcast Thread

BroadcastThread implements the Runnable interface. It has two instance variables: a Vector<String> called messageQueue and an ArrayList<BufferedWriter> called writeList. The BroadcastThread constructor takes in these two variables and sets the instance variables to their values.

The run() method contains a while loop that runs indefinitely. Inside the while loop, the thread sleeps for 1/10th of a second using Thread.sleep(100) and then checks if there are any messages in the messageQueue vector. If there are messages in the vector, the thread removes the first message and broadcasts it to all clients by iterating over the writeList ArrayList of BufferedWriters and writing the message to each BufferedWriter using write() method and then flushing it using flush() method.

The purpose of this code is to implement a thread that constantly listens for new messages in the messageQueue vector and broadcasts them to all clients that are stored in the writeList ArrayList. This code is used to ensure that all messages are broadcasted to all connected clients.

## Connection

The purpose of this class is to handle each incoming client request in a separate thread.

The class has four instance variables:

client - a Socket object representing the client's socket connection

handler - a static Handler object used to process client requests

vect - a Vector object to store messages to be broadcasted

writeList - an ArrayList object containing BufferedWriter objects used to send messages to all connected clients

The run() method is the main method of this class, which runs in a separate thread for each client connection. When a client connection is accepted, this method calls the process() method of the Handler class, passing in the client socket, message vector, and writer list.

If any exception occurs while processing the client request, it is caught and printed to the standard error output.

## Handler

Handler contains the logic for echoing results back to the client.

The class has a constant field BUFFER_SIZE with a value of 256.

The class has a method named process which takes a Socket object, a Vector<String> object and an ArrayList<BufferedWriter> object as arguments. This method is invoked by a separate thread.

The process method creates a BufferedReader object to read input from the client socket's input stream. It then reads lines from the client until there are no more lines to read or an IOException is thrown.

If the line read from the client contains the string "LEAVE", the client is removed from the writeList list.

The line read from the client is then added to the vect vector.

Finally, the method closes the input stream of the client socket.

## Server 

Finally a multi-threaded server is created that listens on a socket for incoming client connections. When a client connects, a new thread is created to handle the connection. The server also creates a new thread for broadcasting messages to all connected clients.

The Server class contains the main method, which initializes a thread pool for concurrency and creates a BroadcastThread object to handle broadcasting messages. It then sets up a socket to listen for incoming client connections, and for each connection, creates a new Connection object and executes it in a separate thread using the thread pool.

The BroadcastThread class continuously checks a Vector for new messages and broadcasting them to all connected clients using the BufferedWriter objects stored in an ArrayList.

The Connection class is responsible for handling each incoming client connection. It creates a Handler object to process incoming messages from the client.

The Handler class contains the logic for echoing messages back to the client. It reads incoming messages from the client and adds them to a Vector. If the message contains "LEAVE", it removes the corresponding BufferedWriter object from the ArrayList.

Each of these pieces works in conjunction to allow the user to chat!


## Usage
To use the program, you must run it with the following command-line arguments:

java ChatScreen 'server' 'username'

'server' is the hostname or IP address of the chat server.

'username' is the username you want to use in the chat.

Once you start the program, you will see a GUI window with a text input field, a send button, and an exit button. Type your message into the input field and press enter or click the send button to send the message to the chat server.
