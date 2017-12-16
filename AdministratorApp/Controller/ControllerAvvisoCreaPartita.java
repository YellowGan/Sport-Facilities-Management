package Amministratore.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ControllerAvvisoCreaPartita implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    public void annulla(ActionEvent event){
        try {
            CaricaPagine.avvisoStage.close();
            new CaricaPagine().startVisualizzaTornei();
        } catch (Exception ex) {
            Logger.getLogger(ControllerCreaPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
