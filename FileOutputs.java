import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
Gordon Gregory
CSPC 1060
May 2
FileOutputs.java
 */

/**
 * Used to handle the file output very generic
 */
public class FileOutputs {
    private ArrayList<String> scoreSeeds = new ArrayList<>();
    private String fileName;

    private FileOutputStream fileStream;

    public void printFile(){
        try (FileOutputStream  fileStream =new FileOutputStream(fileName)) {
            PrintWriter outWrite = new PrintWriter(fileStream);
            for (String i : scoreSeeds) {
                System.out.println(i);
                outWrite.println(i);
                outWrite.close();
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public void addPlay(int score, int seed){
        String ScSe = String.format("Seed: %d \t Score: %d",seed,score);
        scoreSeeds.add(ScSe);
    }
    FileOutputs(String fileName){
        this.fileName = fileName+".txt";
    }
}
