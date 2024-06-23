package Model;

public class Tree {
    public Node root= null;

    public void Insert(int p){
        if(root == null){
            Node q = new Node(p);
            root = q;
        }
        else {
            InsideInsert(root,p);
        }
    }

    private void InsideInsert(Node root, int p) {
        if(root.left == null){
            Node q = new Node(p);
            root.left = q;
            q.number = p;
            return;
        }
        if(root.right == null){
            Node q = new Node(p);
            root.right = q;
            q.number = p;
            return;
        }
        else {
            InsideInsert(root.left,p);
        }
    }
}
