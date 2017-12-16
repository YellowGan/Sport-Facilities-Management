package Amministratore.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ControllerHome implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    public void prenotazioni(ActionEvent event){
        try {
            new CaricaPagine().startSceltaCampo();
        } catch (Exception ex) {
            Logger.getLogger(ControllerHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    public void tornei(ActionEvent event){
        try {
            new CaricaPagine().startVisualizzaTornei();
        } catch (Exception ex) {
            Logger.getLogger(ControllerHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void statistiche(ActionEvent event){
        try {
            new CaricaPagine().startSceltaTorneoStatistica();
        } catch (Exception ex) {
            Logger.getLogger(ControllerHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
