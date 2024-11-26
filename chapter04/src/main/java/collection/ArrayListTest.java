package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {

	public static void main(String[] args) {
		// 요즘 버전
		List<String> list = new ArrayList<>();

		list.add("둘리");
		list.add("마이콜");
		list.add("또치");

		// 순회1
		// 직접 데이터에 접근
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			System.out.println(s);
		}

		// 삭제
		// int - idx, string - 데이터 값
		list.remove(2);

		// 순회2
		// Enumeration → Iterator
		Iterator<String> it = list.iterator(); // 한번만 순회 가능
		while (it.hasNext()) {
			String s = it.next();
			System.out.println(s);
		}

		// 순회3
		// 데이터에 접근하지 않고 받아오는 것
		for (String s : list) {
			System.out.println(s);
		}
	}

}
