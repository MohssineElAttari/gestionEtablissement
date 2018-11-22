
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author dell
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("vue/MenuAdminVue.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("لوحة تسجيل الدخول");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        launch(args);

    }

}
