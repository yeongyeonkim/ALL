##### Stream

* 배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를 필터링하고 가공된 결과를 얻을 수 있다.

* 람다를 이용해서 코드의 양을 줄이고 간결하게 표현할 수 있다. 즉, 배열과 컬렉션을 함수형으로 처리할 수 있다.
* 병렬처리가 가능하다. 하나의 작업을 둘 이상의 작업으로 잘게 나눠서 동시에 진행하는 것.



##### 배열 스트림

* 스트림은 배열 또는 컬렉션 인스턴스를 이용해서 생성할 수 있다.
* 배열은 아래와 같이 `Arrays.stream` 메소드를 사용한다.

```java
String[] arr = new String[]{"a", "b", "c"};
Stream<String> stream = Arrays.stream(arr);
Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // index 1 ~ 2 요소 [b, c]
```



##### 컬렉션 스트림

* 컬렉션 타입(Collection, List, Set)의 경우 인터페이스에 추가된 디폴트 메소드 `stream`을 이용해서 스트림을 만들 수 있다.

```java
public interface Collection<E> extends Iterable<E> {
	default Stream<E> stream() {
		return StreamSupport.stream(spliterator(), false);
	}
	// ...
}
```

* 이후 다음과 같이 생성할 수 있다.

```java
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();
Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림
```







참고 : https://futurecreator.github.io/2018/08/26/java-8-streams/