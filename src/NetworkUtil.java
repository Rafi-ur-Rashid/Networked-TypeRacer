import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class NetworkUtil {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
     String name;

    public NetworkUtil(String s,int port)
    {
        try {
            socket=new Socket(s, port);
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("In network util:"+e);
        }
    }
    public NetworkUtil(String s,int port,String name)
    {
        try {
            this.name=name;
            socket=new Socket(s, port);
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("In network util:"+e);
        }
    }
    public NetworkUtil(Socket socket)
    {
        try {
            this.socket=socket;
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("In network util:"+e);
        }
        
        
    }
    public Object read()
    {
        Object o=null;
        try {
            o=ois.readObject();
        } catch (Exception ex) {
            System.out.println("IN read: "+ex);
        }
        return o;
    }
    public void write(Object o) {
		try {
			oos.writeObject(o);                        
		} catch (IOException e) {
			System.out.println("Writing  Error in network : " + e);
		}
	}
    public void closeConnection()
    {
        try {
            oos.close();
            ois.close();
        } catch (Exception e) {
            System.out.println("Writing  Error in network : " + e);
        }
    }
    public Socket getSocket()
    {
        return socket;
    }
    
    
}
