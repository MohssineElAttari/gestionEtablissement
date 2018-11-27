
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import services.EtudiantService;
import util.HibernateUtil;

/**
 *
 * @author dell
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("vue/Login2Vue.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("لوحة تسجيل الدخول");
        stage.getIcons().add(new Image(this.getClass().getResource("img/ministere.jpg").toString()));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        launch(args);

//        EtudiantService es = new EtudiantService();
//        
//        int n=es.countDecision("ff");
//        System.out.println(n);

    }

}
