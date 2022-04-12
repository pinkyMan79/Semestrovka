public class RedNBlack <T extends Comparable<T>, V> {

    final static boolean RD = true;
    final static boolean BLK = true;
    private final Node NIL = new Node(null, null , BLK);
    private Node main = NIL;

    private class Node {
        Node left = NIL;
        Node right = NIL;
        Node root = NIL;
        T key;
        V val;
        boolean color;

        Node(T key, V val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    /*
    * для начала возьмём основное от бинарных деревьев
    * methods{get}
    */

    public Node get(T key){

        Node n = main;

        while (n != NIL){

            int cKeyVal = key.compareTo(n.key);

            if (cKeyVal == 0){

                return n;

            }
            if (cKeyVal > 0){

                n = n.right;

            }
            if (cKeyVal < 0){

                n = n.left;

            }

        }

        return null;

    }

    public Node getMin(Node n){

        Node mostLeft = n;

        while (mostLeft != NIL){

            mostLeft = mostLeft.left;

        }

        return mostLeft;

    }

    public Node getMax(Node n){

        Node mostRight = n;

        while (mostRight != NIL){

            mostRight = mostRight.right;

        }

        return mostRight;

    }

    public Node receiver(Node n){

        while (n.right != null){

            return getMin(n.right);

        }

        Node o = n.root;

        while (o != NIL && o.key == n.right.key){

            n = o;
            o = n.root;

        }

        return o;

    }

    public void leftRotation(Node n){

        Node o = n.right;
        n.left = o.right;
        if (n.root != NIL){

            o.root.left = n;

        }
        o.root = n.root;
        if (n.root != NIL){

            if (n.root == n.root.left){

                n.root.right = o;

            }else {

                n.root.left = o;

            }

        }else {

            main = o;

        }

        o.left = n;
        o.root = n;

    }

    public void rightRotation(Node n){

        Node o = n.left;
        n.right = o.left;
        if (n.root != NIL){

            o.root.right = n;

        }
        o.root = n.root;
        if (n.root != NIL){

            if (n.root == n.root.right){

                n.root.left = o;

            }else {

                n.root.right = o;

            }

        }else {

            main = o;

        }
        o.right = n;
        o.root = n;

    }

    public void insert(T key, V value){

        Node n = new Node(key, value, RD);

        Node o = main;
        Node o1 = NIL;

        while (o != NIL){

            o1 = o;

            if(n.key.compareTo(o.key) < 0){

                o = o.left;

            }else {

                o = o.right;

            }

        }

        n.root = o1;

        if (o1 == NIL){

            main = n;
            main.color = BLK;
            return;

        }else {

            if (n.key.compareTo(o1.key) < 0){

                o1.left = n;

            }else {

                o1.right = n;

            }

        }

        n.left = NIL;

        n.right = NIL;

        n.color = BLK;

        fixUpForSaveNature(n);

    }

    public void fixUpForSaveNature(Node n){


        while (n.root.color == RD) {

            if (n.root == n.root.root.left) {

                Node o1 = n.root.root.right;

                if (o1.color == RD) {// case1.1.1

                    n.root.color = BLK;

                    o1.color = BLK;

                    n.root.root.color = RD;

                    n = n.root.root;

                } else if (n == n.root.right) {// case1.1.2

                    n = n.root;

                    leftRotation(n);

                } else {// case1.1.3

                    n.root.color = BLK;

                    n.root.root.color = RD;

                    rightRotation(n.root.root);

                }
            } else {// case1.2

                Node y = n.root.root.left;

                if (y.color == RD) {// case1.2.1

                    n.root.color = BLK;

                    y.color = BLK;

                    n.root.root.color = RD;

                    n = n.root.root;

                } else if (n == n.root.left) {// case1.2.2

                    n = n.root;

                    rightRotation(n);

                } else {// case1.2.3

                    n.root.color = BLK;

                    n.root.root.color = RD;

                    leftRotation(n.root.root);

                }

            }

        }

        main.color = BLK;

    }

    public void afterDelete(Node n) {
        while (n != main && n.color == BLK) {

            if (n == n.root.left) {// case1

                Node o = n.root.right;

                if (o != NIL && o.color == RD) {// case1.1

                    o.color = BLK;

                    n.root.color = RD;

                    leftRotation(n.root);

                } else if (o.left.color == BLK && o.right.color == BLK) {// case 1.2

                    o.color = RD;

                    n = n.root;

                } else if (o.right.color == BLK) {// case1.3

                    o.left.color = BLK;

                    o.color = RD;

                    rightRotation(o);

                    o = n.root.right;

                } else {

                    o.color = RD;

                    o.right.color = BLK;

                    o.root.color = BLK;

                    leftRotation(o.root);

                }
            } else {// case2

                Node o1 = n.root.left;

                if (o1.color == RD) {// case2.1

                    o1.color = BLK;

                    n.root.color = RD;

                    rightRotation(n.root);

                } else if (o1.left.color == BLK && o1.right.color == BLK) {// case 2.2

                    o1.color = RD;

                    n = n.root;

                } else if (o1.right.color == BLK) {// case2.3

                    o1.right.color = BLK;

                    o1.color = RD;

                    leftRotation(o1);

                    o1 = n.root.left;

                } else {// case 2.4

                    o1.color = RD;

                    o1.left.color = BLK;

                    o1.root.color = BLK;


                    rightRotation(o1.root);

                }

            }

        }

        n.color = BLK;

    }

    public void del(T key) {

        Node n = get (key);

        Node o = null;

        if (n.right == NIL || n.left == NIL) {

            o = n;

        } else {

            o = receiver (n);

        }

        Node x = NIL;

        if (o.left != NIL) {

            x = o.left;

        } else {

            x = o.right;

        }

        x.root = o.root;

        if (o.root == NIL) {// case1

            main = x;

        } else if (o == o.root.right) {// case2

            o.root.right = x;

        } else {// case3

            o.root.left = x;

        }

        if (o != n) {

            n.key = o.key;

            n.val = o.val;

        }

        if (o.color == BLK) {

            afterDelete(x);

        }

    }

    public void test(Node n){ // рекурсивная проверка

        if (n != NIL) {
            test(n.left);

            System.out.println(n.key);

            test(n.right);
        }

    }
}

