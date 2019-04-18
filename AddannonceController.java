/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.CategorieC;
import alecso.Entity.Club;
import alecso.Entity.fos_user;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudAnnonce;
import alecsoServices.CrudCatC;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class AddannonceController implements Initializable {

    @FXML
    private Button addannonce;
    @FXML
    private TextField nom;
    @FXML
    private TextArea desc;
    @FXML
    private TextField adresse;
    @FXML
    private DatePicker delai;
    @FXML
    private TextField nbplaces;
    @FXML
    private TextField tel;
    @FXML
    private Button btnimg;
    @FXML
    private ComboBox<String> cbxcat;
    @FXML
    private ComboBox<String> cbxvilles;
    private static File selectedfile;
    
    private static Path destination;
    @FXML
    private ImageView UploadImage;
    private String imageUpload;
    @FXML
    private ComboBox<Integer> prolongation;
    @FXML
    private CheckBox supression;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        prolongation.setValue(-1);
        cbxvilles.getItems().add("Ariana");cbxvilles.getItems().add("Beja"); cbxvilles.getItems().add("Ben Arous");cbxvilles.getItems().add("Bizerte");  cbxvilles.getItems().add("Gabes"); 
        cbxvilles.getItems().add("Gafsa"); cbxvilles.getItems().add("Jendouba"); cbxvilles.getItems().add("Kairouan"); cbxvilles.getItems().add("Kasserine");  cbxvilles.getItems().add("Kebili");
        cbxvilles.getItems().add("Le Kef");cbxvilles.getItems().add("Mahdia"); cbxvilles.getItems().add("Manouba");cbxvilles.getItems().add("Medenine");  cbxvilles.getItems().add("Monastir"); 
        cbxvilles.getItems().add("Nabeul"); cbxvilles.getItems().add("Sfax"); cbxvilles.getItems().add("Sidi Bouzid"); cbxvilles.getItems().add("Siliana");  cbxvilles.getItems().add("Sousse");
        cbxvilles.getItems().add("Tataouine"); cbxvilles.getItems().add("Tozeur"); cbxvilles.getItems().add("Tunis"); cbxvilles.getItems().add("Zagouan");
        delai.setDayCellFactory(d
                    -> new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisable(item.isBefore(LocalDate.now()));
                }
            });
         prolongation.getItems().addAll(1,2,3,5,4,6,1,2,3,5,4,6,1,2,3,5,4,6);
         
        //numeric(tel);
        numeric(nbplaces);
        tel.addEventHandler(KeyEvent.KEY_TYPED, numeric_Validation(8));
        
        
          
         try {
            CrudCatC sr =new CrudCatC();
            String requete="select * from categorie_c";
            ObservableList<String> list = FXCollections.observableArrayList();
            Connection con= MyBDConnection.getInstanceBD().getConnection();
            Statement ste = con.createStatement();
            ResultSet rs;
            rs=ste.executeQuery(requete);
            while(rs.next()){
                list.add(rs.getString("libelle"));
                cbxcat.setItems(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddannonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void addannonce(ActionEvent event) throws IOException {

         if(nom.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez le nom de votre annonce!");}
         else if(desc.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez la description de votre annonce!");}
         else if(adresse.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez l'adresse de votre annonce!");}
        else if(nbplaces.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez le nombre de place de votre annonce!");}
     
        else if(cbxcat.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez la categorie de votre annonce!");}
        else if(cbxvilles.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez la ville de votre annonce!");}
        else if(tel.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez votre telephone!");}
        else if(imageUpload==null){
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider vos champs");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez choisir une image ");
            alert1.showAndWait();
         
         } 
         
         else {
                     int id = fos_user.getIdcnct();
         //fos_user p = as.GetPersByName(a.username_text);
         
        int place = Integer.parseInt(nbplaces.getText());
        File outputFile = new File("C:\\Users\\Nawres\\Desktop\\pialecso3a23-master\\src\\" + imageUpload);
        BufferedImage bImage = SwingFXUtils.fromFXImage(UploadImage.getImage(), null);
        try {
      ImageIO.write(bImage, "png", outputFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
        
        
        CrudCatC cat = new CrudCatC();
         CategorieC cc = cat.GetcatByName(cbxcat.getValue().toString());
        
        java.sql.Date datedelai = java.sql.Date.valueOf(delai.getValue());
        if(prolongation.isPressed()) 
        { Club c = new Club(id,nom.getText(),desc.getText(),imageUpload,adresse.getText(),cbxvilles.getValue(),tel.getText(),datedelai,place,cc,prolongation.getValue());
         
       
        CrudAnnonce service =new CrudAnnonce(); 
            service.AddAnnonce(c);
           JOptionPane.showMessageDialog(null, "Annonce ajoutée avec succées");
            
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("annoncesadmin.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }

        else {      Club c = new Club(id,nom.getText(),desc.getText(),imageUpload,adresse.getText(),cbxvilles.getValue(),tel.getText(),datedelai,place,cc,prolongation.getValue());
        
        
        CrudAnnonce service =new CrudAnnonce(); 
            service.AddAnnonce(c);
           JOptionPane.showMessageDialog(null, "Annonce ajoutée avec succées");
            
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("annoncesadmin.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
         }
         
        
        
        
        
        
    }

    @FXML
    private void choisirimg(ActionEvent event) throws IOException {
         FileChooser fc = new FileChooser();
           FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.jpg");
           FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.png");
           fc.getExtensionFilters().addAll(ext1,ext2);
           Stage stage = new Stage();
           File file = fc.showOpenDialog(stage);
           BufferedImage bf;
           if( file != null){
               bf = ImageIO.read(file);
           javafx.scene.image.Image image = SwingFXUtils.toFXImage(bf, null);
           UploadImage.setImage(image);
           FileInputStream fin = new FileInputStream(file);
           imageUpload = file.getName();
           }
    }
    private void numeric(final TextField tf) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }

        });
    }
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("([1-9]|[0-9])")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }
        };
    }
     public EventHandler<KeyEvent> libelle() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                
                if (e.getCharacter().matches("([1-9]|[0-9])")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } 
                } else {
                    e.consume();
                }
            }
        };
    }
    
}

