/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Evenement;
import static alecso.EventController.IdpourReserver;
import alecso.Utils.MyBDConnection;
import alecsoServices.EvenementDB;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class ExempleController implements Initializable {
      @FXML
    private TableView<Evenement> Event_tab;

    @FXML
    private AnchorPane container;
    @FXML
    private GridPane gridEvents;
    Image image;
    GridPane item;
    @FXML
    private ImageView UploadImage;
     Button reserver;
     private String imageUpload;
     public GridPane initItem(Evenement ev) {
        GridPane gridItem = new GridPane();
       

       String A = ev.getNom_image();
                A = "C:\\wamp64\\www\\alecso\\" + A;
                File F1 = new File(A);
            Image image1 = new Image(F1.toURI().toString());
          // UploadImage.setImage(image1);
                        // imageUpload = result.getString("nom_image");
                
        
        ImageView imageview1 = new ImageView();
        imageview1.setImage(image1);
        imageview1.setFitWidth(300);
        imageview1.setFitHeight(150);

        Label nom = new Label(ev.getTitre());
        nom.setFont(Font.font("Cambria", 28));
        nom.setMaxWidth(Double.MAX_VALUE);
        nom.setAlignment(Pos.CENTER);
        
        Label nomn = new Label(ev.getDescription());
       nomn.setFont(Font.font("Cambria", 28));
        nomn.setMaxWidth(Double.MAX_VALUE);
        nomn.setAlignment(Pos.CENTER);
        
          Label lieu = new Label(ev.getLieu());
       lieu.setFont(Font.font("Cambria", 28));
        lieu.setMaxWidth(Double.MAX_VALUE);
        lieu.setAlignment(Pos.CENTER);

        Label date = new Label(ev.getDate_event() + " - " );
        date.setFont(Font.font("Cambria", 18));
        date.setMaxWidth(Double.MAX_VALUE);
        date.setAlignment(Pos.CENTER);
        
        Button btn = new Button("reserve" );
      
        btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e)  {
                       ObservableList<Evenement> selectedRows, AllCovs;
       // AllCovs = Event_tab.getItems();

        //this gives us the rows that were selected
               // selectedRows =Event_tab.getSelectionModel().getSelectedItems();

       
              // EvenementDB c=new EvenementDB();
                IdpourReserver = ev.getId();

            
        //loop over the selected rows and remove the Person objects from the table
               EvenementDB  crud= new EvenementDB();
                        try {
                            // crud.Reserver(IdpourReserver);
                            crud.quantiteReduce(IdpourReserver);
                             Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("Information Dialog");
            alert3.setHeaderText("réservation effectuée avec succés");
            Optional <ButtonType> result=alert3.showAndWait();
                            // Event_tab.setItems(crud.afficherEvent());
                        } catch (SQLException ex) {
                            Logger.getLogger(ExempleController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ExempleController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
                        
                    });
                

        
        
        
        btn.setFont(Font.font("Cambria", 18));
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER);
        
      
     // Button btn = new Button("Afficher les employés");
  //btn.addActionListener(new Afficheur());
      

        GridPane.setConstraints(imageview1, 0, 0);
        GridPane.setConstraints(nom, 0, 1);
        GridPane.setConstraints(nomn, 0, 2);
        GridPane.setConstraints(lieu, 0, 3);
        GridPane.setConstraints(date, 0, 4);
        GridPane.setConstraints(btn, 0, 5);
     

        gridItem.getChildren().addAll(imageview1, nom,nomn,lieu, date,btn);

        gridItem.setPrefWidth(400);
        gridItem.setPrefHeight(700);
        gridItem.setCenterShape(true);
      /*  gridItem.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/AfficherDetailsEvent.fxml"));
                    Parent tableViewParent = loader.load();
                    Scene scene = new Scene(tableViewParent);

                    AfficherDetailsEventController controller = loader.getController();
                    controller.initData(ev);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AccueilEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

      */
      
      
      
      
      
      
      
      
      
      
      
      
        return gridItem;
    }
     
     


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO container.getChildren().add(new MenuComponentEventController());

         EvenementDB  crud = new  EvenementDB  ();
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        list = (ObservableList<Evenement>) crud.afficherEvent();

        int k = -1;
        int n;
        Evenement ev;
        for (int i = 0; i <= 1; i++) {
            n = list.size() % 2 == 0 ? (list.size() / 2) - 1 : list.size() / 2;
            for (int j = 0; j <= n; j++) {

                k++;
                if (k < list.size()) {
                    ev = list.get(k);

                    item = initItem(ev);

                    GridPane.setConstraints(item, i, j);
                    gridEvents.getChildren().add(item);
                }

            }
        }

    } 
     public void Afficheur(ActionEvent event) throws SQLException, IOException {
           ObservableList<Evenement> selectedRows, AllCovs;
       // AllCovs = Event_tab.getItems();

        //this gives us the rows that were selected
                selectedRows =Event_tab.getSelectionModel().getSelectedItems();

        for ( Evenement c : selectedRows) {

            try {
                IdpourReserver = c.getId();

            } catch (NumberFormatException e) {

            }

        }

        //loop over the selected rows and remove the Person objects from the table
               EvenementDB  crud= new EvenementDB();
      // crud.Reserver(IdpourReserver);
        crud.quantiteReduce(IdpourReserver);
       // Event_tab.setItems(crud.afficherEvent());
    }

    
    
    
     
    
    }
    

