/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.fos_user;
import alecsoServices.CrudUser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class RegistrationController implements Initializable {

    @FXML
    private ProgressBar progressPersonal;
    @FXML
    private Label lblComplete;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtpwd;
    @FXML
    private TextField txtprenom;
    @FXML
    private ComboBox<String> comboUserType;
    @FXML
    private Button acc;

    
    private static double progress1 = 0;
    private static double progress2 = 0;
    private static double progress3 = 0;
    private static double progress4 = 0;
    private static double progress5 = 0;
    @FXML
    private TextField txtpseudo;
    @FXML
    private Button btnSave1;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateProgress();
        comboUserType.getItems().addAll("Etudiant","Parent","Enseignant","Partenaire");      
    }    


    public void updateProgress()
    {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        //progressPersonal.setProgress(0.00);
        double sum_progress = progress1 + progress2 + progress3 + progress4 + progress5  ;

        txtnom.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress1 = 0.2;

                } else {
                    progress1 = 0.0;

                }

                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }

            
        });

        txtemail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress2 = 0.2;

                } else {
                    progress2 = 0.0;

                }
                double sum = (progress1 + progress2 + progress3 + progress4 + progress5 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

        txtprenom.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress3 = 0.2;

                } else {
                    progress3 = 0.0;

                }
                double sum = (progress1 + progress2 + progress3 + progress4 + progress5 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });





        // pwd
        txtpwd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress4 = 0.2;

                } else {
                    progress4 = 0.0;

                }
                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

 


        comboUserType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue,
                    String newValue) {
              

                        progress5 = 0.2;
            double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
    }

@FXML
    private void Acceuil(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Acceuil1.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    
    @FXML
    public void saveUser(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
               String role;
        switch (comboUserType.getValue()) {
            case "Admin":
                role=":{i:0;s:10:\"ROLE_ADMIN\";}" ;
                break;
            case "Etudiant":
                role="a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}" ;
                break;
                
            case "Partenaire":
                role="a:1:{i:0;s:15:\"ROLE_PARTENAIRE\";}" ;
                break;
            default:
                role=":{i:0;s:9:\"ROLE_PROF\";}" ;
                break;
        }

        CrudUser cu =new CrudUser();
        fos_user user2 =new fos_user(txtpseudo.getText(),txtemail.getText(),txtpwd.getText(),role,txtnom.getText(),txtprenom.getText());
        cu.register(user2);
        JOptionPane.showMessageDialog(null, "Felicitations! Utilisateur ajouté");
        Parent root = FXMLLoader.load(getClass().getResource("Acceuil1.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();   

    }

   
    @FXML
    private void clearFields(ActionEvent event) {
        txtnom.setText(null);
        txtprenom.setText(null);
        txtemail.setText(null);
       
        txtpseudo.setText(null);
        
        txtpwd.setText(null);
        
        comboUserType.setValue(null);
  
        progressPersonal.setProgress(0.0);
        lblComplete.setText("0% complete");

    }

  

    
    

    
 
    
}