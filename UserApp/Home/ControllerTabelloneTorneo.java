package Client.Home;

import Client.Aquisizioni;
import Client.Client;
import Client.ElementiTabellone;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * Classe che controlla la pagina dove vengono visualizzati tutte le date delle
 * partite del torneo e se le partite sono state svolte ne fa vedere il risultato
 */
public class ControllerTabelloneTorneo implements Initializable{
    private Aquisizioni ritorno;
    
    @FXML Label nomeTorneo = new Label();
    @FXML TableView<ElementiTabellone> tabellone = new TableView<ElementiTabellone>();
    @FXML TableColumn casa = new TableColumn("casa");
    @FXML TableColumn trasferta = new TableColumn("trasferta");
    @FXML TableColumn data = new TableColumn("data");
    @FXML TableColumn risultato = new TableColumn("risultato");
    
    private ArrayList<ElementiTabellone> ricevutiTabellone;
    private ObservableList<ElementiTabellone> visualizzatiTabellone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeTorneo.setText(ControllerPagine.globalTorneo);
        riempiTabella();
    }
    
    @FXML
    public void tornaIndietro(MouseEvent event){
        try {
            new CaricaPagine().startTorneo(CaricaPagine.globalIdTipoTorneo);
        } catch (Exception ex) {
            Logger.getLogger(ControllerTabelloneTorneo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Metodo che permette di riempire la tabella dove ci sono le informazioni
     * sul toreno
    */
    public void riempiTabella(){
        ritorno = Client.trasmissione(new Aquisizioni("riempiTabellone",ControllerPagine.globalTorneo));
        ricevutiTabellone = ritorno.getArrayTabellone();
        
        casa.setEditable(true);
        trasferta.setEditable(true);
        data.setEditable(true);
        risultato.setEditable(true);
        
        visualizzatiTabellone = FXCollections.observableArrayList(ricevutiTabellone);
        
        casa.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("casa"));
        trasferta.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("trasferta"));
        data.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("data"));
        risultato.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("risultato"));
        
        tabellone.setItems(visualizzatiTabellone);
        ricevutiTabellone.clear();
    }
}
