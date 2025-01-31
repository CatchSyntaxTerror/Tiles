
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.web;

/**
 * Youssef Amin
 * TODO: Write comments
 */

public class Tiles extends StackPane {
    private final Color triColor;
    private final Color cirColor;
    private final Color sqColor;
    private final int SCALE_MULTIPLIER = 1;
    private final double [] TRIANGLE_POINTS =
            {0.0, -25.0 * SCALE_MULTIPLIER, -20.0 * SCALE_MULTIPLIER,
                    20.0 * SCALE_MULTIPLIER, 20.0 * SCALE_MULTIPLIER,
                    20.0 * SCALE_MULTIPLIER};
    private Boolean selected = false;
    private Circle pupil;
    private Circle iris;
    private StackPane eyeStack;


    public Tiles(Color sqColor, Color cirColor, Color triColor) {
        this.cirColor = cirColor;
        this.triColor = triColor;
        this.sqColor = sqColor;

        makeTiles();

        this.setOnMouseClicked(e -> handleClicks());

    }

    public void handleClicks(){
        if (!selected) {
            pupil.setFill(Color.FIREBRICK);
            selected = true;
            System.out.println("grrrrr");
        } else {
            pupil.setFill(Color.BLACK);
            selected = false;
            System.out.println("few!");
        };
    }

    private void makeTiles() {

        int RECTANGLE_HEIGHT = 100 * SCALE_MULTIPLIER;
        int RECTANGLE_WIDTH = 80 * SCALE_MULTIPLIER;
        Rectangle rect = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT, sqColor);

        int CIRCLE_SIZE = 35 * SCALE_MULTIPLIER;
        Circle circle = new Circle(CIRCLE_SIZE, cirColor);

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(TRIANGLE_POINTS[0], TRIANGLE_POINTS[1],
                TRIANGLE_POINTS[2], TRIANGLE_POINTS[3], TRIANGLE_POINTS[4],
                TRIANGLE_POINTS[5]);
        triangle.setFill(triColor);

        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        triangle.setStroke(Color.BLACK);
        triangle.setStrokeWidth(2);

        StackPane eyeStack = makeEyes();
        int EYE_TRANSLATE = 10 * SCALE_MULTIPLIER;
        eyeStack.setTranslateY(EYE_TRANSLATE);

        this.getChildren().addAll(rect, circle, triangle, eyeStack);


    }

    private StackPane makeEyes() {
        int OUTER_SIZE = 10 * SCALE_MULTIPLIER;
        Circle outer = new Circle(OUTER_SIZE, Color.WHITE);
        outer.setStroke(Color.BLACK);
        outer.setStrokeWidth(.5);
        int IRISE_SIZE = 6 * SCALE_MULTIPLIER;
        iris = new Circle(IRISE_SIZE, web("#4B8B9B"));
        iris.setStroke(Color.BLACK);
        iris.setStrokeWidth(.5);
        int PUPIL_SIZE = 4 * SCALE_MULTIPLIER;
        pupil = new Circle(PUPIL_SIZE, Color.BLACK);
        pupil.setStroke(Color.BLACK);
        pupil.setStrokeWidth(.5);

        eyeStack = new StackPane(outer, iris, pupil);
        return eyeStack;
    }

    public void updateEyeDirectionTile(double mouseX, double mouseY) {

        double eyeCenterX = eyeStack.localToScene(0, 0).getX();
        double eyeCenterY = eyeStack.localToScene(0, 0).getY();

        double x = mouseX - eyeCenterX;
        double y = mouseY - eyeCenterY;
        
        double distance = Math.sqrt(x * x + y * y);
        double maxDistance = 4 * SCALE_MULTIPLIER;

        double scale = (distance > 0) ? Math.min(maxDistance / distance, 1) : 0;
        iris.setTranslateX(x * scale);
        iris.setTranslateY(y * scale);
        pupil.setTranslateX(x * scale * 1.2);
        pupil.setTranslateY(y * scale * 1.2);
    }

}
