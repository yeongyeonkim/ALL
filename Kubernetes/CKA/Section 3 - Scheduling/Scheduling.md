#### Manual Scheduling

* scheduler가 없으면 pod는 Pending 상태가 된다.

* node 할당이 되지 않은 pod에 nodeName으로 node 할당하기
  
  ```yaml
  apiVersion: v1
  king:  Pod
  metadata:
    name: nginx
  spec:
    nodeName: node01
    containrs:
    - image: nginx
      name: nginx
  ```

---

#### Labels and Selectors

Label을 지정하고 selector로 매칭

* env=dev인 pod들 찾기
  
  1. `kubectl get pods --selector env=dev`
  2. `kubectl get po -l env=dev`

* Identify the POD which is part of the `prod` environment, the `finance` BU and of `frontend` tier?
  
  `kubectl get po -l env=prod,bu=finance,tier=frontend`

---

#### Taints and Tolerations

Taint: 노드마다 설정 가능. 설정한 노드에는 Pod가 스케줄되지 않음.

Toleration: taint를 무시할 수 있다.

주로 노드를 지정된 역할만 하게끔 하기 위해 사용.

* Taint에 적용 가능한 Effect 3가지
  
  1. `NoSchedule`: toleration이 없으면 pod가 스케줄 되지 않는다. 기존 실행된 pod는 적용되지 않음.
  2. `NoExecute`: toleration이 없으면 pod가 스케줄 되지 않는다. 기존에 실행된 pod도 적용됨.
  3. `PreferNoSchedule`: toleratino이 없으면 pod가 스케줄 되지 않지만 필수는 아니다.

* Taint 적용
  
  `kubectl taint node <node-name> <key>=<value>:<effect>`

* Pod 생성 manifest 파일에 toleration 적용
  
  ```yaml
  apiVersion: v1
  kind: Pod
  metadata:
    name: bee
  spec:
    containers:
    - image: nginx
      name: bee
    tolerations:
    - key: spray
      value: mortein
      effect: NoSchedule
  ```

* 적용한 Taint를 삭제하려면 `-`를 붙여준다.
  
  `kubectl taint nodes controlplane node-role.kubernetes.io/master:NoSchedule-`

* taint가 적용되었는지 확인하고 해당 노드에 파드 배포가 가능한지 확인
  
  `kubectl describe node node01 | grep -i taints`
  
  -> `Taints: <none>` 이면 배포가 가능하다. 

---

#### Node Affinity

Pod가 특정 node로 배포되도록 하는 기능

affiniy 종류에는 주로 다음 두 가지가 있다.

`requiredDuringSchedulingIgnoredDuringExecution` - 조건이 부합하는 node에만 배포하는 것.

` preferredDuringSchedulingIgnoredDuringExecution` - 되도록이면 조건이 부합하는 node에 배포할 수 있도록 선호도를 주는 것.

* [Node Affinity Manifest](https://kubernetes.io/ko/docs/concepts/scheduling-eviction/assign-pod-node/#%EB%85%B8%EB%93%9C-%EC%96%B4%ED%94%BC%EB%8B%88%ED%8B%B0)

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  labels:
    app: blue
  name: blue
spec:
  replicas: 3
  selector:
    matchLabels:
      app: blue
  template:
    metadata:
      labels:
        app: blue
    spec:
      containers:
      - image: nginx
        name: nginx
      affinity:
        nodeAffinity:
          requiredDuringSchedulingIgnoredDuringExecution:
            nodeSelectorTerms:
            - matchExpressions:
              - key: color
                operator: In
                values:
                - blue
```

---

#### DaemonSets

* 모든 노드에 Pod를 띄우는 컨트롤러

* 데몬셋의 용도는 다음과 같다.
  
  * 모든 노드에서 클러스터 스토리지 데몬 실행
  * 모든 노드에서 로그 수집 데몬 실행
  * 모든 노드에서 노드 모니터링 데몬 실행

* DaemonSet 구조는 Deployment와 비슷해서 생성할 때 `kubectl create deploy > yaml` 형식으로 manifest를 생성하고 Kind를 Deployment -> Daemonset 으로 변경해주고, replicas 등 Deployment 요소를 제거해주면 된다.

---

#### Static Pods

* API 서버의 개입 없이 kubelet이 자체적으로 생성한 Pod 또는 나머지 kubernetes 클러스터 구성 요소는 정적 포드로 알려져 있다.

* 정적 포드를 식별하는 방법 중 하나는 **접미사**를 찾는 것. (-controlplane)

* 정적 포드 정의 파일이 있는 디렉토리 경로 확인
  
  `ps -aux | grep kubelet`
  
  `grep -i staticpod /var/lib/kubelet/config.yaml`

* Static Pod 생성하는 방법
  
  1. kubelet이 사용하는 config 위치를 확인. 
     주로 `/etc/kubernetes/manifests` 
  
  2. 해당 파일 내에 yaml 파일을 생성하면 static pod가 자동으로 배포된다.
     
     `kubectl run --image=busybox static-busybox --dry-run=client -o yaml --command -- sleep 1000 > /etc/kubernetes/manifests/static-busybox.yaml`
     
     명령적 선언시 --command 위치는 중요하다.

* 예제 - 특정 node에 static pod를 제거하는 방법
  
  1. `ssh <node-name>`
  2. `ps -ef |  grep /usr/bin/kubelet`
  3. `grep -i staticpod /var/lib/kubelet/config.yaml`
  4. 나온 경로의 해당 static pod manifest 파일 제거

---

#### Multiple scheduler

[Docs](https://kubernetes.io/docs/tasks/extend-kubernetes/configure-multiple-schedulers/)

* kubelet의 configPath로 가서 scheduler.yaml 복사.
  custom이므로 `leader-elect=false`, `port`, `secure-port` 변경해야 한다.
* Custom scheduler를 사용하고 싶은 pod의 spec 아래에 `schedulerName`을 넣어주면 된다.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: nginx
spec:
  schedulerName: my-scheduler
  containers:
  - image: nginx
    name: nginx
```

---

* pod 상태 강제로 변경하는 명령어 `replace --force`
  
  `kubectl replace -f test.yaml --force`
