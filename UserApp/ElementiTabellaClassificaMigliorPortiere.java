package Client;

import java.io.Serializable;

/**
 * Classe che serve per riempire la tabella della classifica del miglior portiare
 * di un determinato torneo
 */
public class ElementiTabellaClassificaMigliorPortiere implements Serializable{
    private String nomePortiere;
    private String goalPortiereSubiti;

    public ElementiTabellaClassificaMigliorPortiere(String nomePortiere, String goalPortiereSubiti) {
        this.nomePortiere = nomePortiere;
        this.goalPortiereSubiti = goalPortiereSubiti;
    }

    public String getNomePortiere() {
        return nomePortiere;
    }

    public String getGoalPortiereSubiti() {
        return goalPortiereSubiti;
    }
    
    
    
}
