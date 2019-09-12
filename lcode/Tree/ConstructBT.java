import java.util.*;

class SolutionConstruct {
    public TreeNode build (List<Integer> rest, int[] root, int i) {
        
        if (rest.size() == 0) return null;
        
        // current root
        TreeNode rootNode = new TreeNode(root[i]);
        
        // get index of root to divide left and right tree
        int rootIndex = rest.indexOf(root[i]);
        List<Integer> left = rest.subList(0, rootIndex);;
        List<Integer> right;
        if (rootIndex + 1 < rest.size())
            right = rest.subList(rootIndex + 1, rest.size());
        else 
            right = new ArrayList<Integer>();
        
        // find next root for each part
        for (int nextI = i - 1; nextI >= 0 && left.size() > 0; nextI--) {
            if (left.contains(root[nextI])) {
                rootNode.left = build(left, root, nextI);
                break;
            }
        }

        
        for (int nextI = i - 1; nextI >= 0 && right.size() > 0; nextI--) {
            if (right.contains(root[nextI])) {
                rootNode.right = build(right, root, nextI);
                break;
            }
        }
        
     
        return rootNode;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        List<Integer> rest = new ArrayList<Integer>();
        for (int i : inorder) {
            rest.add(i);
        }
        return build(rest, postorder, postorder.length - 1);
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