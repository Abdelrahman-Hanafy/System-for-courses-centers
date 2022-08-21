package com.example.systemforroboticsacademy;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class CantineController extends StageController{

    @FXML
    ListView<String> Food;

    @FXML
    ListView<String> Drink;

    ObservableList<String> typs = FXCollections.observableArrayList("Food","Drink");

    Cantine cnt;

    @FXML
    public void initialize() throws SQLException {
        cnt = Cantine.CreateCantine();

        Food.getItems().add("Nothing");
        Drink.getItems().add("Nothing");

        for(CantineItem i:cnt.items){
            UpdateList(i);
        }

        types.setItems(typs);

    }

    private void UpdateList(CantineItem i){
        if(i.getType().equals("Food")){
            Food.getItems().add(i.getID());
        }else if(i.getType().equals("Drink")){
            Drink.getItems().add(i.getID());
        }
    }

    private String getSlectedItemPrice() throws SQLException {
        CantineItem i;
        int ans =0;

        String[] ids = {Food.getFocusModel().getFocusedItem(),
                        Drink.getFocusModel().getFocusedItem()};

        for (String id:ids) {
            i = cnt.getInstance(id);
            if(i != null) {
                ans += i.buy();
            }
        }

        return ids[0]+ " + "+ ids[1] + " = " + ans;
    }

    @FXML
    void onBuyClick() throws SQLException {
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setHeaderText("Amount to Pay");
        al.setContentText(getSlectedItemPrice());
        al.showAndWait();
    }

    @FXML
    TextField id;

    @FXML
    TextField price;

    @FXML
    ComboBox<String> types;

    @FXML
    TextField qnt;


    @FXML
    void onAddClick() throws SQLException {
        DataBase db = DataBase.CreateDataBase();
        CantineItem ci = new CantineItem(id.getText(),types.getSelectionModel().getSelectedItem(),
                                        Integer.parseInt(price.getText()), Integer.parseInt(qnt.getText()));
        cnt.Updateitems(ci);
        db.Add(ci,"cantine");
        UpdateList(ci);
    }
}
