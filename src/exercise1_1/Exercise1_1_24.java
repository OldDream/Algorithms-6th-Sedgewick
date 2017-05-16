package exercise1_1;

public class Exercise1_1_24 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid Input.");
            System.exit(0);
        }
        System.out.println("The gcd of " + args[0] + " and " + args[1] + " is "
                + euclid(Integer.parseInt(args[0]), Integer.parseInt(args[1])));

    }

    public static int euclid(int p, int q) {
        System.out.println("p = " + p + " q = " + q);
        if (q == 0)
            return p;
        int r = p % q;
        return euclid(q, r);
    }

}
