
import java.util.Hashtable;

public class ReadThreadServer implements Runnable {

    private Thread thr;
    private NetworkUtil nc;
    static String s = "s";
    static int j = 0,i=0;
    public Hashtable<String, NetworkUtil> table;

    public ReadThreadServer(Hashtable<String, NetworkUtil> Table, NetworkUtil nc) {
        this.nc = nc;
        table = Table;
        this.thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                s = (String) nc.read();
                if(s.equals("wait")){
                 table.put("c" + (++i), nc);
                 if (i % 2 == 0) {
                        WriteThreadServer w1=new WriteThreadServer(table, "c" + i, "start");
                         WriteThreadServer w2=new WriteThreadServer(table, "c" + (i - 1), "start");
                         w1.thr.join();
                         w2.thr.join();
                    }
                }
                else if(s.equals("finish"))
                {
                    ++j;
                    if(j%2==0){
                        WriteThreadServer w3=new WriteThreadServer(table, "c"+j,"okay");
                        WriteThreadServer w4=new WriteThreadServer(table, "c"+(j-1),"okay");
                        w3.thr.join();
                         w4.thr.join();
                    }
                    
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        nc.closeConnection();
    }

}
