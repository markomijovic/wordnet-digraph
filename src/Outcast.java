public class Outcast
{
    private final WordNet wn;
    public Outcast(WordNet wordnet)         // constructor takes a WordNet object
    {
        if (WordNet.isNull(wordnet)) throw new IllegalArgumentException();
        wn = wordnet;
    }
    public String outcast(String[] nouns)   // given an array of WordNet nouns, return an outcast
    {
        int max_dist = -1;
        int ans = -1;
        int[] dist = new int[nouns.length];
        for (int i = 0; i < nouns.length; i++)
        {
            for (int j = i+1; j < nouns.length; j++)
            {
                int d = wn.distance(nouns[i], nouns[j]);
                dist[i] += d;
                dist[j] += d;
            }
            if (dist[i] > max_dist)
            {
                max_dist = dist[i];
                ans = i;
            }
        }
        return nouns[ans];
    }
    public static void main(String[] args)  // see test client below
    {

    }
}