/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LINA
 */
public class Alecso extends Application {
    
    @Override
       public void start(Stage primaryStage) throws IOException, SQLException {
         Parent login =FXMLLoader.load(getClass().getResource("Acceuil1.fxml"));
                
                
                Scene scene = new Scene(login);
               System.out.println("0");
        
        primaryStage.setTitle("Authentification");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
