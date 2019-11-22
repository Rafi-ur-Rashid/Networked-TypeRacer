import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartPageController {
    @FXML
    private Button Start;
    
    @FXML
    private Button Quit;

    @FXML
    private Button option;
    @FXML
    void handleStart(ActionEvent event) throws IOException {
        Start.setStyle("-fx-background-color : red");
        Stage stage=TypeRacer.Pstage;//new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Start_Game.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void handleOption(ActionEvent event) throws IOException {
        Stage stage=TypeRacer.Pstage;//new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Option.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void handleQuit(ActionEvent event) throws IOException {
        Stage Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("Quit.fxml"));
        Scene scene = new Scene(root);
        Pstage.setScene(scene);
    }

}
