
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReadThreadClient extends AnchorPane implements Runnable {
    private Thread thr;
    private NetworkUtil nc;
    static String s = "s";
    Text tx,test;
    ImageView IV;

    public ReadThreadClient(NetworkUtil nc, Text t, ImageView iv,Text T) {
        this.nc = nc;
        tx = t;
        IV = iv;
        test=T;
        this.thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                s = (String) nc.read();
                if (s.equals("start")) {
                    tx.setText("Click Here to Start");
                    tx.setLayoutX(195);
                    tx.setLayoutY(190);
                    tx.setOnMouseEntered(e->tx.setFill(Color.RED));
                    tx.setOnMouseExited(e->tx.setFill(Color.BLACK));
                    IV.setImage(new Image("thumbs-up-192.png"));
                    IV.setFitHeight(417);
                    IV.setFitWidth(384);
                    IV.setLayoutX(195);
                    IV.setLayoutY(268);
                }
                if(s.equals("okay")){
                    test.setText("Click to Show Score");
                    test.setOnMouseEntered(e->test.setFill(Color.ROYALBLUE));
                    test.setOnMouseExited(e->test.setFill(Color.YELLOW));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        nc.closeConnection();
    }

}
