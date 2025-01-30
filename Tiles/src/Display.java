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

    private final Map<Color, Integer> colors = new HashMap<>();

    public Display() {
        //colors taken from the internet
        colors.put(Color.web("#E57373"), 0); // Rose
        colors.put(Color.web("#81C784"), 0); // Green
        colors.put(Color.web("#64B5F6"), 0); // Blue
        colors.put(Color.web("#BA68C8"), 0); // Purple
        colors.put(Color.web("#F28C28"), 0); // Distinct Orange
        colors.put(Color.web("#4DB6AC"), 0); // Teal
        colors.put(Color.web("#9575CD"), 0); // Deep Purple
        colors.put(Color.web("#A1887F"), 0); // Brown
        colors.put(Color.web("#F06292"), 0); // Pink
        colors.put(Color.web("#7986CB"), 0); // Indigo
        colors.put(Color.web("#D32F2F"), 0); // Vibrant Red (instead of duplicate orange)
        colors.put(Color.web("#AED581"), 0); // Light Green
        colors.put(Color.web("#00838F"), 0); // Dark Cyan
        colors.put(Color.web("#546E7A"), 0); // Steel Grey
        colors.put(Color.web("#007FFF"), 0); // Electric Blue
    }
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


        GridPane gp = new GridPane();
        gp.setBackground(new Background(new BackgroundFill(
                web("#2E3B4E"), CornerRadii.EMPTY , Insets.EMPTY)));
        gp.setHgap(4);
        gp.setVgap(4);

        int colorIndex = 0;

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {


                List<Color> colors = getColors();
                Color sqColor = colors.get(0);
                Color cirColor = colors.get(1);
                Color triColor = colors.get(2);

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

    private List<Color> getColors(){
        List<Color> newColors = new ArrayList<>();

        for (Map.Entry<Color, Integer> entry : colors.entrySet()) {
            if (entry.getValue() < 18) {
                newColors.add(entry.getKey());
            }
        }


        Collections.shuffle(newColors);

        if (newColors.size() < 3) {
            System.out.println("Not enough colors available!");
            return Arrays.asList(null, null, null);
        }

        Color sqColor = newColors.get(0);
        Color cirColor = newColors.get(1);
        Color triColor = newColors.get(2);

        colors.put(sqColor, colors.get(sqColor) + 1);
        colors.put(cirColor, colors.get(cirColor) + 1);
        colors.put(triColor, colors.get(triColor) + 1);

        return Arrays.asList(sqColor, cirColor, triColor);
    }
    }
