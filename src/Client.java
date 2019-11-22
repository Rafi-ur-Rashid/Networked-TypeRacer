import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
public class Client extends TypeRacer{
    String User;
    public Client(String user,TextField UserName,Text sc,ImageView IV,Text T) throws IOException {
        User = user;
        String serverAddress =Server.SERVER_IP;
        int serverPort = Server.SERVER_PORT;
        NetworkUtil nc = new NetworkUtil(serverAddress, serverPort,User);
        new WriteThreadClient(nc,"wait");
        new ReadThreadClient(nc,sc,IV,T);
    }
}
