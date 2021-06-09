#### Secret

password, key와 같은 민감한 정보를 저장하기에 적합하다.

인코딩되거나 Hash 형식으로 저장된다는 점을 제외하면 configMap과 유사

### Create Secrets

##### Imperative

* `kubectl create secret generic <secret-name> --from-literal=<key>=<value>`
  ex) kubectl create secret generic db-secret --from-literal=DB_Host=sql01 --from-literal=DB_User=root
* `kubectl create secret generic <secret-name> --from-file=<path-to-file>`

##### Declarative

```yaml
apiVersion: v1
kind: Secret
metadata:
	name: app-secret
data:
	DB_Host: mysql
	DB_User: root
	DB_Password: passwrd
```

위의 key: value를 넣는 과정에서 value는 인코딩된 형식으로 넣어야하는데

그 방법으로

`echo -n 'mysql' | base64` 이런식으로 해서 나온 값을 넣는다.

* `kubectl get secrets`
* `kubectl describe secrets` -> 비밀의 속성을 보여주지만 값은 숨겨진다.

**Secrets in Pods**

```yaml
## pod-definition.yaml
apiVersion: v1
kind: Pod
metadata:
	name: webapp
	labels:
	  name: webapp
spec:
	containers:
	- name: webapp
	  image: webapp
	  ports:
	    - containerPort: 8080
	  envFrom:
	    - secretRef:
	    	name: app-secret
```

```
## secret-data.yaml
apiVersion: v1
kind: Secret
metadata:
  name: app-secret
data:
  DB_Host: bXlzcWw=
  DB_User: cm9vdA==
  DB_Password: cGFzd3Jk
```



`kubectl explain pods --recursive | less` 명령어로 사용가능한 값들을 확인할 수 있다.

-> envFrom 인자들을 
