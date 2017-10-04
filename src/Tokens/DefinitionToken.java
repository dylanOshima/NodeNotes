package Tokens;

public class DefinitionToken extends Token{
    private String type = "DEF";
    String def;

    public DefinitionToken(String def, int current){
        this.def = def;
        this.current = current;
    }

    public String getType() {
        return type;
    }
}
