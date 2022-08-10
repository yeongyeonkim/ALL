package a0811;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_카드구매하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int[] r = new int[n + 1];
		int[] d = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			r[i] = Integer.parseInt(str[i - 1]);
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				d[i] = Math.max(d[i - j] + r[j], d[i]);
			}
		}
		System.out.println(d[n]);
	}

}
