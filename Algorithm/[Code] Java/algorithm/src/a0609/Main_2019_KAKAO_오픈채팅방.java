package a0609;

import java.util.ArrayList;
import java.util.HashMap;

public class Main_2019_KAKAO_오픈채팅방 {

	public static void main(String[] args) {
		System.out.println(solution(new String[] { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234",
				"Enter uid1234 Prodo", "Change uid4567 Ryan" }));
	}

	public static String[] solution(String[] record) {
		String[] answer = {};
		ArrayList<String> arr = new ArrayList<>();
		// uid에 대한 HashMap을 생성해주어야 할 듯
		// uid, 최종 이름
		HashMap<String, String> map = new HashMap<>();
		for (String str : record) {
			String[] tmp = str.split(" ");
			if (tmp[0].equals("Leave")) {
				continue;
			}
			map.put(tmp[1], tmp[2]);
		}
		for (String str : record) {
			String[] tmp = str.split(" ");
			if (tmp[0].equals("Change")) {
				continue;
			}
			String status = tmp[0];
			String uid = tmp[1];
			StringBuilder sb = new StringBuilder();
			if (status.equals("Enter")) {
				sb.append(map.get(uid) + "님이 들어왔습니다.");
			} else { // Leave
				sb.append(map.get(uid) + "님이 나갔습니다.");
			}
			arr.add(sb.toString());
		}
		answer = new String[arr.size()];
		for(int i=0; i<arr.size(); i++) {
			answer[i] = arr.get(i);
		}
		return answer;
	}
}
