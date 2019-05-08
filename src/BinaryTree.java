import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Stack;

/**
 * Simple binary trees. Traversal
 */
public class BinaryTree<T> implements Iterable<T> {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The root of the tree
   */
   BinaryTreeNode<T> root;

  /**
   * The number of values in the tree.
   */
  int size;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty, tree.
   */
  public BinaryTree() {
    this.size = 0;
    this.root = null;
  } // BinaryTree

  /**
   * Create a new, somewhat balanced, tree.
   */
  public BinaryTree(T[] values) {
    this.size = values.length;
    this.root = makeTree(values, 0, values.length);
  } // BinaryTree(T[])

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Dump the tree to some output location.
   */
  public void dump(PrintWriter pen) {
    dump(pen, root, "");
  } // dump(PrintWriter)

  /**
   * Get an iterator for the tree.
   */
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      
      BinaryTreeNode<T> current = root;
      public boolean hasNext() {  
        return (current.left != null || current.right != null);
      } // hasNext()

      public T next() {
       // first case: no hasNext return null
        if (!hasNext()) {
          return null;
        } 
        if (current.left != null) {
          BinaryTreeNode<T> temp = current;
          current = current.left;
          return temp.value;
        }
        return null;
      } // next()
    }; // new Iterator()
  } // iterator()

  /*
  public Iterator<BinaryTreeNode<K,V>> iterator() {
    return new Iterator<K,V>() {
      SimpleQueue<Node<K,V>> q = new SimpleQueue<Node<K,V>>(root);

      public boolean hasNext() {
        return !q.isEmpty();
      } // hasNext

      public Node<K,V> next() {
        Node<K,V> node = q.get(); // dequeue
        if (node.left != null) { q.put(node.left); }
        if (node.right != null) { q.put(node.right); }
        return node;
      } // next
    }; // new Iterator<K,V>
  } // iterator()

  public Iterator<Pair<K,V>> iterator() {
    return new Iterator<K,V>() {
      SimpleQueue<Node<K,V>> q = new SimpleQueue<Node<K,V>>(root);

      public boolean hasNext() {
        return !q.isEmpty();
      } // hasNext

      public Pair<K,V> next() {
        Node<K,V> node = q.get(); // dequeue
        if (node.left != null) { q.put(node.left); }
        if (node.right != null) { q.put(node.right); }
        return new Pair<K,V>(node.key(), node.value());
      } // next
    }; // new Iterator<K,V>
  } // iterator()

  */
  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  public void printHelper(BinaryTreeNode<T> node, PrintWriter pen) {
    // if root is null, return
    if (node == null) {
      return;
    }
    pen.print(" " + node.value);
    printHelper(node.left, pen);
    printHelper(node.right, pen);
  }

  public void printHelper2(BinaryTreeNode<T> node, PrintWriter pen) {
    // if root is null, return
    if (node == null) {
      return;
    }

    printHelper2(node.left, pen);
    pen.print(" " + node.value);
    printHelper2(node.right, pen);

  }

  public void elements02(PrintWriter pen) {
    printHelper2(this.root, pen);
    pen.println();
    return;
  }

  public void elements01(PrintWriter pen) {
    printHelper(this.root, pen);
    pen.println();
    return;
  }

  /**
   * Dump a portion of the tree to some output location.
   */
  void dump(PrintWriter pen, BinaryTreeNode<T> node, String indent) {
    if (node == null) {
      pen.println(indent + "<>");
    } else {
      pen.println(indent + node.value);
      if ((node.left != null) || (node.right != null)) {
        dump(pen, node.left, indent + "  ");
        dump(pen, node.right, indent + "  ");
      } // if has children
    } // else
  } // dump

  /**
   * Build a tree from a subarray from lb (inclusive) to ub (exclusive).
   */
  BinaryTreeNode<T> makeTree(T[] values, int lb, int ub) {
    if (ub <= lb) {
      return null;
    } else if (ub - lb == 1) {
      return new BinaryTreeNode<T>(values[lb]);
    } else {
      int mid = lb + (ub - lb) / 2;
      return new BinaryTreeNode<T>(values[mid], makeTree(values, lb, mid),
          makeTree(values, mid + 1, ub));
    } // if/else
  } // makeTree(T[], int, int)

  /****
   * 
   * Print all of the elements in some order or other.**Note: We are trying to avoid recursion.
   */

  // this is depth first preorder

  public void printPreOrder(PrintWriter pen) {
    // A collection of the remaining things to print
    Stack<Object> remaining = new Stack<Object>();
    remaining.push(this.root);
    // Invariants:
    // remaining only contains Strings or Nodes
    // All values in the tree are either
    // (a) already printed
    // (b) in remaining
    // (c) in or below a node in remaining
    while (!remaining.isEmpty()) {
      // popping the root in first iteration
      Object next = remaining.pop();
      if (next instanceof BinaryTreeNode<?>) {
        @SuppressWarnings("unchecked")
        BinaryTreeNode<T> node = (BinaryTreeNode<T>) next;
        // if right is not null, push right first
        if (node.right != null) {
          remaining.push(node.right);
        } // if (node.right!= null)
        // if left not null, push left on top of right
        if (node.left != null) {
          remaining.push(node.left);
        } // if (node.left != null)
        // push the value (which is a string) last
        remaining.push(node.value);
      } else {
        pen.print(next);
        pen.print(" ");
      } // if/else
      // in the next iteration, when pop is called, we are popping the string first
      // then else condition is executed
    } // while
    pen.println();
  } // print(PrintWriter)

  /*
   * In what order do you expect it to print the values in the tree? we should expect the tree to be
   * printed out depth first (go as deep left as you can) then go to right
   */

  public void                                                                                                                                                         (PrintWriter pen) {
    // A collection of the remaining things to print
    Stack<Object> remaining = new Stack<Object>();
    remaining.push(this.root);
    // Invariants:
    // remaining only contains Strings or Nodes
    // All values in the tree are either
    // (a) already printed
    // (b) in remaining
    // (c) in or below a node in remaining
    while (!remaining.isEmpty()) {
      // popping the root in first iteration
      Object next = remaining.pop();
      if (next instanceof BinaryTreeNode<?>) {
        @SuppressWarnings("unchecked")
        BinaryTreeNode<T> node = (BinaryTreeNode<T>) next;
        // if right is not null, push right first
        if (node.right != null) {
          remaining.push(node.right);
        } // if (node.right!= null)
        // push the value (which is a string) last
        remaining.push(node.value);
        // if left not null, push left on top of right
        if (node.left != null) {
          remaining.push(node.left);
        } // if (node.left != null)
      } else {
        pen.print(next);
        pen.print(" ");
      } // if/else
      // in the next iteration, when pop is called, we are popping the string first
      // then else condition is executed
    } // while
    pen.println();
  } // print(PrintWriter)

  public void printPostOrder(PrintWriter pen) {
    // A collection of the remaining things to print
    Stack<Object> remaining = new Stack<Object>();
    remaining.push(this.root);
    // Invariants:
    // remaining only contains Strings or Nodes
    // All values in the tree are either
    // (a) already printed
    // (b) in remaining
    // (c) in or below a node in remaining
    while (!remaining.isEmpty()) {
      // popping the root in first iteration
      Object next = remaining.pop();
      if (next instanceof BinaryTreeNode<?>) {
        @SuppressWarnings("unchecked")
        BinaryTreeNode<T> node = (BinaryTreeNode<T>) next;
        // push the value (which is a string) last
        remaining.push(node.value);
        // if right is not null, push right first
        if (node.right != null) {
          remaining.push(node.right);
        } // if (node.right!= null)
        // if left not null, push left on top of right
        if (node.left != null) {
          remaining.push(node.left);
        } // if (node.left != null)
      } else {
        pen.print(next);
        pen.print(" ");
      } // if/else
      // in the next iteration, when pop is called, we are popping the string first
      // then else condition is executed
    } // while
    pen.println();
  } // print(PrintWriter)
} // class BinaryTree
