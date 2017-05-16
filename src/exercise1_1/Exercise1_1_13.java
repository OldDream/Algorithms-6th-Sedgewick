package exercise1_1;

public class Exercise1_1_13 {

    public static void main(String[] args) {
        Integer[][] a = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        Integer[][] b = Exercise1_1_13.transposition(a);
        
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                System.out.print(b[i][j]);
            }
            System.out.println();
        }

    }
    
    public static Integer[][] transposition(Integer[][] array) {
        Integer[][] array1 = new Integer[array[1].length][array.length];
        for (int i = 0; i < array[1].length; i++) {
            for (int j = 0; j < array.length; j++) {
                array1[i][j] = array[j][i];
            }
        }
        return array1;
    }
}
