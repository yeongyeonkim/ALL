#### 쿠버네티스 클러스터 다이어그램

<img src="1.png"/>

* Control Plane 컴포넌트와 Node 컴포넌트로 구성이 되어있음.
  * 컨트롤 플레인 컴포넌트는 클러스터에 관한 전반적인 결정(스케줄링 등)을 수행하고 클러스터 이벤트를 감지하고 반응한다.
    * Control Plane: 컨테이너의 라이프사이클을 정의, 배포, 관리하기 위한 API와 인터페이스들을 노출하는 컨테이너 오케스트레이션 레이어
  * 노드 컴포넌트는 동작 중인 Pod를 유지시키고 쿠버네티스 런타임 환경을 제공한다.

---

#### Control Plane Component

* kube-apiserver

  * Control Plane의 프론트엔드로, 내부 및 외부 요청을 처리합니다. 
  * 요청이 유효한지 판별하고 유효한 요청을 처리합니다.
  * REST 호출, kubectl 커맨드라인, kubeadm과 같은 CLI를 통해 API에 액세스할 수 있다.

  ```shell
  kubectl cluster-info
  # Kubernetes control plane is running at https://10.b.c.d:????
  # kubectl이 바라보는 API 서버의 주소를 출력함.
  
  # kubectl은 kubeconfig 파일을 확인하여 이 API 주소를 알 수 있다.
  # 기본적으로 $HOME/.kube/config에 위치함.
  ```

* etcd

  * 모든 클러스터 데이터를 담는 쿠버네티스 뒷단의 일관성, 고가용성 키-값 저장소.

  * 쿠버네티스 클러스터에서 etcd를 뒷단의 저장소로 사용한다면, 이 데이터를 **백업**하는 계획은 필수이다.

    * 클러스터의 상태들이 담긴 저장소

  * **etcd 백업**

    * Built-in 스냅샷 방식

      ```shell
      # $ENDPOINT 파일에 제공되는 키스페이스의 스냅샷을 만드는 예
      ETCDCTL_API=3 etcdctl --endpoints $ENDPOINT snapshot save snapshotdb
      ```

    * Volume 스냅샷 방식

      * etcd가 AWS EBS와 같이 백업을 지원하는 스토리지 볼륨에서 실행 중인 경우 스토리지 볼륨의 스냅샷을 만들어 etcd 데이터를 백업합니다.
      
      ```shell
      # etcdctl에서 사용할 수 있는 다양한 옵션을 나열
      ETCDCTL_API=3 etcdctl -h
      # endpoint, certification 등을 지정하여 스냅샷을 찍는 예시
      ETCDCTL_API=3 etcdctl --endpoints=https://127.0.0.1:2379 \
        --cacert=<trusted-ca-file> \
        --cert=<cert-file> \
        --key=<key-file> \
        snapshot save <backup-file-location>
      ```

* kube-scheduler

  * 클러스터가 양호한 상태인지? 새 컨테이너가 필요하다면 어디에 적합한지?를 정해준다.
  * CPU, Memory와 같은 Pod의 리소스 요구 사항과 함께 클러스터의 상태를 고려해서 Pod를 적절한 Node에 예약한다.

* kube-controller-manager

  * 컨트롤러는 실제로 클러스터를 실행합니다.
  * 스케줄러를 참고하여 정확한 수의 Pod가 실행되게 합니다.
  * 시스템 내의 다양한 구성 요소의 상태를 지속적으로 모니터링하고 전체 시스템을 원하는 작동 상태로 유지하는 역할.

---

#### Node Component

* kubelet
  * 클러스터의 각 노드에서 실행되는 에이전트.
  * kubelet은 Pod에서 컨테이너가 동작하도록 관리한다.
  * Control Plane과 통신하는 애플리케이션. 컨트롤 플레인에서 노드에 작업을 요청하는 경우 kubelet이 이 작업을 실행한다.
    * 마스터의 API 서버와 통신을 하면서, 노드가 수행해야 할 명령을 받아 수행하고, 반대로 노드의 상태도 마스터로 전달하는 역할.
* kube-proxy
  * 각 노드에서 실행되는 네트워크 프록시로, 쿠버네티스의 서비스(파드 집합에서 실행중인 애플리케이션을 노출하는 방법) 개념의 구현부이다.
  * 내부 네트워크 세션이나 클러스터 바깥에서 파드로 네트워크 통신을 할 수 있도록 해준다.
    * 노드로 들어오는 네트워크 트래픽을 적절한 컨테이너로 라우팅.

---

Pod: 애플리케이션의 단일 인스턴스

