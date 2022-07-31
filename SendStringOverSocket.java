
                                        //send string over socket example
import java.io.*;
import java.net.*;
  
class Client {
  
    public static void main(String args[])
        throws Exception
    {
  
        // Create client socket
        Socket s = new Socket("localhost", 888);
  
        // to send data to the server
        DataOutputStream dos
            = new DataOutputStream(
                s.getOutputStream());
  
        // to read data coming from the server
        BufferedReader br
            = new BufferedReader(
                new InputStreamReader(
                    s.getInputStream()));
  
        // to read data from the keyboard
        BufferedReader kb
            = new BufferedReader(
                new InputStreamReader(System.in));
        String str, str1;
  
        // repeat as long as exit
        // is not typed at client
        while (!(str = kb.readLine()).equals("exit")) {
  
            // send to the server
            dos.writeBytes(str + "\n");
  
            // receive from the server
            str1 = br.readLine();
  
            System.out.println(str1);
        }
  
        // close connection.
        dos.close();
        br.close();
        kb.close();
        s.close();
    }
}
import java.io.*;
import java.net.*;
  
class Server {
  
    public static void main(String args[])
        throws Exception
    {
  
        // Create server Socket
        ServerSocket ss = new ServerSocket(888);
  
        // connect it to client socket
        Socket s = ss.accept();
        System.out.println("Connection established");
  
        // to send data to the client
        PrintStream ps
            = new PrintStream(s.getOutputStream());
  
        // to read data coming from the client
        BufferedReader br
            = new BufferedReader(
                new InputStreamReader(
                    s.getInputStream()));
  
        // to read data from the keyboard
        BufferedReader kb
            = new BufferedReader(
                new InputStreamReader(System.in));
  
        // server executes continuously
        while (true) {
  
            String str, str1;
  
            // repeat as long as the client
            // does not send a null string
  
            // read from client
            while ((str = br.readLine()) != null) {
                System.out.println(str);
                str1 = kb.readLine();
  
                // send to client
                ps.println(str1);
            }
  
            // close connection
            ps.close();
            br.close();
            kb.close();
            ss.close();
            s.close();
  
            // terminate application
            System.exit(0);
  
        } // end of while
    }
}


// To execute the Server and Client classes, run the Server.java and Client.java in two separate Command Prompt windows.

//Server Output
/*
connection established
Hi This is Client 1 talking
Hi Client 1. This is Server
*/

// Client Output
/*
Hi This is Client 1 talking
Hi Client 1. This is Server
*/
