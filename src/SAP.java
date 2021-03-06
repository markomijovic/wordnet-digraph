import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class SAP
{
    private final Digraph graph;
    private BreadthFirstDirectedPaths pV, pW;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        if (WordNet.isNull(G))
        {
            throw new IllegalArgumentException();
        }
        this.graph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)
    {
        if (v > graph.V() || w > graph.V()) throw new IllegalArgumentException();
        pV = new BreadthFirstDirectedPaths(graph, v);
        pW = new BreadthFirstDirectedPaths(graph, w);
        int min_dist = Integer.MAX_VALUE;
        for (int i = 0; i < graph.V(); i++)
        {
            if (pV.hasPathTo(i) && pW.hasPathTo(i))
            {
                min_dist = Math.min(min_dist, pV.distTo(i)+pW.distTo(i));
            }
        }
        if (min_dist == Integer.MAX_VALUE) return -1;
        return min_dist;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        if (v > graph.V() || w > graph.V()) throw new IllegalArgumentException();
        pV = new BreadthFirstDirectedPaths(graph, v);
        pW = new BreadthFirstDirectedPaths(graph, w);
        int dist = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < graph.V(); i++)
        {
            if (pV.hasPathTo(i) && pW.hasPathTo(i))
            {
                if (pV.distTo(i) + pW.distTo(i) < dist)
                {
                    dist = pV.distTo(i) + pW.distTo(i);
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {

    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {

    }

    // do unit testing of this class
    public static void main(String[] args)
    {

    }
}
