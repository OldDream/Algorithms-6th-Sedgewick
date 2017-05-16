package exercise1_2;
import edu.princeton.cs.algs4.*;

public class Exercise1_2_6 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid args.");
            System.exit(0);
        }
        
        String a = args[0];
        String b = args[1];
        
        if (a.length() != b.length()) {
            System.out.println("Not a circular rotation.");
            System.exit(0);
        }
        
        //   1.取出a中的一个字符。
        //   2.在b中寻找这个字符（并记录下这个index），找到后逐个对比后面的字符。
        char key = a.charAt(0);
        int length = a.length();
        int index = 0; 
        boolean checker = false;
        while (true) {
            if (b.charAt(index) == key) {
                for (int i = index + 1, j = 1; j < length; i++, j++) {
                    int tempI = i;
                    if (i >= length) 
                        tempI = i - length;
                    if (b.charAt(tempI) == a.charAt(j)) {
                        if (j != length - 1)
                            continue;
                        else {
                            checker = true;
                            break;
                        }
                    }
                    else 
                        break;
                }
            }
            if (checker == false)
                index++;
            else
                break;
            
            if (index == length) {
                System.out.println("Not a circular rotation.");
                System.exit(0);
            }
        }
        
        System.out.println(a + " and " + b + " are circular rotation.");
    }
    
}
