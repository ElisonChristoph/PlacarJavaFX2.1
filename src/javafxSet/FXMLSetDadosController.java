/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxSet;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxFutebol.FXMLFutebolController;
import javafxFutebol.JavaFXFutebol;
import javafxPrincipal.FXMLPrincipalController;

/**
 * FXML Controller class
 *
 * @author Elison
 */
public class FXMLSetDadosController implements Initializable {

    @FXML
    private Button bDireito;
    @FXML
    private Button bEsquerdo;
    @FXML
    private Button bVoltar;
    @FXML
    private Button bContinuar;
    @FXML
    private ImageView ivDireito;
    @FXML
    private ImageView ivEsquerdo;
    @FXML
    private TextField tfTimeEsquerda;
    @FXML
    private TextField tfTimeDireita;
    private int esporte;
    protected static StringProperty timea = new SimpleStringProperty();
    protected static StringProperty timeb = new SimpleStringProperty();
    private static FXMLPrincipalController pc;
    
    @FXML
    private void ContinuarButtonAction(Event e) {
        timea.setValue(tfTimeEsquerda.getText());
        timeb.setValue(tfTimeDireita.getText());

    }

    public String retornaTimeA() {

        return timea.get();

    }

    public String retornaTimeB() {

        return timeb.get();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bContinuar.setOnMouseClicked((MouseEvent e) -> {
            ContinuarButtonAction(e);
            //timea.setValue(tfTimeDireita.getText());

            if (esporte == 0) {
                //timeA = tfDireito.getText();

                JavaFXFutebol fb = new JavaFXFutebol();
                try {
                    fb.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(FXMLFutebolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        

    }

}
