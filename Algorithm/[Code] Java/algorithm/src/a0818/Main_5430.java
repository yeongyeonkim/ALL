package a0818;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_5430 {

	/*
	 * 4 RDD 4 [1,2,3,4] DD 1 [42] RRD 6 [1,1,2,3,5,8] D 0 []
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			String AC = br.readLine(); // RDD
			int n = Integer.parseInt(br.readLine()); // 배열 크기
			if(n == 1) {
				if(AC.equals("R")) {
					System.out.println(br.readLine());
				} else {
					br.readLine();
					System.out.println("error");
				}
				continue;
			}
			String[] str = br.readLine().replace("[", "").replace("]", "").split(",");
			int start_index = 0;
			int end_index = n - 1;
			boolean flag = true; // true: 정방향, true: 역방향
			for (int i = 0; i < AC.length(); i++) {
				if (AC.charAt(i) == 'R') {
					if (flag)
						flag = false;
					else
						flag = true;
				} else {
					if (flag)
						start_index++;
					else
						end_index--;
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			if (start_index >= end_index) {
				System.out.println("error");
			} else if (flag) {
				for (int i = start_index; i <= end_index; i++) {
					sb.append(str[i]);
					if (i == end_index)
						break;
					sb.append(",");
				}
				sb.append("]");
				System.out.println(sb.toString());
			} else {
				for (int i = end_index; i >= start_index; i--) {
					sb.append(str[i]);
					if (i == start_index)
						break;
					sb.append(",");
				}
				sb.append("]");
				System.out.println(sb.toString());
			}
		}
	}
}
