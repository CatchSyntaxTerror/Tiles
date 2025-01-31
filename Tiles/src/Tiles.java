import javafx.scene.Node;
import javafx.scene.layout.Border;
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
    private Color triColor;
    private Color cirColor;
    private Color sqColor;
    private Boolean selected = false;
    private int numSelected = 0;
    private Circle pupil;

    private Circle iris;

    private StackPane eyeStack;


    public Tiles(Color sqColor, Color cirColor, Color triColor) {
        this.cirColor = cirColor;
        this.triColor = triColor;
        this.sqColor = sqColor;

        makeTiles();

        this.setOnMouseClicked(e -> {
            if (!selected && numSelected <= 2) {
                pupil.setFill(Color.FIREBRICK);
                selected = true;
                numSelected++;
            } else {
                pupil.setFill(Color.BLACK);
                selected = false;
            }

        });
    }

    private void makeTiles() {

        Rectangle rect = new Rectangle(80, 100, sqColor);

        Circle circle = new Circle(35, cirColor);

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(0.0, -25.0, -20.0, 20.0, 20.0, 20.0);
        triangle.setFill(triColor);

        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        triangle.setStroke(Color.BLACK);
        triangle.setStrokeWidth(2);

        StackPane eyeStack = makeEyes();
        eyeStack.setTranslateY(10);

        this.getChildren().addAll(rect, circle, triangle, eyeStack);


    }

    private StackPane makeEyes() {
        Circle outer = new Circle(10, Color.WHITE);
        outer.setStroke(Color.BLACK);
        outer.setStrokeWidth(.5);
        iris = new Circle(6, web("#4B8B9B"));
        iris.setStroke(Color.BLACK);
        iris.setStrokeWidth(.5);
        pupil = new Circle(4, Color.BLACK);
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
        double maxDistance = 4;

        double scale = (distance > 0) ? Math.min(maxDistance / distance, 1) : 0;
        iris.setTranslateX(x * scale);
        iris.setTranslateY(y * scale);
        pupil.setTranslateX(x * scale * 1.2);
        pupil.setTranslateY(y * scale * 1.2);
    }

}
