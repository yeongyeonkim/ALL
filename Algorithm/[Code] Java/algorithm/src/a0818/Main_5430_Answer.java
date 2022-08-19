package a0818;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main_5430_Answer {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String p = br.readLine();
			br.readLine();
			String[] str = br.readLine().replace("[", "").replace("]", "").split(",");
			Deque<Integer> deque = new LinkedList<>();
			for (String s: str) 
				if(!s.equals(""))
					deque.add(Integer.parseInt(s));
			System.out.println(ac(deque, p));
		}
	}

	static String ac(Deque<Integer> deque, String commands) {
		boolean reverse = false;

		for (char command : commands.toCharArray()) {
			if (command == 'R')
				reverse = !reverse;
			else {
				if (deque.size() == 0)
					return "error";

				if (reverse)
					deque.removeLast();
				else
					deque.removeFirst();
			}
		}

		StringBuilder sb = new StringBuilder("[");
		while (!deque.isEmpty()) {
			sb.append(reverse ? deque.removeLast() : deque.removeFirst());
			if (deque.size() != 0)
				sb.append(',');
		}
		sb.append(']');

		return sb.toString();
	}
}
