package Tokens;

public class ImportantToken extends Token{
    private String type = "IMP";
    String value;

    public ImportantToken(String value, int current){
        this.value = value;
        this.current = current;
    }


    public String getType() {
        return type;
    }
}
