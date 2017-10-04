package Tokens;

import javax.xml.soap.Text;

public class TextToken extends Token{
    String val;
    private String type = "TXT";

    public TextToken(String val, int current){
        this.val = val;
        this.current = current;
    }

    public String getType() {
        return type;
    }
}
