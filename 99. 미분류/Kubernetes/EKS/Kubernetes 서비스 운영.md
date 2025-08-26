쿠버네티스 클러스터는 크게 **마스터 노드**와 **워커 노드**로 구성된다.

* 마스터 노드는 쿠버네티스 설정 환경을 저장하고 전체 클러스터 관리
* 컨테이너 애플리케이션이 작동하는 서버로서 Api Server 에 의하여 명령을 받고 실제 워크로드에 생성하여 서비스가 실행된다.

etcd : 모든 클러스터 데이터를 담는 고가용성 키-값 저장소

-> 이 데이터를 백업하는 계획은 필수★ ([Operating etcd clusters for Kubernetes | Kubernetes](https://kubernetes.io/docs/tasks/administer-cluster/configure-upgrade-etcd/#backing-up-an-etcd-cluster))

도커는 hyper-v 같은 가상 머신이 아니고 서비스라서 docker 데몬

node(ec2) 마다 docker, kubelet 등이 있음.

docker : hub에 이미 만들어진 컨테이너를 실행시켜주는 플랫폼.

c advisor : 도커모니터링 툴 - 하드웨어 리소스 사용량, 이미지, 컨테이너 정보들을 수집.

-> kubelet안에서 동작하고 있음. (워커 노드 장비)



API Server는 etcd에서 정보를 알아오고 Scheduler에게 준다. 그리고 Scheduler는 가장 적절한 노드에 할당을 해주려고 한다.

Api Server -> Controller : 갯수를 보장해주는 것

kubelet은 컨테이너 실행 능력이 없어서 docker run 명령을 노드 안의 docker에게 전달해줌. docker는 hub에서 이미지 다운 받고 running 해주게 됨.

자신만의 ip 



kube-proxy 는 로드밸런서, iptables와 같다.?