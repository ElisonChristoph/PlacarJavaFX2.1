/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxlogin;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxPrincipal.JavaFXPrincipal;

/**
 *
 * @author Elison
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField tflogin;
    @FXML
    private PasswordField pfsenha;
    @FXML
    private Button bacessar;
    @FXML
    private Button bsair;
    
    //varivel user
    protected static StringProperty user = new SimpleStringProperty();
    //retrona user
    public String retornaUser() {

        return user.get();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bacessar.setOnMouseClicked((MouseEvent e) -> {
            if (tflogin.getText().equals("admin") && pfsenha.getText().equals("admin")) {
                
                //seta valor do user
                user.setValue("admin");
                JavaFXPrincipal p = new JavaFXPrincipal();
                try {
                    p.start(new Stage());
                    
                   //fecha();
                } catch (Exception ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            if (tflogin.getText().equals("cliente") && pfsenha.getText().equals("cliente")) {
                
                //seta valor do user
                user.setValue("cliente");
                JavaFXPrincipal p = new JavaFXPrincipal();
                
                try {
                    p.start(new Stage());
                    
                    //fecha();
                } catch (Exception ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            }else{
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Erro");
//                alert.setHeaderText("Login ou Senha inválidos!");
//                alert.setContentText("Digite novamente seu Login e sua Senha! ");
//                alert.show();
//            }
        });

        bsair.setOnMouseClicked((MouseEvent e) -> {
            fecha();
        });
    }

    public void fecha() {
        JavaFXLogin.getStage().close();
    }
}
