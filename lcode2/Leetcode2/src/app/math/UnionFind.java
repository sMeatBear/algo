package app.math;

public class UnionFind {
    public int[] parent;

    /**
     * Initialize union find set size
     * @param size union find set size
     */
    public UnionFind(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    /**
     * Find the ancestor of current set x
     * @param x current set x
     * @return set x belongs to which set
     */
    public int find(int x) {
        if (parent[x] != x) {parent[x] = find(parent[x]);}
        return parent[x];
    }

    /**
     * Combine two sets and unify their identifier
     */
    public void union(int x, int y) {
        // let x's identifier to be y's
        parent[find(y)] = find(x);
    }
}
