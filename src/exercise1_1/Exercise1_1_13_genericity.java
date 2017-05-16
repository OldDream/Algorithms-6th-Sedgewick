package exercise1_1;

public class Exercise1_1_13_genericity {

    public static void main(String[] args) {
        Integer[][] a = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        Exercise1_1_13.<Integer>transposition(a);
/*        Integer[][] b = Exercise1_1_13.<Integer>transposition(a);
        
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }*/

    }
    
    public static <E> void transposition(E[][] array) {
//        E[][] array1 = (E[][])(new Object[array[1].length][array.length]);
        for (int i = 0; i < array[1].length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[j][i]);
//                array1[i][j] = array[j][i];
            }
            System.out.println();
        }
        
//        return array1;
    }

}
