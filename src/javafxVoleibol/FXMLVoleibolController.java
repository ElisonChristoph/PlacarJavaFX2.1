/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxVoleibol;

import java.io.File;
import javafxFutebol.*;
import javafxPrincipal.*;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafxSet.FXMLSetDadosController;
import javafxlogin.FXMLLoginController;

/**
 *
 * @author Elison
 */
public class FXMLVoleibolController implements Initializable {

    @FXML
    private AnchorPane apVolei;
    @FXML
    public BorderPane bpVolei;
    @FXML
    private MediaView mvDireitaVolei;
    @FXML
    private MediaView mvEsquerdaVolei;
    @FXML
    private Label lTimeEsquerda;
    @FXML
    private Label lTimeDireita;
    @FXML
    private Label lPlacarEsquerda;
    @FXML
    private Label lPlacarDireita;
    @FXML
    private Label lSetsEsquerda;
    @FXML
    private Label lSetsDireita;
    @FXML
    private Label lSet;
    @FXML
    private Label lPrimeiroSet;
    @FXML
    private Label lSegundoSet;
    @FXML
    private Label lTerceiroSet;
    @FXML
    private Label lQuartoSet;
    @FXML
    private Label lCronometroVolei;
    @FXML
    private ImageView ivTimeEsquerda;
    @FXML
    private ImageView ivTimeDireita;
    @FXML
    private Label lfaltasesquerda;
    @FXML
    private Label lfaltasdireita;
    
    

    //botões cliente
    @FXML
    private Button bmaispontosesquerda;
    @FXML
    private Button bmaispontosdireita;
    @FXML
    private Button bmenospontosesquerda;
    @FXML
    private Button bmenospontosdireita;
    @FXML
    private Button bstartcron;
    @FXML
    private Button bstopcron;
    @FXML
    private Button bmaisfaltaesquerda;
    @FXML
    private Button bmaisfaltadireita;
    @FXML
    private Button bmenosfaltaesquerda;
    @FXML
    private Button bmenosfaltadireita;
    @FXML
    private Button bmaissetesquerda;
    @FXML
    private Button bmaissetdireita;
    @FXML
    private Button bmenossetesquerda;
    @FXML
    private Button bmenossetdireita;
    @FXML
    private Button bmaisset;
    @FXML
    private Button bmenosset;
    @FXML
    private Button bfimdoset;

    private int pontose = 0;
    private int pontosd = 0;

    private int faltase = 0;
    private int faltasd = 0;

    private static FXMLLoginController lc;

    protected static BooleanProperty v = new SimpleBooleanProperty();

    //recebe o tipo de usuario
    //se for admin seta false na visible dos botoes abaixo
    //se for cliente seta true na visible doa botoes abaixo
    public void cliente(String user) {
        v.setValue(false);
        System.out.println(user);
        if (user.contains("cliente")) {

            v.setValue(true);
        }
        //seta visibilidade dos botoes
        bmaispontosesquerda.visibleProperty().bindBidirectional(v);
        bmaispontosdireita.visibleProperty().bindBidirectional(v);
        bmenospontosesquerda.visibleProperty().bindBidirectional(v);
        bmenospontosdireita.visibleProperty().bindBidirectional(v);
        bstartcron.visibleProperty().bindBidirectional(v);
        bstopcron.visibleProperty().bindBidirectional(v);
        bmaisfaltaesquerda.visibleProperty().bindBidirectional(v);
        bmaisfaltadireita.visibleProperty().bindBidirectional(v);
        bmenosfaltaesquerda.visibleProperty().bindBidirectional(v);
        bmenosfaltadireita.visibleProperty().bindBidirectional(v);
        bmaissetesquerda.visibleProperty().bindBidirectional(v);
        bmaissetdireita.visibleProperty().bindBidirectional(v);
        bmenossetdireita.visibleProperty().bindBidirectional(v);
        bmenossetesquerda.visibleProperty().bindBidirectional(v);
        bmaisset.visibleProperty().bindBidirectional(v);
        bmenosset.visibleProperty().bindBidirectional(v);
        bfimdoset.visibleProperty().bindBidirectional(v);

    }

    private static File file = new File("src/videos/Propaganda.mp4");
    private static final String mediaurl = file.toURI().toString();
    private MediaPlayer mediaplayer;
    private Media media;
    private static FXMLSetDadosController c;

    public boolean stopc = true;
    private int segundo = 0;
    private int minuto = 0;
    private int hora = 0;

    public void iniciaCronometro() {
        Task t = new Task() {

            @Override
            protected Object call() throws Exception {
                while (stopc == true) {
                    segundo++;

                    if (segundo == 60) {
                        minuto++;
                        segundo = 0;
                    }

                    if (minuto == 60) {
                        hora++;
                        minuto = 0;
                    }
                    String min = minuto <= 9 ? "0" + minuto : minuto + "";
                    String seg = segundo <= 9 ? "0" + segundo : segundo + "";
                    String hor = hora <= 9 ? "0" + hora : hora + "";

                    Platform.runLater(() -> {

                        lCronometroVolei.setText(hor + ":" + min + ":" + seg);
                    });
                    Thread.sleep(1000);

                }
                return null;
            }
        };
        new Thread(t).start();

    }

    //configuração do placar
    public void Placar() {
        Task t2 = new Task() {
            @Override
            protected Object call() throws Exception {

                //Soma 1 ponto ao placar do time da esquerda
                //ao pressionar <-
                apVolei.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {

                    if (event.getCode().equals(KeyCode.LEFT)) {

                        pontose = Integer.parseInt(lPlacarEsquerda.getText());
                        pontose = (pontose + 1);
                        String spe = Integer.toString(pontose);

                        Platform.runLater(() -> {

                            lPlacarEsquerda.setText(spe);
                        });
                    }
                    //Soma 1 ponto ao placar do time da direita
                    //ao pressionar ->
                    if (event.getCode().equals(KeyCode.RIGHT)) {

                        pontosd = Integer.parseInt(lPlacarDireita.getText());
                        pontosd = (pontosd + 1);
                        String spd = Integer.toString(pontosd);

                        Platform.runLater(() -> {

                            lPlacarDireita.setText(spd);
                        });
                    }
                });
                //Soma 1 ponto ao placar do time da esquerda
                //ao pressionar o botão +
                bmaispontosesquerda.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontose = Integer.parseInt(lPlacarEsquerda.getText());
                    pontose = (pontose + 1);

                    String spe = Integer.toString(pontose);

                    Platform.runLater(() -> {

                        lPlacarEsquerda.setText(spe);
                    });
                });

                //Soma 1 ponto ao placar do time da direita
                //ao pressionar o botão +
                bmaispontosdireita.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontosd = Integer.parseInt(lPlacarDireita.getText());
                    pontosd = (pontosd + 1);

                    String spd = Integer.toString(pontosd);

                    Platform.runLater(() -> {

                        lPlacarDireita.setText(spd);
                    });
                });

                //Subtrai 1 ponto do placar do time da esquerda
                //ao pressionar o botão -
                bmenospontosesquerda.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    pontose = Integer.parseInt(lPlacarEsquerda.getText());

                    if (pontose == 0) {
                        String spe = Integer.toString(pontose);

                        Platform.runLater(() -> {

                            lPlacarEsquerda.setText(spe);
                        });
                    } else {
                        pontose = (pontose - 1);

                        String spe = Integer.toString(pontose);

                        Platform.runLater(() -> {

                            lPlacarEsquerda.setText(spe);
                        });
                    }
                });

                //Subtrai 1 ponto do placar do time da direita
                //ao pressionar o botão -
                bmenospontosdireita.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontosd = Integer.parseInt(lPlacarDireita.getText());
                    if (pontosd == 0) {
                        String spd = Integer.toString(pontosd);

                        Platform.runLater(() -> {

                            lPlacarDireita.setText(spd);
                        });
                    } else {
                        pontosd = (pontosd - 1);

                        String spd = Integer.toString(pontosd);

                        Platform.runLater(() -> {

                            lPlacarDireita.setText(spd);
                        });
                    }
                });
                //final configuração placar
                
                //inicio configuração das faltas
   
                //Soma uma falta para time da esquerda
                //ao pressionar A
                apVolei.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    
                    if (event.getCode().equals(KeyCode.A)) {

                        faltase = Integer.parseInt(lfaltasesquerda.getText());
                        faltase = (faltase + 1);
                        String spe = Integer.toString(faltase);

                        Platform.runLater(() -> {

                            lfaltasesquerda.setText(spe);
                        });
                    }
                    //Soma uma falta para o time da direita
                    //ao pressionar D
                    if (event.getCode().equals(KeyCode.D)) {

                        faltasd = Integer.parseInt(lfaltasdireita.getText());
                        faltasd = (faltasd + 1);
                        String spd = Integer.toString(faltasd);
                        
                        Platform.runLater(() -> {

                            lfaltasdireita.setText(spd);
                        });
                    }
                });
                //Soma 1 falta ao time da esquerda
                    //ao pressionar o botão +
                bmaisfaltaesquerda.addEventFilter(MouseEvent.MOUSE_CLICKED , event -> {
                    faltase = Integer.parseInt(lfaltasesquerda.getText());
                        faltase = (faltase + 1);
                        
                        String spe = Integer.toString(faltase);                      

                        Platform.runLater(() -> {

                            lfaltasesquerda.setText(spe);
                        });
                });
                
                //Soma 1 falta ao time da direita
                    //ao pressionar o botão +
                bmaisfaltadireita.addEventFilter(MouseEvent.MOUSE_CLICKED , event -> {
                    faltasd = Integer.parseInt(lfaltasdireita.getText());
                        faltasd = (faltasd + 1);
                        
                        String spe = Integer.toString(faltasd);                      

                        Platform.runLater(() -> {

                            lfaltasdireita.setText(spe);
                        });
                });
                
                //Subtrai uma falta do time da esquerda
                    //ao pressionar o botão -
                bmenosfaltaesquerda.addEventFilter(MouseEvent.MOUSE_CLICKED , (MouseEvent event) -> {
                    faltase = Integer.parseInt(lfaltasesquerda.getText());
                    
                    if(faltase == 0){
                        String spe = Integer.toString(faltase);
                        
                        Platform.runLater(() -> {

                            lfaltasesquerda.setText(spe);
                        });
                    }else{
                        faltase = (faltase - 1);
                        
                        String spe = Integer.toString(faltase);
                        
                        Platform.runLater(() -> {

                            lfaltasesquerda.setText(spe);
                        });
                    }
                });
                
                
                //Subtrai uma falta do time da direita
                    //ao pressionar o botão -
                bmenosfaltadireita.addEventFilter(MouseEvent.MOUSE_CLICKED , (MouseEvent event) -> {
                    faltasd = Integer.parseInt(lfaltasdireita.getText());
                    
                    if(faltasd == 0){
                        String spe = Integer.toString(faltasd);
                        
                        Platform.runLater(() -> {

                            lfaltasdireita.setText(spe);
                        });
                    }else{
                        faltasd = (faltasd - 1);
                        
                        String spe = Integer.toString(faltasd);
                        
                        Platform.runLater(() -> {

                            lfaltasdireita.setText(spe);
                        });
                    }
                });
                //final da configuração das faltas
                
                return null;
            }
        };
        new Thread(t2).start();

    }
    //final da configuração do placar
    
    //função abaixo, pega o time que foi setado
    //na tela de configuração do placar
    public void pegarTime(String nomea, String nomeb) {

        this.lTimeEsquerda.setText(nomea);
        this.lTimeDireita.setText(nomeb);

    }
    // final do pega time

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //busca os dados setados na tela anterior
        // e joga eles para o metodo pegar time
        c = new FXMLSetDadosController();
        try {
            pegarTime(c.retornaTimeA(), c.retornaTimeB());
        } catch (Exception ex) {
            System.out.println("Deu merda" + ex.getMessage());
        }

        //esconde ou mostra botões de controle
        lc = new FXMLLoginController();

        try {
            cliente(lc.retornaUser());
        } catch (Exception ex) {
            System.out.println("ex");
        }
        
        media = new Media(mediaurl);
        mediaplayer = new MediaPlayer(media);
        mvDireitaVolei.setMediaPlayer(mediaplayer);
        mvEsquerdaVolei.setMediaPlayer(mediaplayer);
        mediaplayer.play();
        iniciaCronometro();
        Placar();
    }

}
