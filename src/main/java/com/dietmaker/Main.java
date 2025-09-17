package com.dietmaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main JavaFX Application class for the Diet Maker application
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the main FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Scene scene = new Scene(loader.load(), 900, 700);
            
            // Add CSS styling
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            
            // Set up the primary stage
            primaryStage.setTitle("Diet Maker - Your Personal Diet Planner");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);
            primaryStage.setResizable(true);
            primaryStage.centerOnScreen();
            
            // Show the application
            primaryStage.show();
            
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Application entry point
     */
    public static void main(String[] args) {
        launch(args);
    }
}
