import edu.princeton.cs.algs4.Digraph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordNet
{
    private HashMap<Integer, String> synsets = new HashMap<Integer, String>();
    private String fileName_syn;
    private String fileName_hyp;
    private int size;
    private Digraph graph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        // check that inputs are not null
        if (isNull(synsets) || isNull(hypernyms)) throw new IllegalArgumentException();
        fileName_syn = synsets;
        fileName_hyp = hypernyms;
        fillSynsets();
        size = this.synsets.size();
        graph = new Digraph(size);
        makeGraph();
    }
    /*
    // returns all WordNet nouns
    public Iterable<String> nouns()
    {}

    // is the word a WordNet noun?
    public boolean isNoun(String word)
    {}

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {}

    /*
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {}
    */

    // fill the this.synsets list from the input file
    private void fillSynsets()
    {
        try(BufferedReader br = new BufferedReader(new FileReader(this.fileName_syn)))
        {
            for(String line; (line = br.readLine()) != null; )
            {
                List<String> words = new ArrayList<String>();
                String[] tokens = line.split(",");
                this.synsets.put(Integer.parseInt(tokens[0]), tokens[1]);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /* Fill this.hypernyms such that hypernyms[i] = [int1, int2,...]
    where i is the synset ID and int1, int2 are the corresponding hypernyms' synset IDs */
    /* NOT NEEDED ATM
    private void fillHypernyms()
    {
        try(BufferedReader br = new BufferedReader(new FileReader(this.fileName_hyp)))
        {
            for(String line; (line = br.readLine()) != null; )
            {
                List<Integer> ids = new ArrayList<Integer>();
                String[] tokens = line.split(",");
                for (int i = 1; i < tokens.length; i++)
                {
                    ids.add(Integer.parseInt(tokens[i]));
                }
                this.hypernyms.add(ids);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    } */
    /*
    Read the hypernyms file and create the Digraph
     */
    private void makeGraph()
    {
        try(BufferedReader br = new BufferedReader(new FileReader(this.fileName_hyp)))
        {
            for(String line; (line = br.readLine()) != null; )
            {
                String[] ids = line.split(",");
                int v = Integer.parseInt(ids[0]);
                for (int k = 1; k < ids.length; k++)
                {
                    int w = Integer.parseInt(ids[k]);
                    graph.addEdge(v, w);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // check if object is null
    public static boolean isNull(Object obj)
    { return obj == null; }

    // do unit testing of this class
    public static void main(String[] args)
    {
        WordNet myWN = new WordNet("C:\\Users\\Marko\\Desktop\\School\\CS - Personal\\Coursera - Princeton - Algorithms2\\Week1\\WordNet\\data\\synsets.txt",
                "C:\\Users\\Marko\\Desktop\\School\\CS - Personal\\Coursera - Princeton - Algorithms2\\Week1\\WordNet\\data\\hypernyms.txt");
    }
}