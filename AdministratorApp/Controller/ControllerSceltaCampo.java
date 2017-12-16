package Amministratore.Controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ControllerSceltaCampo implements Initializable{

    public static int var;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    /**
     * I seguenti metodi (Clacio5, Clacio7 e Calcio11) si attivano cliccando sui
     * relativi pulsanti permettono di aprire la finestra di prenotazione del 
     * campo a seconda di quale si vuole scegliere, se a 5, a 7 o a 11.
     */
    @FXML
    public void Calcio5(ActionEvent event){
       
       try{
            var = 1;
            new CaricaPagine().startPrenotazione();
        } catch (Exception e){
            e.printStackTrace();            
        }       
    }
    
    @FXML
    public void Calcio7(ActionEvent event){
         try{
            var = 2;
            new CaricaPagine().startPrenotazione();
        } catch (Exception e){
            e.printStackTrace();            
        }
    }
    
    @FXML
    public void Calcio11(ActionEvent event){
         try{
            var = 3;
            new CaricaPagine().startPrenotazione();
        } catch (Exception e){
            e.printStackTrace();            
        }
    }
}
