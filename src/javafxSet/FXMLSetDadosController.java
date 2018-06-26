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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafxBasquetebol.JavaFXBasquetebol;
import javafxFutebol.FXMLFutebolController;
import javafxFutebol.JavaFXFutebol;
import javafxPrincipal.FXMLPrincipalController;
import javafxVoleibol.JavaFXVoleibol;

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
    @FXML
    private Text lbasquete;
    @FXML
    private RadioButton bbrasileiro;
    @FXML
    private RadioButton bamericano;
    private static FXMLPrincipalController pc;
    
    protected static BooleanProperty v = new SimpleBooleanProperty();
    
    public void esporte(String esp) {
        v.setValue(false);
        if (esp.contains("basquete")) {

            v.setValue(true);
    
        }
        //seta visibilidade dos botoes
        lbasquete.visibleProperty().bindBidirectional(v);
        bbrasileiro.visibleProperty().bindBidirectional(v);
        bamericano.visibleProperty().bindBidirectional(v);
    }
    
    protected static StringProperty timea = new SimpleStringProperty();
    protected static StringProperty timeb = new SimpleStringProperty();
    protected static StringProperty tipoBasquete = new SimpleStringProperty();
    
    @FXML
    private void ContinuarButtonAction(Event e) {
        timea.setValue(tfTimeEsquerda.getText());
        timeb.setValue(tfTimeDireita.getText());

    }
    public boolean stopc = false;
    public void Padrao() {
        Task t = new Task() {

            @Override
            protected Object call() throws Exception {
                
                while (stopc == false) {
    
       if(bbrasileiro.selectedProperty().get() == true){
           bamericano.selectedProperty().setValue(false);
           tipoBasquete.setValue("FIBA");
       }
       if(bamericano.selectedProperty().get() == true){
           bbrasileiro.selectedProperty().setValue(false);
           tipoBasquete.setValue("NBA");
       }
                }
    return null;
            }
        };
        new Thread(t).start();

    }

    public String retornaTimeA() {

        return timea.get();

    }

    public String retornaTimeB() {

        return timeb.get();

    }
    
    public String retornatipoBasquete(){
        return tipoBasquete.get();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pc = new FXMLPrincipalController();
        
        try {
            esporte(pc.retornaEsporte());
        } catch (Exception ex) {
            System.out.println("ex");
        }

        Padrao();
        
        bContinuar.setOnMouseClicked((MouseEvent e) -> {
            ContinuarButtonAction(e);
            
            //se o esporte selecioando na janela anterior por futebol
            //abre o placar futebol
            if (pc.retornaEsporte().equals("futebol")) {

                JavaFXFutebol f = new JavaFXFutebol();
                try {
                    f.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(FXMLFutebolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //se o esporte selecioando na janela anterior por basquetebol
            //abre o placar basquetebol
            if (pc.retornaEsporte().equals("basquete")) {

                JavaFXBasquetebol b = new JavaFXBasquetebol();
                try {
                    b.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(FXMLFutebolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //se o esporte selecioando na janela anterior por voleibol
            //abre o placar voleibol
            if (pc.retornaEsporte().equals("volei")) {

                JavaFXVoleibol v = new JavaFXVoleibol();
                try {
                    v.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(FXMLFutebolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        

    }

}
