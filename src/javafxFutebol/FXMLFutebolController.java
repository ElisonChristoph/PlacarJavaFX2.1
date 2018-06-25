/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxFutebol;

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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafxSet.FXMLSetDadosController;
import javafxlogin.FXMLLoginController;

/**
 *
 * @author Elison
 */
public class FXMLFutebolController implements Initializable {

    @FXML
    private AnchorPane apFutebol;
    @FXML
    public BorderPane bpFutebol;
    @FXML
    private MediaView mvFutebol;
    @FXML
    private Label lTimeEsquerda;
    @FXML
    private Label lTimeDireita;
    @FXML
    private Label lPlacarA;
    @FXML
    private Label lPlacarB;
    @FXML
    private Label lCronometro;
    @FXML
    public ImageView ivFundo;
    @FXML
    private Label lfaltasD;
    @FXML
    private Label lfaltasE;
    @FXML
    private Label lperiodo;

    //botões clientes
    @FXML
    public Button bmaisgolA;
    @FXML
    public Button bmenosgolA;
    @FXML
    public Button bmaisgolB;
    @FXML
    public Button bmenosgolB;
    @FXML
    public Button bmaisfaltasA;
    @FXML
    public Button bmenosfaltasA;
    @FXML
    public Button bmaisfaltasB;
    @FXML
    public Button bmenosfaltasB;
    @FXML
    public Button bmaisamareloA;
    @FXML
    public Button bmaisvermelhoA;
    @FXML
    public Button bmenosvermelhoA;
    @FXML
    public Button bmenosamareloA;
    @FXML
    public Button bmaisamareloB;
    @FXML
    public Button bmaisvermelhoB;
    @FXML
    public Button bmenosvermelhoB;
    @FXML
    public Button bmenosamareloB;
    @FXML
    public Button bmaisperiodo;
    @FXML
    public Button bmenosperiodo;
    @FXML
    public Button bmaisacrescimo;
    @FXML
    public TextField tfmaisacrescimo;
    @FXML
    public Label lacrescimo;
    @FXML
    public Label lcronacrescimo;
    @FXML
    public Rectangle rectangleacrescimos;
    @FXML
    public Rectangle rvideo;
    @FXML
    public Button bpararcron;
    @FXML
    public Button biniciarcron;
    @FXML
    public Button bzeraracrescimos;
    @FXML
    public Label lamarelosA;
    @FXML
    public Label lvermelhosA;
    @FXML
    public Label lamarelosB;
    @FXML
    public Label lvermelhosB;

    private int amarelos = 0;
    private int vermelhos = 0;

    private int periodo = 0;

    private int pontose = 0;
    private int pontosd = 0;

    private int faltase = 0;
    private int faltasd = 0;

    private static FXMLLoginController lc;

    protected static BooleanProperty v = new SimpleBooleanProperty();
    protected static BooleanProperty v2 = new SimpleBooleanProperty();
    
    //recebe o tipo de usuario
    //se for admin seta false na visible dos botoes abaixo
    //se for cliente seta true na visible doa botoes abaixo
    public void cliente(String user) {
        v.setValue(false);
        v2.setValue(true);
        System.out.println(user);
        if (user.contains("cliente")) {

            v.setValue(true);
            v2.setValue(false);
        }
        //seta visibilidade dos botoes
        bmaisamareloA.visibleProperty().bindBidirectional(v);
        bmaisvermelhoA.visibleProperty().bindBidirectional(v);
        bmenosvermelhoA.visibleProperty().bindBidirectional(v);
        bmenosamareloA.visibleProperty().bindBidirectional(v);
        bmaisamareloB.visibleProperty().bindBidirectional(v);
        bmaisvermelhoB.visibleProperty().bindBidirectional(v);
        bmenosvermelhoB.visibleProperty().bindBidirectional(v);
        bmenosamareloB.visibleProperty().bindBidirectional(v);
        bmaisgolA.visibleProperty().bindBidirectional(v);
        bmenosgolA.visibleProperty().bindBidirectional(v);
        bmaisfaltasA.visibleProperty().bindBidirectional(v);
        bmenosfaltasA.visibleProperty().bindBidirectional(v);
        bmaisgolB.visibleProperty().bindBidirectional(v);
        bmenosgolB.visibleProperty().bindBidirectional(v);
        bmaisfaltasB.visibleProperty().bindBidirectional(v);
        bmenosfaltasB.visibleProperty().bindBidirectional(v);
        bmaisperiodo.visibleProperty().bindBidirectional(v);
        bmenosperiodo.visibleProperty().bindBidirectional(v);
        bmaisacrescimo.visibleProperty().bindBidirectional(v);
        tfmaisacrescimo.visibleProperty().bindBidirectional(v);
        rectangleacrescimos.visibleProperty().bindBidirectional(v);
        lacrescimo.visibleProperty().bindBidirectional(v);
        lcronacrescimo.visibleProperty().bindBidirectional(v);
        bpararcron.visibleProperty().bindBidirectional(v);
        biniciarcron.visibleProperty().bindBidirectional(v);
        bzeraracrescimos.visibleProperty().bindBidirectional(v);
        rvideo.visibleProperty().bindBidirectional(v2);
    }

    //configuração multimedia {
    private static File file = new File("src/videos/Propaganda.mp4");
    private static final String mediaurl = file.toURI().toString();
    private MediaPlayer mediaplayer;
    private Media media;
    private static FXMLSetDadosController c;
    //} fim configuração multimedia

    //configuração do cronometro
    public boolean startcron = true;
    public boolean stopc = true;
    private int segundo = 0;
    private int minuto = 0;

    public void iniciaCronometro() {
        Task t = new Task() {

            @Override
            protected Object call() throws Exception {
                while (startcron == true) {

                    while (stopc == false) {
                        biniciarcron.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            stopc = true;
                        });
                    }

                    while (stopc == true) {
                        bpararcron.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            stopc = false;
                        });

                        segundo++;

                        if (segundo == 60) {
                            minuto++;
                            segundo = 0;
                        }

                        if (minuto == 60) {

                            minuto = 0;
                        }
                        String min = minuto <= 9 ? "0" + minuto : minuto + "";
                        String seg = segundo <= 9 ? "0" + segundo : segundo + "";

                        Platform.runLater(() -> {

                            lCronometro.setText(min + ":" + seg);
                        });
                        Thread.sleep(1000);

                    }
                }
                return null;
            }
        };
        new Thread(t).start();

    }
    //fim da configuração cronometro

    //inicio da thread
    public void PlacarFaltasPeriodoeCartoes() {
        Task t2 = new Task() {
            @Override
            protected Object call() throws Exception {

                // inicio da configuração do placar
                //Soma 1 gol ao placar do time da esquerda
                //ao pressionar <-
                apFutebol.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {

                    if (event.getCode().equals(KeyCode.LEFT)) {

                        pontose = Integer.parseInt(lPlacarA.getText());
                        pontose = (pontose + 1);
                        String spe = Integer.toString(pontose);

                        Platform.runLater(() -> {

                            lPlacarA.setText(spe);
                        });
                    }
                    //Soma 1 gol ao placar do time da direita
                    //ao pressionar ->
                    if (event.getCode().equals(KeyCode.RIGHT)) {

                        pontosd = Integer.parseInt(lPlacarB.getText());
                        pontosd = (pontosd + 1);
                        String spd = Integer.toString(pontosd);

                        Platform.runLater(() -> {

                            lPlacarB.setText(spd);
                        });
                    }
                });
                //Soma 1 gol ao placar do time da esquerda
                //ao pressionar o botão +
                bmaisgolA.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontose = Integer.parseInt(lPlacarA.getText());
                    pontose = (pontose + 1);

                    String spe = Integer.toString(pontose);

                    Platform.runLater(() -> {

                        lPlacarA.setText(spe);
                    });
                });

                //Soma 1 gol ao placar do time da direita
                //ao pressionar o botão +
                bmaisgolB.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontosd = Integer.parseInt(lPlacarB.getText());
                    pontosd = (pontosd + 1);

                    String spd = Integer.toString(pontosd);

                    Platform.runLater(() -> {

                        lPlacarB.setText(spd);
                    });
                });

                //Subtrai 1 gol do placar do time da esquerda
                //ao pressionar o botão -
                bmenosgolA.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    pontose = Integer.parseInt(lPlacarA.getText());

                    if (pontose == 0) {
                        String spe = Integer.toString(pontose);

                        Platform.runLater(() -> {

                            lPlacarA.setText(spe);
                        });
                    } else {
                        pontose = (pontose - 1);

                        String spe = Integer.toString(pontose);

                        Platform.runLater(() -> {

                            lPlacarA.setText(spe);
                        });
                    }
                });

                //Subtrai 1 gol do placar do time da direita
                //ao pressionar o botão -
                bmenosgolB.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontosd = Integer.parseInt(lPlacarB.getText());
                    if (pontosd == 0) {
                        String spd = Integer.toString(pontosd);

                        Platform.runLater(() -> {

                            lPlacarB.setText(spd);
                        });
                    } else {
                        pontosd = (pontosd - 1);

                        String spd = Integer.toString(pontosd);

                        Platform.runLater(() -> {

                            lPlacarB.setText(spd);
                        });
                    }
                });

//    //final da configuração do placar
//    //inicio configuração das faltas
                //Soma 1 gol ao placar do time da esquerda
                //ao pressionar <-
                apFutebol.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {

                    if (event.getCode().equals(KeyCode.A)) {

                        faltase = Integer.parseInt(lfaltasE.getText());
                        faltase = (faltase + 1);
                        String spe = Integer.toString(faltase);

                        Platform.runLater(() -> {

                            lfaltasE.setText(spe);
                        });
                    }
                    //Soma 1 gol ao placar do time da direita
                    //ao pressionar ->
                    if (event.getCode().equals(KeyCode.D)) {

                        faltasd = Integer.parseInt(lfaltasD.getText());
                        faltasd = (faltasd + 1);
                        String spd = Integer.toString(faltasd);

                        Platform.runLater(() -> {

                            lfaltasD.setText(spd);
                        });
                    }
                });
                //Soma 1 falta ao time da esquerda
                //ao pressionar o botão +
                bmaisfaltasA.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    faltase = Integer.parseInt(lfaltasE.getText());
                    faltase = (faltase + 1);

                    String spe = Integer.toString(faltase);

                    Platform.runLater(() -> {

                        lfaltasE.setText(spe);
                    });
                });

                //Soma 1 falta ao time da direita
                //ao pressionar o botão +
                bmaisfaltasB.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    faltasd = Integer.parseInt(lfaltasD.getText());
                    faltasd = (faltasd + 1);

                    String spe = Integer.toString(faltasd);

                    Platform.runLater(() -> {

                        lfaltasD.setText(spe);
                    });
                });

                //Subtrai uma falta do time da esquerda
                //ao pressionar o botão -
                bmenosfaltasA.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    faltase = Integer.parseInt(lfaltasE.getText());

                    if (faltase == 0) {
                        String spe = Integer.toString(faltase);

                        Platform.runLater(() -> {

                            lfaltasE.setText(spe);
                        });
                    } else {
                        faltase = (faltase - 1);

                        String spe = Integer.toString(faltase);

                        Platform.runLater(() -> {

                            lfaltasE.setText(spe);
                        });
                    }
                });

                //Subtrai uma falta do time da direita
                //ao pressionar o botão -
                bmenosfaltasB.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    faltasd = Integer.parseInt(lfaltasD.getText());

                    if (faltasd == 0) {
                        String spe = Integer.toString(faltasd);

                        Platform.runLater(() -> {

                            lfaltasD.setText(spe);
                        });
                    } else {
                        faltasd = (faltasd - 1);

                        String spe = Integer.toString(faltasd);

                        Platform.runLater(() -> {

                            lfaltasD.setText(spe);
                        });
                    }
                });
                //final da configuração das faltas

                //configuração do periodo
                bmaisperiodo.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    periodo = Integer.parseInt(lperiodo.getText());
                    periodo = (periodo + 1);

                    String per = Integer.toString(periodo);

                    Platform.runLater(() -> {

                        lperiodo.setText(per + "°");
                    });
                });

                bmenosperiodo.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    periodo = Integer.parseInt(lperiodo.getText());

                    if (periodo == 0) {
                        String per = Integer.toString(periodo);

                        Platform.runLater(() -> {

                            lperiodo.setText(per + "°");
                        });
                    } else {
                        periodo = (periodo - 1);

                        String per = Integer.toString(periodo);

                        Platform.runLater(() -> {

                            lperiodo.setText(per + "°");
                        });
                    }
                });
                // fim configuração periodo

                //inicio configuração cartões
                //Soma 1 falta ao time da esquerda
                //ao pressionar o botão +
                bmaisamareloA.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    amarelos = Integer.parseInt(lamarelosA.getText());
                    amarelos = (amarelos + 1);

                    String am = Integer.toString(amarelos);

                    Platform.runLater(() -> {

                        lamarelosA.setText(am);
                    });
                });

                //Soma 1 falta ao time da esquerda
                //ao pressionar o botão +
                bmaisvermelhoA.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    vermelhos = Integer.parseInt(lvermelhosA.getText());
                    vermelhos = (vermelhos + 1);

                    String ver = Integer.toString(vermelhos);

                    Platform.runLater(() -> {

                        lvermelhosA.setText(ver);
                    });
                });

                //Soma 1 falta ao time da direita
                //ao pressionar o botão +
                bmaisvermelhoB.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    vermelhos = Integer.parseInt(lvermelhosB.getText());
                    vermelhos = (vermelhos + 1);

                    String ver = Integer.toString(vermelhos);

                    Platform.runLater(() -> {

                        lvermelhosB.setText(ver);
                    });
                });

                //Soma 1 falta ao time da direita
                //ao pressionar o botão +
                bmaisamareloB.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    amarelos = Integer.parseInt(lamarelosB.getText());
                    amarelos = (amarelos + 1);

                    String am = Integer.toString(amarelos);

                    Platform.runLater(() -> {

                        lamarelosB.setText(am);
                    });
                });

                //Subtrai uma falta do time da esquerda
                //ao pressionar o botão -
                bmenosvermelhoA.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    vermelhos = Integer.parseInt(lvermelhosA.getText());

                    if (vermelhos == 0) {
                        String ver = Integer.toString(vermelhos);

                        Platform.runLater(() -> {

                            lvermelhosA.setText(ver);
                        });
                    } else {
                        vermelhos = (vermelhos - 1);

                        String ver = Integer.toString(vermelhos);

                        Platform.runLater(() -> {

                            lvermelhosA.setText(ver);
                        });
                    }
                });

                //Subtrai uma falta do time da esquerda
                //ao pressionar o botão -
                bmenosamareloA.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    amarelos = Integer.parseInt(lamarelosA.getText());

                    if (amarelos == 0) {
                        String am = Integer.toString(amarelos);

                        Platform.runLater(() -> {

                            lamarelosA.setText(am);
                        });
                    } else {
                        amarelos = (amarelos - 1);

                        String am = Integer.toString(amarelos);

                        Platform.runLater(() -> {

                            lamarelosA.setText(am);
                        });
                    }
                });

                //Subtrai uma falta do time da esquerda
                //ao pressionar o botão -
                bmenosvermelhoB.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    vermelhos = Integer.parseInt(lvermelhosB.getText());

                    if (vermelhos == 0) {
                        String ver = Integer.toString(vermelhos);

                        Platform.runLater(() -> {

                            lvermelhosB.setText(ver);
                        });
                    } else {
                        vermelhos = (vermelhos - 1);

                        String ver = Integer.toString(vermelhos);

                        Platform.runLater(() -> {

                            lvermelhosB.setText(ver);
                        });
                    }
                });

                //Subtrai uma falta do time da esquerda
                //ao pressionar o botão -
                bmenosamareloB.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    amarelos = Integer.parseInt(lamarelosB.getText());

                    if (amarelos == 0) {
                        String am = Integer.toString(amarelos);

                        Platform.runLater(() -> {

                            lamarelosB.setText(am);
                        });
                    } else {
                        amarelos = (amarelos - 1);

                        String am = Integer.toString(amarelos);

                        Platform.runLater(() -> {

                            lamarelosB.setText(am);
                        });
                    }
                });
                return null;
            }
        };
        new Thread(t2).start();

    }
    //final da thread

    //função abaixo, pega o time que foi setado
    //na tela de configuração do placar
    public void pegarTime(String nomea, String nomeb) {

        this.lTimeEsquerda.setText(nomea);
        this.lTimeDireita.setText(nomeb);

    }
    // final do pega time

    //Start do placar
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

        //configuração de reprodução 
        // do multimedia
        if(lc.retornaUser().equals("admin")){
        media = new Media(mediaurl);
        mediaplayer = new MediaPlayer(media);
        mvFutebol.setMediaPlayer(mediaplayer);
        mediaplayer.play();
        }
        // ****************

        //Chama e inicia a thread do cronometro
        iniciaCronometro();
        // ***************

        //chama e inicia a thread do placar 
        PlacarFaltasPeriodoeCartoes();
        // ***************

    }

}
