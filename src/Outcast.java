import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private WordNet wordnet;
    
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int[] di = new int[nouns.length];
        for (int i = 0; i < nouns.length; i++) {
            for (String s : nouns) {
                di[i] += wordnet.distance(nouns[i], s);
            }
        }
        return nouns[getMax(di)];
    }
    
    private int getMax (int[] di) {
        int maxIndex = 0;
        for (int i = 0; i < di.length; i++) {
            if (di[maxIndex] < di[i])
                maxIndex = i;
        }
        return maxIndex;
    }

    // see test client below
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}