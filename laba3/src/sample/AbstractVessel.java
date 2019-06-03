package sample;

public class AbstractVessel {
public float volume;
public Liquid liquid = null;
public String name;

    public AbstractVessel(float volume){
        this.volume = volume;
    }

    public void setVolume(float volume){
        this.volume = volume;
    }

    public float getVolume(){
        return volume;
    }

    public Liquid outLiquid(){
        Liquid liquid = this.liquid;
        this.liquid = null;
        return liquid;
    }

    public void inLiquid(Liquid liquid){
        this.liquid = liquid;
    }
}
