package Tokens;

public class CodeToken extends Token {
    private String type = "CD";

    String code;

    public CodeToken(String code, int current) {
        this.code = code;
        this.current = current;

    }

    public String getType() {
        return type;
    }
}
