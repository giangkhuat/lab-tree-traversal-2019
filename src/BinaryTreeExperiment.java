import java.io.PrintWriter;

/**
 * A place to experiment with binary trees.
 */
public class BinaryTreeExperiment {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    String[] strings = {"aardvark", "billygoat", "chinchilla", "dingo", "emu",
        "frog", "gnu", "hippo", "iguana", "jackalope", "koala", "llama"};
    BinaryTree<String> tree = new BinaryTree<String>(strings);

    String[] str2 = {"aaa", "bb"};
    BinaryTree<String> tree2 = new BinaryTree<String>(str2);
    
    tree.dump(pen);
    tree.elements01(pen);
    tree.elements02(pen);
  } // main
} // class BinaryTreeExperiment
