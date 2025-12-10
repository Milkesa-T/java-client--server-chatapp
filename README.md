## **Project Title**
**chat app**

## **1. Overview**
This project is a **multi-client chat application** implemented using
**Java TCP socket programming**.\
It demonstrates:
-   Server creation using `ServerSocket`
-   Client connection using `Socket`
-   Data exchange using `InputStream` and `OutputStream`
-   Multithreading (one thread per client)
-   Broadcasting messages to all connected clients
-   Username system
-   Timestamps
-   `/users` and `/exit` commands

## **2. Features**

### **Connection-Oriented TCP Communication**

Uses `ServerSocket` and `Socket` for reliable, stream-based
communication.

### **Multiple Clients Support**

Each client runs in a separate thread (`ClientHandler`).

### **Usernames**

Clients choose a name when joining:

    Enter your name: Mty

### **Broadcast Messaging**

All connected users receive messages instantly.

### **Timestamps**

Every message includes the time it was sent:

    [10:42 PM] [Mty]: hello everyone

### **/users Command**

Displays all users currently online:

    Online users: Mty, Sara, Alex,

### **/exit Command**

Allows a client to leave gracefully:

    /exit
    You have left the chat.

### **Join & Leave Notifications**

    Mty joined the chat.
    Mty left the chat.

## **3. How to Run**

### **Step 1: Compile all Java files**
``` bash
javac ChatServer.java ClientHandler.java ChatClient.java
```
### **Step 2: Run the server**

``` bash
java ChatServer
```
### **Step 3: Run clients in separate terminals**

``` bash
java ChatClient
```
## **4. Example Usage**

### **Client entering name**

    Enter your name: Mty
    Connected to chat server!

### **Chat messages**

    [10:41 PM] [Sara]: hello
    [10:42 PM] [Mty]: hi sara

### **Show online users**

    /users
    Online users: Mty, benol, dave, berson

### **Exit**

    /exit
    You have left the chat.

## **5. Requirements**

-   Java JDK 8 or higher\
-   Windows / Linux / macOS\
-   Terminal / CMD / PowerShell

## **9. Author**

*Your Name*\
*Your ID*\
*Course/Section*
