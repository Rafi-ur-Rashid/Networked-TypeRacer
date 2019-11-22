
import com.sun.deploy.trace.Trace;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class tol {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<my> al = new ArrayList<>();
        ArrayList<String> a = new ArrayList<>();
        ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream("C:\\Users\\ASUS\\Documents\\PROGRAMS\\Netbeans\\TypeRacer\\src\\Scores\\High Scores.txt"));
        ObjectInputStream fin1 = new ObjectInputStream(new FileInputStream("C:\\Users\\ASUS\\Documents\\PROGRAMS\\Netbeans\\TypeRacer\\src\\Scores\\High Scores.txt"));
        FileOutputStream fout2 = new FileOutputStream("C:\\Users\\ASUS\\Documents\\PROGRAMS\\Netbeans\\TypeRacer\\src\\Text\\easy\\1.txt", true);
        FileInputStream fin2 = new FileInputStream("C:\\Users\\ASUS\\Documents\\PROGRAMS\\Netbeans\\TypeRacer\\src\\Text\\easy\\1.txt");
        for (int i = 0; i < 10; i++) {
            fout.writeObject(new my(i, "Hello"));
        }
        for (int i = 0; i < 10; i++) {
            al.add((my) fin1.readObject());
            System.out.println(al.get(i));
        }
        System.out.println(al.size());
        for (int i = 0; i < al.size() - 1; i++) {
            for (int j = i + 1; j < al.size(); j++) {
                if ((al.get(i).i) < (al.get(j).i)) {
                    my m = al.get(j);
                    al.set(j, al.get(i));
                    al.set(i, m);
                }
            }
        }
        System.out.println(al.size());
        for (int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i));
        }
        int c;
        fout2.write("If you can type fast it will help you in many aspect of your career as the world is getting stuck to computer based technology day by day.And if your typing speed is slow till now this game can improve your performance noticeabally.\n".getBytes());
        while ((c = fin2.read()) != -1) {
            System.out.print((char) c);
        }
        fin1.close();
        fin2.close();
        fout.close();
        fout2.close();
        fout2 = new FileOutputStream("C:\\Users\\ASUS\\Documents\\PROGRAMS\\Netbeans\\TypeRacer\\src\\Text\\easy\\1.txt", true);
        fout2.write("Mara\n".getBytes());
        fin2 = new FileInputStream("C:\\Users\\ASUS\\Documents\\PROGRAMS\\Netbeans\\TypeRacer\\src\\Text\\easy\\1.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fin2))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                a.add(i++, line);
            }
        }

        System.out.println(a.get(0));
        System.out.println(a.get(1));
        FileWriter fwOb = new FileWriter("C:\\Users\\ASUS\\Documents\\PROGRAMS\\Netbeans\\TypeRacer\\src\\Text\\easy\\1.txt", false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
        fout2.close();
        fin2.close();
    }
}

class my implements Serializable {

    int i;
    String s;

    my(int I, String S) {
        i = I;
        s = S;
    }

    @Override
    public String toString() {
        return s + " " + i;
    }

}
