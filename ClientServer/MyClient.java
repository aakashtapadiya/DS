
import java.net.*;
import java.io.*;

public class MyClient {
    public static void main(String[] args) throws Exception{
        
        //The socket object takes ip and port number of the server which client wants to connect
        Socket s = new Socket("localhost",8888);
        System.out.println("Connected to Server, Please type your message and hit Enter to send");
        
        //InputStream objects to recieve from Server
        InputStream istream = s.getInputStream();
        //OutputStream object to write to Server
        OutputStream ostream = s.getOutputStream();
        
        //Reading input from KeyBoard
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Reading receieved message from Server        
        BufferedReader recieve = new BufferedReader(new InputStreamReader(istream));

        //PrintWriter object to send the data to the outputstream 
        PrintWriter pw = new PrintWriter(ostream, true);

        

        

        //Client Message and Server Message objects
        String clientmessage = "";
        String servermessage = "";


        while(true)
        {
            //Input Message to be sent to Server
            System.out.print("Client: ");
            clientmessage = br.readLine();

            //print writer object sending the message to the socket through outputstream
            pw.println(clientmessage);
            
            //if the message is bye end the communication here
            if(clientmessage.equals("bye"))
            {
                break;
            }

            //Read the inputstream of the server from the socket
            servermessage = recieve.readLine();
            System.out.println("Server: "+servermessage);

            //if the message is bye end the communication here
            if(servermessage.equals("bye"))
            {
                break;
            }
        }

        //closing all the streams and sockets 
        s.close();
        istream.close();
        ostream.close();

        System.out.println("Connection Terminated");
    }
}
