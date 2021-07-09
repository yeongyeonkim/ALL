예를 들어, 클라이언트가 인증서 파일과 키를 사용하여 kubernetes api 서버의 주소로 curl 요청을 보낸다면

`curl https://apiserver:6443/api/v1/pods \ --key admin.key --cert admin.crt --cacert ca.crt`

이후 API 서버에서는 사용자를 인증하기 위해 유효성 검사를 한다.(서버, 클라이언트 키, 클라이언트 인증서 등 동일한 정보를 지정할 수 있다.)

`kubectl get pods --server paiserver:6443 --client-key admin.key --client-certificate admin.crt --certiicate-authority ca.crt`

하지만, kubectl에 유틸리티에 대한 권한을 매번 입력하는 것은 번거로운 작업이므로 해당 내용을 **KubeConfig**에 저장하여 kubectl 명령어로 불러오는 것으로 사용한다.

---

* `~/.kube/config` 파일은 kubectl 명령어 사용시 기본적으로 적용시킬 config 파일이다.

```yaml
# $HOME/.kube/config
apiVersion: v1
kind: Config
current-context: {user}@{cluster}

clusters:
- name: apiserver
  cluster:
    certificate-authority: ca.crt # 인증 기관의 인증서 - clusters
    server: https://apiserver:6443
    namespace: ~~~

contexts: # clusters와 users를 통합
- name : admin@apiserver
  context:
    cluster: apiserver
    user: admin


users:
- name: admin
  user:
    client-certificate: admin.crt # 클라이언트의 인증서 및 키 - users
    client-key: admin.key
```

`kubectl config view`

`kubectl config view --kubeconfig={config file name}`

* 명령어 옵션 확인

  `kubectl config -h`

* current-context 확인하는 명령어

  `kubectl config current-context`

* current-context 변경하는 명령어

  `kubectl config use-context {context의 name}`

* kubeconfig의 사용자 항목 설정

  `kubectl config set-credentials user1 --client-certificate=user1.crt --client-key=user1.key`

* kubeconfig의 컨텍스트 항목 설정

  `kubectl config set-context user1-context --cluster=minikube --user=user1`

* cluster 내의 certificate-authority 를 certificate-authority-data로 파일 경로를 읽는 것이 아닌 인코딩된 텍스트 형식으로 줄 수 있다.

  ```yaml
  cat ca.crt | base64 # 이 결과 값을 data 값으로
  certificate-authority-data: {data}
  ```

