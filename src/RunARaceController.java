import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.Robot;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RunARaceController extends AnchorPane {
    ArrayList<String> a = new ArrayList<>();
    boolean b = OptionController.b;
    Stage Pstage;
    FileOutputStream fout;
    FileInputStream fin;
    Robot robot;
    public char[] text = "hey".toCharArray(), writ;
    double x, x1, x2, x3, wpm = 0;
    String time, difficulty;
    static int m, s, countWord = 0;
    int i = 0, k = 0, j = 0, c,err=0;
    static MediaPlayer error;
    @FXML
    private TextField Write;
    @FXML
    private Text Ready;
    @FXML
    private TextArea TEXT;
    @FXML
    private TextField fromBox;
    @FXML
    private TextArea ToBox;
    @FXML
    private ImageView car;
    @FXML
    private ImageView car3;
    @FXML
    private ImageView car2;
    @FXML
    private ImageView car1;
    @FXML
    private Button yes;
    @FXML
    private Circle circle;
    @FXML
    private Label L1;
    @FXML
    private TextArea Help;
    @FXML
    private Text Wait;

    public RunARaceController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Run A Race.fxml"));
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
        switch (i) {
            case 0:
                car2.setVisible(false);
                car3.setVisible(false);
                car1.setVisible(false);
                car.setVisible(true);
                break;
            case 1:
                car2.setVisible(false);
                car3.setVisible(false);
                car.setVisible(false);
                car1.setVisible(true);
                break;
            case 2:
                car1.setVisible(false);
                car3.setVisible(false);
                car.setVisible(false);
                car2.setVisible(true);
                break;
            case 3:
                car1.setVisible(false);
                car2.setVisible(false);
                car.setVisible(false);
                car3.setVisible(true);
                break;
        }
    }

    @FXML
    void handleYes(ActionEvent event) {
        circle.setVisible(false);
        Ready.setVisible(false);
        yes.setVisible(false);
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> {
                    if (i == text.length) {
                        click(631, 208);
                        L1.setStyle("-fx-background-color:  yellow");
                        hOMEpAGEController.sp.stop();
                    } else if (k < 10) {
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
    void CollectType(KeyEvent e) throws FileNotFoundException, IOException, InterruptedException {

        text = (TEXT.getText()).toCharArray();
        writ = new char[text.length];
        x = car.getLayoutX();
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
            car.setLayoutX(x += (900 / (1.9 * text.length)));
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
            if (b) {
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
            StringTokenizer st = new StringTokenizer(L1.getText(), ": ");
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            wpm = (countWord / (m + s / 60.0));
            fout = new FileOutputStream(Server.f, true);
            fin = new FileInputStream(Server.f);
            switch (DifficultyController.dif) {
                case 1:
                    difficulty = "(Beginner)";
                    break;
                case 2:
                    difficulty = "(Matured)";
                    break;
                case 3:
                    difficulty = "(Professional)";
                    break;
            }
            fout.write(((int)accuracy+": " +difficulty + ": " + hOMEpAGEController.Pstage.getTitle() + ": " + (int) wpm + "\n").getBytes());
            fout.close();
            fout = new FileOutputStream(Server.HighScore, true);
            fout.write(((int)accuracy+": " +difficulty + ": " + hOMEpAGEController.Pstage.getTitle() + ": " + (int) wpm + "\n").getBytes());
            display(hOMEpAGEController.t, fin);
            NetworkUtil nc = new NetworkUtil(Server.SERVER_IP, Server.SERVER_PORT);
            new WriteThreadClient(nc, "finish");
            wpm = countWord = 0;

        }
    }

    @FXML
    void handleBack(ActionEvent event) throws IOException {
        hOMEpAGEController.sp.stop();
        TypeRacer.mp.play();
        Pstage = TypeRacer.Pstage;
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

    void display(Text t, FileInputStream fin) {
        Pstage = hOMEpAGEController.Pstage;
        AnchorPane layout = new AnchorPane();
        layout.setStyle("-fx-background-color: white");
        Text first = new Text();
        first.setLayoutX(95);
        first.setLayoutY(227);
        first.setFont(Font.font("Britannic Bold", 38));
        first.setFill(Color.YELLOW);
        first.setVisible(false);
        Text sec = new Text();
        sec.setLayoutX(95);
        sec.setLayoutY(320);
        sec.setFont(Font.font("Britannic Bold", 31));
        sec.setFill(Color.RED);
        sec.setVisible(false);
        ImageView IV = new ImageView("1939207-bigthumbnail.jpg");
        IV.setFitHeight(800);
        IV.setFitWidth(800);
        IV.setLayoutX(0);
        IV.setLayoutY(0);
        ImageView Iv = new ImageView("golden_cup_PNG14574.png");
        Iv.setFitHeight(75);
        Iv.setFitWidth(71);
        Iv.setLayoutX(25);
        Iv.setLayoutY(175);
        Iv.setVisible(false);
        Button back = new Button("Back");
        back.setPrefSize(70, 39);
        back.setLayoutX(30);
        back.setLayoutY(33);
        back.setOnAction(e -> {
            hOMEpAGEController.sp.stop();
            TypeRacer.mp.play();
            FileWriter fwOb;
            try {
                fwOb = new FileWriter("C:\\Users\\ASUS\\Documents\\PROGRAMS\\Netbeans\\TypeRacer\\src\\Scores\\Trio.txt", false);
                PrintWriter pwOb = new PrintWriter(fwOb, false);
                pwOb.flush();
                pwOb.close();
                fwOb.close();
                fout.close();
                fin.close();
                a.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Pstage = TypeRacer.Pstage;
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Start_Game.fxml"));
                Scene scene = new Scene(root);

                Pstage.setScene(scene);
                Pstage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        layout.getChildren().addAll(IV, t, back, first, Iv, sec);
        Scene scene = new Scene(layout, 800, 800);
        Pstage.setScene(scene);
        t.setOnMouseClicked(e -> {
            t.setVisible(false);
            Iv.setVisible(true);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(fin))) {
                String line;
                int i = 0;
                while ((line = br.readLine()) != null) {
                    a.add(i++, line);
                }
                StringTokenizer st1 = new StringTokenizer(a.get(0), ": ");
                int ac1 = Integer.parseInt(st1.nextToken());
                String l1 = st1.nextToken();
                String A = st1.nextToken();
                int a1 = Integer.parseInt(st1.nextToken());

                StringTokenizer st2 = new StringTokenizer(a.get(1), ": ");
                int ac2 = Integer.parseInt(st2.nextToken());
                String l2 = st2.nextToken();
                String B = st2.nextToken();
                int a2 = Integer.parseInt(st2.nextToken());
                if (a1 >= a2) {
                    first.setText("1." + A + " " + a1 + " wpm " +" Accuracy "+ac1+ l1);
                    sec.setText("2." + B + " " + a2 + " wpm "+" Accuracy "+ac2 + l2);
                } else {
                    first.setText("1." + B + " " + a2 + " wpm "+" Accuracy "+ac2 + l2);
                    sec.setText("2." + A + " " + a1 + " wpm "+" Accuracy "+ac1 + l1);
                }
                first.setVisible(true);
                sec.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
    }
}
