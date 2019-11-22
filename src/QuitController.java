
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QuitController {

    @FXML
    private Button no;

    @FXML
    private Button yes;

    @FXML
    void handleYes(ActionEvent event) {
        TypeRacer.Pstage.close();
        System.exit(0);

    }

    @FXML
    void handleNo(ActionEvent event) throws IOException {
        if (hOMEpAGEController.sp != null) {
            hOMEpAGEController.sp.stop();
            TypeRacer.mp.pause();
            TypeRacer.mp.play();
        }
        if (Start_GameController.sp != null) {
            Start_GameController.sp.stop();
            TypeRacer.mp.pause();
            TypeRacer.mp.play();
        }
        Stage Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Scene scene = new Scene(root);
        Pstage.setScene(scene);
    }

}
