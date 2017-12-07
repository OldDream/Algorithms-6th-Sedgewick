package Test;

public class TestRGB {
    public static void main(String[] args) {
        // 我怀疑这样搞，符号位会消失
        // 的确消失了，不行。
        int rgb = (1 << 16) + (2 << 8) + (3 << 0);
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb >> 0) & 0xFF;
        
        System.out.println("R = " + r);
        System.out.println("G = " + g);
        System.out.println("B = " + b);
    }
}
