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
import java.util.ArrayList;
import java.util.List;

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
        TreeNode q13 = new TreeNode(36);
        TreeNode q14 = new TreeNode(15);
        q6.setLeft(q13);
        q6.setRight(q14);


        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        TreeNode nod = LowestCommon(R,q14,q11);
        System.out.println(nod.getValue()+ " the real LCA");





        drawTree(gc, R, WIDTH / 2, 40, WIDTH / 4);


        root.getChildren().add(canvas);
        stage.setTitle("LCA");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void commonPath(TreeNode nod, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = new ArrayList<>();
        List<TreeNode> qPath = new ArrayList<>();

        findPath(nod,p,pPath);
        findPath(nod,q,qPath);

        int n = pPath.size();
        int m = qPath.size();

        int i;
        for (i = 0; i < pPath.size() && i < qPath.size(); i++) {
            if (!pPath.get(i).equals(qPath.get(i))) {
                break;
            }
        }




    }

    private TreeNode LowestCommon(TreeNode root, TreeNode p, TreeNode q) {
        ChangeColorNode(root,p);
        ChangeColorNode(root,q);

        TreeNode lca = LCA(root , p , q);
        commonPath(lca,p,q);

        lca.setColor(10);
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

    private void colorPath(List<TreeNode> path, TreeNode lca) {
        for (TreeNode node : path) {
            node.setColor(5); // Color nodes in the path
            if (node.equals(lca)) {
                break;
            }
        }
    }

    private void ChangeColorNode(TreeNode root,TreeNode x) {
        if (root == null){
            return;
        }if (root.getValue() == x.getValue()){
            root.setColor(5);
            System.out.println("color "+ root.getColor() +" in node with value "+ root.getValue());
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
            if (node.getColor()==5){
                gc.setFill(Color.RED);
            }
            else if(node.getColor()==10){
                gc.setFill(Color.GREEN);
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