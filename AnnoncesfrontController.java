/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Club;
import alecsoServices.CrudAnnonce;
import alecsoServices.inscetudiantCrud;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class AnnoncesfrontController implements Initializable {

    @FXML
    private Text labelUser;
    @FXML
    private VBox annonces;
    @FXML
    private ImageView backmenu;
    @FXML
    private Button myinsc;
    public static int Idpourajouter,placeamodifier,nbinscriptions;
    public static String etatamodifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudAnnonce fs = new CrudAnnonce();
        List<Club> us = fs.Displayadmin(); // id user seesion!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Iterator iterator = us.iterator();
        HBox hbu;
        
        //***********************
        for (int i = 0; i < us.size(); i++) {
            hbu = new HBox(10);
            hbu.setSpacing(10); // espace horizontal entre deux images
            for (int j = 0; j < 3; j++) {
                if (iterator.hasNext()) {
                    hbu.getChildren().add(createVBAnnonce((Club) iterator.next()));
                }
            }
            annonces.getChildren().add(hbu);
            
        }
        notif();
    }  
    public VBox createVBAnnonce(Club f){
        
       
        CrudAnnonce fs = new CrudAnnonce();
        VBox vbFriends = new VBox();
        HBox hbBoutons = new HBox();
        vbFriends.setStyle("background-color : #fff; padding-right: 0px");
        vbFriends.setSpacing(10);
         String A = f.getAffiche();
                A = "C:/Users/Nawres/Desktop/pialecso3a23-master/src/" + A;
            File F1 = new File(A);
            Image image1 = new Image(F1.toURI().toString());
             
        ImageView img = new ImageView(image1); //f.getPicturePath()
        //http://localhost/pi/image/f.getPicturePath()
        img.setPreserveRatio(false);
        //img.setX(100);
        //img.setY(100);
        img.setFitHeight(100);
        img.setFitWidth(100);
        Label nomJ = new Label(f.getNom());
        nomJ.setFont(Font.font("Amble CN", FontWeight.BOLD, 15));
        Label description = new Label(f.getVille());
        description.setFont(Font.font("Amble CN", FontWeight.BOLD, 10));
        Label categorie = new Label(f.getLibelle());
        categorie.setFont(Font.font("Amble CN", FontWeight.BOLD, 10));
        
        
        Label etatencours = new Label("en cours");
        etatencours.setFont(Font.font("Amble CN", FontWeight.BOLD, 10));
        Label etatcomplet = new Label("complet");
        etatcomplet.setFont(Font.font("Amble CN", FontWeight.BOLD, 10));

        //  Label nomJ1 = new Label(f.getEmail());
        //HBox hbnote = new HBox();
        //FriendsService js = new FriendsService();
        //double rate=js.getNoteJardinier(j);
        //ArrayList<FontAwesomeIconView> note = new ArrayList<>();
        //FontAwesomeIconView fullstar =new FontAwesomeIconView(FontAwesomeIcon.STAR_HALF_EMPTY);
        //FontAwesomeIconView empstar =new FontAwesomeIconView(FontAwesomeIcon.STAR_HALF_EMPTY);
        //FontAwesomeIconView halfstar =new FontAwesomeIconView(FontAwesomeIcon.STAR_HALF_EMPTY);
        //fullstar.setStyle("color:gold");
        //halfstar.setStyle("color:gold");
        // note.add(new FontAwesomeIconView(FontAwesomeIcon.STAR_HALF_EMPTY));
        //hbnote.getChildren().addAll(note);
        Button details = new Button("Voir plus");
        Button inscenfant = new Button("s'inscrire");
        Button inscparent = new Button("s'inscrire");
        Button insc = new Button("s'inscrire");

        details.setStyle("-fx-background-color: #f0ad4e; -fx-text-fill: #fff; -fx-font-size: 75%;");
        inscenfant.setStyle("-fx-background-color: #f0ad4e; -fx-text-fill: #fff; -fx-font-size: 75%;");
        inscparent.setStyle("-fx-background-color: #f0ad4e; -fx-text-fill: #fff; -fx-font-size: 75%;");
        insc.setStyle("-fx-background-color: #f0ad4e; -fx-text-fill: #fff; -fx-font-size: 75%;");
        // final String receiverMailAdress="";

        insc.setOnAction((event) -> {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("inscetudiant.fxml"));
                Scene newScene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(newScene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(AnnoncesfrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            placeamodifier = f.getPlace();
            Idpourajouter = f.getId();
            nbinscriptions = f.getNbinscriptions();
            etatamodifier = f.getEtat();
            System.out.println(Idpourajouter);
            System.out.println(placeamodifier);

        });

        details.setOnAction((event) -> {

          try {
                Parent root = FXMLLoader.load(getClass().getResource("detailsannonce.fxml"));
                Scene newScene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(newScene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(AnnoncesfrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            placeamodifier = f.getPlace();
            Idpourajouter = f.getId();
            nbinscriptions = f.getNbinscriptions();
            etatamodifier = f.getEtat();
            System.out.println(Idpourajouter);
            System.out.println(placeamodifier);

        });
        //for(int i=0; i<rate%1; i++) note.add(fullstar);
        //if(rate%1!=0) note.add(empstar);
        //for(int i=0; i<rate%1+4; i++) note.add(fullstar);
        vbFriends.getChildren().add(img);
        vbFriends.getChildren().add(nomJ);
        vbFriends.getChildren().add(description);
         vbFriends.getChildren().add(categorie);
          if (f.getPlace()!=0){vbFriends.getChildren().add(etatencours);}
          else {vbFriends.getChildren().add(etatcomplet);}
        hbBoutons.getChildren().add(details);
        hbBoutons.getChildren().add(insc);
        hbBoutons.setSpacing(20);
        vbFriends.getChildren().add(hbBoutons);

        //vbFriends.getChildren().add(hbnote);
        // vbFriends.getChildren().add(delete);
        //vbFriends.getChildren().add(coworker);  pas sur la meme ligne !! 
        return vbFriends;
        
    }
     
     
     


   


    @FXML
    private void backmenu(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void myinsc(ActionEvent event) {
    }
     public void notif()
        {
            
        
        CrudAnnonce i = new CrudAnnonce();
        if(i.Notifdate()!=-1)
        {
        Notifications notificationBuilder=Notifications.create()
                .title("Notification")
                .text("Il y'a "+i.Notifdate()+" annonces qui seront bientot finir")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        
        notificationBuilder.darkStyle();
        notificationBuilder.showWarning();
        
        }
        }

    
}
