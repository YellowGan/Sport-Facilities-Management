package Client;

import java.io.Serializable;

/**
 * Classe che serve per riempire la tabella della classifica del capocannoniere
 * di un determninato torneo
 */
public class ElementiTabellaClassificaMarcatori implements Serializable{
    private String nomeMarcatore;
    private String goalGiocatoreFatti;
    
    public ElementiTabellaClassificaMarcatori(){
        this.nomeMarcatore = null;
        this.goalGiocatoreFatti = null;
    }

    public ElementiTabellaClassificaMarcatori(String nomeMarcatore, String goalGiocatoreFatti) {
        this.nomeMarcatore = nomeMarcatore;
        this.goalGiocatoreFatti = goalGiocatoreFatti;
    }

    public String getNomeMarcatore() {
        return nomeMarcatore;
    }

    public String getGoalGiocatoreFatti() {
        return goalGiocatoreFatti;
    }
    
    
    
}
