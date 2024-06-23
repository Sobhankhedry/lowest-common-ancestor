package com.example.lca;

import Model.Node;
import Model.Tree;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NODE_RADIUS = 20;
    private static final int VERTICAL_SPACE = 50;

    
    @Override
    public void start(Stage stage) throws IOException {
        Tree tree = new Tree();
        tree.Insert(10);
        tree.Insert(20);
        tree.Insert(30);

        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawTree(gc, tree.root, WIDTH / 2, 40, WIDTH / 4);

        root.getChildren().add(canvas);
        stage.setTitle("LCA");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void drawTree(GraphicsContext gc, Node node, double x, double y, double hGap) {
        if (node != null) {
            gc.setFill(Color.BLACK);
            gc.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
            gc.setFill(Color.WHITE);
            gc.fillText(String.valueOf(node.number), x - NODE_RADIUS / 4, y + NODE_RADIUS / 4);

            if (node.left != null) {
                gc.strokeLine(x, y, x - hGap, y + VERTICAL_SPACE);
                drawTree(gc, node.left, x - hGap, y + VERTICAL_SPACE, hGap / 2);
            }
            if (node.right != null) {
                gc.strokeLine(x, y, x + hGap, y + VERTICAL_SPACE);
                drawTree(gc, node.right, x + hGap, y + VERTICAL_SPACE, hGap / 2);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}