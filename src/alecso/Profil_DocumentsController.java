/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Documents;
import alecso.Entity.comment;
import alecsoServices.*;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Baz enna
 */
public class Profil_DocumentsController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label dispo;
    @FXML
    private Label prix;
    @FXML
    private Rectangle rectangle;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button acheter;
    @FXML
    private Label id_stripe_plante;
    @FXML
    private ListView<comment> listview;
    @FXML
    private JFXButton retour;
    @FXML
    private Button delete;
    @FXML
    private Button edit;

    /**
     * Initializes the controller class.
     */
    void afficher_comment()
    {
         Service_comment ps = new Service_comment();
        Service_Documents sdd = new Service_Documents();
        ArrayList list;
        
        System.out.println(nom.getText());
        String s =String.valueOf(sdd.getelementt(nom.getText()));
        System.out.println(s);
        list = (ArrayList) ps.getshows(s);
        ObservableList<comment> obslist =FXCollections.observableArrayList(list);
        listview.setItems(obslist);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficher_comment();
      delete.setOnAction(e->{
          Service_comment sc = new Service_comment();
          sc.Delete(listview.getSelectionModel().getSelectedItem());
          afficher_comment();
      });
        edit.setOnAction(e->{
            System.out.println(listview.getSelectionModel().getSelectedItem().getId());
            TextInputDialog dialog = new TextInputDialog(listview.getSelectionModel().getSelectedItem().getText());
            dialog.setTitle("Text Input Dialog");
            dialog.setHeaderText("Look, a Text Input Dialog");
            dialog.setContentText("comment:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                Date d = new Date(2019);
                String text;
                text = dialog.getResult();
                Service_Documents sd = new Service_Documents();
                System.out.println(nom.getText());
                int id_d = sd.getelementt(nom.getText());
                System.out.println(id_d);
                afficher_comment();
                Service_comment sc = new Service_comment();
                int s;
                
                comment C = new comment();
                C= listview.getSelectionModel().getSelectedItem();
                
            comment c = new comment(C.getId(), id_d, text, d);

                sc.Update(c);
            }
        });
        acheter.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Text Input Dialog");
            dialog.setHeaderText("Look, a Text Input Dialog");
            dialog.setContentText("comment:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                Date d = new Date(2019);
                String text;
                text = dialog.getResult();
                Service_Documents sd = new Service_Documents();
                System.out.println(nom.getText());
                int id_d = sd.getelementt(nom.getText());
                System.out.println(id_d);
                afficher_comment();
                Service_comment sc = new Service_comment();
            comment c = new comment(0, id_d, text, d);

                sc.insert(c);
            }
            if (result.isPresent()) {
                System.out.println("Your name: " + result.get());
            }

        });

    }

    public void LoadValues(Documents e) throws IOException {
        Service_Documents sp = new Service_Documents();
        nom.setText(e.getNom());
        prix.setText(String.valueOf(e.getClasse()));
        dispo.setText(e.getCorrige());

        Image imageURI2 = new Image("file:C://wamp64/www/uploads/" + e.getNomfich());
        rectangle.setFill(new ImagePattern(imageURI2));

    }

    public Profil_DocumentsController() {

    }

}
