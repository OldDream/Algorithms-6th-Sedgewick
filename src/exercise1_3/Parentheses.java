package exercise1_3;

import edu.princeton.cs.algs4.*;
import java.util.Scanner;

public class Parentheses {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter some parentheses : ");
        String temps = input.nextLine();
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < temps.length(); i++) {
            char temp = temps.charAt(i);
            if (temp == '[' || temp == '{' || temp == '(')
                stk.push(temp);
            else {
                char top = stk.pop();
                if (top == '[') {
                    if (temp == ']')
                        continue;
                    else {
                        StdOut.println("false");
                        System.exit(0);
                    }
                } else if (top == '(') {
                    if (temp == ')')
                        continue;
                    else {
                        StdOut.println("false");
                        System.exit(0);
                    }
                } else if (top == '{') {
                    if (temp == '}')
                        continue;
                    else {
                        StdOut.println("false");
                        System.exit(0);
                    }
                }
            }
        }
        input.close();
        if (stk.isEmpty())
            StdOut.println("true");
        else
            StdOut.println("flase");
        
        
    }

}
