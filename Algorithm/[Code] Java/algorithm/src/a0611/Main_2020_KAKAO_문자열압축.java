package a0611;

public class Main_2020_KAKAO_문자열압축 {

	public static void main(String[] args) {
		System.out.println(solution("a"));
		System.out.println(solution("aabbaccc"));
		System.out.println(solution("ababcdcdababcdcd"));
		System.out.println(solution("abcabcdede"));
		System.out.println(solution("abcabcabcabcdededededede"));
		System.out.println(solution("xababcdcdababcdcd"));
	}

	public static int solution(String s) {
		int answer = s.length(); // s.length가 최대 값 일 것 + 길이가 1일 때 아래의 케이스가 작동하지 않음. 잡아내면 더 좋았을 텐데
		for (int i = 1; i <= s.length() / 2; i++) {
			int cnt = 1;
			String begin = s.substring(0, i);
			StringBuilder result = new StringBuilder();
			for(int j=i; j<=s.length(); j+=i) {
				String now = s.substring(j, j + i > s.length() ? s.length() : i + j); // 인덱스 벗어나는 경우
				// 넘는 경우 s.substring(j) 로 begin 인덱스로 끝까지 찾아지게끔 해도 무관하다.
				if(begin.equals(now)) cnt++;
				else {
					result.append((cnt != 1 ? cnt : "") + begin);
					begin = now;
					cnt = 1;
				}
			}
			result.append(begin);
			answer = result.length() < answer ? result.length() : answer; 
		}
		return answer;
	}
}
