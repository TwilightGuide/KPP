package sample;

import java.util.ArrayList;

public class Score {
    private ArrayList<Liquid>liquids = new ArrayList<Liquid>();
    public Score(){
        liquids.add(new Coffee(0.5f));
        liquids.add(new Tea(0.5f));
        liquids.add(new Water(1.5f));
        liquids.add(new Juice(1.0f,"Апельсиновый"));
    }


    public ArrayList<Liquid> getLiquids() {
        return liquids;
    }

    public Liquid issueGoods(String name){ //Мог устроить поиск и сравнение объектов, но нет времени :(
        Liquid liquid = null;
            for(Liquid a: liquids) {
                if(a.name == name) {
                    liquid = a;
                    liquids.remove(a);
                    return liquid;
                }
            }
        return null;
    }

    public void acceptBuyer(){

    }
}
