package Tokens;

public class HeaderToken extends Token{
    private String type = "HDR";
    String value;
    int strength;

    public HeaderToken(String value, int strength, int current){
        this.value = value;
        this.strength = strength;
        this.current = current;
    }


    public String getType() {
        return type;
    }
}
