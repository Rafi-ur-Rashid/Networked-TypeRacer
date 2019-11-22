import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.Robot;
import java.io.IOException;
import java.util.StringTokenizer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Practice extends AnchorPane {

    Robot robot;
    Stage stage;
    static MediaPlayer error;
    public char[] text = "hey".toCharArray(), writ;
    double x1, x2, x3, wpm = 0;
    String time;
    static int m, s, countWord = 0;
    int i = 0, k = 0, j = 0,err=0;
    @FXML
    private TextField Write;
    @FXML
    private ImageView car1;
    @FXML
    private ImageView car2;
    @FXML
    private ImageView car3;
    @FXML
    private Text Ready;
    @FXML
    private Circle circle;
    @FXML
    private Label L1;
    @FXML
    private TextArea TEXT;
    @FXML
    private Button yes;
    @FXML
    public static Label count;
    @FXML
    private TextArea Help;
    @FXML
    private Text Wait;

    public Practice() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Practice.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setScore(String s) {
        TEXT.setText(s);
    }

    public void setImage(int i) {
        if (i == 1) {
            car2.setVisible(false);
            car3.setVisible(false);
            car1.setVisible(true);
        }
        if (i == 2) {
            car1.setVisible(false);
            car3.setVisible(false);
            car2.setVisible(true);
        }
        if (i == 3) {
            car1.setVisible(false);
            car2.setVisible(false);
            car3.setVisible(true);
        }
    }

    @FXML
    void handleYes(ActionEvent event) {
        stage = TypeRacer.Pstage;
        circle.setVisible(false);
        Ready.setVisible(false);
        yes.setVisible(false);
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> {
                    if (i == text.length) {
                        click(631, 208);
                        L1.setStyle("-fx-background-color:  yellow");
                        Start_GameController.sp.stop();
                    }
                    if (k < 10) {
                        L1.setText("   0" + j + " : " + "0" + k++);
                    } else if (k < 60) {
                        L1.setText("   0" + j + " : " + k++);
                    } else {
                        k = 0;
                        L1.setText("   0" + (++j) + " : " + "0" + k++);
                    }

                }));
        L1.setOnMouseClicked(et -> {
            timeline.stop();
        });
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();

    }

    @FXML
    void CollectType(KeyEvent e) throws InterruptedException {

        text = (TEXT.getText()).toCharArray();
        writ = new char[text.length];
        x1 = car1.getLayoutX();
        x2 = car2.getLayoutX();
        x3 = car3.getLayoutX();
        writ[i++] = (e.getCharacter()).charAt(0);
        if (writ[i - 1] == text[i - 1]) {
            String old = Help.getText();
            String string = old + e.getCharacter();
            Help.setText(string);
            Write.setStyle("-fx-background-color: white");
            Write.setStyle("-fx-alignment-color:  #58efea");
            if (writ[i - 1] == ' ') {
                countWord++;
                Write.clear();
            }
            car1.setLayoutX(x1 += (900 / (1.9 * text.length)));
            car2.setLayoutX(x2 += (900 / (1.9 * text.length)));
            car3.setLayoutX(x3 += (900 / (1.9 * text.length)));
        } else {
            err++;
            Write.setStyle("-fx-background-color: red");
            i--;
            String path = TypeRacer.class.getResource("/Computer Error-SoundBible.com-69768060.mp3").toString();
            Media media = new Media(path);
            error = new MediaPlayer(media);
            if (OptionController.b) {
                error.play();
            }
        }
        if (i == text.length) {
            double accuracy=(text.length-err)*100/text.length;
            countWord++;
            Write.setText("COMPLETED");
            Write.setFont(Font.font("Britannic Bold", 24));
            Write.setStyle("-fx-border-color:  green");
            Write.setEditable(false);
            L1.setStyle("-fx-background-color:  yellow");
            Start_GameController.sp.stop();
            StringTokenizer st = new StringTokenizer(L1.getText(), ": ");
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            wpm = (countWord / (m + s / 60.0));
            Show_scoreController sh = new Show_scoreController();
            sh.set((int) wpm + " wpm","Accuracy "+(int)accuracy+"%");
            wpm = countWord = 0;
            stage.setScene(new Scene(sh));
            stage.setTitle("Score");
            stage.setWidth(800);
            stage.setHeight(800);
        }
    }

    @FXML
    void handle_Back(ActionEvent event) throws IOException {
        Start_GameController.sp.stop();
        TypeRacer.mp.play();
        Stage Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("Start_Game.fxml"));
        Scene scene = new Scene(root);
        Pstage.setScene(scene);
        Pstage.show();
    }

    public void click(int x, int y) {
        try {
            robot = new Robot() {
                @Override
                protected void _create() {
                }

                @Override
                protected void _destroy() {
                }

                @Override
                protected void _keyPress(int code) {
                }

                @Override
                protected void _keyRelease(int code) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                protected void _mouseMove(int x, int y) {
                }

                @Override
                protected void _mousePress(int buttons) {
                }

                @Override
                protected void _mouseRelease(int buttons) {
                }

                @Override
                protected void _mouseWheel(int wheelAmt) {
                }

                @Override
                protected int _getMouseX() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                protected int _getMouseY() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                protected int _getPixelColor(int x, int y) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                protected Pixels _getScreenCapture(int x, int y, int width, int height, boolean isHiDPI) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
        } catch (Exception e) {
        }
        robot.mouseMove(x, y);
        robot.mousePress(0);
        robot.mouseRelease(0);
    }
}
