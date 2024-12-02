package behavioral.observer;

//Subject가 T라는 데이터를 가지고 있다
public interface Observer<T> {
	//T라는 데이터가 변하면 무언가를 하겠다
	void update(T val);
}
