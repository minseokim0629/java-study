package chapter04;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		// key에 1:1 매핑되기 때문에 값 자체로는 순회할 수 없다.
		// key를 통해서는 순회할 수 있다.
		Map<String, Integer> m = new HashMap<>();

		// key, value
		m.put("one", 1); // auto boxing
		m.put("two", 2);
		m.put("three", 3);

		// key를 통해 value 찾기
		int i = m.get("one"); // auto unboxing
		int j = m.get(new String("two"));
		System.out.println(i + ":" + j);
		
		// 이미 key값이 있는 경우
		// 값을 덮어버린다
		m.put("three", 3333);
		System.out.println(m.get("three"));
		
		// 순회
		// key에서 set을 빼와야 한다
		Set<String> s = m.keySet();
		for(String key : s) {
			System.out.println(m.get(key));
		}
	}

}
