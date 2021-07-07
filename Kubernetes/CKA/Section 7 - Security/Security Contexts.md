

### Kubernetes 보안

* 컨테이너 수준 또는 포드 수준에서 보안 설정을 구성하도록 선택할 수 있다.
  * Container : 프로세스들이 사용하는 사용자(runAsUser)와 그룹(fsGroup) 등을 설정
  * Pod : `spec: ` 에 설정되는 항목에 대한 권한을 설정

---

Q) What is the user used to execute the sleep process within the 'ubuntu-sleeper' pod?

A) `kubectl exec ubuntu-sleeper -- whoami`

---

### Pod 전체의 컨테이너에 적용한 예

Q)

Edit the pod 'ubuntu-sleeper' to run the sleep process with user ID 1010.

Note: Only make the necessary changes. Do not modify the name or image of the pod.

- Pod Name: ubuntu-sleeper
- Image Name: ubuntu
- SecurityContext: User 1010

A)

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: ubuntu-sleeper
spec:
  securityContext: # spec 이하에 securityContext가 위치
    runAsUser: 1010
  containers:
  - command:
    - sleep
    - "4800"
    image: ubuntu
    name: ubuntu-sleeper
```

---

### Container에 보안 컨텍스트를 적용

* securityContext 섹션을 container 이하에 두면 된다.
* 기능을 추가하려면 `capabilities` 옵션을 사용.

Q)

Add SYS_TIME capability to the container's securityContext

A)

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: ubuntu-sleeper
  namespace: default
spec:
  containers:
  - command:
    - sleep
    - "4800"
    image: ubuntu
    name: ubuntu-sleeper
    securityContext: # spec: containers: 이하에 securityContext가 위치
      capabilities:
        add: ["SYS_TIME"]
```
