import javafx.application.Application;
import javafx.stage.Stage;

public class MainGameLoop extends Application {

    @Override
    public void start(Stage primaryStage) {
        Display display = new Display();
        display.setStage(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}
