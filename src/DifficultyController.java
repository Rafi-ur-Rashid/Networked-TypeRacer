
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DifficultyController {

    static int dif = 1;

    @FXML
    void handleBeg(ActionEvent event) throws IOException {
        dif = 1;
        Stage Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("Option.fxml"));
        Scene scene = new Scene(root);
        Pstage.setScene(scene);
        System.out.println(dif);

    }

    @FXML
    void handleProf(ActionEvent event) throws IOException {
        dif = 3;
        Stage Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("Option.fxml"));
        Scene scene = new Scene(root);
        Pstage.setScene(scene);
        System.out.println(dif);
    }

    @FXML
    void handleMat(ActionEvent event) throws IOException {
        dif = 2;
        Stage Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("Option.fxml"));
        Scene scene = new Scene(root);
        Pstage.setScene(scene);
        System.out.println(dif);
    }

    @FXML
    void handle_Back(ActionEvent event) throws IOException {
        Stage Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("Option.fxml"));

        Scene scene = new Scene(root);

        Pstage.setScene(scene);
    }

}
