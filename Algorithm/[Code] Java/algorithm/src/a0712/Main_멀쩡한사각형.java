package a0712;

/*
 * 점수 50점 짜리 코드
 */
public class Main_멀쩡한사각형 {

	public static void main(String[] args) {
		System.out.println(solution(2, 2)); // 1 ==> 4 - 1*2 = 2
		System.out.println(solution(2, 6)); // 2 ==> 18 - 2*3 = 12
		System.out.println(solution(3, 6)); // 2 ==> 18 - 2*3 = 12
		System.out.println(solution(4, 12)); // 3 ==> 48 - 3*4 = 36
		System.out.println(solution(8, 12)); // 4 ==> 96 - 4*4 = 80
	}

	public static long solution(int w, int h) {
		/*
		 * 가장 먼저 두 수의 1이 아닌 최대 공약수를 구한다. w, h가 값이 1억까지 되기 때문에 미리 연산해서는 안된다. 8 12의 경우
		 * 4x(최대공약수 4)의 제외되는 16과 나머지 2x3 짜리가 16-(최대공약수4)개만큼 있다.
		 * 
		 * 최대 공약수를 제외한 타일은 (1,1) (1,2), (1,3), (2,3) 인 총 4가지의 경우의 수가 존재.
		 */
		int common = GCD(w, h);
		w /= common;
		h /= common;
		long includedTile = (long)((long)(w * h) * (common - 1));
		// 한 줄의 타일을 구할 것
		if (w + h == 5) {
			includedTile += 2;
		}
		// 이후 모든 줄의 타일을 return
		return includedTile * common;
	}
	// 호제법
	public static int GCD(int w, int h) {
		if (h == 0) {
			return w;
		}
		return GCD(h, w % h);
	}
}
