package Test;

import edu.princeton.cs.algs4.In;

public class TestArgs {

	public static void main(String[] args) {
		System.out.println(args[1]);
		int[] a = In.readInts(args[0]);
		for (int b : a) {
			System.out.println(b);
		}
	}

}
