package sample;

public class Man {
    public Bottle bottle = new Bottle();
    public Cup cup = new Cup();
    public void pourLiquid(){
        if(cup.liquid != null && bottle.liquid == null)
            bottle.inLiquid(cup.outLiquid());
        else
        if(cup.liquid == null && bottle.liquid != null)
            cup.inLiquid(bottle.outLiquid());

    }
    public  void drinkLiquid(){
        cup.outLiquid();
    }
}
