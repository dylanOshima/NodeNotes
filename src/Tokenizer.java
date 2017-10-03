import Tokens.CodeToken;
import Tokens.HeaderToken;
import Tokens.Token;

import java.util.LinkedList;

public class Tokenizer {
/**
 *    Token Types:
 *      CD
 *          1. Opens "`"
 *          2. CONTENT
 *          3. Closes "`"
 *      HDR
 *          1. "#" checks the number of #
 *          2. CONTENT
 *          3. Closes when it sees "\n"
 *
 *      DEF
 *          1. Reads symbol "DEF" *All caps
 *          2: Def name
 *          3. Closes when "-"
 *
 *      IMP
 *          1. Reads "|" symbol
 *          2 Traverse the rest of the line
 *          3 If "\n" appears before another "|"
 *          3.1 Then input = content next to this and before " "
 *          2. Otherwise input = text between "|" and "|"
 *
 *      IND
 *
 *      BP
 *
 *    Tokenizer's Role:
 *          Read through the input and figure out the token type and its value
 *
 *    Code Run-Down:
 *      1. Check if there is white space
 *      2. Check for backspaces
 *      3. Check for code tokens
 *
 *    TODO:
 *      1. Create custom exceptions
 *          a) Power too great
 **/
    public LinkedList<Token> tokens = new LinkedList<Token>();

    public LinkedList<Token> tokenize(String input){
        try{
            int current = 0;
            System.out.println(input.length());

            while(current < (input.length()-1)){
                System.out.println(current); //TESTING


                //Check for spaces
                if(input.charAt(current) == (' ')){
                    current++;
                    continue;
                }

                //Check for backspace
                if(input.charAt(current) == '\n'){

                    System.out.println("+++ Backspace detected");
                    current++;
                    continue;
                }

                //Check for a CD token
                if(input.charAt(current) == '`'){
                    current++;
                    int start = current;

                    while(input.charAt(current) != '`'){
                        current++;
                    }

                    String val = input.substring(start, current);
                    tokens.add(new CodeToken(val, current));


                    System.out.println("+++ Code detected: " + "'" + val + "'" );
                    current++;
                    continue;
                }

                //Checks for a HDR token
                if(input.charAt(current) == '#'){
                    int pow = 1;

                    current++;
                    while(input.charAt(current) == '#'){
                        if(pow > 6){
                            System.out.println("--- ERROR: Power is greater than 6"); //Should create a custom Exception
                            break;
                        }

                        pow++;
                        current++;
                    }

                    int start = current;
                    current++;
                    while(input.charAt(current) != '\n'){
                        current++;
                    }

                    String val = input.substring(start,(current-1));
                    tokens.add(new HeaderToken(val, pow, current));

                    System.out.println("+++ Header detected: " + "'" + val + "'" + " with power, " + pow);
                    current++;
                    continue;
                }

                current++;
            }

        } catch(Exception e){
            System.out.println("Something went wrong when tokenzing: \n" + e);
        }

        return tokens;
    }
}
