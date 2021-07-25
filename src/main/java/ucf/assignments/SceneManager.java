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

        loader = new FXMLLoader(getClass().getResource(fxmlFile));
        loader.setController(controller);

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
        ModifyItemController modifyItemController = new ModifyItemController(itemList, itemToModify, this);
        addScene(modifyItemController, "ModifyItem.fxml", "ModifyItem");
        return getScene("ModifyItem");
    }
}
