package sample;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.awt.*;
import java.util.ArrayList;

public class Main extends Application {

    ArrayList<Integer> sig1 = new ArrayList<>();
    ArrayList<Integer> sig2 = new ArrayList<>();
    ArrayList<Integer> arrCorr = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        TextField fieldSig1 = new TextField("");
        TextField fieldSig2 = new TextField("");

        Label labelSig1 = new Label("Сигнал №1");
        Label labelSig2 = new Label("Сигнал №2");
        Button butStart = new Button("Вычислить корреляцию");

        root.add(labelSig1,0,0);
        root.add(labelSig2,0,1);
        root.add(fieldSig1,1,0);
        root.add(fieldSig2,1,1);
        root.add(butStart,0,2,2,1);
        butStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String str1, str2, ms1[],ms2[];
                int mn = 1;
                str1 = fieldSig1.getText();
                str2 = fieldSig2.getText();

                ms1 = str1.split(" ");
                ms2 = str2.split(" ");

                for(String a: ms1)
                    sig1.add(Integer.parseInt(a));
                for(String a: ms2)
                    sig2.add(Integer.parseInt(a));

                findCorrelation();
            }
        });

    }

    public void findCorrelation(){
        int len_min;
        float result = 0;
        ArrayList<Correlation> arrThreads = new ArrayList<>();
        if(sig1.size()> sig2.size())
           len_min = sig1.size();
        else len_min = sig2.size();
        for(int i=0; i < len_min; i++) {
            arrThreads.add(new Correlation("Thread " + i, sig1.get(i), sig2.get(i), arrCorr));
            arrThreads.get(i).start();
        }

        try {
            for (Correlation a : arrThreads)
                a.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(Integer a: arrCorr) {
            result += (int) a;
            System.out.println(a);
        }
        result /= len_min;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(Float.toString(result));
        alert.showAndWait();

        sig1.clear();
        sig2.clear();
        arrCorr.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
