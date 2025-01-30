import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;

import static javafx.scene.paint.Color.web;

public class Display {
    private final int SCREEN_HEIGHT = 500;
    private final int SCREEN_WIDTH = 500;
    private final int NUM_ROWS = 5;
    private final int NUM_COLS = 6;
    public void setStage(Stage stage) {
        stage.setTitle("Tiles");
        StackPane root = new StackPane();
        GridPane gridPane = makeGrid();
        root.getChildren().add(gridPane);
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    private GridPane makeGrid() {
        Map<Color, Integer> colors = new HashMap<>();
        colors.put(Color.web("#E57373"), 0);
        colors.put(Color.web("#81C784"), 0);
        colors.put(Color.web("#64B5F6"), 0);
        colors.put(Color.web("#BA68C8"), 0);
        colors.put(Color.web("#FF8A65"), 0);


        GridPane gp = new GridPane();
        gp.setBackground(new Background(new BackgroundFill(
                web("#2E3B4E"), CornerRadii.EMPTY , Insets.EMPTY)));
        gp.setHgap(4);
        gp.setVgap(4);

        int colorIndex = 0;

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {

                //TODO: Use getColors here
                Color sqColor;
                Color cirColor;
                Color triColor;
                colorIndex++;

                int n = row * NUM_COLS + col;

                Rectangle rect = new Rectangle(80, 100,
                        sqColor);

                Circle circle = new Circle(35, cirColor);
                Polygon triangle = new Polygon();

                triangle.getPoints().addAll(
                        0.0, -25.0, -20.0, 20.0, 20.0, 20.0);
                triangle.setFill(triColor);

                Circle Outer = new Circle(10, Color.WHITE);
                Circle iris = new Circle(6, web("#4B8B9B"));
                Circle pupil = new Circle(4, Color.BLACK);
                StackPane eyeStack = new StackPane(Outer, iris, pupil);
                eyeStack.setTranslateY(10);

                StackPane sp = new StackPane(rect, circle, triangle, eyeStack);
                gp.add(sp, col, row);
            }
        }
        return gp;
    }

    private Color getColors(Map<Color, Integer> colors){
        //TODO: Randomly choose colors whos count is < 6
        return null;
    }
}