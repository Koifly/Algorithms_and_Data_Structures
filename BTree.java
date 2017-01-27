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

    public BTreeNode parent;
    public void insert(int key) {
        if (root == null || root.getKeys() == null) {
            root = new BTreeNode(degree);
            root.addKey(key);
            return;
        } else {
            boolean isgtFirstChild = true;
            if (root.getChildren().size() != 0) {
                int firstChildLength = root.getChildren().get(0).getKeys().size();
                for (int i = 0; i < firstChildLength; i++) {
                    if (key <= root.getChildren().get(0).getKeys().get(i)) {
                        isgtFirstChild = false;
                    }
                }
            }
            if (root.getKeys().size() <= 2 * degree && isgtFirstChild == true && root.getChildren().size() != root.getKeys().size() + 1) {
                boolean hasBeenAdded = false;
                for (int i = 0; i < root.getKeys().size(); i++) {
                    if (key < root.getKeys().get(i)) {
                        root.getKeys().add(i, key);
                        hasBeenAdded = true;
                        break;
                    }
                }
                if (hasBeenAdded == false) {
                    root.getKeys().add(key);
                }
                if (root.getKeys().size() <= 2 * degree) {
                    return;
                } else {
                    int median = (root.getKeys().size()) / 2;
                    if (parent != null) {
                        hasBeenAdded = false;
                        int index = 0;

                        if (parent.getKeys().size() <= 2* degree) {
                            for (int i = 0; i < parent.getKeys().size(); i++) {
                                if (root.getKeys().get(median) < parent.getKeys().get(i)) {
                                    parent.getKeys().add(i, root.getKeys().get(median));
                                    root.getKeys().remove(median);
                                    index = i;
                                    hasBeenAdded = true;
                                    break;
                                }
                            }
                            if (hasBeenAdded == false) {
                                root.getKeys().add(key);
                            }
                        }
                        BTreeNode child = root;
                        this.root = parent;

                        BTreeNode leftLeaf = new BTreeNode(degree);
                        for (int i = 0; i < median; i++){
                            leftLeaf.addKey(child.getKeys().get(0));
                            child.getKeys().remove(0);
                        }
                        root.getChildren().add(index, leftLeaf);

                        BTreeNode rightLeaf = new BTreeNode(degree);
                        int length = child.getKeys().size();
                        for (int i = 0; i < length; i++){
                            rightLeaf.addKey(child.getKeys().get(0));
                            child.getKeys().remove(0);
                        }
                        root.getChildren().add(index + 1, rightLeaf);

                        root.getChildren().remove(index + 2);
                        return;

                    } else {

                        root.addChild(new BTreeNode(degree));
                        for (int i = 0; i < median; i++) {
                            root.getChildren().get(0).addKey(root.getKeys().get(0));
                            root.getKeys().remove(0);
                        }

                        root.addChild(new BTreeNode(degree));
                        for (int i = 1; i <= root.getKeys().size(); i++) {
                            root.getChildren().get(1).addKey(root.getKeys().get(1));
                            root.getKeys().remove(1);
                        }
                    }
                }
            } else {
                for (int i = 0; i < root.getKeys().size(); i++) {
                    if (key < root.getKeys().get(i)) {
                        BTree child = new BTree(degree);
                        child.setRoot(root.getChildren().get(i));
                        child.parent = this.root;
                        child.insert(key);
                        return;
                    }
                }

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
