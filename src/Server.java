import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Random;

public class Server{
    private ServerSocket ServSock;
    public int I = 0;
    static final String SERVER_IP="127.0.0.1";
    static final int SERVER_PORT=33333;
    public static Hashtable<String, NetworkUtil> table;
    NetworkUtil nc;
    static File f=new File("C:\\Users\\ASUS\\Documents\\PROGRAMS\\Netbeans\\TypeRacer\\src\\Scores\\Trio.txt");
    static File HighScore=new File("C:\\Users\\ASUS\\Documents\\PROGRAMS\\Netbeans\\TypeRacer\\src\\Scores\\High Scores.txt");
    FileInputStream fin;
    Server() throws FileNotFoundException {
        this.fin = new FileInputStream(f);
        table = new Hashtable<>();
        try {
            ServSock = new ServerSocket(SERVER_PORT);
            while (true) {
                Socket clientSock = ServSock.accept();
                NetworkUtil nc = new NetworkUtil(clientSock);
                new ReadThreadServer(table,nc);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }

    }

    public static void main(String args[]) throws FileNotFoundException {
        Server objServer = new Server();
    }
    final int countLine(FileInputStream fin) throws IOException
    {
        byte[] b = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = fin.read(b)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (b[i] == '\n') {
                        ++count;
                    }
                }
            }
            return count;
    }
}
