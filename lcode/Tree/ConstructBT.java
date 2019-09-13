import java.util.*;

class SolutionConstruct {
    // use hashmap accelerate searching
    private Map<Integer, Integer> cacheRest;

    public TreeNode build (int[] rest, int s, int e, int[] root, int i) {
        // outer
        if (s > e) return null;
        
        // current root
        TreeNode rootNode = new TreeNode(root[i]);
        
        // get index of root to divide left and right tree
        int rootIndex = cacheRest.get(root[i]);
        int left = rootIndex - 1;
        int right = rootIndex + 1;
        
        // find next root for each part
        for (int nextI = i - 1; nextI >= 0 && left - s >= 0; nextI--) {
            int nextRootIndex = cacheRest.get(root[nextI]);
            if (nextRootIndex <= left && nextRootIndex >= s) {
                rootNode.left = build(rest, s, left, root, nextI);
                break;
            }
        }
        
        
        for (int nextI = i - 1; nextI >= 0 && e - right >= 0; nextI--) {
            int nextRootIndex = cacheRest.get(root[nextI]);
            if (nextRootIndex >= right && nextRootIndex <= e) {
                rootNode.right = build(rest, right, e, root, nextI);
                break;
            }
        }
        
        return rootNode;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        cacheRest = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            cacheRest.put(inorder[i], i);
        }
        return build(inorder, 0, postorder.length - 1, postorder, postorder.length - 1);
    }
}

public class ConstructBT {
    public static void main(String[] args) {
        SolutionConstruct s = new SolutionConstruct();
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode root = s.buildTree(inorder, postorder);
        String result = root.toString();
        System.out.println(result);
    }
}