import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {

    public static String[][] getRawDF(String fileref) {

        ArrayList<String[]> rawDF = new ArrayList<>();

        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileref))) {
            while ((line = br.readLine()) != null) {
                String[] linelist = line.split(",");
                for (int l = 0; l < linelist.length; l++) {
                    linelist[l] = linelist[l].replace("\"", "").trim();
                }
                rawDF.add(linelist);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] rawDFmat = new String[rawDF.size()][];
        rawDF.toArray(rawDFmat);

        return rawDFmat;
    }

}
