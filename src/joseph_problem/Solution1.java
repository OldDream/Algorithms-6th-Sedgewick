package joseph_problem;

import java.util.Scanner;

// 一圈共有N个人，从1开始报数，报到M的人自杀，然后重新报数，问最后自杀的人是谁？

public class Solution1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the total number N : ");
        int N = in.nextInt();
        System.out.print("Enter the kill number M : ");
        int M = in.nextInt();
        
        boolean[] alive = new boolean[N];
        for (int i = 0; i < N; i++)
            alive[i] = true;
        
        int numOfAlive = N;
        for (int i = 0, j = 0; ; ) {
            // i is index; j is number
            if (alive[i] == true)
                j++;
            if (j == M && numOfAlive == 1) {
                System.out.println("Last one alive is : " + (i + 1));
                System.exit(0);
            }
            else if (j == M && alive[i] == true) {
                System.out.println("kill : " + (i + 1));
                alive[i] = false;
                numOfAlive--;
                i = 0;
                j = 0;
            }
            
            i++;
            i = i % N;
            
        }
    }
    
}
