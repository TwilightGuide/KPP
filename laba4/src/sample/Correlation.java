package sample;

import java.util.ArrayList;

public class Correlation extends Thread {
    int result, a, b;
    ArrayList<Integer> arrCorr;
    Correlation(String name, Integer a, Integer b, ArrayList<Integer> arrCorr){
        super (name);
        this.a = a;
        this.b = b;
        this.arrCorr = arrCorr;
    }

    public void run(){
        synchronized (arrCorr) {
            System.out.printf("%s started... \n", Thread.currentThread().getName());
            result = a * b;
            arrCorr.add(result);
            System.out.printf("%s fiished... \n", Thread.currentThread().getName());
        }
    }

}
