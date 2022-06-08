package a0608;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main_2019_KAKAO_실패율 {

	public static void main(String[] args) {
		System.out.println(solution(5, new int[] { 2, 1, 2, 6, 2, 4, 3, 3 }));
		System.out.println(solution(4, new int[] { 4, 4, 4, 4, 4 }));
	}

	public static int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		double[] result = new double[N + 1];
		int[] visit = new int[N + 2];
		int cnt = 0;
		ArrayList<double[]> arr = new ArrayList<>();
		for (int stage : stages) {
			visit[stage]++;
		}
		for (int i = 1; i <= N; i++) {
			result[i - 1] = (double) visit[i] / (stages.length - cnt);
			arr.add(new double[] { (double) i, visit[i], result[i - 1] });
			cnt += visit[i];
		}
		Collections.sort(arr, new Comparator<>() {
			@Override
			public int compare(double[] o1, double[] o2) {
				// [0]: 해당 스테이지 N, [1]: 해당 스테이지 실패 인원, [2]: 해당 스테이지 실패율
				if (o1[2] > o2[2]) {
					return -1;
				} else if (o1[2] < o2[2]) {
					return 1;
				} else { // 같은 경우
					return Double.compare(o2[1], o1[1]);
				}
			}
		});
		for (int i = 0; i < arr.size(); i++) {
			answer[i] = (int) arr.get(i)[0];
		}
		return answer;
	}
}
