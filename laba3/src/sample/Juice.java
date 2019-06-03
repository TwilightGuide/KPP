package sample;

public class Juice extends  Liquid{
    Juice(float volume ,String flavor){
        super(volume);
        this.flavor = flavor;
        name = "Сок";
    }
    public String flavor;
}
