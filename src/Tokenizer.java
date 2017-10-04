import Tokens.*;

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
 *      1. Check if there is white space √
 *      2. Check for backspaces √
 *      3. Check for indent token √
 *      4. Check for header tokens √
 *      5. Check for definition tokens √
 *      6. Check for bullet tokens √
 *      7. Check for important token
 *      8. Check for code tokens √
 *      9. Check for text tokens √
 *
 *    TODO:
 *      1. Create custom exceptions
 *          a) Power too great
 **/
    public LinkedList<Token> tokens = new LinkedList<Token>();

    public LinkedList<Token> tokenize(String input){
        try{
            int current = 0;

            do {
//                System.out.println("Currently: " + input.charAt(current) + " at " + current); //TESTING DELETE

                //Check for spaces
                if(input.charAt(current) == (' ')){
//                    System.out.println("+++ Spaces detected"); //DEBUGGING

                    current++;
                    continue;
                }

                //Check for backspace
                if(input.charAt(current) == '\n'){
//                    System.out.println("+++ Backspace detected"); //DEBUGGING

                    current++;
                    continue;
                }

                //Check for indents
                if(input.charAt(current) == '\t'){
                    tokens.add(new IndentToken(current));

                    System.out.println("+++ IND token detected" );
                    current++;
                    continue;
                }

                //Checks for a HDR token
                if(input.charAt(current) == '#'){
                    int pow = 1;
                    current++;

                    //Finds the power of the Header token
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
                        if(current == input.length()){
                            throw new Error("--- ERROR: List out of bounds when tokenizing HDR"); //Should create a custom Exception
                        }
                        current++;
                    }

                    String val = input.substring(start,(current-1));
                    tokens.add(new HeaderToken(val, pow, current));

                    System.out.println("+++ HDR token detected: " + "'" + val + "'" + " with power " + pow);
                    current++;
                    continue;
                }

                //Check for a DEF token
                if(current+3 < input.length()){ //Makes sure that checking next chars does not cause indexOutOfBounds
                    if(input.substring(current, current+3).equals("DEF")){
                        current += 4;
                        int start = current;

                        while(input.charAt(current) != '-'){
                            if(current == input.length()){
                                throw new Error("--- ERROR: List out of bounds when tokenizing DEF"); //Should create a custom Exception
                            } else if(input.charAt(current) == '\n'){
                                throw new Error("--- ERROR: Missing '-' when tokenizing DEF"); //Should create a custom Exception
                            }

                            current++;
                        }

                        String val = input.substring(start, current);
                        tokens.add(new DefinitionToken(val,current));

                        System.out.println("+++ DEF token detected: " + "'" + val + "'");
//                        System.out.println("Currently: " + input.charAt(current)); //DELETE
                        current++;
                        continue;
                    }
                }

                //Check for Bullet tokens
                if(input.charAt(current) == '*'){
                    tokens.add(new BulletToken(current));

                    System.out.println("+++ BP token detected" );
                    current++;
                    continue;
                }

                //check for IMP token
                if(input.charAt(current) == '|'){
                    current++;
                    int start = current;
                    int temp = current;

                    while(input.charAt(temp) != '|'){
                        if (input.charAt(temp) == '\n') break;
                        else temp++;
                    }

                    String val;

                    if(input.charAt(temp) != '|'){
                        int end = start;
                        while(input.charAt(end) != ' '){
                            end++;
                        }

                        current = end;
                        val = input.substring(start, end);
                    } else {
                        current = temp;
                        val = input.substring(start, current);
                    }

                    tokens.add(new ImportantToken(val, current));

                    System.out.println("+++ IMP token detected: " + '"' + val + '"');
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

                    System.out.println("+++ CD token detected: " + "'" + val + "'" );
                    current++;
                    continue;
                }

                //Check for TXT token
                if(input.charAt(current) == '"'){
                    current++;
                    int start = current;

                    while(input.charAt(current) != '"'){
                        if(current == input.length()){
                            throw new Error("--- ERROR: List out of bounds when tokenizing TXT"); //Should create a custom Exception
                        }
                        current++;
                    }

                    String val = input.substring(start, current);
                    tokens.add(new TextToken(val, current));

                    System.out.println("+++ TXT token detected: " + "'" + val + "'");
//                        System.out.println("Currently: " + input.charAt(current)); //DELETE
                    current++;
                    continue;
                }

                current++;
            } while(current < (input.length()));

        } catch(Exception e){
            System.out.println("Something went wrong when tokenizing: \n" + e);
        }

        return tokens;
    }
}
