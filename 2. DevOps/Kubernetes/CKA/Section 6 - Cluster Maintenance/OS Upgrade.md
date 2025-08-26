1. `drain` : 유지 보수를 위해서 node를 준비 상태로 만듬.

2. `cordon` : 스케줄링할 수 없도록 함.

3. `uncordon` : 스케줄링할 수 있도록 함.

---

* node01을 제거, 모든 application 의 노드를 비우고 예약 불가능으로
  
  - `kubectl drain node01 --ignore-daemonsets`

* 다시 예약할 수 있도록 노드 구성
  
  * `kubectl uncordon node01`

* node01에 pod가 없는 이유
  
  * 새 pod가 예약될 때 까지 생기지 않음.

* 포드가 제어 플레인 노드에 배치되는 이유는 무엇입니까?
  
  * `kubectl describe nodes controlplane | grep -i taint`
  * controlplane 노드에 taint가 없어서.

* drain 명령이 먹히지 않는 경우 -> node01에 예약된 포드가 있을 수 있다.
  강제로 적용하는 방법 `--force`
  
  * `kubectl drain node01 --ignore-daemonsets --force`

* `hr-app`은 중요한 앱이며 제거를 원하지 않고
  `node01`에서 더이상 pod를 예약하고 싶지 않다.</br>
  이 노드에서 새 포드가 예약되지 않도록 `node01`을 예약 불가능으로 표시하라
  
  * `kubectl cordon node01`


