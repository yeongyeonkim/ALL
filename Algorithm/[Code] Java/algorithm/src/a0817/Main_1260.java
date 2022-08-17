package a0817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260 {

	/*
	 * 	4 5 1
		1 2
		1 3
		1 4
		2 4
		3 4
		
		-> 1 2 4 3
		-> 1 2 3 4
	 */
	public static int n, m;
	public static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	public static int[][] map;
	public static boolean[] visit;
	public static void d(int start) {
		visit[start] = true;
		System.out.print(start);
		for(int i=1; i<=n; i++) {
			if(map[start][i] == 1 && !visit[i]) {
				System.out.print(" ");
				d(i);
			}
		}
	}
	public static void b(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visit[start] = true;
		while(!q.isEmpty()) {
			int tmp = q.poll();
			System.out.print(tmp + " ");
			for(int i=1; i<=n; i++) {
				if(map[tmp][i] == 1 && !visit[i]) {
					visit[i] = true;
					q.add(i);
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
		}
		visit = new boolean[n+1];
		d(start);
		System.out.println();
		visit = new boolean[n+1];
		b(start);
	}

}
