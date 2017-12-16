package Amministratore.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ControllerAvvisiCreaTorneo implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    public void okno(ActionEvent event){
        CaricaPagine.avvisoStage.close();
    }
    
    @FXML
    public void ok(ActionEvent event){
        try {
            new CaricaPagine().startVisualizzaTornei();
            CaricaPagine.avvisoStage.close();
        } catch (Exception ex) {
            Logger.getLogger(ControllerAvvisiCreaTorneo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
