package Amministratore.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ControllerSceltaAzioneFasciaOraria implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    public void eliminaPrenotazione(ActionEvent event){
        try {
            CaricaPagine.avvisoStage.close();
            new CaricaPagine().startEliminaFasciaOraria();
        } catch (Exception ex) {
            Logger.getLogger(ControllerSceltaAzioneFasciaOraria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void effettuaPrenotazione(ActionEvent event){
        try {
            CaricaPagine.avvisoStage.close();
            new CaricaPagine().startAvvisoPrenotaFasciaoraria();
        } catch (Exception ex) {
            Logger.getLogger(ControllerSceltaAzioneFasciaOraria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void torna(ActionEvent event){
        CaricaPagine.avvisoStage.close();
    }
    
}
