package exercise1_1;

public class Exercise1_1_27 {
    private static long count = 0;
    public static void main(String[] args) {
        System.out.println(binomial(30, 20, 0.25));
        System.out.println(count);

    }
    
    public static double binomial(int N, int k, double p) {
        count++;
        if (N == 0 && k == 0)
            return 1.0;
        if (N < 0 || k < 0)
            return 0.0;
        return (1.0 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
    }

}
