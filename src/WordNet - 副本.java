import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;

public class WordNet {
    private RedBlackBST<String, Integer> synSet;
    private RedBlackBST<Integer, String> reverse;
    private Digraph g;
    private SAP sap;
   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) {
       if (synsets == null || hypernyms == null)
           throw new  java.lang.IllegalArgumentException();
       synSet = getSynset(synsets);
       g = getHypernyms(hypernyms);
       sap = new SAP(g);
       
       checkCycle();
   }
   
   private Digraph getHypernyms(String hypernyms) {
       In in = new In(hypernyms);
       Digraph g = new Digraph(getMax(hypernyms));
       
       while (in.hasNextLine()) {
           String[] numbers = in.readLine().split(",");
           int start = Integer.parseInt(numbers[0]);
           for (int i = 1; i < numbers.length; i++) {
               int end = Integer.parseInt(numbers[i]);
               g.addEdge(start, end);
           }
       }
       
       return g;
   }
   
   // find maximun number in hypernyms,I konw it's inefficient...
   private int getMax(String hypernyms) {
       int max = 0;
       In in = new In(hypernyms);
       while (in.hasNextLine()) {
           for (String s : in.readLine().split(",")) {
               int temp = Integer.parseInt(s);
               if (temp > max)
                   max = temp;
           }
       }
       return max;
   }
   
   private RedBlackBST<String, Integer> getSynset(String synsets) {
       RedBlackBST<String, Integer> bst = new RedBlackBST<>();
       reverse = new RedBlackBST<>();
       In in = new In(synsets);
       while (in.hasNextLine()) {
           String temp = in.readLine();
           String[] firstSplit = temp.split(",");
           int id = Integer.parseInt(firstSplit[0]);
           
           reverse.put(id, firstSplit[1]);
           
           String[] synonyms = firstSplit[1].split("\\s+");
           for (String s : synonyms) {
               bst.put(s, id);
           }
       }
       
       return bst;
   }
   
   private void checkCycle() {
       DirectedCycle cycle = new DirectedCycle(this.g);
       if (cycle.hasCycle()) throw new IllegalArgumentException("Digraph has cycle!");
   }
   
   // returns all WordNet nouns
   public Iterable<String> nouns() {
       return synSet.keys();
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word) {
       if (word == null)
           throw new IllegalArgumentException();
       return synSet.contains(word);
   }

   // distance between nounA and nounB (defined below)
   public int distance(String nounA, String nounB) {
       if (nounA == null || nounB == null)
           throw new IllegalArgumentException();
       if (!isNoun(nounA) || !isNoun(nounB))
           throw new IllegalArgumentException();
       int idA = synSet.get(nounA);
       int idB = synSet.get(nounB);
      /* String strA = reverse.get(idA);
       String strB = reverse.get(idB);
       String[] setA = strA.split("\\s+");
       String[] setB = strB.split("\\s+");*/
       int distance = sap.length(idA, idB);
       return distance;
   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB) {
       if (!isNoun(nounA) || !isNoun(nounB))
           throw new java.lang.IllegalArgumentException();
       
       int idA = synSet.get(nounA);
       int idB = synSet.get(nounB);
      
       int ancestor = sap.ancestor(idA, idB);
       return reverse.get(ancestor);
   }

   // do unit testing of this class
   public static void main(String[] args) {
       
   }
}