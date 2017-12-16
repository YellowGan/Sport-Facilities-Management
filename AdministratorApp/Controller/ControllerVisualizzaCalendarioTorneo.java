package Amministratore.Controller;

import Amministratore.Amministratore;
import Client.Aquisizioni;
import Client.ElementiTabellaCalendarioStatistiche;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControllerVisualizzaCalendarioTorneo implements Initializable{

    @FXML Label nomeTorneo = new Label();
    @FXML TableView<ElementiTabellaCalendarioStatistiche> tabCalendarioStatistiche = new TableView<ElementiTabellaCalendarioStatistiche>();
    @FXML TableColumn idPartita = new TableColumn();
    @FXML TableColumn squadraCasa = new TableColumn();
    @FXML TableColumn squadraTrasferta = new TableColumn();
    @FXML TableColumn data = new TableColumn();
    @FXML TableColumn risultatoPartita = new TableColumn();
    
    private ArrayList<ElementiTabellaCalendarioStatistiche> ricevuti;
    private ObservableList<ElementiTabellaCalendarioStatistiche> visualizzati;
    private Aquisizioni ritorno;
    public static String GlobalIdPartita;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeTorneo.setText(ControllerSceltaTorneoStatistica.globalNomeTorneoStatistica);
        riempiTabella();
    }
    
    @FXML
    public void tornaHome(MouseEvent event){
        try {
            new CaricaPagine().startHome();
        } catch (Exception ex) {
            Logger.getLogger(ControllerVisualizzaCalendarioTorneo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void modificaStatistiche(){
        
        System.out.println("modificaStatistiche");
        TablePosition pos = tabCalendarioStatistiche.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ElementiTabellaCalendarioStatistiche item = tabCalendarioStatistiche.getItems().get(row);
        TableColumn col = pos.getTableColumn();
        GlobalIdPartita = (String) col.getCellObservableValue(item).getValue();
        ritorno = Amministratore.trasmissione(new Aquisizioni("controlloPartitaSvolta",GlobalIdPartita));
        if(ritorno.getFeedBack()){
            try {
                new CaricaPagine().startAvvisoPartitaNonModificabile();
            } catch (Exception ex) {
                Logger.getLogger(ControllerVisualizzaCalendarioTorneo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                new CaricaPagine().startStatisticheTorneo();
            } catch (Exception ex) {
                Logger.getLogger(ControllerVisualizzaCalendarioTorneo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void riempiTabella(){
        ritorno = Amministratore.trasmissione(new Aquisizioni("riempiTabellaCalendarioStatistiche",ControllerSceltaTorneoStatistica.globalNomeTorneoStatistica));
        ricevuti = ritorno.getArrayElementiTabellaCalendarioStatistiche();
        
        this.idPartita.setEditable(true);
        this.squadraCasa.setEditable(true);
        this.squadraTrasferta.setEditable(true);
        this.data.setEditable(true);
        this.risultatoPartita.setEditable(true);
        
        visualizzati = FXCollections.observableArrayList(ricevuti);
        
        idPartita.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("idPartita"));
        squadraCasa.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("Casa"));
        squadraTrasferta.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("Trasferta"));
        data.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("data"));
        risultatoPartita.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("risultato"));
        
        tabCalendarioStatistiche.setItems(visualizzati);
        ricevuti.clear();   
    }
    
    @FXML
    public void ok(ActionEvent evetn){
        CaricaPagine.avvisoStage.close();
    }
    
}
