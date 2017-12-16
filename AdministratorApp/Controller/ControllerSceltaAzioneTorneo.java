package Amministratore.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ControllerSceltaAzioneTorneo implements Initializable{

    @FXML Label nomeTorneo = new Label();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeTorneo.setText(ControllerVisualizzaTornei.globalTorneo);
    }
    
    @FXML
    public void chiudiIscrizioni(ActionEvent event){
        try {
            new CaricaPagine().startAvvisoChiudiIscrizioni();
        } catch (Exception ex) {
            Logger.getLogger(ControllerSceltaAzioneTorneo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void aggiungiPartita(ActionEvent event){
        try {
            new CaricaPagine().startCreaPartita();
        } catch (Exception ex) {
            Logger.getLogger(ControllerSceltaAzioneTorneo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void torna(ActionEvent event){
        try {
            new CaricaPagine().startVisualizzaTornei();
        } catch (Exception ex) {
            Logger.getLogger(ControllerSceltaAzioneTorneo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
