/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxSet;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Elison
 */
public class JavaFXSetDados extends Application {
    private static Stage stage;
    private int esporte;
    public FXMLSetDadosController controller;
    
    
    

    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    @Override
    public void start(Stage stage) throws IOException {
        try{ 
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSetDados.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSetDados.fxml"));
        //Parent root = (Parent) loader.load();
        //this.controller = (FXMLSetDadosController) loader.getController();
        
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setTitle("Dados da Partida");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
        
        //System.out.println(esporte);
        //Fecha janela ao pressionar ESC
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                fecha();
            }
        });
        }catch(Exception ex){
            System.out.println(ex.getMessage()); 
        }
    }

    public int getEsporte() {
        return esporte;
    }

    public void setEsporte(int esporte) {
        this.esporte = esporte;
    }
    
    
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        JavaFXSetDados.stage = stage;
    }
    
    
    
    public void fecha() {
        JavaFXSetDados.getStage().close();
    }
    
    
    

    
}
