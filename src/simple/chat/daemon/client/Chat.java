package simple.chat.daemon.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 *
 * @author Luca Mezzolla
 */
public class Chat extends Thread {
    Socket socket;
    public Chat(Socket s) throws IOException {
        this.socket = s;
    }
    
    @Override
    public void run() {
        try {
            String message;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(!this.isInterrupted()) {
                message = in.readLine();
                if(message != null) {
                    System.out.println(message);
                }
                Thread.sleep(100);
            }
        } catch(Exception e) {
            //
        }
    }
    
}