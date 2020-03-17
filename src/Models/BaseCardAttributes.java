package Models;

public class BaseCardAttributes {
    private int damage;
    private int block;
    private int heal;
    private int draw;
    private int discard;

    public void ascend(AbstractRoom room){
        //logic
        AbstractRoom newRoom = new AbstractRoom();
        return newRoom;
    }
    public void setDamage(int d){
        damage = d;
    }
    public void setBlock(int b){
        block = b;
    }
    public void setHeal(int h){
        heal = h;
    }
    public void setDraw(int d){
        draw = d;
    }
    public void setDiscard(int d){
        discard = d;
    }
    public int getDamage(){
        return damage;
    }
    public int getBlock(){
        return block;
    }
    public int getHeal(){
        return heal;
    }
    public int getDraw(){
        return draw;
    }
    public int getDiscard(){
        return discard;
    }
}
