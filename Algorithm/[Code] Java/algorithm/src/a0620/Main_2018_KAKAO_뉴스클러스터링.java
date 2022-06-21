package a0620;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main_2018_KAKAO_뉴스클러스터링 {

//	public static Map<String, Integer> map1, map2;
	public static void main(String[] args) {
		System.out.println(solution("FRANCE", "french")); // 16384
		System.out.println(solution("handshake", "shake hands")); // 65536
		System.out.println(solution("aa1+aa2", "AAAA12")); // 43690
		System.out.println(solution("E=M*C^2", "e=m*c^2	")); // 65536
		System.out.println(solution("BAAAA", "AAA"));
	}
	/*
	 * 중복 허용 {1,1,2,2,3} {1,2,2,4,5} {1,2,2} {1,1,2,2,3,4,5} -> 이 처리를 위해 무조건
	 * Map<String, Integer>로 생성해야할 듯 둘 중 작은 값을 교집함, 큰 값을 합집합 요소로 사용
	 *
	 * // 65 ~ 90 'A' ~ 'Z'
	 * 
	 * 유사도는 0~1 사이의 실수이므로 65536을 곱한 후 소수점 아래를 버림.
	 */

	public static int solution(String str1, String str2) {
		double answer = 0;
		Map<String, Integer> map1 = new HashMap<>();
		Map<String, Integer> map2 = new HashMap<>();
		makeMap(str1, map1);
		makeMap(str2, map2);
		// 특정 str이 null이 아닌 것은 교집합에 ++
		// 합집합은 무조건 ++
		int and = 0; // 교집합
		int or = 0;
		Map<String, Integer> result = new HashMap<>(); // 합집합
		for (String key : map1.keySet()) {
			// 1. map1과 map2에 같이 있는지 확인한다.
			// 1-1. 같이 있는 경우
			int value1 = map1.get(key);
			if (!Objects.isNull(map2.get(key))) {
				int value2 = map2.get(key);
				and += Math.min(value1, value2);
				result.put(key, Math.max(value1, value2));
			} else { // 1-2. 같이 없는 경우
				result.put(key, value1);
			}
		}
		for (String key : map2.keySet()) { // 나머지 처리
			if (Objects.isNull(result.get(key))) {
				result.put(key, map2.get(key));
			}
		}
		for (String key : result.keySet()) {
			or += result.get(key);
		}
		if (or == 0) {
			answer = 1; // 공집합
		} else {
			answer = (double) and / (double) or;
		}
		return (int) (answer * 65536);
	}

	public static void makeMap(String str, Map<String, Integer> map) {
		StringBuilder sb;
		for (int i = 0; i < str.length() - 1; i++) {
			char front = str.charAt(i);
			char back = str.charAt(i + 1);
			if (check(front, back)) {
				sb = new StringBuilder();
				sb.append(front);
				sb.append(back);
				String tmp = sb.toString().toUpperCase();
				if (Objects.isNull(map.get(tmp))) {
					map.put(tmp, 1);
				} else {
					map.put(tmp, map.get(tmp) + 1);
				}
			}
		}
	}

	public static boolean check(char front, char back) {
		if ((('a' <= front && front <= 'z') || ('A' <= front && front <= 'Z'))
				&& (('a' <= back && back <= 'z') || ('A' <= back && back <= 'Z'))) {
			return true;
		}
		return false;
	}
}
