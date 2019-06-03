package sample;

public class Liquid {
    public  Liquid(float volume){
        this.volume = volume;
    }
    public String name;
    private float volume;
    public void setVolume(float volume){
        this.volume = volume;
    }
    public float getVolume(){
        return volume;
    }
}
