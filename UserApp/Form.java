package Client;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * Classe dove viene inizializzata e visualizzata l'interfaccia iniziale 
 * dell'applicativo
 */
public class Form extends Application{
    public static Stage stage;
    
    /**
     * Nel metodo start si fa parire la pagina Form.xml dove viene visualizzata
     * la pagina del login/iscrizione al database
     * @param primaryStage: è lo stage dell'intero applicativo
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("Home/Graphics/Form.fxml"));
            Scene scene = new Scene(root);         
            stage = primaryStage;
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>()
            {   
               public void handle(WindowEvent we){
                   try{
                       System.out.println(Client.socket.getRemoteSocketAddress().toString());
                       Client.socket.close();
                   } catch (IOException ex) {
                       System.exit(0);
                   }
               }
            });
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void parti(String[] args) {
        launch(args);
    }
    
}
