package Amministratore.Controller;

import Amministratore.Amministratore;
import Client.Aquisizioni;
import Client.ElementiTabellaTornei;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControllerVisualizzaTornei implements Initializable{

    static int stato = 1;
    public static String globalTorneo;
    
    @FXML TableView<ElementiTabellaTornei> tabTornei = new TableView<ElementiTabellaTornei>();
    @FXML TableColumn tornei = new TableColumn("torneiInCorso");    
    @FXML TableColumn dataInizio = new TableColumn("dataInizio");
    @FXML TableColumn tipoTorneo = new TableColumn("tipoTorneo");
    
    ArrayList<ElementiTabellaTornei> ricevutiTornei;
    ObservableList<ElementiTabellaTornei> visualizzatiTornei;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        riempiTabTornei(stato);
        this.stato = 1;
    }
    
    @FXML
    public void tornaHome(MouseEvent event){
        try {
            new CaricaPagine().startHome();
        } catch (Exception ex) {
            Logger.getLogger(ControllerPrenotazione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void creaTorneo(ActionEvent event){
        try {
            new CaricaPagine().startCreaTorneo();
        } catch (Exception ex) {
            Logger.getLogger(ControllerVisualizzaTornei.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void torneiTerminati(ActionEvent event){
        System.out.println("torneiTerminati");
        this.stato = 0;
        try {
            new CaricaPagine().startVisualizzaTorneiTerminati();
        } catch (Exception ex) {
            Logger.getLogger(ControllerPrenotazione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void riempiTabTornei(int stato){
        Aquisizioni aqui = Amministratore.trasmissione(new Aquisizioni("tabellaTornei",stato));
        ricevutiTornei = aqui.getArrayTornei();
        
        tornei.setEditable(true);
        dataInizio.setEditable(true);
        tipoTorneo.setEditable(true);
      
        visualizzatiTornei = FXCollections.observableArrayList(ricevutiTornei);
        
        tornei.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("nomeTorneo"));
        dataInizio.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("dataInizio"));
        tipoTorneo.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("tipoTorneo"));
        
        tabTornei.setItems(visualizzatiTornei);
        ricevutiTornei.clear();
    }
     
     @FXML
    public void selezionaTorneo(){
        
        TablePosition pos = tabTornei.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ElementiTabellaTornei item = tabTornei.getItems().get(row);
        TableColumn col = pos.getTableColumn();
        globalTorneo = (String) col.getCellObservableValue(item).getValue();
        
        Aquisizioni ritorno = Amministratore.trasmissione(new Aquisizioni("restituisciIdTipoTorneo",globalTorneo)); 
        System.out.println("globaltorneo "+ritorno.getGlobalID());
        
        try {
            new CaricaPagine().startSceltaAzioneTorneo();
        } catch (Exception ex) {
            Logger.getLogger(ControllerVisualizzaTornei.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
