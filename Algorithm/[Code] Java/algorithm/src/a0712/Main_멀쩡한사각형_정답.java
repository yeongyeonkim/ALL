package a0712;

import java.math.BigInteger;

public class Main_멀쩡한사각형_정답 {

	public static void main(String[] args) {
		System.out.println(solution(2, 2)); // 1 ==> 4 - 1*2 = 2
		System.out.println(solution(2, 6)); // 2 ==> 18 - 2*3 = 12
		System.out.println(solution(3, 6)); // 2 ==> 18 - 2*3 = 12
		System.out.println(solution(4, 12)); // 3 ==> 48 - 3*4 = 36
		System.out.println(solution(8, 12)); // 4 ==> 96 - 4*4 = 80
	}

	public static long solution(int w, int h) {
		int gcd = BigInteger.valueOf(w).gcd(BigInteger.valueOf(h)).intValue();
		return ((long) w * (long) h) - ((((long) w / gcd) + ((long) h / gcd) - 1) * gcd);
	}
}
