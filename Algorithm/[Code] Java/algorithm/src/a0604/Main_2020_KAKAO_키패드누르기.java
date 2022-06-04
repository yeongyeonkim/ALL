package a0604;

public class Main_2020_KAKAO_키패드누르기 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 }, "right"));
		System.out.println(solution(new int[] { 7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2 }, "left"));
		System.out.println(solution(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 }, "right"));
	}

	public static String answer;
	public static int left_x, left_y, right_x, right_y;
	public static String solution(int[] numbers, String hand) {
		answer = "";
		left_x = 0; 
		left_y = 3;
		right_x = 2; 
		right_y = 3;
		for (int i = 0; i < numbers.length; i++) {
			int num = numbers[i];
			if (num == 1) {
				answer += "L";
				left_x = 0;
				left_y = 0;
			} else if (num == 2) {
				append(Math.abs(left_x - 1) + left_y, Math.abs(right_x - 1) + right_y, hand, 1, 0);
			} else if (num == 3) {
				answer += "R";
				right_x = 2;
				right_y = 0;
			} else if (num == 4) {
				answer += "L";
				left_x = 0;
				left_y = 1;
			} else if (num == 5) {
				append(Math.abs(left_x - 1) + Math.abs(left_y - 1), Math.abs(right_x - 1) + Math.abs(right_y - 1), hand, 1, 1);
			} else if (num == 6) {
				answer += "R";
				right_x = 2;
				right_y = 1;
			} else if (num == 7) {
				answer += "L";
				left_x = 0;
				left_y = 2;
			} else if (num == 8) {
				append(Math.abs(left_x - 1) + Math.abs(left_y - 2), Math.abs(right_x - 1) + Math.abs(right_y - 2), hand, 1, 2);
			} else if (num == 9) {
				answer += "R";
				right_x = 2;
				right_y = 2;
			} else {
				append(Math.abs(left_x - 1) + Math.abs(left_y - 3), Math.abs(right_x - 1) + Math.abs(right_y - 3), hand, 1, 3);
			}
		}
		return answer;
	}
	public static void append(int l, int r, String hand, int x, int y) {
		if(l > r) {
			answer += "R";
			right_x = x;
			right_y = y;
		} else if (r > l) {
			answer += "L";
			left_x = x;
			left_y = y;
		} else {
			if (hand.equals("right")) {
				answer += "R";
				right_x = x;
				right_y = y;
			} else {
				answer += "L";
				left_x = x;
				left_y = y;
			}
		}
	}
}
