
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HighScoreController extends AnchorPane {

    @FXML
    private Text one;
    @FXML
    private Button Back;
    @FXML
    private Text sp;
    @FXML
    private Text accu;
    public HighScoreController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HighScore.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void set(ArrayList Score, int[] Sp,String[] difficulty,int[]accuracy) {
        for (int i = 0; i < Score.size(); i++) {
            String old = one.getText();
            String string = null;
            if ((i+1) < 10) {
                string = old +"  "+ (i + 1) + ".          " + Score.get(i) + "\n";
            } else {
                string = old + (i + 1) + ".           " + Score.get(i) + "\n";
            }
            one.setText(string);
            String Old = sp.getText();
            String tring = Old + "  " + Sp[i] +difficulty[i]+ "\n";
            sp.setText(tring);
            one.setText(string);
            String OLD = accu.getText();
            String S = OLD + "  " + accuracy[i] +"\n";
            accu.setText(S);
        }

    }

    @FXML
    void handleBack(ActionEvent event) throws IOException {
        Stage Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("Option.fxml"));
        Scene scene = new Scene(root);
        Pstage.setScene(scene);

    }

}
