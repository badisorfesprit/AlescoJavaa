/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Profil_DocumentsController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import alecso.Entity.Documents;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Baz enna
 */
public class DivDocumentsController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private Pane sq;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label id;
    @FXML
    private Rectangle rectangle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void LoadValues(Documents e) throws IOException {

        nom.setText(e.getNom());

        //   etoile.setRating(Double.valueOf(e.getEtoile()));
        prix.setText(String.valueOf(e.getClasse()) + " DT");
        id.setText(String.valueOf(e.getMatiere()));


        Image imageURI2 = new Image("file:C://wamp64/www/uploads/" + e.getNomfich());
        rectangle.setFill(new ImagePattern(imageURI2));

        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    doubleclick(event, e);
                }

            }
        });

    }

    public void doubleclick(MouseEvent event, Documents selectedetab) {
        if (event.getClickCount() == 2) {
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil_Document.fxml"));
                Parent root = loader.load();
                Profil_DocumentsController DDC = loader.getController();
                DDC.LoadValues(selectedetab);
                /*
                FXMLLoader loade = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                Parent roo = loade.load();
                HomePageController Dhp = loade.getController();*/
                Stage ss = new Stage();
                Scene sc = new Scene(root);
                ss.setScene(sc);
                

                ss.show();

            } catch (IOException ex) {
                Logger.getLogger(DivDocumentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }

}
