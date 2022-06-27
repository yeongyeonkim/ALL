package a0625;

public class Main_문제2_level1 {

	public static void main(String[] args) {
		System.out.println(solution("01033334444"));
		System.out.println(solution("027778888"));
		System.out.println(solution("1234"));
	}
	public static String solution(String str) {
		StringBuilder sb = new StringBuilder();
		// 살려야하는 건 4개
		// str가 예를들어 6개면
		// 0~2까지 * 2~6까지 숫자
		for(int i=0; i<str.length() - 4; i++) {
			sb.append("*");
		}
		for(int i=str.length() - 4; i<str.length(); i++) {
			sb.append(str.charAt(i));
		}
		str = sb.toString();
		return str;
	}
}
