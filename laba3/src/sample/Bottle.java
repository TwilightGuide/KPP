package sample;

public class Bottle extends AbstractVessel{
    public boolean cap;

    public Bottle(float volume) {
        super(volume);
        name = "Бутылка";
    }
}
