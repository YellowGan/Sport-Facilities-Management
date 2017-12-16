package Server;

import ConnDB.ConnDB;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server 
{

    private int port;
    public static ArrayList arrayClient = new ArrayList();
    public Socket socket;
    public static ConnDB db = new ConnDB();
        
    /**
     * Costruttore dovo si inizializza la porta dove il server resta in ascolto
     * in attesa di client
     * @param port 
     */
    public Server(int port)
    {
        this.port = port;
    }
    
    /**
     * Metodo in cui il server fa partire il thread che si occupa del ping di 
     * ogni client connesso. Inoltre in questo metodo il server si connette al 
     * database mediante la chiamata del metodo conessione() della classe ConnDB
     * e avviando il metodo accept() della classe ServerSocket si fa in modo che
     * il server resti in ascolto nella porta scelta
     */
    public void StartServer()
    {
        ServerSocket serverSocket;
        try
        {
            serverSocket = new ServerSocket(port);
        }
        catch (IOException e)
        {
            System.out.println("Errore nel Server");
            return;
        }
        System.out.println("Server in ascolto");
        Ping objping = new Ping();
        objping.start();
        db.connessione();
        do
        {
            try
            {
                socket = serverSocket.accept();
                System.out.println("Connesione con il client stabilita: " + socket.getRemoteSocketAddress().toString());
                arrayClient.add(new ObjClient(socket.getRemoteSocketAddress().toString(), socket));
                new ClientHandler(socket).start();      
            } 
            catch (IOException e)
            {
                break;
            }
        } while (true);    
    }

    /**
     * Metodo di partenza del server dove viene creato il server con una determinata
     * porta.
     */
    public static void main(String[] args)
    {
        Server server = new Server(1337);
        server.StartServer();
    }
}
