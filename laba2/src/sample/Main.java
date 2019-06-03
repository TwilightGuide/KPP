package sample;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class  Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Man man = new Man();
        Button pButton = new Button("Перелить");
        Button dButton = new Button("Выпить");
        Label cLabel = new Label("Стакан");
        Label bLabel = new Label("Бутылка");
        Label c0Label = new Label("");
        Label b0Label = new Label("");

        GridPane root = new GridPane();
        root.setMinSize(200, 200);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(30);
        root.setVgap(30);

        root.setAlignment(Pos.CENTER);
        root.add(c0Label, 0,0);
        root.add(b0Label, 1,0);
        root.add(cLabel, 0,1);
        root.add(bLabel, 1,1);
        root.add(pButton, 0,2);
        root.add(dButton, 1,2);

        c0Label.setText(man.cup.liquid.name);
        b0Label.setText(man.bottle.liquid.name);

        primaryStage.setTitle("Laba2");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        //root.setGridLinesVisible(true);

        pButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                man.pourLiquid();
                if(man.cup.liquid != null)
                    c0Label.setText(man.cup.liquid.name);
                else c0Label.setText("");
                if(man.bottle.liquid != null)
                    b0Label.setText(man.bottle.liquid.name);
                else b0Label.setText("");
            }
        });
        dButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                man.drinkLiquid();
                if(man.cup.liquid != null)
                c0Label.setText(man.cup.liquid.name);
                else c0Label.setText("");
                if(man.bottle.liquid != null)
                b0Label.setText(man.bottle.liquid.name);
                else b0Label.setText("");
            }
        });
    }

    public static void main(String[] args) {

        launch(args);
    }
}
