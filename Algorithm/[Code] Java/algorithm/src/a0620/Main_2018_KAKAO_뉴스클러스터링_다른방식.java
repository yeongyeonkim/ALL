package a0620;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main_2018_KAKAO_뉴스클러스터링_다른방식 {

	public static void main(String[] args) {
		System.out.println(solution("FRANCE", "french")); // 16384
		System.out.println(solution("handshake", "shake hands")); // 65536
		System.out.println(solution("aa1+aa2", "AAAA12")); // 43690
		System.out.println(solution("E=M*C^2", "e=m*c^2	")); // 65536
		System.out.println(solution("BAAAA", "AAA"));
	}
	public static int solution(String str1, String str2) {
		double answer = 0;
        Map<String, Long> map1 = makeMap(str1.toUpperCase());
        Map<String, Long> map2 = makeMap(str2.toUpperCase());
//		if (or == 0) {
//			answer = 1; // 공집합
//		} else {
//			answer = (double) and / (double) or;
//		}
		return (int) (answer * 65536);
	}
	public static Map<String, Long> makeMap(String str) {
		return IntStream.range(0, str.length() - 1)
				.mapToObj(index -> str.substring(index, index + 2))
				.filter(text -> text.chars().allMatch(character -> Character.isAlphabetic((char) character)))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
}
