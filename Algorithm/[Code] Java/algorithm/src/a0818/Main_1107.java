package a0818;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1107 {

	/*
99999
8
2 3 4 5 6 7 8 9
-> 7
반례.
		첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다.  
		둘째 줄에는 고장난 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다. 
		고장난 버튼이 있는 경우에는 셋째 줄에는 고장난 버튼이 주어지며, 
		같은 버튼이 여러 번 주어지는 경우는 없다.
		
		버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다. 
		채널 0에서 -는 0
	 */
	public static int min;
	public static String target;
	public static int[] answer;
	public static boolean[] visit;
	public static void find(int idx) {
		if (idx == target.length()) {
			StringBuilder sb = new StringBuilder();
			int sum = 0;
			for(int i=0; i<target.length(); i++) {
				sb.append(answer[i]);
				sum++;
			}
			sum += Math.abs(Integer.parseInt(target) - Integer.parseInt(sb.toString()));
			min = min > sum ? sum : min;
			return;
		}
		for(int i=0; i<10; i++) {
			if(!visit[i]) {
				answer[idx] = i;
				find(idx + 1);
			}
		}
	}
	public static void main(String[] args) throws Exception {
		// 있는 숫자로 결과 값과 가장 가까운 값을 찾고, cnt
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		target = br.readLine();
		int n = Integer.parseInt(br.readLine());
		String[] str = new String[n];
		if(n!=0) {
			str = br.readLine().split(" ");
		}
		min = Integer.MAX_VALUE/2;
		
		answer = new int[target.length()];
		visit = new boolean[10];
		for(int i=0; i<n; i++) {
			visit[Integer.parseInt(str[i])] = true;
		}
		// 1. 버튼 활용 시
		find(0);
		// 2. 버튼 누르지 않을 시
		int sum = Math.abs(Integer.parseInt(target) - 100);
		min = min > sum ? sum : min;
		System.out.println(min);
	}
}
