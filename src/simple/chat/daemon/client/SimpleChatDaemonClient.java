package simple.chat.daemon.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Luca Mezzolla
 */
public class SimpleChatDaemonClient {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(">>> CUTA CHAT!!! <<<\n");
        new SimpleChatDaemonClient();
    }

    public SimpleChatDaemonClient() throws IOException {
        try {
            Socket socket = new Socket("192.168.1.253", 9090);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String answer = in.readLine();
            System.out.println(answer);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Come ti chiami? ");
            String myName = scanner.nextLine();
            if(!myName.isEmpty()) {
                while(true) {
                    Chat chat = new Chat(socket);
                    chat.start();
                    String message = scanner.nextLine();
                    if(message.equals("bye")) {
                        chat.interrupt();
                        break;
                    } else {
                        out.println(myName+": "+message);
                        Thread.sleep(100);
                    }
                }
            }
            System.out.println("Arrivederci!");
        } catch(Exception e) {
            //System.out.println("Bye bye!");
            System.out.println(e.getMessage());
        } finally {
            if(in != null) in.close();
            if(out != null) out.close();
            System.exit(0);
        }
    }
    
}
