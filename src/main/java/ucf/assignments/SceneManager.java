/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Map<String, Scene> sceneMap;

    public SceneManager() {
        this.sceneMap = new HashMap<>();
    }

    void load() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        InventoryManagerController inventoryManagerController = new InventoryManagerController(itemList, this);
        AddItemController addItemController = new AddItemController(itemList, this);

        addScene(inventoryManagerController, "InventoryManager.fxml", "InventoryManager");
        addScene(addItemController, "AddItem.fxml", "AddItem");
        addScene(null, "Error.fxml", "Error");
    }

    private <Controller> void addScene(Controller controller, String fxmlFile, String sceneName) {
        Parent root;
        FXMLLoader loader;

        // load the scene and set the controller
        loader = new FXMLLoader(getClass().getResource(fxmlFile));
        loader.setController(controller);

        // store the scene into the scene map
        try {
            root = loader.load();
            this.sceneMap.put(sceneName, new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene(String sceneName) {
        return this.sceneMap.get(sceneName);
    }

    public Scene getModifyItemScene(ObservableList<Item> itemList, Item itemToModify) {
        // create a new modify item controller instance and add it to the scene map
        ModifyItemController modifyItemController = new ModifyItemController(itemList, itemToModify, this);
        addScene(modifyItemController, "ModifyItem.fxml", "ModifyItem");

        // return the modify item scene
        return getScene("ModifyItem");
    }
}
