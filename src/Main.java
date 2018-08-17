import Tokens.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

/*
    Node Notes Compiler
        This is the compiler for the NN Language.

        TODO:
            1. Create a new document when writing
            2. Fix the writing function so that it deletes what was in the document prior
            3. Fix the ugly getType() bug with the Tokens and the subclasses
 */

public class Main {

    public static void main(String args[]){

        String fileNameRead = "Resources/Tests/HeadersTest.note"; //Easy example
        String fileNameWrite = "writeinme.txt";

        Tokenizer tokenizer = new Tokenizer();

        try {
            //Reads the entire file and saves it as a string.
            String content = new String(Files.readAllBytes(Paths.get(fileNameRead)));

            System.out.println("List length: " + content.length());
            LinkedList<Token> tokens = tokenizer.tokenize(content);

            System.out.println("\nReading from the Token List");
            for(int i=0;i < tokens.size();i++){
                System.out.print(tokens.get(i).getType() + ", ");
            }




            //Writes to a pre-existing file called fileNameWrite
            //Puts in the content in goodStuff
//            String goodStuff = "Hi my name is dylan \nI like dancing \nLOL";
//            Files.write(Paths.get(fileNameWrite), goodStuff.getBytes(), StandardOpenOption.CREATE);

        } catch (Exception e){
            System.out.println("In the main method: " + e);
        }

    }
}
