package a0611;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main_2020_KAKAO_문자열압축 {

	public static void main(String[] args) {
		System.out.println(solution("aabbaccc"));
//		System.out.println(solution("ababcdcdababcdcd"));
//		System.out.println(solution("abcabcdede"));
//		System.out.println(solution("abcabcabcabcdededededede"));
//		System.out.println(solution("xababcdcdababcdcd"));
	}

	public static int solution(String s) {
		int answer = Integer.MAX_VALUE/2;
//		for (int k = 1; k <= s.length() / 2; k++) {
		for (int k = 1; k <= 1; k++) {
			Stack<String> st = new Stack<>();
			Map<String, Integer> map = new HashMap<>();
			StringBuilder sb = new StringBuilder();
			int idx = 0;
			while (true) {
				if (idx + k > s.length()) { // 범위
					break;
				}
				String tmp = s.substring(idx, idx + k);
				if (!st.isEmpty()) {
					if (st.peek().equals(tmp)) { // 기존 값과 같으면 해당 String에 대한 갯수를 갱신해 줌
						map.put(tmp, map.get(tmp) + 1);
					} else { // 기존 값과 다르면 map에 갯수와 해당 문자를 StringBuilder에 ++
						String before = st.pop();
						sb.append(map.get(before));
						map.put(tmp, 0);
						sb.append(before);
						st.add(tmp);
					}
				} else {
					st.add(tmp);
					map.put(tmp, 1);
				}
				idx += k;
				continue;
			}
			System.out.println(k + "gae :" + sb.toString());
			// aaaaabbbbb인경우 stack에 있는 값 빼주는 작업 필요
			int sb_length = sb.toString().length();
			answer = sb_length > answer ? answer : sb_length;
		}
		return answer;
	}
}
