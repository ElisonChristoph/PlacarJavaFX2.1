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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxSet.FXMLSetDadosController;
import javafxSet.JavaFXSetDados;

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
    
    //varivel esporte
    protected static StringProperty esporte = new SimpleStringProperty();
    //retrona esporte
    public String retornaEsporte() {

        return esporte.get();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bFutebol.setOnMouseClicked((MouseEvent e) -> {
            //seta valor do user
                esporte.setValue("futebol");
            
            JavaFXSetDados jsd = new JavaFXSetDados();
            try {
                jsd.start(new Stage());

            } catch (IOException ex) {
                Logger.getLogger(FXMLSetDadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        bBasquetebol.setOnMouseClicked((MouseEvent e) -> {
            //seta valor do user
                esporte.setValue("basquete");
            
            JavaFXSetDados jsdb = new JavaFXSetDados();
            try {
                jsdb.start(new Stage());

            } catch (IOException ex) {
                Logger.getLogger(FXMLSetDadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        bVoleibol.setOnMouseClicked((MouseEvent e) -> {
            //seta valor do user
                esporte.setValue("volei");
            
            JavaFXSetDados jsdv = new JavaFXSetDados();
            try {
                jsdv.start(new Stage());

            } catch (IOException ex) {
                Logger.getLogger(FXMLSetDadosController.class.getName()).log(Level.SEVERE, null, ex);
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
