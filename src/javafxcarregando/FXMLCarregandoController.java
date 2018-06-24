/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcarregando;

import javafxlogin.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author Elison
 */
public class FXMLCarregandoController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void fecha() {
        JavaFXLogin.getStage().close();
    }
}
