package a0819;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_9019 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			String[] command = new String[10000];
			boolean[] visit = new boolean[10000];
			int target = Integer.parseInt(str[1]);
			// 숫자 범위만큼의 배열을 생성 후, 각 숫자에 대한 Command를 기억
			Queue<Integer> q = new LinkedList<>();
			int start = Integer.parseInt(str[0]);
			q.add(start);
			visit[start] = true;
			Arrays.fill(command, "");
			while(!q.isEmpty() && !visit[target]) {
				int num = q.poll();
				int D = (num * 2) % 10000;
				int S = (num == 0) ? 9999 : num - 1;
				int L = (num % 1000) * 10 + num/1000;
				int R = (num % 10) * 1000 + num/10;
				if(!visit[D]) {
					visit[D] = true;
					command[D] = command[num] + "D";
					q.add(D);
				}
				if(!visit[S]) {
					visit[S] = true;
					command[S] = command[num] + "S";
					q.add(S);
				}
				if(!visit[L]) {
					visit[L] = true;
					command[L] = command[num] + "L";
					q.add(L);
				}
				if(!visit[R]) {
					visit[R] = true;
					command[R] = command[num] + "R";
					q.add(R);
				}
			}
			System.out.println(command[target]);
		}
	}
}
