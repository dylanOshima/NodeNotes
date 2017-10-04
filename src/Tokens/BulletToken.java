package Tokens;

public class BulletToken extends Token{
    private String type = "BLT";

    public BulletToken(int current){
        this.current = current;
    }

    @Override
    public String getType() {
        return type;
    }
}
