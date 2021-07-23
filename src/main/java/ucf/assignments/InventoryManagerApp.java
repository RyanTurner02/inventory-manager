 /*
  * UCF COP3330 Summer 2021 Assignment 5 Solution
  * Copyright 2021 Ryan Turner
  */

 package ucf.assignments;

 import javafx.application.Application;
 import javafx.scene.Scene;
 import javafx.stage.Stage;

 public class InventoryManagerApp extends Application {
     public static void main(String[] args) {
         launch(args);
     }

     @Override
     public void start(Stage primaryStage) {
         SceneManager sceneManager = new SceneManager();
         sceneManager.load();

         Scene scene = sceneManager.getScene("InventoryManager");

         primaryStage.setTitle("Inventory Manager");
         primaryStage.setScene(scene);
         primaryStage.show();
     }
 }
