package sample;

public class Vessel {
    public Liquid liquid = null;

    public Liquid outLiquid(){
        Liquid liquid = this.liquid;
        this.liquid = null;
        return liquid;
    }
    public void inLiquid(Liquid liquid){
        this.liquid = liquid;
    }
}
