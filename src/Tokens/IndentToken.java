package Tokens;

public class IndentToken extends Token{
    private String type = "IND";

    public IndentToken(int current){
        this.current = current;
    }

    public String getType() {
        return type;
    }
}
