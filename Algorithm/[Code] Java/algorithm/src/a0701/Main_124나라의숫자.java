package a0701;

import java.util.*;

public class Main_124나라의숫자 {

	public static void main(String[] args) {
		System.out.println(solution(3)); // 4
		System.out.println(solution(4)); // 11
		System.out.println(solution(14)); // 42
		System.out.println(solution(125)); // 11122
	}

	public static String solution(int n) {
		String answer = "";
		String[] numbers = {"4", "1", "2"};
		int num = n;
		while (num > 0) {
			int tmp = num % 3;
			if(tmp == 0) num--;
			num /= 3;
//			if(tmp == 0) num--; // 4가 들어왔을 경우 3으로 치환하면서 -1
			answer = numbers[tmp] + answer;
		}
		return answer;
	}
}
