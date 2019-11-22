import java.util.*;

public class WriteThreadServer implements Runnable {

    public Thread thr;
    String name,mess;
    public Hashtable<String, NetworkUtil> table;
    public WriteThreadServer(Hashtable<String, NetworkUtil> table, String name,String message) {
        this.table = table;
        this.name = name;
        mess=message;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            //while(true) {

            NetworkUtil nc = table.get(name);
            if (nc != null) {
                nc.write(mess);
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
