/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.*;
import alecsoServices.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author marwen
 */
public class DocumentFXMLController implements Initializable {

    @FXML
    private ImageView pic1;
    @FXML
    private Button ajouter_image;
    @FXML
    private TextField nom;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXComboBox<String> corrige;
    @FXML
    private JFXComboBox<String> matiere;
    @FXML
    private JFXComboBox<String> section;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> classe;

    /**
     * Initializes the controller class.
     */
    public void combobox_filler() {
        type.getItems().addAll("cours", "serie", "examen");
        corrige.getItems().addAll("avec corrige", "sans corrige");
        matiere.getItems().addAll("math", "physisque", "svt", "info", "arab", "français", "anglais", "philo");
        section.getItems().addAll("math", "svt", "technique", "informatique", "lettre");
        classe.getItems().addAll("7eme","_8eme","9eme","1ere","2eme","3eme","BAC!!!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox_filler();

        ajouter_image.setOnAction(e -> {
            Image img = pic1.getImage();
            try {
                String nameImage1 = saveToFileImageNormal(img);
                Documents ev = new Documents();
               
                ev = new Documents(0, nom.getText(), type.getValue(), corrige.getValue(), matiere.getValue(), classe.getValue(), section.getValue(), Date.valueOf(date.getValue()), nameImage1);
                Service_Documents sev = new Service_Documents();
                sev.insert(ev);

                Parent root;
                root = FXMLLoader.load(getClass().getResource("Afficher.fxml"));
                Stage stage = new Stage();
                stage.close();
                Scene scene = new Scene(root);
                Stage stagee = (Stage) ajouter_image.getScene().getWindow();

                stagee.close();
                stage.setScene(scene);
                stage.show();
            } catch (SQLException ex) {

            } catch (IOException ex) {
                Logger.getLogger(DocumentFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public static String saveToFileImageNormal(Image image) throws SQLException {

        String ext = "jpg";
        File dir = new File("C:\\wamp64\\www\\alecso\\");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    @FXML
    private void addImage(MouseEvent event) throws IOException {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (.PDF)", "*.PDF");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPDF, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            pic1.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
@FXML
    private void retour(ActionEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("LibCatAll.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }  
@FXML
    private void GoToAdminUser(ActionEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("AdminUser.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void GoToProduit(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("AdminProd.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void Annonces(ActionEvent event)  throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("AdminProd.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void CatC(ActionEvent event) throws IOException {
        
 Parent root = FXMLLoader.load(getClass().getResource("affcatc.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();

    }

    @FXML
    private void categoriebtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("categorieBack.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
        
 
    }

    @FXML
    private void bntAnnotation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("backEtudiant.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();}

    @FXML
    private void GoToAffiche(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Affiche.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();}

    @FXML
    private void statbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stat.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    

}
