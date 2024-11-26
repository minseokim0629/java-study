package collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		Queue<String> q = new LinkedList<>();
		
		q.offer("둘리"); // 입력
		q.offer("마이콜");
		q.offer("도우너");
		
		while(!q.isEmpty()) {
			String s = q.poll(); // 출력
			System.out.println(s);
		}
		
		// 비어있는 경우 오류 발생하지 않고 null 반환
		System.out.println(q.poll());
		
		q.offer("둘리"); // 입력
		q.offer("마이콜");
		q.offer("도우너");
		
		System.out.println(q.poll());
		System.out.println(q.peek());
		System.out.println(q.poll());
	}

}
