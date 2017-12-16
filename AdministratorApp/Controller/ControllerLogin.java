package Amministratore.Controller;

import Client.Aquisizioni;
import Amministratore.Amministratore;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerLogin implements Initializable{

    @FXML TextField Username;
    @FXML TextField Password;
    @FXML Label errore = new Label();
    
    private Aquisizioni ritorno;
    public static String GlobalID;
    
    @FXML
    public void login(ActionEvent event){
        
        String username, pass;
        username = Username.getText();
        pass = Password.getText();
        
        if(!username.equals("") && !pass.equals("")){
            ritorno = Amministratore.trasmissione(new Aquisizioni(username, pass, "loginAdmin"));
            if(ritorno.getFeedBack()){
                try {
                    GlobalID = ritorno.getGlobalID();
                    new CaricaPagine().startHome();
                } catch (Exception ex) {
                    Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                errore.setVisible(true);
                errore.setText("Username o password errati");
            }
        }else{
            errore.setVisible(true);
            errore.setText("Non hai inserito l'username o la password");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
