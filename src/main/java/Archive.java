import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Archive {

    private static ArrayList<String> content = new ArrayList<>();

    public static ArrayList<String> read (String path) throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String line = "";
        while(true){
            if (line != null) {
                if (line != "") {
                    content.add(line);
                }
            } else {
              break;
            }
            line = buffRead.readLine();
        }
        buffRead.close();
        return content;
    }



}
