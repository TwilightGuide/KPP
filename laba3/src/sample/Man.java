package sample;
import java.util.ArrayList;

public class Man {
    public Man(Score score){
        this.score = score;
        vessels.add(new Cup(0.5f));
        vessels.add(new Teapot(0.1f));
        vessels.add(new Bottle(2f));
    }
    private Score score = null;
    private String name;
    private ArrayList<AbstractVessel> vessels = new ArrayList<AbstractVessel>();
    private ArrayList<Liquid> liquids = new ArrayList<Liquid>();

    public void pourLiquid(String name1, String name2){
        AbstractVessel vessel1 = getVessel(name1);
        AbstractVessel vessel2 = getVessel(name2) ;
        float v1, vl2;
        //v1 = vessel1.volume;
        //vl2 = vessel2.liquid.getVolume();
        //if(v1>=vl2 && vessel1.liquid == null){
       // if(vessel2.liquid != null && vessel1.liquid == null)
            vessel2.inLiquid(vessel1.outLiquid());
    }
    public  void drinkLiquid(AbstractVessel vessel){
        vessel.liquid = null;
    }

    public void buyLiquid(Liquid liquid){
        liquids.add(liquid);
    }

    public void toPourLiquid(String nameVessel, String nameLiquid){
        AbstractVessel vessel = getVessel(nameVessel);
        Liquid liquid = getLiquid(nameLiquid);
        if(liquid != null &&vessel.liquid == null)
        vessel.liquid = liquid;
    }

    public AbstractVessel getVessel(String name){
        for(AbstractVessel a: vessels)
        {
            if(name==a.name)
                return a;
        }
        return null;
    }

    public Liquid getLiquid(String name){
        for(Liquid a: liquids)
        {
            if(name==a.name)
                return a;
        }
        return null;
    }

    public ArrayList<AbstractVessel> getVessels(){
        return vessels;
    }
    public ArrayList<Liquid> getLiquids(){
        return liquids;
    }
}
