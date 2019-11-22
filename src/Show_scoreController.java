
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Show_scoreController extends AnchorPane{

   @FXML
    private Text wpm;
   @FXML
    private Text accu;
   public Show_scoreController()
   {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
"Showing_score.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
   }
   public void set(String s,String a)
   {
       wpm.setText(s);
       accu.setText(a);
   }
   @FXML
    void handleBack(ActionEvent event) throws IOException {
        TypeRacer.mp.play();
        Stage Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("Start_Game.fxml"));

        Scene scene = new Scene(root);

        Pstage.setScene(scene);
    }
    
}
