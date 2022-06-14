http://tech.pick-git.com/embedded-redis-server/

https://jojoldu.tistory.com/297

##### 임베디드 레디스 메모리 설정 이유

* maxmemory를 설정해 주지 않고 사용하면, embedded redis가 maxheap사이즈를 과도하게 차지하는 것을 확인
  TC 수행시 많은 용량이 불필요하다고 판단하여 고정된 설정값을 적용

---

##### 기술 스택

* lettuce 
  * Redis Java Client
  * netty를 기반으로 하며 연결 인스턴스(StatefulRedisConnection)는 여러 스레드에서 공유할 수 있다. 따라서 다중 스레드 응용 프로그램은 Lettuce와 상호 작용하는 동시 스레드 수에 관계없이 단일 연결을 사용할 수 있다.
* Jedis
  * Jedis는 애플리케이션이 여러 스레드에서 단일 Jedis 인스턴스를 공유하려는 경우 **스레드로부터 안전하지 않은** 간단한 Redis 클라이언트입니다. 다중 스레드 환경에서 Jedis를 사용하는 접근 방식은 연결 풀링을 사용하는 것입니다. Jedis를 사용하는 각 동시 스레드는 Jedis 상호 작용 기간 동안 자체 Jedis 인스턴스를 가져옵니다. 연결 풀링은 Redis 연결 수를 증가시키는 Jedis 인스턴스당 물리적 연결 비용으로 발생합니다.
* 