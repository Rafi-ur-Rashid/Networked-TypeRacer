import java.io.IOException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class hOMEpAGEController extends OptionController {

    static Text t;
    static MediaPlayer sp;
    static Stage Pstage;
    Parent root;
    @FXML
    private Button button;

    @FXML
    private TextField UserName;

    @FXML
    private Label label;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        t = new Text("Please Wait....");
        t.setLayoutX(68);
        t.setLayoutY(183);
        t.setFont(Font.font("Britannic Bold", 55));
        t.setFill(Color.YELLOW);
        Pstage = TypeRacer.Pstage;
        String User = UserName.getText();
        Pstage.setTitle(User);
        AnchorPane layout = new AnchorPane();
        layout.setStyle("-fx-border-color: blue");
        layout.setStyle("-fx-background-color: white");
        ImageView IV = new ImageView("1a1d7a621f4ac6b27a7490b13e256e7a.jpg");
        IV.setFitHeight(437);
        IV.setFitWidth(410);
        IV.setLayoutX(195);
        IV.setLayoutY(242);
        Text Tx = new Text(" Please Wait For Other Player");
        Tx.setLayoutX(85);
        Tx.setLayoutY(184);
        Tx.setFont(Font.font("Britannic Bold", 51));
        layout.getChildren().addAll(IV, Tx);
        Scene scene = new Scene(layout, 800, 800);
        Pstage.setScene(scene);
        new Client(User, UserName, Tx, IV, t);

        Tx.setOnMouseClicked(e -> {
            Pstage = TypeRacer.Pstage;
            RunARaceController rr = new RunARaceController();
            int r = new Random().nextInt(3);
            rr.setImage(r);
            switch (DifficultyController.dif) {
            case 1:
               
                int sub = new Random().nextInt(3);
                switch (sub) {
                    case 0:
                        rr.setScore("If you can type fast it will help you in many aspect of your career as the world is getting stuck to computer based technology day by day. And if your typing speed is slow till now this game can improve your performance noticeabally.");
                        break;
                    case 1:
                        rr.setScore("Look all around you and try to find the essence of beauty in nature.It is every where.You need not travel far distant places to meet your hunger for beauty.It lies very near to us.");
                        break;
                    case 2:
                        rr.setScore("Dream is not something that you see while sleeping rather dream is something that does not let you to sleep. So keep on dreaming and let them come true.");
                        break;
                }
                break;
            case 2:
                int sub2 = new Random().nextInt(3);
                switch (sub2) {
                    case 0:
                        rr.setScore("Typing is a fun. If you do'nt have any experience of fast typing, this game can improve your speed. Looking on keyboard while typing is not a good habit. Try to set your view towards monitor and type as precisely as you can. Don't bother about the speed, it'll increase gradually with repeated practice.");
                        break;
                    case 1:
                        rr.setScore("Here is your that so called 'Freedom', now you can enjoy every moment in your life. But are you sure of getting true happiness by this? Will you be able to fructify your dreams from now on? If you do then congratulations.");
                        break;
                    case 2:
                        rr.setScore("Courtesy isn't a thing inherent by born. It has to be learnt from family, school, society and friends. You mightn't be rich or beautiful looking but you must be courteous as it needs no money to be so.");
                        break;
                }

            case 3:
                int sub3 = new Random().nextInt(3);
                switch (sub3) {
                    case 0:
                        rr.setScore("Typing fast is not only a 'Quality'; it's also an art.It reflects how fast you can think & correspond your limb with the brain work.Typing A, B, C, D or 1, 2, 3, 4 or *, &, ?, # or the characters of any other languages is a great challenge for a beginner.But its all about practice & this is exactly the purpose of this game. ");
                        break;
                    case 1:
                        rr.setScore("Chemistry, Physics, Biology, Math are all of the prime most subjects of today's study for Science. Each subject has it's respective field to work in & consult about. But if you think them detached from each other I'll say-\"Nice Joke!\". Rather I like the term 'Chem-Phy-logy'.");
                        break;
                    case 2:
                        rr.setScore("'LOL', 'BTW', 'OMG' etc has become some of the mostly used short-cut expressions nowadays. People frequently use them in Facebook, Twitter, Whatsapp and other social networks. I am afraid whether the OXFORD English dictionary knows this word or not! ");
                        break;
                }
        }
            Pstage.setScene(new Scene(rr));
            Pstage.setTitle(User);
            TypeRacer.mp.stop();
            String path = TypeRacer.class.getResource("/Running Race.mp3").toString();
            Media media = new Media(path);
            sp = new MediaPlayer(media);
            sp.setOnEndOfMedia(new Runnable() {
                public void run() {
                    sp.seek(Duration.ZERO);
                }
            });
            if (b) {
                sp.play();
            }

        });

    }

    @FXML
    void handleBack(ActionEvent event) throws IOException {
        Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("Start_Game.fxml"));
        Scene scene = new Scene(root);
        Pstage.setScene(scene);
    }

}
