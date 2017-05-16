package exercise3_4;

import edu.princeton.cs.algs4.Queue;

public class SeparateChainingHashST<Key, Value> {
	private int N; // the total number of key-value pairs.
	private int M; // the size of hashST
	private ModSequentialSearchST<Key, Value>[] st;

	public SeparateChainingHashST() {
		this(997);
	}

	public SeparateChainingHashST(int M) {
		this.M = M;
		st = (ModSequentialSearchST<Key, Value>[]) new ModSequentialSearchST[M];
		for (int i = 0; i < M; i++)
			st[i] = new ModSequentialSearchST();
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M; // 消除符号位并hash成大于0，小于M的整数
	}

	public Value get(Key key) {
		return (Value) st[hash(key)].get(key);
	}

	public void put(Key key, Value val) {
		st[hash(key)].put(key, val, N);
		N++;
	}

	public void delAbove(int k) {
		for (int i = 0; i < M; i++) {
			for (Key key : st[i].keys()) {
				if (st[i].getTotalNum(key) > k) {
					st[i].delete(key);
				}
			}
		}
	}

	public Iterable<Key> keys() {
		Queue<Key> qe = new Queue<>();
		for (int i = 0; i < M; i++) {
			for (Key key : st[i].keys()) {
				qe.enqueue(key);
			}
		}
		return qe;
	}
}

// 不要在意。。测试git
//Elder + 1s
