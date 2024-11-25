package creational.singleton;

public class Singleton {
	private static Singleton instance = null;
	// 외부에서 생성 못하게 해야함 (내부에서만 생성하도록)
	private Singleton() {
		
	}

	public static Singleton getInstance() {
		if(instance==null) {
			instance = new Singleton();
		}
		return instance;
	}
}
