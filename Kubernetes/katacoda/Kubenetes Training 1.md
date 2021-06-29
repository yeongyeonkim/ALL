### 워크로드

##### Kubernetes에 Application을 배포하고, 외부에서 접속 할 수 있도록 설정을 하는 실습.

현재 Kubernetes 환경의 상태를 확인하기 위해서는,

`kubectl cluster-info`

`kubectl get nodes`

`kubectl get pods -A`

를 각각 실행시켜 보면 된다.

![1](img1/1.PNG)

* 위의 Kubernetes Cluster 환경은, master와 node01 두개의 host로 구성되어 있으며, master node에는 control plan이 배포되고, 사용자가 배포하는 application은 모두 node01로 배포가 된다.
* kubernetes 의 최소 단위는 POD 이며, POD의 Replica 개수를 지정하는 것이 ReplicaSet, 그리고 ReplicaRest의 배포 및 롤백과 같이 History를 포함하는 것이 Deployment 

#### POD

```yml
apiVersion: v1
kind: Pod
metadata:
	name: httpd
	labels:
	 app: httpd
spec:
	containers:
	- image: ethos93/go-httpd:v1
	  imagePullPolicy: Always
	  name: httpd
	  ports:
	  - containerPort: 80
	    protocol: TCP
```

