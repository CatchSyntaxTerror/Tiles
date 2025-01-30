import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainGameLoop extends Application {

    private final Display display = new Display();

    @Override
    public void start(Stage primaryStage) {
    }

    public static void main(String[] args) {
        launch();
    }
}
