package exercise1_1;

public class Exercise1_1_11 {

    public static void main(String[] args) {
        boolean[][] a = {{true,false,true}, {false,true,false}, {true,false,true}};
        System.out.println("  1 2 3");
        for (int i = 0; i < a.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == true)
                    System.out.print(" *");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }

}
