package a0625;

public class Main_문제1_level1 {

	public static void main(String[] args) {
		System.out.println(solution(10));
		System.out.println(solution(12));
		System.out.println(solution(11));
		System.out.println(solution(13));
	}
	public static boolean solution(int x) {
		int num = x;
		int sum = 0;
		while(num > 0) {
			sum += num % 10;
			num /= 10;
		}
		if (x % sum == 0) {
			return true;
		} else {
			return false;
		}
	}
}
