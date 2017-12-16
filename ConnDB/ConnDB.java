package ConnDB;

import java.sql.*;

public class ConnDB 
{
	private String driver;
	private String url;
	private String username;
	private String Password;
	private String nomeDB;
	private Connection conn = null;
	
    /**
     * Costruttore dove al suo interno si definiscono gli elementi che servono 
     * per la connessione al database tra cui il driver, l'url, il nome del
     * database, l'username e la password
     */
	public ConnDB()
    {
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost/";
        this.nomeDB = "campi_yellow";
        this.username = "username";
        this.Password = "password";
	}
	
    /**
     * Metodo che permette la connessione al database
     */
	public void connessione()
    {	
        try
        {
            getClass().forName(this.driver).newInstance();
            this.conn = DriverManager.getConnection(url+nomeDB, this.username,this.Password);
            System.out.println("Il database è connesso");
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }	
	}
	
    /**
     * Metodo che permette di disconnettere il server dal database
     */
	public void disconnessione() throws SQLException
    {
        conn.close();	
        System.out.println("Database disconnesso");
	}
	
    /**
     * Metodo che permette di interrogare il database con delle query a seconda
     * dell'operazione che il server deve compiere
     */
	public ResultSet interrogazione(String query)
    {
        ResultSet risultato = null;
        try
        {
            Statement state = conn.createStatement();
            risultato = state.executeQuery(query);
        } 
        catch (Exception e)
        {
            System.out.println("Errore nella query");
        }
        return risultato;
	}
        
    /**
     * Metodo che permette di aggiornare il database
     */
    public void inserimento(String query)
    {
        try
        {
            Statement state = conn.createStatement();
            state.executeUpdate(query);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
