import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/*
    Node Notes Compiler
        This is the compiler for the NN Language.

        TODO:
            1. Fix the writing function so that it deletes what was in the document prior
            2. Create a new document when writing
 */

public class Main {

    public static void main(String args[]){

        String fileNameRead = "temp.txt";
        String fileNameWrite = "writeinme.txt";
        int num = 5;

        try {
            //Reads the entire file and saves it as a string.
            String content = new String(Files.readAllBytes(Paths.get(fileNameRead)));

            System.out.println(content.charAt(num) == '\n');


            //Writes to a pre-existing file called fileNameWrite
            //Puts in the content in goodStuff
            String goodStuff = "Hi my name is dylan \nI like dancing \nLOL";
            Files.write(Paths.get(fileNameWrite), goodStuff.getBytes(), StandardOpenOption.CREATE);

        } catch (Exception e){
            System.out.println(e);
        }

    }
}
