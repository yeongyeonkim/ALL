package a0527;

import java.util.Objects;

public class Main_2018_KAKAO_1차_비밀지도 {

	public static void main(String[] args) {
		System.out.println(solution(5, new int[] { 9, 20, 28, 18, 11 }, new int[] { 30, 1, 21, 17, 28 }));
		System.out.println(solution(6, new int[] { 46, 33, 33 ,22, 31, 50 }, new int[] { 27 ,56, 19, 14, 14, 10 }));
	}

	public static String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = {};
		answer = new String[n];
		String[][] map = new String[n][n];
		for(int i=0; i<n; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<n; j++) {
				int binary = (int) Math.pow(2, n-j-1);
				if (arr1[i] >= binary) {
					arr1[i] -= binary;
					map[i][j] = "#";
				} 
				if (arr2[i] >= binary) {
					arr2[i] -= binary;
					map[i][j] = "#";
				}
				if (Objects.isNull(map[i][j])) {
					sb.append(" ");
				} else {
					sb.append("#");
				}
			}
			answer[i] = sb.toString();
		}
		return answer;
	}

}
