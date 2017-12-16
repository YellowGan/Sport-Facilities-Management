package Amministratore.Controller;

import Amministratore.Amministratore;
import Client.Aquisizioni;
import Client.ElementiStatisticheGiocatore;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerStatisticheTorneo implements Initializable{
    
    @FXML Label nomeTorneo = new Label();
    @FXML Label nomeCasa = new Label();
    @FXML Label nomeTrasferta = new Label();
    @FXML Label giocatoreCasa1 = new Label();
    @FXML Label giocatoreCasa2 = new Label();
    @FXML Label giocatoreCasa3 = new Label();
    @FXML Label giocatoreCasa4 = new Label();
    @FXML Label giocatoreCasa5 = new Label();
    @FXML Label giocatoreCasa6 = new Label();
    @FXML Label giocatoreCasa7 = new Label();
    @FXML Label giocatoreCasa8 = new Label();
    @FXML Label giocatoreCasa9 = new Label();
    @FXML Label giocatoreCasa10 = new Label();
    @FXML Label giocatoreCasa11 = new Label();
    @FXML Label giocatoreCasa12 = new Label();
    @FXML Label giocatoreCasa13 = new Label();
    @FXML Label giocatoreCasa14 = new Label();
    @FXML Label giocatoreTrasferta1 = new Label();
    @FXML Label giocatoreTrasferta2 = new Label();
    @FXML Label giocatoreTrasferta3 = new Label();
    @FXML Label giocatoreTrasferta4 = new Label();
    @FXML Label giocatoreTrasferta5 = new Label();
    @FXML Label giocatoreTrasferta6 = new Label();
    @FXML Label giocatoreTrasferta7 = new Label();
    @FXML Label giocatoreTrasferta8 = new Label();
    @FXML Label giocatoreTrasferta9 = new Label();
    @FXML Label giocatoreTrasferta10 = new Label();
    @FXML Label giocatoreTrasferta11 = new Label();
    @FXML Label giocatoreTrasferta12 = new Label();
    @FXML Label giocatoreTrasferta13 = new Label();
    @FXML Label giocatoreTrasferta14 = new Label();
    @FXML TextField risultatoCasa = new TextField();
    @FXML TextField risultatoTrasferta = new TextField();
    @FXML TextField goalFattiCasa1 = new TextField();
    @FXML TextField goalFattiCasa2 = new TextField();
    @FXML TextField goalFattiCasa3 = new TextField();
    @FXML TextField goalFattiCasa4 = new TextField();
    @FXML TextField goalFattiCasa5 = new TextField();
    @FXML TextField goalFattiCasa6 = new TextField();
    @FXML TextField goalFattiCasa7 = new TextField();
    @FXML TextField goalFattiCasa8 = new TextField();
    @FXML TextField goalFattiCasa9 = new TextField();
    @FXML TextField goalFattiCasa10 = new TextField();
    @FXML TextField goalFattiCasa11 = new TextField();
    @FXML TextField goalFattiCasa12 = new TextField();
    @FXML TextField goalFattiCasa13 = new TextField();
    @FXML TextField goalFattiCasa14 = new TextField();
    @FXML TextField goalFattiTrasferta1 = new TextField();
    @FXML TextField goalFattiTrasferta2 = new TextField();
    @FXML TextField goalFattiTrasferta3 = new TextField();
    @FXML TextField goalFattiTrasferta4 = new TextField();
    @FXML TextField goalFattiTrasferta5 = new TextField();
    @FXML TextField goalFattiTrasferta6 = new TextField();
    @FXML TextField goalFattiTrasferta7 = new TextField();
    @FXML TextField goalFattiTrasferta8 = new TextField();
    @FXML TextField goalFattiTrasferta9 = new TextField();
    @FXML TextField goalFattiTrasferta10 = new TextField();
    @FXML TextField goalFattiTrasferta11 = new TextField();
    @FXML TextField goalFattiTrasferta12 = new TextField();
    @FXML TextField goalFattiTrasferta13 = new TextField();
    @FXML TextField goalFattiTrasferta14 = new TextField();
    @FXML TextField goalSubitiCasa1 = new TextField();
    @FXML TextField goalSubitiCasa2 = new TextField();
    @FXML TextField goalSubitiCasa3 = new TextField();
    @FXML TextField goalSubitiCasa4 = new TextField();
    @FXML TextField goalSubitiCasa5 = new TextField();
    @FXML TextField goalSubitiCasa6 = new TextField();
    @FXML TextField goalSubitiCasa7 = new TextField();
    @FXML TextField goalSubitiCasa8 = new TextField();
    @FXML TextField goalSubitiCasa9 = new TextField();
    @FXML TextField goalSubitiCasa10 = new TextField();
    @FXML TextField goalSubitiCasa11 = new TextField();
    @FXML TextField goalSubitiCasa12 = new TextField();
    @FXML TextField goalSubitiCasa13 = new TextField();
    @FXML TextField goalSubitiCasa14 = new TextField();
    @FXML TextField goalSubitiTrasferta1 = new TextField();
    @FXML TextField goalSubitiTrasferta2 = new TextField();
    @FXML TextField goalSubitiTrasferta3 = new TextField();
    @FXML TextField goalSubitiTrasferta4 = new TextField();
    @FXML TextField goalSubitiTrasferta5 = new TextField();
    @FXML TextField goalSubitiTrasferta6 = new TextField();
    @FXML TextField goalSubitiTrasferta7 = new TextField();
    @FXML TextField goalSubitiTrasferta8 = new TextField();
    @FXML TextField goalSubitiTrasferta9 = new TextField();
    @FXML TextField goalSubitiTrasferta10 = new TextField();
    @FXML TextField goalSubitiTrasferta11 = new TextField();
    @FXML TextField goalSubitiTrasferta12 = new TextField();
    @FXML TextField goalSubitiTrasferta13 = new TextField();
    @FXML TextField goalSubitiTrasferta14 = new TextField();
    @FXML TextField ammonitiCasa1 = new TextField();
    @FXML TextField ammonitiCasa2 = new TextField();
    @FXML TextField ammonitiCasa3 = new TextField();
    @FXML TextField ammonitiCasa4 = new TextField();
    @FXML TextField ammonitiCasa5 = new TextField();
    @FXML TextField ammonitiCasa6 = new TextField();
    @FXML TextField ammonitiCasa7 = new TextField();
    @FXML TextField ammonitiCasa8 = new TextField();
    @FXML TextField ammonitiCasa9 = new TextField();
    @FXML TextField ammonitiCasa10 = new TextField();
    @FXML TextField ammonitiCasa11 = new TextField();
    @FXML TextField ammonitiCasa12 = new TextField();
    @FXML TextField ammonitiCasa13 = new TextField();
    @FXML TextField ammonitiCasa14 = new TextField();
    @FXML TextField ammonitiTrasferta1 = new TextField();
    @FXML TextField ammonitiTrasferta2 = new TextField();
    @FXML TextField ammonitiTrasferta3 = new TextField();
    @FXML TextField ammonitiTrasferta4 = new TextField();
    @FXML TextField ammonitiTrasferta5 = new TextField();
    @FXML TextField ammonitiTrasferta6 = new TextField();
    @FXML TextField ammonitiTrasferta7 = new TextField();
    @FXML TextField ammonitiTrasferta8 = new TextField();
    @FXML TextField ammonitiTrasferta9 = new TextField();
    @FXML TextField ammonitiTrasferta10 = new TextField();
    @FXML TextField ammonitiTrasferta11 = new TextField();
    @FXML TextField ammonitiTrasferta12 = new TextField();
    @FXML TextField ammonitiTrasferta13 = new TextField();
    @FXML TextField ammonitiTrasferta14 = new TextField();
    @FXML TextField espulsoCasa1 = new TextField();
    @FXML TextField espulsoCasa2 = new TextField();
    @FXML TextField espulsoCasa3 = new TextField();
    @FXML TextField espulsoCasa4 = new TextField();
    @FXML TextField espulsoCasa5 = new TextField();
    @FXML TextField espulsoCasa6 = new TextField();
    @FXML TextField espulsoCasa7 = new TextField();
    @FXML TextField espulsoCasa8 = new TextField();
    @FXML TextField espulsoCasa9 = new TextField();
    @FXML TextField espulsoCasa10 = new TextField();
    @FXML TextField espulsoCasa11 = new TextField();
    @FXML TextField espulsoCasa12 = new TextField();
    @FXML TextField espulsoCasa13 = new TextField();
    @FXML TextField espulsoCasa14 = new TextField();
    @FXML TextField espulsoTrasferta1 = new TextField();
    @FXML TextField espulsoTrasferta2 = new TextField();
    @FXML TextField espulsoTrasferta3 = new TextField();
    @FXML TextField espulsoTrasferta4 = new TextField();
    @FXML TextField espulsoTrasferta5 = new TextField();
    @FXML TextField espulsoTrasferta6 = new TextField();
    @FXML TextField espulsoTrasferta7 = new TextField();
    @FXML TextField espulsoTrasferta8 = new TextField();
    @FXML TextField espulsoTrasferta9 = new TextField();
    @FXML TextField espulsoTrasferta10 = new TextField();
    @FXML TextField espulsoTrasferta11 = new TextField();
    @FXML TextField espulsoTrasferta12 = new TextField();
    @FXML TextField espulsoTrasferta13 = new TextField();
    @FXML TextField espulsoTrasferta14 = new TextField();
    
    //Squadra casa
    private ArrayList<ArrayList> casa = new ArrayList<ArrayList>();
    private ArrayList<Label> giocatoriCasa = new ArrayList<Label>();
    private ArrayList<TextField> goalFattiCasa = new ArrayList<TextField>();
    private ArrayList<TextField> goalSubitiCasa = new ArrayList<TextField>();
    private ArrayList<TextField> ammonitiCasa = new ArrayList<TextField>();
    private ArrayList<TextField> espulsiCasa = new ArrayList<TextField>();
    //Squadra trasferta
    private ArrayList<ArrayList> trasferta = new ArrayList<ArrayList>();
    private ArrayList<Label> giocatoriTrasferta = new ArrayList<Label>();
    private ArrayList<TextField> goalFattiTrasferta = new ArrayList<TextField>();
    private ArrayList<TextField> goalSubitiTrasferta = new ArrayList<TextField>();
    private ArrayList<TextField> ammonitiTrasferta = new ArrayList<TextField>();
    private ArrayList<TextField> espulsiTrasferta = new ArrayList<TextField>();
    
    private Aquisizioni ritorno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inizializzazioneArrayList();
        inizializzaLabelPagina();
    }
    
    @FXML
    public void salva(ActionEvent event){
        int j;
        String nomeGiocatore, GF, GS, CG, CR;
        ArrayList<ElementiStatisticheGiocatore> giocatoreCasa = new ArrayList<ElementiStatisticheGiocatore>();
        ArrayList<ElementiStatisticheGiocatore> giocatoreTrasferta = new ArrayList<ElementiStatisticheGiocatore>();
        
        for(j = 0; j < 14; j++){
            Label l = (Label) trasferta.get(0).get(j);
            System.out.println("giocatore: "+l.getText());
            if(l.getText().equals("")) break;
            nomeGiocatore = l.getText();
            TextField t = (TextField) trasferta.get(1).get(j);
            GF =  t.getText();
            t = (TextField) trasferta.get(2).get(j);
            GS = t.getText();
            t = (TextField) trasferta.get(3).get(j);
            CG = t.getText();
            t = (TextField) trasferta.get(4).get(j);
            CR = t.getText();
            giocatoreTrasferta.add(new ElementiStatisticheGiocatore(ControllerVisualizzaCalendarioTorneo.GlobalIdPartita,nomeGiocatore,GF,GS,CG,CR,nomeTrasferta.getText(),risultatoCasa.getText()));
        }   
        
        for(j = 0; j < 14; j++){
            Label l = (Label) casa.get(0).get(j);
            if(l.getText().equals("")) break;
            nomeGiocatore = l.getText();
            TextField t = (TextField) casa.get(1).get(j);
            GF =  t.getText();
            t = (TextField) casa.get(2).get(j);
            GS = t.getText();
            t = (TextField) casa.get(3).get(j);
            CG = t.getText();
            t = (TextField) casa.get(4).get(j);
            CR = t.getText();
            giocatoreCasa.add(new ElementiStatisticheGiocatore(ControllerVisualizzaCalendarioTorneo.GlobalIdPartita,nomeGiocatore,GF,GS,CG,CR,nomeCasa.getText(),risultatoTrasferta.getText()));
        }
        ritorno = Amministratore.trasmissione(new Aquisizioni(giocatoreCasa,giocatoreTrasferta,"inserisciStatistiche"));
        if(ritorno.getFeedBack())
            System.out.println("apposto");
        else
            System.out.println("non apposto");
    }
    
   
    
    @FXML
    public void annulla(ActionEvent event){
        try {
            new CaricaPagine().startHome();
        } catch (Exception ex) {
            Logger.getLogger(ControllerStatisticheTorneo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inizializzazioneArrayList() {
        giocatoriCasa.add(giocatoreCasa1);
        giocatoriCasa.add(giocatoreCasa2);
        giocatoriCasa.add(giocatoreCasa3);
        giocatoriCasa.add(giocatoreCasa4);
        giocatoriCasa.add(giocatoreCasa5);
        giocatoriCasa.add(giocatoreCasa6);
        giocatoriCasa.add(giocatoreCasa7);
        giocatoriCasa.add(giocatoreCasa8);
        giocatoriCasa.add(giocatoreCasa9);
        giocatoriCasa.add(giocatoreCasa10);
        giocatoriCasa.add(giocatoreCasa11);
        giocatoriCasa.add(giocatoreCasa12);
        giocatoriCasa.add(giocatoreCasa13);
        giocatoriCasa.add(giocatoreCasa14);
        
        giocatoriTrasferta.add(giocatoreTrasferta1);
        giocatoriTrasferta.add(giocatoreTrasferta2);
        giocatoriTrasferta.add(giocatoreTrasferta3);
        giocatoriTrasferta.add(giocatoreTrasferta4);
        giocatoriTrasferta.add(giocatoreTrasferta5);
        giocatoriTrasferta.add(giocatoreTrasferta6);
        giocatoriTrasferta.add(giocatoreTrasferta7);
        giocatoriTrasferta.add(giocatoreTrasferta8);
        giocatoriTrasferta.add(giocatoreTrasferta9);
        giocatoriTrasferta.add(giocatoreTrasferta10);
        giocatoriTrasferta.add(giocatoreTrasferta11);
        giocatoriTrasferta.add(giocatoreTrasferta12);
        giocatoriTrasferta.add(giocatoreTrasferta13);
        giocatoriTrasferta.add(giocatoreTrasferta14);
        
        goalFattiCasa.add(goalFattiCasa1);
        goalFattiCasa.add(goalFattiCasa2);
        goalFattiCasa.add(goalFattiCasa3);
        goalFattiCasa.add(goalFattiCasa4);
        goalFattiCasa.add(goalFattiCasa5);
        goalFattiCasa.add(goalFattiCasa6);
        goalFattiCasa.add(goalFattiCasa7);
        goalFattiCasa.add(goalFattiCasa8);
        goalFattiCasa.add(goalFattiCasa9);
        goalFattiCasa.add(goalFattiCasa10);
        goalFattiCasa.add(goalFattiCasa11);
        goalFattiCasa.add(goalFattiCasa12);
        goalFattiCasa.add(goalFattiCasa13);
        goalFattiCasa.add(goalFattiCasa14);
        
        goalFattiTrasferta.add(goalFattiTrasferta1);
        goalFattiTrasferta.add(goalFattiTrasferta2);
        goalFattiTrasferta.add(goalFattiTrasferta3);
        goalFattiTrasferta.add(goalFattiTrasferta4);
        goalFattiTrasferta.add(goalFattiTrasferta5);
        goalFattiTrasferta.add(goalFattiTrasferta6);
        goalFattiTrasferta.add(goalFattiTrasferta7);
        goalFattiTrasferta.add(goalFattiTrasferta8);
        goalFattiTrasferta.add(goalFattiTrasferta9);
        goalFattiTrasferta.add(goalFattiTrasferta10);
        goalFattiTrasferta.add(goalFattiTrasferta11);
        goalFattiTrasferta.add(goalFattiTrasferta12);
        goalFattiTrasferta.add(goalFattiTrasferta13);
        goalFattiTrasferta.add(goalFattiTrasferta14);
        
        goalSubitiCasa.add(goalSubitiCasa1);
        goalSubitiCasa.add(goalSubitiCasa2);
        goalSubitiCasa.add(goalSubitiCasa3);
        goalSubitiCasa.add(goalSubitiCasa4);
        goalSubitiCasa.add(goalSubitiCasa5);
        goalSubitiCasa.add(goalSubitiCasa6);
        goalSubitiCasa.add(goalSubitiCasa7);
        goalSubitiCasa.add(goalSubitiCasa8);
        goalSubitiCasa.add(goalSubitiCasa9);
        goalSubitiCasa.add(goalSubitiCasa10);
        goalSubitiCasa.add(goalSubitiCasa11);
        goalSubitiCasa.add(goalSubitiCasa12);
        goalSubitiCasa.add(goalSubitiCasa13);
        goalSubitiCasa.add(goalSubitiCasa14);
        
        goalSubitiTrasferta.add(goalSubitiTrasferta1);
        goalSubitiTrasferta.add(goalSubitiTrasferta2);
        goalSubitiTrasferta.add(goalSubitiTrasferta3);
        goalSubitiTrasferta.add(goalSubitiTrasferta4);
        goalSubitiTrasferta.add(goalSubitiTrasferta5);
        goalSubitiTrasferta.add(goalSubitiTrasferta6);
        goalSubitiTrasferta.add(goalSubitiTrasferta7);
        goalSubitiTrasferta.add(goalSubitiTrasferta8);
        goalSubitiTrasferta.add(goalSubitiTrasferta9);
        goalSubitiTrasferta.add(goalSubitiTrasferta10);
        goalSubitiTrasferta.add(goalSubitiTrasferta11);
        goalSubitiTrasferta.add(goalSubitiTrasferta12);
        goalSubitiTrasferta.add(goalSubitiTrasferta13);
        goalSubitiTrasferta.add(goalSubitiTrasferta14);
        
        ammonitiCasa.add(ammonitiCasa1);
        ammonitiCasa.add(ammonitiCasa2);
        ammonitiCasa.add(ammonitiCasa3);
        ammonitiCasa.add(ammonitiCasa4);
        ammonitiCasa.add(ammonitiCasa5);
        ammonitiCasa.add(ammonitiCasa6);
        ammonitiCasa.add(ammonitiCasa7);
        ammonitiCasa.add(ammonitiCasa8);
        ammonitiCasa.add(ammonitiCasa9);
        ammonitiCasa.add(ammonitiCasa10);
        ammonitiCasa.add(ammonitiCasa11);
        ammonitiCasa.add(ammonitiCasa12);
        ammonitiCasa.add(ammonitiCasa13);
        ammonitiCasa.add(ammonitiCasa14);
        
        ammonitiTrasferta.add(ammonitiTrasferta1);
        ammonitiTrasferta.add(ammonitiTrasferta2);
        ammonitiTrasferta.add(ammonitiTrasferta3);
        ammonitiTrasferta.add(ammonitiTrasferta4);
        ammonitiTrasferta.add(ammonitiTrasferta5);
        ammonitiTrasferta.add(ammonitiTrasferta6);
        ammonitiTrasferta.add(ammonitiTrasferta7);
        ammonitiTrasferta.add(ammonitiTrasferta8);
        ammonitiTrasferta.add(ammonitiTrasferta9);
        ammonitiTrasferta.add(ammonitiTrasferta10);
        ammonitiTrasferta.add(ammonitiTrasferta11);
        ammonitiTrasferta.add(ammonitiTrasferta12);
        ammonitiTrasferta.add(ammonitiTrasferta13);
        ammonitiTrasferta.add(ammonitiTrasferta14);
        
        espulsiCasa.add(espulsoCasa1);
        espulsiCasa.add(espulsoCasa2);
        espulsiCasa.add(espulsoCasa3);
        espulsiCasa.add(espulsoCasa4);
        espulsiCasa.add(espulsoCasa5);
        espulsiCasa.add(espulsoCasa6);
        espulsiCasa.add(espulsoCasa7);
        espulsiCasa.add(espulsoCasa8);
        espulsiCasa.add(espulsoCasa9);
        espulsiCasa.add(espulsoCasa10);
        espulsiCasa.add(espulsoCasa11);
        espulsiCasa.add(espulsoCasa12);
        espulsiCasa.add(espulsoCasa13);
        espulsiCasa.add(espulsoCasa14);
        
        espulsiTrasferta.add(espulsoTrasferta1);
        espulsiTrasferta.add(espulsoTrasferta2);
        espulsiTrasferta.add(espulsoTrasferta3);
        espulsiTrasferta.add(espulsoTrasferta4);
        espulsiTrasferta.add(espulsoTrasferta5);
        espulsiTrasferta.add(espulsoTrasferta6);
        espulsiTrasferta.add(espulsoTrasferta7);
        espulsiTrasferta.add(espulsoTrasferta8);
        espulsiTrasferta.add(espulsoTrasferta9);
        espulsiTrasferta.add(espulsoTrasferta10);
        espulsiTrasferta.add(espulsoTrasferta11);
        espulsiTrasferta.add(espulsoTrasferta12);
        espulsiTrasferta.add(espulsoTrasferta13);
        espulsiTrasferta.add(espulsoTrasferta14);
        
        casa.add(giocatoriCasa);
        casa.add(goalFattiCasa);
        casa.add(goalSubitiCasa);
        casa.add(ammonitiCasa);
        casa.add(espulsiCasa);
        
        trasferta.add(giocatoriTrasferta);
        trasferta.add(goalFattiTrasferta);
        trasferta.add(goalSubitiTrasferta);
        trasferta.add(ammonitiTrasferta);
        trasferta.add(espulsiTrasferta);
        
        
    }

    private void inizializzaLabelPagina() {
        int i = 0;
        nomeTorneo.setText(ControllerSceltaTorneoStatistica.globalNomeTorneoStatistica);
        ritorno = Amministratore.trasmissione(new Aquisizioni("restituisciNomiSquadreStatistiche", ControllerVisualizzaCalendarioTorneo.GlobalIdPartita));
        nomeCasa.setText(ritorno.getNomiSquadreStatisticheTorneo().getNomeCasa());
        nomeTrasferta.setText(ritorno.getNomiSquadreStatisticheTorneo().getNomeTrasferta());
        ritorno = Amministratore.trasmissione(new Aquisizioni("restituisciNomiGiocatoriSquadreStatistiche", ControllerVisualizzaCalendarioTorneo.GlobalIdPartita));
        for(i=0; i<ritorno.getArrayNomiGiocatoriCasa().size(); i++){
            Label l = (Label) casa.get(0).get(i);
            l.setText((String) ritorno.getArrayNomiGiocatoriCasa().get(i)); 
            l = (Label) trasferta.get(0).get(i);
            l.setText((String) ritorno.getArrayNomiGiocatoriTrasferta().get(i)); 
        }
    }
}

