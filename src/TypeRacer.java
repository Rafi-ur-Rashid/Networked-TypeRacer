import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.Robot;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TypeRacer extends Application {

    static Stage Pstage;
    double size;
    Rectangle R;
    Robot robot;
    Text T1, T2;
    boolean myturn = false;
    static MediaPlayer mp;

    @Override
    public void start(Stage stage) throws Exception {

        Pstage = stage;
        stage.setOnCloseRequest(e -> {
            e.consume();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Quit.fxml"));
                Scene scene = new Scene(root);
                Pstage.setScene(scene);
                
            } catch (IOException ex) {
                Logger.getLogger(TypeRacer.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        Pstage.setTitle("TYPE RACER");
        AnchorPane layout = new AnchorPane();
        ImageView IV = new ImageView("18729526-Female-hands-typing-on-the-laptop-with-flying-letters-Stock-Photo.jpg");
        IV.setFitHeight(800);
        IV.setFitWidth(800);
        IV.setLayoutX(0);
        IV.setLayoutY(0);
        R = new Rectangle(10, 15, Color.RED);
        R.setLayoutX(0);
        R.setLayoutY(785);
        size = R.getWidth();
        T1 = new Text(" TYPE");
        T2 = new Text("RACER");
        Label L3 = new Label("Loading......"), L4 = new Label("Typeracer.com@all rights-reserved");
        T1.setFont(Font.font("Britannic Bold", 55));
        T1.setLayoutX(564);
        T1.setLayoutY(293);
        T1.setFill(Color.YELLOW);
        T2.setFont(Font.font("Britannic Bold", 55));
        T2.setLayoutX(547);
        T2.setLayoutY(368);
        T2.setFill(Color.YELLOW);
        L3.setPrefSize(120, 30);
        L3.setLayoutX(12);
        L3.setLayoutY(750);
        L3.setFont(Font.font("Verdana", 20));
        L3.setTextFill(Color.RED);
        L4.setPrefSize(231, 21);
        L4.setLayoutX(6);
        L4.setLayoutY(4);
        L4.setTextFill(Color.WHITE);
        layout.getChildren().addAll(IV, R, T1, T2, L3, L4);
        Scene scene = new Scene(layout, 800, 800);
        Pstage.setScene(scene);
        Pstage.show();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(280),
                ae -> {
                    if (size >= 776 && size <= 850) {
                        click(565, 295);
                        try {
                            display();
                            size = 900;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        myTask();
                    }
                }));
        T1.setOnMouseClicked(et -> {
            timeline.stop();
        });
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }

    public void myTask() {
        if (size <= 150) {

            R.setWidth(size += 30);
        } else if (size <= 400) {
            R.setWidth(size += 50);
        } else if (size < 750) {
            R.setWidth(size += 70);
        } else if (size <= 800) {
            R.setWidth(size += 40);
        }
    }

    public void display() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Scene scene = new Scene(root);
        Pstage.setScene(scene);
        String path = TypeRacer.class.getResource("/Lynn Music Boulangerie - Gaming Background Music (HD)(128kbps).mp3").toString();
        Media media = new Media(path);
        mp = new MediaPlayer(media);
        mp.setOnEndOfMedia(new Runnable() {
            public void run() {
                mp.seek(Duration.ZERO);
            }
        });
        mp.play();
    }

    public static void main(String[] args) {
        launch(args);
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
