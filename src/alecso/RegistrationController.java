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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

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

    private static double sum= 0;
    private static double progress1 = 0;
    private static double progress2 = 0;
    private static double progress3 = 0;
    private static double progress4 = 0;
    private static double progress5 = 0;
     private static double progress6 = 0;
    @FXML
    private TextField txtpseudo;
    @FXML
    private Button btnSave1;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl4;
    @FXML
    private Label lbl5;
    private static int verif=0;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateProgress();
        comboUserType.getItems().addAll("Etudiant","Parent","Enseignant");  
        
        
       
    }    
    
  


    public void updateProgress()
    {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);


        //progressPersonal.setProgress(0.00);
     double    sum_progress = progress1 + progress2 + progress3 + progress4 + progress5+ progress6  ;
        
             txtpseudo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress6 = 0.16;

                } else {
                    progress6 = 0.0;

                }

                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5+ progress6 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
             });

        txtnom.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress1 = 0.16;

                } else {
                    progress1 = 0.0;

                }

                sum = ( progress1 + progress2 + progress3 + progress4 + progress5 + progress6);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }

            
        });

        txtemail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress2 = 0.16;

                } else {
                    progress2 = 0.0;

                }
                sum = (progress1 + progress2 + progress3 + progress4 + progress5+ progress6 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

        txtprenom.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress3 = 0.16;

                } else {
                    progress3 = 0.0;

                }
                double sum = (progress1 + progress2 + progress3 + progress4 + progress5 + progress6);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });





        // pwd
        txtpwd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress4 = 0.16;

                } else {
                    progress4 = 0.0;

                }
                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 + progress6);
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
            double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 + progress6);
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
                 System.out.println(sum);
               if(sum==0.64){
                  
             
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
        window.show();   }
               else{  JOptionPane.showMessageDialog(null, "Veuiller remplir tous les champ");}

    }

    @FXML
    private void controle_mail(KeyEvent event) {
        String pattern = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    String mdp = txtemail.getText();
    
     if((txtemail.getText().equals("")))
            {lbl4.setText("*Champ obligatoire");
            lbl4.setTextFill(Color.web("#b52727  "));
            
            }
     else if(!mdp.matches(pattern))
        {
            lbl4.setText("Email invalide!");
            
            lbl4.setTextFill(Color.web("#b52727 "));
        }
        
        else
        {
            verif=verif+1;
         
         lbl4.setText("");
       }
        
   
    }
     @FXML
    private void controle_username(KeyEvent event) throws SQLException {       
    String u=txtpseudo.getText();
    CrudUser crud=new CrudUser();
    boolean c=crud.checkUser(u.toString());
        System.out.println(c);
    
    
   
    if((txtpseudo.getText().equals("")))
            {lbl1.setText("*Champs obligatoire");
              
           
         
            }
     else if(c==true)                 
        {lbl1.setText("*username existant");
  
        
        
         }
    else                  
        {lbl1.setText("");
        verif=verif+1;
        
         }
    }
    
    @FXML
    private void controle_nom(KeyEvent event) {
        String pattern="^[A-Za-z]*$";
          String u=txtnom.getText();
   
    if((u.equals("")))
            {lbl2.setText("*Champs obligatoire!");
            
           
            }
    else if(!u.matches(pattern))
      {lbl2.setText("*Seulement des lettres ");
           
      }
  
    else  if(u.length()>10) {lbl2.setText("*max 10 lettres");
            
      }
  
        else{ lbl1.setText("");
        verif=verif+1;
       
        }
    }
    
       @FXML
    private void controle_prenom(KeyEvent event) {
          String u=txtprenom.getText();
          String pattern = "^[a-zA-Z]*$";
   
    if((u.equals("")))
            {lbl3.setText("*Champs obligatoire!");
            lbl3.setTextFill(Color.web("#C70039  "));
           
            }
     else if(!u.matches(pattern))
      {lbl3.setText("*Seulement des lettres");
            lbl3.setTextFill(Color.web("#b52727  "));
     }
  
     else if(u.length()>10) {lbl3.setText("*max 10 lettres");
            lbl3.setTextFill(Color.web("#b52727  "));
     }
        else{ lbl3.setText("");
         verif=verif+1;
        
        }
    }
        
   @FXML
    private void controle_mp(KeyEvent event) {
       
    String mdp = txtemail.getText();
    
     if((txtpwd.getText().equals("")))
            {lbl5.setText(" *Champ Obligatoire");
            lbl5.setTextFill(Color.web("#b52727  "));
           
            }
     else{ lbl4.setText("");
      verif=verif+1;
       
     }  
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
