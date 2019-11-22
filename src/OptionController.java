
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class OptionController {

    Stage Pstage;
    ArrayList<String> score;
    int[] Sp,Accuracy;
    String[]difficulty;
    static boolean b = true;
    @FXML
    private Button Score;
    @FXML
    private Button Back;
    @FXML
    private Button Sound;

    @FXML
    private Button diff;
    @FXML
    private ImageView Off;

    @FXML
    private ImageView On;

    @FXML
    void handleSound(ActionEvent event) {
        On.setVisible(true);
        Off.setVisible(true);
        Off.setOnMouseClicked(e -> {
            On.setVisible(true);
            Off.setVisible(false);
            b = false;
            if (TypeRacer.mp != null) {
                TypeRacer.mp.setMute(true);
            }
            if (hOMEpAGEController.sp != null) {
                hOMEpAGEController.sp.setMute(true);
            }
            if (Start_GameController.sp != null) {
                Start_GameController.sp.setMute(true);
            }
            if (Practice.error != null) {
                Practice.error.setMute(true);
            }
            if (RunARaceController.error != null) {
                RunARaceController.error.setMute(true);
            }

        });
        On.setOnMouseClicked(e -> {
            Off.setVisible(true);
            On.setVisible(false);
            b = true;
            if (TypeRacer.mp != null) {
                TypeRacer.mp.setMute(false);
            }
            if (hOMEpAGEController.sp != null) {
                hOMEpAGEController.sp.setMute(false);
            }
            if (Start_GameController.sp != null) {
                Start_GameController.sp.setMute(false);
            }
            if (Practice.error != null) {
                Practice.error.setMute(false);
            }
            if (RunARaceController.error != null) {
                RunARaceController.error.setMute(false);
            }
        });

    }

    @FXML
    void handlediff(ActionEvent event) throws IOException {
        Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("Difficulty.fxml"));
        Scene scene = new Scene(root);
        Pstage.setScene(scene);
    }

    @FXML
    void handleScore(ActionEvent event) throws IOException {
        FileInputStream fin = new FileInputStream(Server.HighScore);
        score = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fin))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                score.add(i++, line);
            }
            Sp = new int[score.size()];
            Accuracy =new int[score.size()];
            difficulty=new String[score.size()];
            for (int j = 0; j < score.size(); j++) {
                StringTokenizer st1 = new StringTokenizer(score.get(j), ": ");
                int ac1 = Integer.parseInt(st1.nextToken());
                String l1 = st1.nextToken();
                String A = st1.nextToken();
                int a1 = Integer.parseInt(st1.nextToken());
                Accuracy[j]=ac1;
                Sp[j] = a1;
                difficulty[j]=l1.trim();
                score.set(j, A);
            }
            for (int j = 0; j < score.size() - 1; j++) {
                for (int k = j + 1; k < score.size(); k++) {
                    if (Sp[j] < Sp[k]) {

                        String m = score.get(k);
                        score.set(k, score.get(j));
                        score.set(j, m);
                        int M = Sp[k];
                        Sp[k] = Sp[j];
                        Sp[j] = M;
                        int N = Accuracy[k];
                        Accuracy[k] = Accuracy[j];
                        Accuracy[j] = N;
                        String n = difficulty[k];
                        difficulty[k] = difficulty[j];
                        difficulty[j] = n;
                    }
                }
            }
        }
        if (score.size() > 10) {
            int size = score.size();
            for (int j = 10; j < size; j++) {
                score.remove(10);
            }

        }
        HighScoreController hs = new HighScoreController();
        hs.set(score, Sp,difficulty,Accuracy);
        fin.close();
        Pstage = TypeRacer.Pstage;
        Pstage.setScene(new Scene(hs));
        Pstage.setTitle("Score");
        Pstage.setWidth(800);
        Pstage.setHeight(800);
    }

    @FXML
    void handleBack(ActionEvent event) throws IOException {
        Pstage = TypeRacer.Pstage;
        Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Scene scene = new Scene(root);
        Pstage.setScene(scene);
    }

}
