package a0711;

import java.util.ArrayList;

public class Main_기능개발 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 93, 30, 55 }, new int[] { 1, 30, 5 }));
		System.out.println(solution(new int[] { 95, 90, 99, 99, 80, 99 }, new int[] { 1, 1, 1, 1, 1, 1 }));
	}

	public static int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};
		ArrayList<Integer> result = new ArrayList<>(); // answer 에 담아 줄 동적인 배열이 필요
		ArrayList<Integer> arr = new ArrayList<>(); // 종료되는 날들을 담을 배열
		// 5 10 1 1 20 1
		// 5일째 1개 5
		// 10일 째 3개 10 1 1
		// 20일 째 2개 20 1
		for (int i = 0; i < speeds.length; i++) {
			int num = progresses[i];
			int cnt = 0;
			while (num < 100) {
				num += speeds[i];
				cnt++;
			}
			arr.add(cnt);
		}
		// 5 10 1 1 20 1
		int day = arr.get(0);
		int cnt = 0;
		for (int i = 0; i < arr.size(); i++) {
			if(day >= arr.get(i)) {
				cnt++;
			} else { 
				result.add(cnt);
				cnt = 1;
				day = arr.get(i);
			}
		}
		if(cnt >= 1) { // 나머지 처리
			result.add(cnt);
		}
		answer = new int[result.size()];
		for(int i=0; i<result.size(); i++) {
			answer[i] = result.get(i);
			System.out.print(answer[i] + " ");
		}
		return answer;
	}
}
