
public class WriteThreadClient implements Runnable {

    private Thread thr;
    private NetworkUtil nc;
    String name;
    public WriteThreadClient(NetworkUtil nc, String name) {
        this.nc = nc;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {

            while (true) {
                if (name != null) {
                    nc.write(name);
                 name=null;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        nc.closeConnection();
    }
}
