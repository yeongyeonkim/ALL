http://tech.pick-git.com/embedded-redis-server/

임베디드 레디스 메모리 설정 이유

maxmemory를 설정해 주지 않고 사용하면, embedded redis가 maxheap사이즈를 과도하게 차지하는 것을 확인
TC 수행시 많은 용량이 불필요하다고 판단하여 고정된 설정값을 적용