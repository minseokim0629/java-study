package collection;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Stack<String> s = new Stack<>();
		
		s.push("둘리");
		s.push("마이콜");
		s.push("또치");
		
		while(!s.empty()) {
			String str = s.pop();
			System.out.println(str);
		}
		
		// 비어 있으면 예외가 발생한다
		// s.pop();
		
		s.push("둘리");
		s.push("마이콜");
		s.push("또치");
		
		System.out.println(s.pop());
		System.out.println(s.peek()); // pop을 하지 않고 맨 위에 뭐가 있는지 살펴보는 것
		System.out.println(s.pop());
	}

}
