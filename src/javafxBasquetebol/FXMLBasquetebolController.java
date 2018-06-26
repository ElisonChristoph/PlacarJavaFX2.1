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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafxSet.FXMLSetDadosController;
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
    private Label lShotClock;
    @FXML
    private Label lTimeA;
    @FXML
    private Label lTimeB;
    @FXML
    private Label lfaltasE;
    @FXML
    private Label lfaltasD;
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
    private TextField tfmaispontosE;
    @FXML
    private TextField tfmenospontosE;
    @FXML
    private TextField tfmaispontosD;
    @FXML
    private TextField tfmenospontosD;
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
    private static FXMLSetDadosController c;

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
        bplaycron.visibleProperty().bindBidirectional(v);
        bstopcron.visibleProperty().bindBidirectional(v);
        bplayshot.visibleProperty().bindBidirectional(v);
        bstopshot.visibleProperty().bindBidirectional(v);
        bmaispontosA.visibleProperty().bindBidirectional(v);
        bmenospontosA.visibleProperty().bindBidirectional(v);
        bmaisfaltasA.visibleProperty().bindBidirectional(v);
        bmenosfaltasA.visibleProperty().bindBidirectional(v);
        tfmaispontosE.visibleProperty().bindBidirectional(v);
        tfmenospontosE.visibleProperty().bindBidirectional(v);
        tfmaispontosD.visibleProperty().bindBidirectional(v);
        tfmenospontosD.visibleProperty().bindBidirectional(v);
        bmaispontosB.visibleProperty().bindBidirectional(v);
        bmenospontosB.visibleProperty().bindBidirectional(v);
        bmaisfaltasB.visibleProperty().bindBidirectional(v);
        bmenosfaltasB.visibleProperty().bindBidirectional(v);
        bmaisperiodo.visibleProperty().bindBidirectional(v);
        bmenosperiodo.visibleProperty().bindBidirectional(v);
    }
    //variavel do periodo
    private int periodo = 0;
    //variaveis dos pontos
    private int pontose = 0;
    private int pontosd = 0;
    //variaveis dos pontos do textfield
    private int pontosmais = 0;
    private int pontosmenos = 0;
    //variaveis das faltas
    private int faltase = 0;
    private int faltasd = 0;

    String tipoBasquete;

    //Arquivo Propaganda
    private static File video = new File("src/videos/Propaganda.mp4");
    private static final String videourl = video.toURI().toString();
    private MediaPlayer mediaplayer;
    private Media media;

    //Arquivo Som
    private static File somshotclock = new File("src/videos/apito.wav");
    private static final String somurl = somshotclock.toURI().toString();
    private MediaPlayer mediaplayershotclock;
    private Media mediasom;

    public boolean startcron = true;
    public boolean stopc = false;
    private int segundo = 60;
    private int minutoNBA = 11;

    //Cronometro NBA
    public void CronometroNBA() {
        lCronometroB.setText("12:00");
        Task t = new Task() {

            @Override
            protected Object call() throws Exception {
                while (startcron == true) {

                    while (stopc == false) {
                        bplaycron.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            stopc = true;
                        });
                    }

                    while (stopc == true) {

                        bstopcron.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            stopc = false;
                        });

                        segundo--;

                        if (segundo == 00) {
                            minutoNBA--;
                            segundo = 59;
                        }

                        if (minutoNBA == 00) {
                            stopc = false;
                            mediasom = new Media(somurl);
                            mediaplayer = new MediaPlayer(mediasom);
                            mediaplayer.play();
                            minutoNBA = 11;
                            segundo = 60;
                        }
                        String min = minutoNBA <= 9 ? "0" + minutoNBA : minutoNBA + "";
                        String seg = segundo <= 9 ? "0" + segundo : segundo + "";

                        Platform.runLater(() -> {

                            lCronometroB.setText(min + ":" + seg);
                        });
                        Thread.sleep(1000);

                    }
                }
                return null;

            }
        };
        new Thread(t).start();

    }

    //Cronometro FIBA
    private int minutoFIBA = 9;

    public void CronometroFIBA() {
        lCronometroB.setText("10:00");
        Task t = new Task() {

            @Override
            protected Object call() throws Exception {
                while (startcron == true) {

                    while (stopc == false) {
                        bplaycron.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            stopc = true;
                        });
                    }

                    while (stopc == true) {

                        bstopcron.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            stopc = false;
                        });

                        segundo--;

                        if (segundo == 00) {
                            minutoFIBA--;
                            segundo = 59;
                        }

                        if (minutoFIBA == 00) {
                            stopc = false;
                            mediasom = new Media(somurl);
                            mediaplayer = new MediaPlayer(mediasom);
                            mediaplayer.play();
                            minutoFIBA = 9;
                            segundo = 60;

                        }
                        String min = minutoFIBA <= 9 ? "0" + minutoFIBA : minutoFIBA + "";
                        String seg = segundo <= 9 ? "0" + segundo : segundo + "";

                        Platform.runLater(() -> {

                            lCronometroB.setText(min + ":" + seg);
                        });
                        Thread.sleep(1000);

                    }
                }
                return null;

            }
        };
        new Thread(t).start();

    }

    //Cronometro Shot Clock
    private int shotclock = 24;
    public boolean startshot = false;
    public boolean start = true;

    public void shotclock() {
        Task t3 = new Task() {
            @Override
            protected Object call() throws Exception {

                while (start == true) {

                    while (startshot == false) {
                        bplayshot.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            startshot = true;
                        });
                    }
                    while (startshot == true) {

                        bstopshot.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            startshot = false;
                            shotclock = 24;
                            lShotClock.setText("24");
                        });

                        String seg = shotclock <= 9 ? "0" + shotclock : shotclock + "";

                        Platform.runLater(() -> {

                            lShotClock.setText(seg);
                        });

                        if (shotclock == 00) {

                            mediasom = new Media(somurl);
                            mediaplayer = new MediaPlayer(mediasom);
                            mediaplayer.play();

                            startshot = false;
                        }
                        if (shotclock != 0) {
                            shotclock--;

                        }
                        Thread.sleep(1000);

                    }
                }

                return false;
            }

        };
        new Thread(t3).start();
    }

    //Placar
    public void PlacarFaltasEPeriodo() {
        Task t2 = new Task() {

            @Override
            protected Object call() throws Exception {

                //Soma os pontos do textfield ao placar do time da esquerda
                //ao pressionar o botão +
                bmaispontosA.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontose = Integer.parseInt(lPontosA.getText());
                    pontosmais = Integer.parseInt(tfmaispontosE.getText());
                    pontose = (pontose + pontosmais);
                    pontosmais = 0;
                    String spe = Integer.toString(pontose);

                    if (pontose < 10) {
                        Platform.runLater(() -> {

                            lPontosA.setText("0" + spe);
                        });
                    } else {
                        Platform.runLater(() -> {

                            lPontosA.setText(spe);
                        });
                    }
                });

                //Soma os pontos do textfield ao placar do time da esquerda
                //ao pressionar o botão +
                bmaispontosB.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontosd = Integer.parseInt(lPontosB.getText());
                    pontosmais = Integer.parseInt(tfmaispontosD.getText());
                    pontosd = (pontosd + pontosmais);
                    pontosmais = 0;
                    String spd = Integer.toString(pontosd);
                    if (pontosd < 10) {
                        Platform.runLater(() -> {

                            lPontosB.setText("0" + spd);
                        });
                    } else {
                        Platform.runLater(() -> {

                            lPontosB.setText(spd);
                        });
                    }
                });

                //Subtrai os pontos do textfield, do placar do time da esquerda
                //ao pressionar o botão -
                bmenospontosA.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    pontose = Integer.parseInt(lPontosA.getText());
                    pontosmenos = Integer.parseInt(tfmenospontosE.getText());
                    if (pontose == 0) {
                        String spe = Integer.toString(pontose);

                        Platform.runLater(() -> {

                            lPontosA.setText("0" + spe);
                        });
                    } else {
                        pontose = (pontose - pontosmenos);

                        String spe = Integer.toString(pontose);
                        if (pontose < 10) {
                            Platform.runLater(() -> {

                                lPontosA.setText("0" + spe);
                            });
                        } else {
                            lPontosA.setText("0" + spe);
                        }
                        pontosmenos = 0;
                    }
                });

                //Subtrai os pontos do textfield, do placar do time da esquerda
                //ao pressionar o botão -
                bmenospontosB.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontosd = Integer.parseInt(lPontosB.getText());
                    pontosmenos = Integer.parseInt(tfmenospontosD.getText());
                    if (pontosd == 0) {
                        String spd = Integer.toString(pontosd);

                        Platform.runLater(() -> {

                            lPontosB.setText("0" + spd);
                        });
                    } else {
                        pontosd = (pontosd - pontosmenos);

                        String spd = Integer.toString(pontosd);
                        if (pontosd < 10) {
                            Platform.runLater(() -> {

                                lPontosB.setText("0" + spd);
                            });
                        } else {
                            Platform.runLater(() -> {

                                lPontosB.setText(spd);
                            });
                        }
                        pontosmenos = 0;
                    }
                });

//    //final da configuração do placar
//    //inicio configuração das faltas
                //Soma uma falta ao time da esquerda
                //ao pressionar A
                apBasquete.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {

                    if (event.getCode().equals(KeyCode.A)) {

                        faltase = Integer.parseInt(lfaltasE.getText());
                        faltase = (faltase + 1);
                        String spe = Integer.toString(faltase);
                        if (faltase < 10) {
                            Platform.runLater(() -> {

                                lfaltasE.setText("0" + spe);
                            });
                        } else {
                            Platform.runLater(() -> {

                                lfaltasE.setText(spe);
                            });
                        }
                    }
                    //Soma uma falta ao time da esquerda
                    //ao pressionar D
                    if (event.getCode().equals(KeyCode.D)) {

                        faltasd = Integer.parseInt(lfaltasD.getText());
                        faltasd = (faltasd + 1);
                        String spd = Integer.toString(faltasd);
                        if (faltasd < 10) {
                            Platform.runLater(() -> {

                                lfaltasD.setText("0" + spd);
                            });
                        } else {
                            Platform.runLater(() -> {

                                lfaltasD.setText(spd);
                            });
                        }
                    }
                });
                //Soma 1 falta ao time da esquerda
                //ao pressionar o botão +
                bmaisfaltasA.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    faltase = Integer.parseInt(lfaltasE.getText());
                    faltase = (faltase + 1);

                    String spe = Integer.toString(faltase);
                    if (faltase < 10) {
                        Platform.runLater(() -> {

                            lfaltasE.setText("0" + spe);
                        });
                    } else {
                        Platform.runLater(() -> {

                            lfaltasE.setText(spe);
                        });
                        }     
                    });

                    //Soma 1 falta ao time da direita
                    //ao pressionar o botão +
                    bmaisfaltasB.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                        faltasd = Integer.parseInt(lfaltasD.getText());
                        faltasd = (faltasd + 1);

                        String spe = Integer.toString(faltasd);
                        if(faltasd < 10){
                        Platform.runLater(() -> {

                            lfaltasD.setText("0" + spe);
                        });
                        }else{
                           Platform.runLater(() -> {

                            lfaltasD.setText(spe);
                        }); 
                        }
                    });

                    //Subtrai uma falta do time da esquerda
                    //ao pressionar o botão -
                    bmenosfaltasA.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                        faltase = Integer.parseInt(lfaltasE.getText());

                        if (faltase == 0) {
                            String spe = Integer.toString(faltase);

                            Platform.runLater(() -> {

                                lfaltasE.setText("0" + spe);
                            });
                        } else {
                            faltase = (faltase - 1);

                            String spe = Integer.toString(faltase);
                            if(faltase < 10){
                            Platform.runLater(() -> {

                                lfaltasE.setText("0" + spe);
                            });
                            }else{
                               Platform.runLater(() -> {

                                lfaltasE.setText(spe);
                            }); 
                            }
                        }
                    });

                    //Subtrai uma falta do time da direita
                    //ao pressionar o botão -
                    bmenosfaltasB.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                        faltasd = Integer.parseInt(lfaltasD.getText());

                        if (faltasd == 0) {
                            String spe = Integer.toString(faltasd);

                            Platform.runLater(() -> {

                                lfaltasD.setText("0" + spe);
                            });
                        } else {
                            faltasd = (faltasd - 1);

                            String spe = Integer.toString(faltasd);
                            if(faltasd < 10){
                            Platform.runLater(() -> {

                                lfaltasD.setText("0" + spe);
                            });
                            }else{
                               Platform.runLater(() -> {

                                lfaltasD.setText(spe);
                            }); 
                            }
                        }
                    });
                    //final da configuração das faltas

                    //configuração do periodo
                    bmaisperiodo.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                        periodo = Integer.parseInt(lPeriodo.getText());
                        periodo = (periodo + 1);

                        String per = Integer.toString(periodo);

                        Platform.runLater(() -> {

                            lPeriodo.setText(per);
                        });
                    });

                    bmenosperiodo.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                        periodo = Integer.parseInt(lPeriodo.getText());

                        if (periodo == 1) {
                            String per = Integer.toString(periodo);

                            Platform.runLater(() -> {

                                lPeriodo.setText(per);
                            });
                        } else {
                            periodo = (periodo - 1);

                            String per = Integer.toString(periodo);

                            Platform.runLater(() -> {

                                lPeriodo.setText(per);
                            });
                        }
                    });

                    return null;
                }
            }

            ;
        new Thread(t2)
        
    

    .start();

    }

    //função abaixo, pega o time que foi setado
    //na tela de configuração do placar
    public void pegarTime(String nomea, String nomeb) {

        this.lTimeA.setText(nomea);
        this.lTimeB.setText(nomeb);

    }
    // final do pega time

    public void pegarTipoBasquete(String tipo) {
        this.tipoBasquete = tipo;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //busca os dados setados na tela anterior
        // e joga eles para o metodo pegar time
        c = new FXMLSetDadosController();
        try {
            pegarTime(c.retornaTimeA(), c.retornaTimeB());
            pegarTipoBasquete(c.retornatipoBasquete());
        } catch (Exception ex) {
            System.out.println("Deu merda" + ex.getMessage());
        }

        lc = new FXMLLoginController();

        try {
            cliente(lc.retornaUser());
        } catch (Exception ex) {
            System.out.println("ex");
        }

        media = new Media(videourl);
        mediaplayer = new MediaPlayer(media);
        mvBasquete.setMediaPlayer(mediaplayer);
        mediaplayer.play();

        if (tipoBasquete.contains("FIBA")) {
            CronometroFIBA();
        }
        if (tipoBasquete.contains("NBA")) {
            CronometroNBA();
        }
        shotclock();
        PlacarFaltasEPeriodo();
    }
}
