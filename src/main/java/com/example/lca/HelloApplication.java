package com.example.lca;

import Model.TreeNode;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

        TreeNode R = new TreeNode(2);
        TreeNode q1 = new TreeNode(4);
        TreeNode q2 =new TreeNode(7);
        R.setLeft(q1);
        R.setRight(q2);
        TreeNode q3 = new TreeNode(8);
        TreeNode q4 = new TreeNode(12);
        q1.setLeft(q3);
        q1.setRight(q4);
        TreeNode q5 = new TreeNode(13);
        TreeNode q6 = new TreeNode(10);
        q2.setLeft(q5);
        q2.setRight(q6);





        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();



        drawTree(gc, R, WIDTH / 2, 40, WIDTH / 4);

        TreeNode nod = LowestCommon(R,q2,q5);


        root.getChildren().add(canvas);
        stage.setTitle("LCA");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private TreeNode LowestCommon(TreeNode root, TreeNode q2, TreeNode q5) {
        ChangeColorNode(root,q2);
        ChangeColorNode(root,q5);
        return LCA(root,q2,q5);
    }

    private void ChangeColorNode(TreeNode root,TreeNode x) {
        if (root == null){
            return;
        }if (root.getValue() == x.getValue()){
            System.out.println(root.getValue());
            root.setColor(-1);
            return;
        }

        ChangeColorNode(root.getLeft(),x);
        ChangeColorNode(root.getRight(),x);

    }

    private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null){
            return null;
        }
        if(root.getValue()== p.getValue() || root.getValue()== q.getValue()){
            return root;
        }
        TreeNode leftsubTree = LCA(root.getLeft(),p,q);
        TreeNode rightsubTree = LCA(root.getRight(),p,q);
        if(leftsubTree==null){
            return rightsubTree;
        }if (rightsubTree==null){
            return leftsubTree;
        }
        return root;
    }




    private void drawTree(GraphicsContext gc, TreeNode node, double x, double y, double hGap) {
        if (node != null) {
            if (node.getColor()==-1){
                gc.setFill(Color.YELLOW);
                System.out.println("im here ");
            }
            else {
                gc.setFill(Color.BLACK);
            }
            gc.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
            gc.setFill(Color.WHITE);
            gc.fillText(String.valueOf(node.getValue()), x - NODE_RADIUS / 4, y + NODE_RADIUS / 4);

            if (node.getLeft() != null) {
                gc.strokeLine(x, y, x - hGap, y + VERTICAL_SPACE);
                drawTree(gc, node.getLeft(), x - hGap, y + VERTICAL_SPACE, hGap / 2);
            }
            if (node.getRight() != null) {
                gc.strokeLine(x, y, x + hGap, y + VERTICAL_SPACE);
                drawTree(gc, node.getRight(), x + hGap, y + VERTICAL_SPACE, hGap / 2);
            }
        }
    }

    public static void main(String[] args) {
        launch();

    }
}