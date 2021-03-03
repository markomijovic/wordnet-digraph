import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class WordNet
{
    private HashMap<Integer, String> synsets = new HashMap<>();
    private HashMap<String, ArrayList<Integer>> synsets_unique = new HashMap<>();
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
        fillSynsets();  // O(N)
        size = this.synsets.size();
        graph = new Digraph(size);
        makeGraph();    // O(N)
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

    // fill the this.synsets hashmap from the input file
    private void fillSynsets()
    {
        try(BufferedReader br = new BufferedReader(new FileReader(this.fileName_syn)))
        {
            for(String line; (line = br.readLine()) != null; )
            {
                String[] tokens = line.split(",");
                int syn_id = Integer.parseInt(tokens[0]);
                this.synsets.put(syn_id, tokens[1]);
                String[] nouns = tokens[1].split(" ");
                mapSynsets(syn_id, nouns);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Helper function that maps the each synset nouns entry to a hashmap in which
     * nouns are keys and values are arrays filled with their respective synset ids
     * Function modifies the synsets_unique class attribute
     * @param syn_id line id that identifies the synset
     * @param nouns array of the synset nouns at a given synset id
     */
    private void mapSynsets(int syn_id, String[] nouns)
    {
        // Elegant and scalable
        for (String noun : nouns)
        {
            ArrayList<Integer> myList = new ArrayList<>();
            /* similar to https://stackoverflow.com/a/4158002
             *For key=noun get the corresponding list of synset ids in the hashmap
             *If the key=noun does not exist return the empty myList, and if it exists
              return the existing list
             */
            ArrayList<Integer> ids = synsets_unique.getOrDefault(noun, myList);
            // Add the new synset id to the empty list or to the already existing list
            ids.add(syn_id);
            synsets_unique.put(noun, ids);
        }
    }

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