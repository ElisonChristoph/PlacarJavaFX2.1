/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxPrincipal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxBasquetebol.JavaFXBasquetebol;
import javafxSet.FXMLSetDadosController;
import javafxSet.JavaFXSetDados;
import javafxVoleibol.JavaFXVoleibol;

/**
 *
 * @author Elison
 */
public class FXMLPrincipalController implements Initializable {

    @FXML
    private Button bFutebol;

    @FXML
    private Button bFutsal;

    @FXML
    private Button bBasquetebol;

    @FXML
    private Button bVoleibol;

    @FXML
    private Button bHandebol;

    @FXML
    private Button bSair;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bFutebol.setOnMouseClicked((MouseEvent e) -> {
            JavaFXSetDados jsd = new JavaFXSetDados();
            try {

                jsd.setEsporte(1);
                jsd.start(new Stage());

            } catch (IOException ex) {
                Logger.getLogger(FXMLSetDadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        bFutebol.setOnKeyPressed((KeyEvent k) -> {
            if (k.getCode() == KeyCode.ENTER) {
                JavaFXSetDados jsd = new JavaFXSetDados();
                try {
                    jsd.setEsporte(1);
                    jsd.start(new Stage());

                } catch (IOException ex) {
                    Logger.getLogger(FXMLSetDadosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        
        bBasquetebol.setOnMouseClicked((MouseEvent e) -> {
            JavaFXBasquetebol b = new JavaFXBasquetebol();
            
            try {
                b.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        bBasquetebol.setOnKeyPressed((KeyEvent k) -> {
            if (k.getCode() == KeyCode.ENTER) {
                JavaFXBasquetebol b = new JavaFXBasquetebol();
                try {
                    b.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        bVoleibol.setOnMouseClicked((MouseEvent e) -> {
            JavaFXVoleibol v = new JavaFXVoleibol();
            try {
                v.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        bVoleibol.setOnKeyPressed((KeyEvent k) -> {
            if (k.getCode() == KeyCode.ENTER) {
                JavaFXVoleibol v = new JavaFXVoleibol();
                try {
                    v.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        bSair.setOnMouseClicked((MouseEvent e) -> {
            fecha();
        });

    }

    public void fecha() {
        JavaFXPrincipal.getStage().close();
    }

}
