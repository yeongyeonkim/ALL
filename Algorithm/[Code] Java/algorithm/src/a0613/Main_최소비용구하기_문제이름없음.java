package a0613;

import java.util.Arrays;

public class Main_최소비용구하기_문제이름없음 {

	// Kruskal 
	// new Comparator<int[]>가 아닌 -> 사용
	
	public static int[] p;

	public static void main(String[] args) {
		System.out
				.println(solution(4, new int[][] { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } }));
	}

	public static int findSet(int x) {
		if (p[x] == x) {
			return x;
		}
		return p[x] = findSet(p[x]);
	}

	public static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if(a < b) p[b] = a;
		else p[a] = b;
	}
	public static int solution(int n, int[][] costs) {
		int answer = 0;
		Arrays.sort(costs, (int[] c1, int[] c2) -> c1[2] - c2[2]);
		p = new int[n + 1];
		for (int i = 0; i < n; i++) {
			p[i] = i;
		}
		for (int[] cost : costs) {
			if (findSet(cost[0]) != findSet(cost[1])) {
				answer = answer + cost[2];
				union(cost[0], cost[1]);
			}
		}
		return answer;
	}
}
