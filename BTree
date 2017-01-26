public class BTree {
    private BTreeNode root = null;
    private final int degree;

    public BTree(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

    public void setRoot(BTreeNode root) {
        this.root = root;
    }

    public BTreeNode getRoot() {
        return root;
    }

    public boolean hasKey(int key) {
        this.printTree();
        if (this.root.hasKey(key) == true) {
            return true;
        } else {
            int indexValue = -1;
            for (int i = 0; i < this.root.getKeys().size(); i ++) {
                if (key < this.root.getKeys().get(i)) {
                    indexValue = i;
                    break;
                }
            }
            if (indexValue == -1) {
                BTree nextTree = this;
                nextTree.root = this.root.getChildren().get(this.root.getChildren().size() - 1);
                return nextTree.root.hasKey(key);
            } else  {
                for (int i = 0; i <= indexValue; i++) {
                    BTree nextTree = this;
                    nextTree.root =  this.root.getChildren().get(i);
                    return nextTree.root.hasKey(key);
                }
            }
        }
        return false;
    }

    public void insert(int key) {
        if (root == null || root.getKeys() == null) {
            root = new BTreeNode(degree);
            root.addKey(key);
            return;
        } else {
            if (root.getKeys().size() <= 2 * degree) {
                int hasBeenAdded = 0;
                for (int i = 0; i < root.getKeys().size(); i++) {
                    if (key < root.getKeys().get(i)) {
                        root.getKeys().add(i, key);
                        hasBeenAdded = 1;
                        break;
                    }
                }
                if (hasBeenAdded == 0) {
                    root.getKeys().add(key);
                }
                if (root.getKeys().size() <= 2 * degree) {
                    return;
                } else {

                }
            } else {

            }
        }
    }

    public String toJson() {
        // TODO
        return null;
    }

    public void printTree() {
        if (root != null) {
            root.print("");
        }
    }
}
