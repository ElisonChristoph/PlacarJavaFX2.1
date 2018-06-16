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
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author Elison
 */
public class FXMLBasquetebolController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        media = new Media(mediaurl);
        mediaplayer = new MediaPlayer(media);
        mvBasquete.setMediaPlayer(mediaplayer);
        mediaplayer.play();
       iniciaCronometro();
       Placar();

    }

}
