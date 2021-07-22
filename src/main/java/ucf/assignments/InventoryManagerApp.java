 /*
  * UCF COP3330 Summer 2021 Assignment 5 Solution
  * Copyright 2021 Ryan Turner
  */

 package ucf.assignments;

 import javafx.application.Application;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.stage.Stage;

 import java.io.IOException;
 import java.util.Objects;

 public class InventoryManagerApp extends Application {
     public static void main(String[] args) {
         launch(args);
     }

     @Override
     public void start(Stage primaryStage) {
         try {
             Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryManager.fxml")));
             Scene scene = new Scene(root);

             primaryStage.setTitle("Inventory Manager");
             primaryStage.setScene(scene);
             primaryStage.show();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 }
