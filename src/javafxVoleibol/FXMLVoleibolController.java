/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxVoleibol;

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
import javafx.scene.image.Image;
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
    @FXML
    public Rectangle rvideo;
    @FXML
    public Rectangle rvideo2;
    
    private int set = 0;
    private int setse = 0;
    private int setsd = 0;

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
        
        if (user.contains("cliente")) {

            v.setValue(true);
            v2.setValue(false);
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
        rvideo.visibleProperty().bindBidirectional(v2);
        rvideo2.visibleProperty().bindBidirectional(v2);
    }

    private static File file = new File("src/videos/Propaganda.mp4");
    private static final String mediaurl = file.toURI().toString();
    private MediaPlayer mediaplayer;
    private Media media;
    private static FXMLSetDadosController c;

    //configuração do cronometro
    public boolean startcron = true;
    public boolean stopc = false;
    private int segundo = 0;
    private int minuto = 0;
    private int hora = 0;

    public void iniciaCronometro() {
        Task t = new Task() {

            @Override
            protected Object call() throws Exception {
                while (startcron == true) {
                while (stopc == false) {
                        bstartcron.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            stopc = true;
                        });
                    }
                
                while (stopc == true) {
                    
                    bstopcron.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            stopc = false;
                        });
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
                }
                return null;
            }
            
        };
        new Thread(t).start();

    }

    //configuração do placar, faltas e set
    public void PlacarFaltaseSet() {
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

                        if (pontose < 10) {
                            Platform.runLater(() -> {

                                lPlacarEsquerda.setText("0" + spe);
                            });
                        } else {
                            Platform.runLater(() -> {

                                lPlacarEsquerda.setText(spe);
                            });
                        }
                    }
                    //Soma 1 ponto ao placar do time da direita
                    //ao pressionar ->
                    if (event.getCode().equals(KeyCode.RIGHT)) {

                        pontosd = Integer.parseInt(lPlacarDireita.getText());
                        pontosd = (pontosd + 1);
                        String spd = Integer.toString(pontosd);
                        if (pontosd < 10) {
                            Platform.runLater(() -> {

                                lPlacarDireita.setText("0" + spd);
                            });
                        } else {
                            Platform.runLater(() -> {

                                lPlacarDireita.setText(spd);
                            });
                        }
                    }
                });
                //Soma 1 ponto ao placar do time da esquerda
                //ao pressionar o botão +
                bmaispontosesquerda.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontose = Integer.parseInt(lPlacarEsquerda.getText());
                    pontose = (pontose + 1);

                    String spe = Integer.toString(pontose);
                    if (pontose < 10) {
                        Platform.runLater(() -> {

                            lPlacarEsquerda.setText("0" + spe);
                        });
                    } else {
                        Platform.runLater(() -> {

                            lPlacarEsquerda.setText(spe);
                        });
                    }
                });

                //Soma 1 ponto ao placar do time da direita
                //ao pressionar o botão +
                bmaispontosdireita.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontosd = Integer.parseInt(lPlacarDireita.getText());
                    pontosd = (pontosd + 1);

                    String spd = Integer.toString(pontosd);
                    if (pontosd < 10) {
                        Platform.runLater(() -> {

                            lPlacarDireita.setText("0" + spd);
                        });
                    } else {
                        Platform.runLater(() -> {

                            lPlacarDireita.setText(spd);
                        });
                    }
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
                        if (pontose < 10) {
                            Platform.runLater(() -> {

                                lPlacarEsquerda.setText("0" + spe);
                            });
                        } else {
                            Platform.runLater(() -> {

                                lPlacarEsquerda.setText(spe);
                            });
                        }
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
                        if (pontosd < 10) {
                            Platform.runLater(() -> {

                                lPlacarDireita.setText("0" + spd);
                            });
                        } else {
                            Platform.runLater(() -> {

                                lPlacarDireita.setText(spd);
                            });
                        }
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
                bmaisfaltaesquerda.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    faltase = Integer.parseInt(lfaltasesquerda.getText());
                    faltase = (faltase + 1);

                    String spe = Integer.toString(faltase);
                    
                        Platform.runLater(() -> {

                            lfaltasesquerda.setText(spe);
                        });
                    
                });

                //Soma 1 falta ao time da direita
                //ao pressionar o botão +
                bmaisfaltadireita.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    faltasd = Integer.parseInt(lfaltasdireita.getText());
                    faltasd = (faltasd + 1);

                    String spd = Integer.toString(faltasd);
                    
                        Platform.runLater(() -> {

                            lfaltasdireita.setText(spd);
                        });
                    
                });

                //Subtrai uma falta do time da esquerda
                //ao pressionar o botão -
                bmenosfaltaesquerda.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    faltase = Integer.parseInt(lfaltasesquerda.getText());

                    if (faltase == 0) {
                        String spe = Integer.toString(faltase);

                        Platform.runLater(() -> {

                            lfaltasesquerda.setText(spe);
                        });
                    } else {
                        faltase = (faltase - 1);

                        String spe = Integer.toString(faltase);
                        
                            Platform.runLater(() -> {

                                lfaltasesquerda.setText(spe);
                            });
                        
                    }
                });

                //Subtrai uma falta do time da direita
                //ao pressionar o botão -
                bmenosfaltadireita.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    faltasd = Integer.parseInt(lfaltasdireita.getText());

                    if (faltasd == 0) {
                        String spd = Integer.toString(faltasd);

                        Platform.runLater(() -> {

                            lfaltasdireita.setText(spd);
                        });
                    } else {
                        faltasd = (faltasd - 1);

                        String spd = Integer.toString(faltasd);
                        
                            Platform.runLater(() -> {

                                lfaltasdireita.setText(spd);
                            });
                        
                    }
                });
                //final da configuração das faltas
                
                //configuração do set
                bmaisset.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    set = Integer.parseInt(lSet.getText());
                    set = (set + 1);

                    String s = Integer.toString(set);

                    Platform.runLater(() -> {

                        lSet.setText(s);
                    });
                });

                bmenosset.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    set = Integer.parseInt(lSet.getText());

                    if (set == 0) {
                        String s = Integer.toString(set);

                        Platform.runLater(() -> {

                            lSet.setText(s);
                        });
                    } else {
                        set = (set - 1);

                        String s = Integer.toString(set);

                        Platform.runLater(() -> {

                            lSet.setText(s);
                        });
                    }
                });
                // fim configuração periodo    

                return null;
            }
        };
        new Thread(t2).start();

    }
    //final da configuração do placar
    
    //configuração dos sets de cada time e do botão fim do set
    public void SetseFimdoset() {
        Task t3 = new Task() {
            @Override
            protected Object call() throws Exception {

                //inicio configuração dos sets
                //Soma 1 set ao time da esquerda
                //ao pressionar o botão +
                bmaissetesquerda.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    setse = Integer.parseInt(lSetsEsquerda.getText());
                    setse = (setse + 1);

                    String s = Integer.toString(setse);
                    
                        Platform.runLater(() -> {

                            lSetsEsquerda.setText(s);
                        });
                    
                });

                //Soma 1 set ao time da direita
                //ao pressionar o botão +
                bmaissetdireita.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    setsd = Integer.parseInt(lSetsDireita.getText());
                    setsd = (setsd + 1);

                    String s = Integer.toString(setsd);
                    
                        Platform.runLater(() -> {

                            lSetsDireita.setText(s);
                        });
                    
                });

                //Subtrai 1 set do time da esquerda
                //ao pressionar o botão -
                bmenossetesquerda.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    setse = Integer.parseInt(lSetsEsquerda.getText());
                    setse = (setse - 1);

                    String s = Integer.toString(setse);
                    
                        Platform.runLater(() -> {

                            lSetsEsquerda.setText(s);
                        });
                });

                //Subtrai 1 set do time da direita
                //ao pressionar o botão -
                bmenossetdireita.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    setsd = Integer.parseInt(lSetsDireita.getText());
                    setsd = (setsd - 1);

                    String s = Integer.toString(setsd);
                    
                        Platform.runLater(() -> {

                            lSetsDireita.setText(s);
                        });
                });
                //final configuração sets
                
                //configuração botao fim do set
                
                bfimdoset.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    
                    stopc = false;
                    segundo = 0;
                    minuto = 0;
                    hora = 0;
                    
                String pe;
                    String pd;
                    pontose = Integer.parseInt(lPlacarEsquerda.getText());
                    
                        pe = lPlacarEsquerda.getText();
                    
                    
                    pontosd = Integer.parseInt(lPlacarDireita.getText());
                    
                        pd = lPlacarDireita.getText();
                    
                    set = Integer.parseInt(lSet.getText());
                    
                    if(set == 1){  
                        
                        Platform.runLater(() -> {

                            lPrimeiroSet.setText(pe +" X "+ pd);
                        });
                        
                    }
                    if(set == 2){  
                        
                        Platform.runLater(() -> {

                            lSegundoSet.setText(pe +" X "+ pd);
                        });
                        
                    }    
                    if(set == 3){  
                        
                        Platform.runLater(() -> {

                            lTerceiroSet.setText(pe +" X "+ pd);
                        });
                        
                    }
                    if(set == 4){  
                        
                        Platform.runLater(() -> {

                            lQuartoSet.setText(pe +" X "+ pd);
                        });
                        
                    }
                    lCronometroVolei.setText("00:00:00");
                    lPlacarDireita.setText("00");
                    lPlacarEsquerda.setText("00");
                    lfaltasdireita.setText("0");
                    lfaltasesquerda.setText("0");
                });

                return null;
            }
        };
        new Thread(t3).start();

    }

    //função abaixo, pega o time que foi setado
    //na tela de configuração do placar
    public void pegarTime(String nomea, String nomeb) {

        this.lTimeEsquerda.setText(nomea);
        this.lTimeDireita.setText(nomeb);

    }
    // final do pega time

    public void pegarBrasao(String brasaoesquerdo, String brasaodireito) {
        Image besquerdo = new Image(brasaoesquerdo);
        Image bdireito = new Image(brasaodireito);
        this.ivTimeEsquerda.setImage(besquerdo);
        this.ivTimeDireita.setImage(bdireito);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //busca os dados setados na tela anterior
        // e joga eles para o metodo pegar time
        c = new FXMLSetDadosController();
        try {
            pegarTime(c.retornaTimeA(), c.retornaTimeB());
            pegarBrasao(c.retornaBrasaoDireito(), c.retornaBrasaoEsquerdo());
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
        
        if(lc.retornaUser().equals("admin")){
        media = new Media(mediaurl);
        mediaplayer = new MediaPlayer(media);
        mvDireitaVolei.setMediaPlayer(mediaplayer);
        mvEsquerdaVolei.setMediaPlayer(mediaplayer);
        mediaplayer.play();
        }
        iniciaCronometro();
        PlacarFaltaseSet();
        SetseFimdoset();
    }

}
