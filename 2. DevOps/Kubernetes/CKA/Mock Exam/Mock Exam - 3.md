# Mock Exam - 3

1. Service Account, ClusterRole, ClusterRoleBinding
    - Create a new service account with the name `pvviewer`. Grant this Service account access to `list` all PersistentVolumes in the cluster by creating an appropriate cluster role called `pvviewer-role` and ClusterRoleBinding called `pvviewer-role-binding`.
    Next, create a pod called `pvviewer` with the image: `redis` and serviceAccount: `pvviewer` in the default namespace.
        
        $ `kubectl create sa pvviewer`
        
        $ `kubectl create clusterrole pvviewer-role --resource=persistentvolume --verb=list`
        
        $ `kubectl create clusterrolebinding pvviewer-role-binding --clusterrole=pvviewer-role --serviceaccount=default:pvviewer` → servcieaccount 파라미터 형태는 `--serviceaccount=namespace:serviceaccountname` 으로 kubectl Reference Docs에 있다.
        
        $ `kubectl run pvviewer --image=redis --serviceaccount=pvviewer`
        
    - ServiceAccount: pvviewer
    - ClusterRole: pvviewer-role
    - ClusterRoleBinding: pvviewer-role-binding
    - Pod: pvviewer
    - Pod configured to use ServiceAccount pvviewer ?
2. 리소스 조회 및 JSON 형태 추출
    - List the `InternalIP` of all nodes of the cluster. Save the result to a file `/root/CKA/node_ips`.
    Answer should be in the format: `InternalIP of controlplane`<space>`InternalIP of node01` (in a single line)
        
        $ `kubectl get nodes -o jsonpath='{.items[*].status.addresses[?(@.type=="InternalIP")].address}' > /root/CKA/node_ips` 
        
3. Pod 생성
    - Create a new pod called `super-user-pod` with image `busybox:1.28`. Allow the pod to be able to set `system_time`.
    The container should sleep for 4800 seconds.
        
        $ `kubectl run multi-pod --image=busybox --dry-run=client -oyaml --command -- sleep 4800 > multi-pod.yaml`
        
        [https://kubernetes.io/docs/tasks/inject-data-application/define-environment-variable-container/#define-an-environment-variable-for-a-container](https://kubernetes.io/docs/tasks/inject-data-application/define-environment-variable-container/#define-an-environment-variable-for-a-container)
        
        ```yaml
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            run: multi-pod
          name: multi-pod
        spec:
          containers:
          - name: alpha
            image: nginx
            env:
            - name: name
              value: alpha
          - name: beta
            image: busybox
            command:
            - sleep
            - "4800"
            env:
            - name: name
              value: beta
        ```
        
    - Pod Name: multi-pod
    - Container 1: alpha
    - Container 2: beta
    - Container beta commands set correctly?
    - Container 1 Environment Value Set
    - Container 2 Environment Value Set
4. Pod Security
    - Create a Pod called `non-root-pod` , image: `redis:alpine`
    runAsUser: 1000
    fsGroup: 2000
        
        $ `kubectl run non-root-pod --image=redis:alpine --dry-run=client -oyaml > non-root-pod.yaml`
        
        ```yaml
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            run: non-root-pod
          name: non-root-pod
        spec:
          securityContext:
            runAsUser: 1000
            fsGroup: 2000
          containers:
          - image: redis:alpine
            name: non-root-pod
        ```
        
    - Pod non-root-pod fsGroup configured
    - Pod non-root-pod runAsUser configured
5. NetworkPolicy
    - We have deployed a new pod called `np-test-1` and a service called `np-test-service`. Incoming connections to this service are not working. Troubleshoot and fix it.
    Create NetworkPolicy, by the name `ingress-to-nptest` that allows incoming connections to the service over port `80`.
        
        ```yaml
        apiVersion: networking.k8s.io/v1
        kind: NetworkPolicy
        metadata:
          name: ingress-to-nptest
        spec:
        podSelector:
            matchLabels:
              run: np-test-1
          policyTypes:
          - Ingress
          ingress:
          - ports:
            - protocol: TCP
              port: 80
        ```
        
    - Important: Don't Alter Existing Objects!
    - NetworkPolicy: Applied to All sources (Incoming traffic from all pods)?
    - NetWorkPolicy: Correct Port?
    - NetWorkPolicy: Applied to correct Pod?
6. 새로운 User에게 권한을 부여
    - Taint the worker node `node01` to be Unschedulable. Once done, create a pod called `dev-redis`, image `redis:alpine`, to ensure workloads are not scheduled to this worker node. Finally, create a new pod called `prod-redis` and image: `redis:alpine` with toleration to be scheduled on `node01`.
    key: `env_type`, value: `production`, operator: `Equal` and effect: `NoSchedule`
        
        $`kubectl taint node node01 env_type=production:NoSchedule`
        
        $`kubectl run dev-redis --image=redis:alpine`
        
        $`kubectl run prod-redis --image=redis:alpine --dry-run=client -oyaml > prod-redis.yaml`
        
        ```yaml
        # prod-redis.yaml
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            run: prod-redis
          name: prod-redis
        spec:
          containers:
          - image: redis:alpine
            name: prod-redis
          tolerations:
          - key: "env_type"
            operator: "Equal"
            value: "production"
            effect: "NoSchedule"
        ```
        
        ![Screenshot_5.png](Mock%20Exam%20-%203%20bc37f1d244e440669e629bb8d68f6c3a/Screenshot_5.png)
        
    - Key = env_type
    - Value = production
    - Effect = NoSchedule
    - pod 'dev-redis' (no tolerations) is not scheduled on node01?
    - Create a pod 'prod-redis' to run on node01
7. Pod 생성 + Label
    - Create a pod called `hr-pod` in `hr` namespace belonging to the `production` environment and `frontend` tier. image: `redis:alpine`
    Use appropriate labels and create all the required objects if it does not exist in the system already.
        
        $ `kubectl create namespace hr`
        
        $ `kubectl run hr-pod --image=redis:alpine --namespace=hr --dry-run=client -oyaml > hr-pod.yaml`
        
        ```yaml
        # hr-pod.yaml
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            environment: production
            tier: frontend
          name: hr-pod
          namespace: hr
        spec:
          containers:
          - image: redis:alpine
            name: hr-pod
        ```
        
    - hr-pod labeled with environment production?
    - hr-pod labeled with tier frontend?
8. Troubleshooting - Cluster
    - A kubeconfig file called `super.kubeconfig` has been created under `/root/CKA`. There is something wrong with the configuration. Troubleshoot and fix it.
        
        $ `kubectl cluster-info`
        
        $ `vi /root/CKA/super.kubeconfig` - port 변경
        
9. Troubleshooting - Deployment & Replicas
    - We have created a new deployment called `nginx-deploy`. scale the deployment to 3 replicas. Has the replica's increased? Troubleshoot the issue and fix it.
        
        $ `kubectl scale deployment/nginx-deploy --replicas=3`
        
        $ `kubectl get pods -A` → controller-manager가 문제. (Pod 이름이 contro1ler로 되어있음)
        
        $ `cd /etc/kubernetes/manifests`/
        
        $ `sed -i 's/kube-contro1ler-manager/kube-controller-manager/g' kube-controller`
        
    - deployment has 3 replicas
    

podSelector:
matchLabels:
run: np-test-1