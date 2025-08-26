# Mock Exam - 2

1. etcd backup 생성
    - Take a backup of the etcd cluster and save it to `/opt/etcd-backup.db`.
        
        $ `cat /etc/kubernetes/manifests/etcd.yaml`
        
        $ `ETCDCTL_API=3 etcdctl \
        --cacert=/etc/kubernetes/pki/etcd/ca.crt \
        --cert=/etc/kubernetes/pki/etcd/server.crt \
        --key=/etc/kubernetes/pki/etcd/server.key \
        snapshot save /opt/etcd-backup.db`
        
2. Pod 생성 + Volume Mount
    - Create a Pod called `redis-storage` with image: `redis:alpine` with a Volume of type `emptyDir` that lasts for the life of the Pod.
        
        $ `kubectl run redis-storage --image=redis:alpine --dry-run=client -oyaml > redis-storage.yaml`
        
        ```yaml
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            run: redis-storage
          name: redis-storage
        spec:
          containers:
          - image: redis:alpine
            name: redis-storage
            volumeMounts:
            - mountPath: /data/redis
              name: redis-volume
          volumes:
          - name: redis-volume
            emptyDir: {}
        ```
        
    - Pod named 'redis-storage' created
    - Pod 'redis-storage' uses Volume type of emptyDir
    - Pod 'redis-storage' uses volumeMount with mountPath = /data/redis
3. Pod 생성
    - Create a new pod called `super-user-pod` with image `busybox:1.28`. Allow the pod to be able to set `system_time`.
    The container should sleep for 4800 seconds.
        
        $ `kubectl run super-user-pod --image=busybox:1.28 --dry-run=client -oyaml --command -- sleep 4800 > super-user-pod.yaml`
        
        [https://kubernetes.io/docs/tasks/configure-pod-container/security-context/](https://kubernetes.io/docs/tasks/configure-pod-container/security-context/)
        
        ```yaml
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            run: super-user-pod
          name: super-user-pod
        spec:
          containers:
          - command:
            - sleep
            - "4800"
            image: busybox:1.28
            name: super-user-pod
            securityContext:
              capabilities:
                add: ["SYS_TIME"]
        ```
        
    - Pod: super-user-pod
    - Container Image: busybox:1.28
    - SYS_TIME capabilities for the conatiner?
4. PV + Pod
    - A pod definition file is created at `/root/CKA/use-pv.yaml`. Make use of this manifest file and mount the persistent volume called `pv-1`. Ensure the pod is running and the PV is bound.
    mountPath: `/data`
    persistentVolumeClaim Name: `my-pvc`
        
        ```yaml
        # pvc.yaml
        apiVersion: v1
        kind: PersistentVolumeClaim
        metadata:
          name: my-pvc
        spec:
          accessModes:
            - ReadWriteOnce
          resources:
            requests:
              storage: 10Mi # kubectl get pv
        ```
        
        ```yaml
        # use-pv.yaml
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            run: use-pv
          name: use-pv
        spec:
          containers:
          - image: nginx
            name: use-pv
            volumeMounts:
            - mountPath: "/data"
              name: mypd
          volumes:
            - name: mypd
              persistentVolumeClaim:
                claimName: my-pvc
        ```
        
    - persistentVolume Claim configured correctly
    - pod using the correct mountPath
    - pod using the persistent volume claim?
5. Deployment 생성 및 image version upgrade
    - Create a new deployment called `nginx-deploy`, with image `nginx:1.16` and `1` replica. Next upgrade the deployment to version `1.17` using rolling update.
        
        $ `kubectl create deployment nginx-deploy --image=nginx:1.16`
        
        $ `kubectl set image deployment.apps/nginx-deploy nginx=nginx:1.17`
        
    - Deployment : nginx-deploy. Image: nginx:1.16
    - Image: nginx:1.16
    - Task: Upgrade the version of the deployment to 1:17
    - Task: Record the changes for the image upgrade
6. 새로운 User에게 권한을 부여
    - Create a new user called `john`. Grant him access to the cluster. John should have permission to `create, list, get, update and delete pods` in the `development` namespace . The private key exists in the location: `/root/CKA/john.key` and csr at `/root/CKA/john.csr`.
    `Important Note`: As of kubernetes 1.19, the CertificateSigningRequest object expects a `signerName`.
        
        $ `cat /root/CKA/john.csr | base64 | tr -d "\n"` 
        
        ```yaml
        # csr.yaml
        apiVersion: certificates.k8s.io/v1
        kind: CertificateSigningRequest
        metadata:
          name: john-developer
        spec:
          request: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURSBSRVFVRVNULS0tLS0KTUlJQ1ZEQ0NBVHdDQVFBd0R6RU5NQXNHQTFVRUF3d0VhbTlvYmpDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRApnZ0VQQURDQ0FRb0NnZ0VCQUtNQkhOMUNQemFrK2ltUTYwZ3Y5c1cveVZXUXhSK0JOcUpsalhzNk5ER2RxK21hCnkrMGcxUEdSZ1p4SkVzcHFIQkNwdm5WV3IxVWw5UXJxR25GQ05BVDN4SC9MYkVzODQxYnp6MWtadWRsZXRtNTQKT0tjUm0wRmV3QXhsTnZJTExwclQ3NlBDOVFseTY5aE1SZ0lqdkFWRENkS1UzQnNRNVFSNkhNcDBwdzNPbWVkZQpmeWJoN0dJL3dad2VwdTdNTHlnRmc4Yys5NGRWRDYvTHMwc1o4aTlOcWpldXRKaXlGU0N1a1lMeVJQcTU0dEpNCjhUb0NJeG85ZlEyRDBNbUhBU1Y2cTJlK3hIRnl6NXIzQjV4cWZ1alFWbDZJSExGa1UxZy9ZMjFiRUY5VmZEbzgKcU5uYWgzdXdMN1VkWlhDVjY3V3lSNDNjY2tCckVHSW11K2kzSkpVQ0F3RUFBYUFBTUEwR0NTcUdTSWIzRFFFQgpDd1VBQTRJQkFRQnV2MUF0QXgvdktqMUVmRnhFcEhPYmZENGhDeFRtdTZrQTdXSUgxYWQ0Q3FHR2FRbVdUMEFjCmVKSUsvRUtRVHhMeDUvTldqTm5hUkI5MUNlTHB5a04yK296K3JZcGpvYlQ1OGRSNFV0cHFzcjNtc2prMC83ZXUKUktoT1pTdENvNUVzUWM1UjJxSDlVcndhVmpuNDVKdGtJTGxCTFlUUzdzQ3VaMkEwY242WUlqS3BtSzJUeFJYZApiVmFiMEJZTjZFQlZjZVB0SkFjc29zK3gzWEZ5K0lGcnEwVFVtN3VwS2hqekFKZnNvUVR3NzI0UlR5UWtLL1ZEClJ4NzE4MGc4WlljNkgrYlJnaGRGY3duL3Vwa2FhOGl3VW1HQW5ON3VuRHlWYWp3Q2NsZHk1MVVZNWhVN2pLdFcKNnNJZXVaajBpMFA5eDk2T3dxek1QMDE4NFNYMHpDd2YKLS0tLS1FTkQgQ0VSVElGSUNBVEUgUkVRVUVTVC0tLS0tCg==
          signerName: kubernetes.io/kube-apiserver-client
          usages:
          - client auth
        ```
        
        $ `kubectl create -f csr.yaml`
        
        $ `kubectl certificate approve john-developer`
        
        $ `kubectl create role developer --namespace=development --resource=pods --verb=create,list,get,update,delete`
        
        $ `kubectl create rolebinding developer-binding-john --namespace=development --role=developer --user=john`
        
    - CSR: john-developer Status:Approved
    - Role Name: developer, namespace: development, Resource: Pods
    - Access: User 'john' has appropriate permissions
7. Pod 생성 및 Service DNS 설정
    - Create a nginx pod called `nginx-resolver` using image `nginx`, expose it internally with a service called `nginx-resolver-service`. Test that you are able to look up the service and pod names from within the cluster. Use the image: `busybox:1.28` for dns lookup. Record results in `/root/CKA/nginx.svc` and `/root/CKA/nginx.pod`
        
        $ `kubectl run nginx-resolver --image=nginx`
        
        $ `kubectl expose pod nginx-resolver --name=nginx-resolver-service --port=80 --target-port=80`
        
        $ `kubectl run test-ns --image=busybox:1.28 --rm -it --restart=Never -- nslookup nginx-resolver-service > /root/CKA/nginx.svc`
        
        $ `kubectl run test-ns --image=busybox:1.28 --rm -it --restart=Never -- nslookup 10-50-192-3.default.pod > /root/CKA/nginx.pod` - {Ip}.{Namespace}.{Resource}
        
        ![Screenshot_4.png](Mock%20Exam%20-%202%20f592d7031b69425db1eadb85f3ea595e/Screenshot_4.png)
        
    - Pod: nginx-resolver created
    - Service DNS Resolution recorded correctly
    - Pod DNS resolution recorded correctly
8. Static Pod 생성
    - Create a static pod on `node01` called `nginx-critical` with image `nginx` and make sure that it is recreated/restarted automatically in case of a failure.
    Use `/etc/kubernetes/manifests` as the Static Pod path for example.
        
        $ `ssh node01`
        
        - 이후에 manifest 경로에서 run으로 yaml 파일 생성하려니 다음과 같은 에러 발생.
        The connection to the server localhost:8080 was refused - did you specify the right host or port?
        - worker node에서 생성할 수 없는 것으로 생각되어, 마스터 노드에서 생성한 것을 카피떠서 vi 편집기로 yaml 파일을 직접 만들어주어야 할 듯.
        
        $ `kubectl run nginx-critical --image=nginx --dry-run=client -o yaml > nginx-critical.yaml`
        
        $ `vi /etc/kubernetes/manifests/nginx-critical.yaml` - 내용 삽입
        
        $ `exit`
        
        $ `kubectl get pods`