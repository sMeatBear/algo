package app.math;

public class UnionFindSet {
    private int[] parent;
    
    public UnionFindSet(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        // find recursively until find the parent idx == parent val
        if (parent[x] != x) {parent[x] = find(parent[x]);}
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }

    public static void main(String[] args) {
        UnionFindSet uf = new UnionFindSet(5);
        uf.union(1, 3);
        uf.union(3, 4);
        System.out.println(uf.find(1));
    }
}
