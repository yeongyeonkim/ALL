package a0811;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_거짓말 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 사람
		int m = Integer.parseInt(st.nextToken()); // 파티
		String[] str = br.readLine().split(" ");
		int fact = Integer.parseInt(str[0]);
		int cnt = 0;
		int[] p = new int[n + 1];
		HashSet<Integer>[] set = new HashSet[n + 1];
		for(int i=1; i<=n; i++) {
			set[i] = new HashSet<>();
		}
		for (int i = 1; i <= n; i++) {
			p[i] = i;
		}
		// 같은 영역에 속하면 0
		for (int i = 1; i <= fact; i++) {
			p[Integer.parseInt(str[i])] = 0;
		}
		for (int k = 0; k < m; k++) {
			str = br.readLine().split(" ");
			int num = Integer.parseInt(str[0]);
			Queue<Integer> q = new LinkedList<>();
			boolean flag = false;
			for (int i = 1; i <= num; i++) {
				int tmp = Integer.parseInt(str[i]);
				if (p[tmp] == 0)
					flag = true;
				q.add(tmp);
				set[num].add(tmp);
			}
			if (flag) {
				while (!q.isEmpty()) {
					p[q.poll()] = 0;
				}
			}
		}
		for(int i=1; i<=n; i++) {
			boolean flag = false;
			for(int person: set[i]) {
				if (p[person] == 0) {
					flag = true;
					break;
				}
			}
			if(!flag) cnt++;
		}
		System.out.println("answer : " + cnt);
	}
}
