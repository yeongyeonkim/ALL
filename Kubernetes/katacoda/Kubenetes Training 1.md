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

* Manifest를 살펴보면, `kind`에는 Object 종류, 그리고 `metadata`에는 name과 label을 지정하도록 되어 있다.
* `spec`에는 pod에 생성될 container의 spec을 지정한다.
* containers로 되어 있는 것을 보면 알 수 있 듯, 다수의 container를 지정하여 하나의 Pod에 여러개의 container가 실행될 수 있도록 한다. 위에서는 ethos93/go-httpd:v1 이미지와 포트 80을 사용하도록 지정.
* yaml 파일을 통해 object를 생성하는 방법은, `kubectl create -f "yaml 파일명"` 이다.
* `apply` command 또한 사용할 수 있는데, apply는 기존에 동일한 이름의 object가 없다면 create를, 동일한 이름의 object가 있다면 replace 를 시켜준다. `kubectl apply -f "yaml 파일명"`

##### ReplicaSet

* Pod에 문제가 생겼을 경우 서비스가 중단될 수 있따. 물론, Kubernetes는 문제가 생긴 Pod를 종료시키고 새로운 Pod를 생성 시키는 자동화가 되어있긴 하지만 이 동안 서비스가 불가능하기 때문에, Production 환경에서는 반드시 복수개의 Pod를 생성하여 중단 없는 서비스를 제공할 필요가 있다. 이를 위한 ReplicaSet

```yml
apiVersion: apps/v1
kind: ReplicaSet
metadata:
	name: httpd-replicaset
	labels:
		app: httpd-replicaset
spec:
	replicas: 3
	selector:
		matchLabels:
			app: httpd-replicaset
	template:
		metadata:
			labels:
            	app: httpd_replicaset
       	spec:
       		containers:
       		- image: ethos93/go-httpd:v1
       		  imagePullPolicy: Always
       		  name: httpd-replicaset
       		  ports:
       		  - containerPort: 80
       		    protocol: TCP
		
```



* 