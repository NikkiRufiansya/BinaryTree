package tugaspraktikum;

class BinaryTreeNode {

    BinaryTreeNode parent;
    BinaryTreeNode left;
    BinaryTreeNode right;
    int data;

    BinaryTreeNode(int new_data) {
        data = new_data;
        parent = null;
        left = null;
        right = null;
    }

    void set_parent(BinaryTreeNode other) {
        this.parent = other;
        if (other != null) {
            if (other.data > this.data) {
                other.left = this;
            } else {
                other.right = this;
            }
        }
    }

    void set_left(BinaryTreeNode other) {
        this.left = other;
        if (other != null) {
            other.parent = this;
        }
    }

    void set_right(BinaryTreeNode other) {
        this.right = other;
        if (other != null) {
            other.parent = this;
        }
    }

    boolean is_left() {
        return this.parent != null && parent.left == this;
    }

    boolean is_right() {
        return this.parent != null && parent.right == this;
    }

    boolean has_right_and_left() {
        return this.left != null && this.right != null;
    }

    boolean only_has_left() {
        return this.left != null && this.right == null;
    }

    boolean only_has_right() {
        return this.left == null && this.right != null;
    }

    boolean has_no_child() {
        return this.left == null && this.right == null;
    }

    void unset_parent() {
        if (this.is_left()) {
            this.parent.left = null;
            this.parent = null;
        } else if (this.is_right()) {
            this.parent.right = null;
            this.parent = null;
        }
    }

    BinaryTreeNode most_left_child() {
        BinaryTreeNode child = this.left;
        while (child.left != null) {
            child = child.left;
        }
        return child;
    }

    BinaryTreeNode most_right_child() {
        BinaryTreeNode child = this.right;
        while (child.right != null) {
            child = child.right;
        }
        return child;
    }

    void print(String spaces, String label) {
        System.out.println(spaces + label + " " + this.data);
        if (this.left != null) {
            this.left.print(" ", "LEFT");
        }
        if (this.right != null) {
            this.right.print(" ", "RIGHT");
        }
    }

    void print() {
        this.print("", "NODE");
    }
    public String toString(){
      return " Find "+data;
    }
    

    void infix() {
        System.out.print("(");
        if (this.left != null) {
            this.left.infix();
        } else {
            System.out.print("null");
        }
        System.out.print(" " + this.data + " ");
        if (this.right != null) {
            this.right.infix();
        } else {
            System.out.print("null");
        }
        System.out.print(")");
    }

    void prefix() {
        System.out.print(this.data + "(");
        if (this.left != null) {
            this.left.prefix();
        } else {
            System.out.print("null");
        }
        System.out.print(" ");
        if (this.right != null) {
            this.right.prefix();
        } else {
            System.out.print("null");
        }
        System.out.print(")");
    }

    void postfix() {
        System.out.print("(");
        if (this.left != null) {
            this.left.postfix();
        } else {
            System.out.print("null");
        }
        System.out.print(" ");
        if (this.right != null) {
            this.right.postfix();
        } else {
            System.out.print("null");
        }
        System.out.print(")" + this.data);
    }


}

public class TugasPraktikum {
 static class BinaryTree {

        BinaryTreeNode root;
        int key;
        public BinaryTree() {
            this.root = null;
        }

        void print() {
            if (this.root != null) {
                this.root.print();
            }
        }

        void prefix() {
            if (this.root != null) {
                this.root.prefix();
            }
            System.out.println();
        }

        void infix() {
            if (this.root != null) {
                this.root.infix();
            }
            System.out.println();
        }

        void postfix() {
            if (this.root != null) {
                this.root.postfix();
            }
            System.out.println();
        }

        void push(BinaryTreeNode new_node) {
            if (this.root == null) {
                this.root = new_node;
            } else {
                BinaryTreeNode current = this.root;
                while (current != null) {
                    if (new_node.data > current.data) {
                        if (current.right == null) {
                            current.set_right(new_node);
                            break;
                        } else {
                            current = current.right;
                        }
                    } else {
                        if (current.left == null) {
                            current.set_left(new_node);
                            break;
                        } else {
                            current = current.left;
                        }
                    }
                }
            }
        }

        void delete(BinaryTreeNode deleted) {
            if (root != null) {
                if (deleted.has_no_child()) {
                    if (deleted == this.root) {
                        this.root = null;
                    } else {
                        deleted.unset_parent();
                    }
                } else if (deleted.only_has_left() || deleted.only_has_right()) {
                    BinaryTreeNode replacement = null;
                    if (deleted.only_has_left()) {
                        replacement = deleted.left;
                    } else {
                        replacement = deleted.right;
                    }

                    if (deleted == this.root) {
                        this.root = replacement;
                        replacement.unset_parent();
                    } else if (deleted.is_left()) {
                        deleted.parent.set_left(replacement);
                        deleted.unset_parent();
                    } else if (deleted.is_right()) {
                        deleted.parent.set_right(replacement);
                        deleted.unset_parent();
                    }
                } else {
                    BinaryTreeNode replacement = deleted.left;
                    if (replacement.right != null) {
                        replacement = replacement.most_right_child();
                    }
                    BinaryTreeNode parent_of_replacement = replacement.parent;
                    if (replacement.only_has_left()) {
                        parent_of_replacement.set_right(replacement.left);
                    }
                    replacement.unset_parent();
                    replacement.set_left(deleted.left);
                    replacement.set_right(deleted.right);
                    if (deleted == this.root) {
                        this.root = replacement;
                    } else if (deleted.is_left()) {
                        deleted.parent.set_left(replacement);
                    } else if (deleted.is_right()) {
                        deleted.parent.set_right(replacement);
                    }
                }
            }
        }

        public BinaryTreeNode findNode(int key) {
             
            BinaryTreeNode focusNode = root;
            while (focusNode.data != key) {
                if (key < focusNode.data) {
                    focusNode = focusNode.left;
                } else {
                    focusNode = focusNode.right;
                }
                if (focusNode == null) {
                    return null;
                }
            }
            return focusNode;
            
        }
        int toint(){
     return this.key;
 }
        
    }
    public static void main(String[] args) {

        BinaryTree bt = new BinaryTree();
        bt.print();
        bt.push(new BinaryTreeNode(20));
        bt.push(new BinaryTreeNode(15));
        bt.push(new BinaryTreeNode(25));
        bt.push(new BinaryTreeNode(12));
        bt.push(new BinaryTreeNode(17));
        bt.push(new BinaryTreeNode(22));
        bt.push(new BinaryTreeNode(27));
        bt.push(new BinaryTreeNode(28));
        System.out.println("cari node 20 : "+ bt.findNode(20));
        bt.print();
        bt.infix();
        bt.prefix();
        bt.postfix();
        BinaryTreeNode deleted = bt.root.most_left_child();
        System.out.println(deleted.data);
        bt.delete(deleted);
        bt.print();
        deleted = bt.root.most_right_child().parent;
        System.out.println(deleted.data);
        System.out.println(deleted.only_has_right());
        System.out.println(deleted.only_has_left());
        bt.delete(deleted);
        bt.print();
        deleted = bt.root;
        System.out.println(deleted.data);
        bt.delete(deleted);
        bt.print();
        System.out.println("cari node 27 = " + bt.findNode(27));
        System.out.println("cari node 28 = " + bt.findNode(28));
        System.out.println("cari node 20 = " + bt.findNode(20));
        System.out.println("cari node 15 = " + bt.findNode(15));
        bt.print();
        
   

    }

}
