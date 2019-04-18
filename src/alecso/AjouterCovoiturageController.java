/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Covoiturage;
import alecso.Entity.fos_user;
import alecsoServices.CovoiturageDao;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AjouterCovoiturageController implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private Button btna;

    @FXML
    private TextField de;

    @FXML
    private TextField vers;

    @FXML
    private DatePicker picker;

    @FXML
    private TextField escal;

    @FXML
    private TextField nbp;

    @FXML
    private TextField intere;

    @FXML
    private ImageView backk;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nbp.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    nbp.setText(oldValue);
                }
            }

            
        });
         escal.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?") ) {
                    escal.setText(oldValue);
                }
            }

            
        });
        LocalDate minDate = LocalDate .now();
        picker.setDayCellFactory(d
                -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isBefore(minDate));
            }
        });        // TODO     
        btn.setOnAction(event -> {

            if (de.getText().isEmpty() || vers.getText().isEmpty() || escal.getText().isEmpty() || nbp.getText().isEmpty() || String.valueOf(picker.getValue()) == null||intere.getText().isEmpty()) {

                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText(null);
                alert1.setContentText("veuillez remplir tous les champs ");
                alert1.show();
            } else {
                

                Covoiturage p = new Covoiturage();
                p.setDe(de.getText());
                p.setVers(vers.getText());
                p.setEscall(Integer.parseInt(escal.getText()));
                p.setDateC(String.valueOf(picker.getValue()));
                p.setIntere(intere.getText());
                p.setNbplaces(Integer.parseInt(nbp.getText()));
                p.setUser_id(fos_user.getIdcnct());

                String masque = "^[0-9]*\\.?[0-9]*$";
                Pattern pattern = Pattern.compile(masque);
                Matcher controler = pattern.matcher(nbp.getText());
                System.out.println(controler.matches());
                if (controler.matches()) {
//Ok : la saisie est bonne

                    CovoiturageDao pdao = CovoiturageDao.getInstance();
                    System.out.println(p);

                    pdao.insert(p);

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Personne insérée avec succés!");
                    alert.show();
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("email non valide!");
                    alert.show();
//La c'est pas bon
                }

                de.setText("");
                vers.setText("");
                // picker.setText("");
                escal.setText("");
                nbp.setText("");
                intere.setText("");

                try {//FXMLLoader loader = new FXMLLoader();
                    //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                    Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/AfficherCovoiturage.fxml"));
                    // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                    Scene scene = new Scene(page2);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AccueilAnnotationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btna.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/AfficherCovoiturage.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAnnotationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        backk.setOnMouseClicked(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/AccueilAnnotation.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAnnotationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
