`apt-get upgrade -y kubeadm=1.12.0-00`

`kubeadm upgrade apply v1.12.0`

`kubectl get nodes` 해도 1.12 버전의 노드가 표시되지 않을 텐데,

이는 이 명령의 출력에서 각 버전의 kubelet 버전을 표시하기 때문이다.

API 서버 자체의 버전이 아니라 API 서버에 등록 된 노드이기 때문

마스터 노드에서 kubelet을 업그레이드 해주어야 한다. 
(kubeadm은 kubelete 업그레이드 까지 해주지 않아서, 따로 kubelet 업그레이드 또한 해주어야 한다.)

`apt-get upgrade -y kubelet=1.12.0-00`

`systemctl restart kubelet`

`kubectl get nodes`

이렇게 되면 마스터 노드만 1.12 버전일 것. 이후 worker 노드들을 업그레이드 한 번에 하나씩 해보겠다.

`kubectl drain node-1`

drain 명령어를 통해 노드에서 모든 pod를 안전하게 종료하고 다시 예약할 수 있다.

`apt-get upgrade -y kubeadm=1.12.0-00`

`apt-get upgrade -y kubelet=1.12.0-00`

`kubeadm upgrade node config --kubelet-version v1.12.0`

`systemctl restart kubelet`

다음을 실행하여 마스크를 해제

`kubectl uncordon node-1`

이제 노드가 예약되었지만 포드가 이 상태(node1)로 바로 돌아올 필요 없다
