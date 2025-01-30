import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.web;

public class Tiles extends StackPane {
    private Color triColor;
    private Color cirColor;
    private Color sqColor;


    public Tiles(Color sqColor, Color cirColor, Color triColor){
        this.cirColor = cirColor;
        this.triColor = triColor;
        this.sqColor = sqColor;

        makeTiles();
    }

    private void makeTiles(){
        Rectangle rect = new Rectangle(80, 100,
                sqColor);

        Circle circle = new Circle(35, cirColor);
        Polygon triangle = new Polygon();

        triangle.getPoints().addAll(
                0.0, -25.0, -20.0, 20.0, 20.0, 20.0);
        triangle.setFill(triColor);

        StackPane eyeStack = makeEyes();
        eyeStack.setTranslateY(10);

        this.getChildren().addAll(rect, circle, triangle, eyeStack);
    }

    private StackPane makeEyes(){
        Circle Outer = new Circle(10, Color.WHITE);
        Circle iris = new Circle(6, web("#4B8B9B"));
        Circle pupil = new Circle(4, Color.BLACK);
        StackPane eyeStack = new StackPane(Outer, iris, pupil);
        return eyeStack;
    }

}
