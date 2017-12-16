package Client;

import java.io.Serializable;

/**
 * Classe che racchiude i dati che servono per riempire la tabella che mostra la
 * classifica di un determinato campionato
 */
public class ElementiRiempiTabelleClassifica implements Serializable{
    private String nomeTorneo;
    private int i;

    public ElementiRiempiTabelleClassifica(){
        this.nomeTorneo = null;
        this.i = 0;
    }
    public ElementiRiempiTabelleClassifica(String nomeTorneo, int i) {
        this.nomeTorneo = nomeTorneo;
        this.i = i;
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public int getI() {
        return i;
    }
}
