package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        //root.setGridLinesVisible(true);
        root.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Laba3");
        primaryStage.setScene(new Scene(root, 500, 350));
        primaryStage.show();

        Score score = new Score();
        Man man = new Man(score);

        Button butPour = new Button("Перелить");
        Button butToPour = new Button("Налить");
        Button butDrink = new Button("Выпить");
        Button butBuy = new Button("Купить напиток");

        Label labLiquids = new Label("Покупки");
        Label labVessels = new Label("Сосуды");
        Label labScore = new Label("Магазин");

        ObservableList<String> vessel = FXCollections.observableArrayList();    //для трех списков в главном окошке
        ObservableList<String> scoreLiquid = FXCollections.observableArrayList();
        ObservableList<String> liquid = FXCollections.observableArrayList();

        ListView<String> vessels = new ListView<String>(vessel);                    //объявление списков javaFX
        ListView<String> scoreLiquids = new ListView<String>(scoreLiquid);
        ListView<String> liquids = new ListView<String>(liquid);

        String nameLiquid = new String("");
        for(AbstractVessel a: man.getVessels()) {
            if(a.liquid != null)
                nameLiquid = a.liquid.name;
            vessel.add(a.name+"("+nameLiquid+")");
        }
        for(Liquid a: score.getLiquids())
            scoreLiquid.add(a.name);
        for(Liquid a: man.getLiquids())
            liquid.add(a.name);

        vessels.setPrefSize(100,100);
        liquids.setPrefSize(100,100);
        scoreLiquids.setPrefSize(100,100);

        root.add(labLiquids,0,0);
        root.add(labVessels,1,0);
        root.add(labScore,2,0);
        root.add(scoreLiquids,2,1);
        root.add(liquids,0,1);
        root.add(vessels,1,1);
        root.add(butPour,3,2);
        root.add(butToPour,0,2);
        root.add(butDrink,1,2);
        root.add(butBuy,2,2);

        butDrink.setOnAction(new EventHandler<ActionEvent>() {      //кнопка выпить(выпить все жидкости)
            @Override
            public void handle(ActionEvent actionEvent) {
                for(AbstractVessel a: man.getVessels())
                    a.liquid = null;
                perezepisVessels( man, vessels, vessel);

            }
        });

        butBuy.setOnAction(new EventHandler<ActionEvent>() {            //кнопка для покупки жидкости
            @Override
            public void handle(ActionEvent actionEvent) {
                if(scoreLiquid.size()!= 0 && scoreLiquids.getSelectionModel().getSelectedItem() != null) {
                    man.buyLiquid(score.issueGoods(scoreLiquids.getSelectionModel().getSelectedItem()));
                    liquids.getItems().clear();
                    scoreLiquids.getItems().clear();
                    for (Liquid a : score.getLiquids())
                        scoreLiquid.add(a.name);
                    for (Liquid a : man.getLiquids())
                        liquid.add(a.name);
                }
            }
        });

        butPour.setOnAction(new EventHandler<ActionEvent>() {           //кнопка для переливания жидкости из сосуда в сосуд

        @Override
        public void handle(ActionEvent event) { //окно "перелить жидкость"
            Stage newWindow = new Stage();
            GridPane root = new GridPane();
            root.setHgap(10);
            root.setVgap(10);
            //root.setGridLinesVisible(true);
            root.setAlignment(Pos.CENTER);
            newWindow.setTitle("Laba3");
            newWindow.setScene(new Scene(root, 300, 200));
            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.initOwner(primaryStage);
            newWindow.show();

            Label labPour1 = new Label("Перелить из");
            Label labPour2 = new Label("в");
            ChoiceBox cbVessel1 = new ChoiceBox();
            ChoiceBox cbVessel2 = new ChoiceBox();
            Button butPour = new Button("Перелить");
            cbVessel1.setItems (FXCollections.observableArrayList ("Чашка", "Бутылка", new Separator(), "Чайник"));
            cbVessel2.setItems (FXCollections.observableArrayList ("Чашка", "Бутылка", new Separator(), "Чайник"));
            root.add(labPour1,0,0);
            root.add(cbVessel1,1,0);
            root.add(labPour2,2,0);
            root.add(cbVessel2,3,0);
            root.add(butPour,1,1);

            butPour.setOnAction(new EventHandler<ActionEvent>() {               //конечная кнопка перелить
                @Override
                public void handle(ActionEvent actionEvent) {
                    if( man.getVessel((String)cbVessel1.getValue()).liquid !=null && man.getVessel((String)cbVessel2.getValue()).liquid == null)
                        man.pourLiquid((String)cbVessel1.getValue(), (String)cbVessel2.getValue());
                    perezepisVessels( man, vessels, vessel);
                }
            });
            }
        });
        butToPour.setOnAction(new EventHandler<ActionEvent>() {             //кнопка для налития жидкости
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage newWindow = new Stage();
                GridPane root = new GridPane();
                root.setHgap(10);
                root.setVgap(10);
                //root.setGridLinesVisible(true);
                root.setAlignment(Pos.CENTER);
                newWindow.setTitle("Laba3");
                newWindow.setScene(new Scene(root, 300, 200));
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(primaryStage);
                newWindow.show();
                Label labPour1 = new Label("Налить");
                Label labPour2 = new Label("в");
                ChoiceBox cbVessel2 = new ChoiceBox();
                ChoiceBox cbLiquid = new ChoiceBox();
                Button butToPour = new Button("Налить");
                cbVessel2.setItems (FXCollections.observableArrayList ("Чашка", "Бутылка", new Separator(), "Чайник"));
                cbLiquid.setItems (FXCollections.observableArrayList ());
                for(Liquid a: man.getLiquids())
                cbLiquid.getItems().add(a.name);
                root.add(labPour1,0,0);
                root.add(cbLiquid,1,0);
                root.add(labPour2,2,0);
                root.add(cbVessel2,3,0);
                root.add(butToPour,1,1);
                butToPour.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        man.toPourLiquid((String)cbVessel2.getValue(),(String)cbLiquid.getValue());
                        perezepisVessels( man, vessels, vessel);
                    }
                });
            }
        });
    }

    public void perezepisVessels(Man man,  ListView<String> vessels,ObservableList<String> vessel){          //метод для обновления списка сосудов в форме
        vessels.getItems().clear();
        for(AbstractVessel a: man.getVessels()) {
            String liquidName = "";
            if(a.liquid != null)
                liquidName = a.liquid.name;
            vessel.add(a.name+"("+liquidName+")");
        }
    }

    public static void main(String[] args) {
        launch(args);

    }
}
