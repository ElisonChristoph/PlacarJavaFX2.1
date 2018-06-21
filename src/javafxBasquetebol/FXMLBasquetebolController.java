/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxBasquetebol;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafxlogin.FXMLLoginController;

/**
 *
 * @author Elison
 */
public class FXMLBasquetebolController implements Initializable {

    //Botões e Labels padrão.
    @FXML
    private AnchorPane apBasquete;
    @FXML
    private MediaView mvBasquete;
    @FXML
    private Label lCronometroB;
    @FXML
    private Label lPeriodo;
    @FXML
    private Label l24seg;
    @FXML
    private Label lTimeA;
    @FXML
    private Label lTimeB;
    @FXML
    private Label lFaltasA;
    @FXML
    private Label lFaltasB;
    @FXML
    private Label lPontosA;
    @FXML
    private Label lPontosB;
    @FXML
    private ImageView ivTimeA;
    @FXML
    private ImageView ivTimeB;
    
    //Botões e Labels do Cliente
    @FXML
    private Button bplaycron;
    @FXML
    private Button bstopcron;
    @FXML
    private Button bplayshot;
    @FXML
    private Button bstopshot;
    @FXML
    private Button bmaispontosA;
    @FXML
    private Button bmenospontosA;
    @FXML
    private Button bmaisfaltasA;
    @FXML
    private Button bmenosfaltasA;
    @FXML
    private Label lmaispontosA;
    @FXML
    private Label lmenospontosA;
    @FXML
    private Label lmaispontosB;
    @FXML
    private Label lmenospontosB;
    @FXML
    private Button bmaispontosB;
    @FXML
    private Button bmenospontosB;
    @FXML
    private Button bmaisfaltasB;
    @FXML
    private Button bmenosfaltasB;
    @FXML
    private Button bmaisperiodo;
    @FXML
    private Button bmenosperiodo;
    
    private static FXMLLoginController lc;

    
    protected static BooleanProperty v = new SimpleBooleanProperty();
    
    //recebe o tipo de usuario
    //se for admin seta false na visible dos botoes abaixo
    //se for cliente seta true na visible doa botoes abaixo
    public void cliente(String user){
        v.setValue(false);
        System.out.println(user);
        if(user.contains("cliente")){
            
            v.setValue(true);
        }
        //seta visibilidade dos botoes
    bplaycron.visibleProperty().bindBidirectional(v);
    bstopcron.visibleProperty().bindBidirectional(v);
    bplayshot.visibleProperty().bindBidirectional(v);
    bstopshot.visibleProperty().bindBidirectional(v);
    bmaispontosA.visibleProperty().bindBidirectional(v);
    bmenospontosA.visibleProperty().bindBidirectional(v);
    bmaisfaltasA.visibleProperty().bindBidirectional(v);
    bmenosfaltasA.visibleProperty().bindBidirectional(v);
    lmaispontosA.visibleProperty().bindBidirectional(v);
    lmenospontosA.visibleProperty().bindBidirectional(v);
    lmaispontosB.visibleProperty().bindBidirectional(v);
    lmenospontosB.visibleProperty().bindBidirectional(v);
    bmaispontosB.visibleProperty().bindBidirectional(v);
    bmenospontosB.visibleProperty().bindBidirectional(v);
    bmaisfaltasB.visibleProperty().bindBidirectional(v);
    bmenosfaltasB.visibleProperty().bindBidirectional(v);
    bmaisperiodo.visibleProperty().bindBidirectional(v);
    bmenosperiodo.visibleProperty().bindBidirectional(v);
    }
    

    private static File file = new File("src/videos/Propaganda.mp4");
    private static final String mediaurl = file.toURI().toString();
    private MediaPlayer mediaplayer;
    private Media media;

    public boolean stopc = true;
    private int segundo = 60;
    private int minuto = 10;
    
    //Cronometro
    public void iniciaCronometro() {
        Task t = new Task() {

            @Override
            protected Object call() throws Exception {
                while (stopc == true) {
                    segundo--;

                    if (segundo == 00) {
                        minuto--;
                        segundo = 59;
                    }

//                    if (minuto == 60) {
//
//                        minuto = 0;
//                    }
                    String min = minuto <= 9 ? "0" + minuto : minuto + "";
                    String seg = segundo <= 9 ? "0" + segundo : segundo + "";

                    Platform.runLater(() -> {

                        lCronometroB.setText(min + ":" + seg);
                    });
                    Thread.sleep(1000);

                }
                return null;
            }
        };
        new Thread(t).start();

    }

    //Placar
    public void Placar() {
        Task t2 = new Task() {

            @Override
            protected Object call() throws Exception {
                
                 //Soma 1 gol ao placar do time da esquerda
                //ao pressionar <-
                apBasquete.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    int pe = 0;                
                int pd = 0;
                    if (event.getCode().equals(KeyCode.LEFT)) {
                        
                        pe = Integer.parseInt(lPontosA.getText());
                        pe = (pe + 1);
                        String spe = Integer.toString(pe);
                        if(pe < 10){
                        Platform.runLater(() -> {

                            lPontosA.setText("0"+spe);
                        });
                        };
                        if(pe >= 10){
                        Platform.runLater(() -> {

                            lPontosA.setText(spe);
                        });
                        };
                    }

                    //Soma 1 gol ao placar do time da direita
                    //ao pressionar ->
                    if (event.getCode().equals(KeyCode.RIGHT)) {
                        
                        pd = Integer.parseInt(lPontosB.getText());
                        pd = (pd + 1);
                        String spd = Integer.toString(pd);
                        if(pd < 10){
                        Platform.runLater(() -> {

                            lPontosB.setText("0"+spd);
                        });
                        }
                        if(pd >= 10){
                        Platform.runLater(() -> {

                            lPontosB.setText(spd);
                        });
                        }
                    }
                });
                //}
                return null;
            }
        };
        new Thread(t2).start();

    }
    
//    public void pegarUser(String u) {
//
//        this.cliente(u);
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        lc = new FXMLLoginController();
        
        try{
        cliente(lc.retornaUser());
        }catch(Exception ex){
            System.out.println("ex");
        }
        
        media = new Media(mediaurl);
        mediaplayer = new MediaPlayer(media);
        mvBasquete.setMediaPlayer(mediaplayer);
        mediaplayer.play();
       iniciaCronometro();
       Placar();

    }

}
