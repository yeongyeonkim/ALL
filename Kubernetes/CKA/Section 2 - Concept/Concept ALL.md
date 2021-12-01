#### ReplicaSet

* manifest 파일의 apiVerison 확인하는 법 (pod 인 경우)
  kubectl explain pod | grep VERSION
* ReplicaSet Scale 변경하는 법 (Desired)
  1. `kubectl edit rs <replicaset-name>`
  2. `kubectl scale rs <replicaset-name> --replicas=5`

##### Namespace

* pod에 namesapce 지정하여 생성

  `kubectl run nginx --image=nginx -n <namespace-name>`

#### Service

```yaml
# manifest
apiVersion: v1
kind: Service
metadata:
  name: webapp-service
spec:
  type: NodePort
  ports:
    - targetPort: 8080
      port: 8080
      nodePort: 30080
  selector:
    name: simple-webapp
```

#### Imperative commands

* Service

  `kubectl expose pod redis --port=6379 --type=ClusterIP --name=redis-service`

* Pod Expose

  * container port 8080 pod 

    `kubectl run nginx --image=nginx --port=8080`

----

#### 핵심

* --dry-run Pod 예시

  `kubectl run redis --image=redis --dry-run=client -o yaml > redis.yaml`

* httpd pod를 생성하면서, target port가 80인 ClusterIP 유형의 서비스를 만든다.

  `kubectl run httpd --image=httpd:alpine --port=80 --expose`
