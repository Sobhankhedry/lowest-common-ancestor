package com.example.lca;

import Model.TreeNode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloApplication extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NODE_RADIUS = 20;
    private static final int VERTICAL_SPACE = 50;

    static List<TreeNode> pPath = new ArrayList<>();
    static List<TreeNode> qPath = new ArrayList<>();

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
        TreeNode q7 = new TreeNode(3);
        TreeNode q8 = new TreeNode(9);
        q3.setLeft(q7);
        q3.setRight(q8);
        TreeNode q9 = new TreeNode(23);
        TreeNode q10 = new TreeNode(31);
        q4.setLeft(q9);
        q4.setRight(q10);
        TreeNode q11 = new TreeNode(17);
        TreeNode q12 = new TreeNode(75);
        q5.setLeft(q11);
        q5.setRight(q12);
        TreeNode q13 = new TreeNode(88);
        TreeNode q14 = new TreeNode(15);
        q6.setLeft(q13);
        q6.setRight(q14);
        TreeNode q15 = new TreeNode(37);
        TreeNode q16 = new TreeNode(91);
        q13.setLeft(q15);
        q13.setRight(q16);


        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        TreeNode nod = LowestCommon(R,q16,q11);
        System.out.println(nod.getValue()+ " the real LCA");


        drawTree(gc, R, WIDTH / 2, 40, WIDTH / 4,stage);

        ShowingPath(gc , R, stage);


        root.getChildren().add(canvas);
        stage.setTitle("LCA");
        stage.setScene(new Scene(root));
        stage.show();



    }

    private void ShowingPath(GraphicsContext gc, TreeNode R, Stage stage) {


            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                for (TreeNode b: pPath
                     ) {
                    b.setColor(2);
                    drawTree(gc, R,WIDTH/2,40,WIDTH/4, stage);

                }

            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();



    }



    private void commonPath(TreeNode nod, TreeNode p, TreeNode q) {
        findPath(nod,p,pPath);
        findPath(nod,q,qPath);

        int i;
        for (i = 0; i < pPath.size() && i < qPath.size(); i++) {
            if (!pPath.get(i).equals(qPath.get(i))) {
                break;
            }
        }

        pPath.remove(0);
        Collections.reverse(pPath);
        pPath.remove(0);



        qPath.remove(0);
        Collections.reverse(qPath);
        qPath.remove(0);


    }

    private TreeNode LowestCommon(TreeNode root, TreeNode p, TreeNode q) {
        ChangeColorNodeToRed(root,p);
        ChangeColorNodeToRed(root,q);

        TreeNode lca = LCA(root , p , q);
        commonPath(lca,p,q);

        lca.setColor(5);
        return lca;
    }

    private boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) {
            return false;
        }

        path.add(root);

        if (root.equals(target)) {
            return true;
        }

        if (root.getLeft() != null && findPath(root.getLeft(), target, path)) {
            return true;
        }

        if (root.getRight() != null && findPath(root.getRight(), target, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }



    private void ChangeColorNodeToRed(TreeNode root,TreeNode x) {

        if (root == null){
            return;
        }if (root.getValue() == x.getValue()){
            root.setColor(1);
            return;
        }
        ChangeColorNodeToRed(root.getLeft(),x);
        ChangeColorNodeToRed(root.getRight(),x);
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




    private void drawTree(GraphicsContext gc, TreeNode node, double x, double y, double hGap, Stage stage) {

        if (node != null) {
            if (node.getColor()==1){
                gc.setFill(Color.RED);
            }
            else if(node.getColor()==5){
                gc.setFill(Color.GREEN);
            } else if (node.getColor() == 2) {
                gc.setFill(Color.OLIVE);

            } else {
                gc.setFill(Color.BLACK);

            }
            gc.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
            gc.setFill(Color.WHITE);
            gc.fillText(String.valueOf(node.getValue()), x - NODE_RADIUS / 4, y + NODE_RADIUS / 4);

            if (node.getLeft() != null) {
                gc.strokeLine(x, y, x - hGap, y + VERTICAL_SPACE);
                drawTree(gc, node.getLeft(), x - hGap, y + VERTICAL_SPACE, hGap / 2, stage);
            }
            if (node.getRight() != null) {
                gc.strokeLine(x, y, x + hGap, y + VERTICAL_SPACE);
                drawTree(gc, node.getRight(), x + hGap, y + VERTICAL_SPACE, hGap / 2, stage);
            }
        }
    }

    public static void main(String[] args) {
        launch();

    }
}