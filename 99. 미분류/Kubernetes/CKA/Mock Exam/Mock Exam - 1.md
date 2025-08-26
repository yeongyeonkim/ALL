# Mock Exam - 1

1. Pod 생성
2. Pod 생성 + Label
3. Namespace 생성
4. Node list가 담긴 JSON 파일 생성
5. Service 생성하여 특정 애플리케이션 expose
    - Create a service `messaging-service` to expose the `messaging` application within the cluster on port `6379`.
        
        $ `kubectl expose pod messaging --port=6379 --name=messaging-service`
        
6. Deployment 생성
    - Create a deployment named `hr-web-app` using the image `kodekloud/webapp-color` with `2` replicas.
        
        $ `kubectl create deployment hr-web-app --image=kodekloud/webapp-color --replicas=2`
        
7. Static Pod 생성
    - Create a static pod named `static-busybox` on the controlplane node that uses the `busybox` image and the command `sleep 1000`.
        
        $ `cd /etc/kubernetes/manifests`
        
        $ `kubectl run static-busybox --image=busybox --dry-run=client -oyaml --command -- sleep 1000 > static-busybox.yaml`
        
        $ `kubectl get pods -A`
        
8. Pod 생성
9. TroubleShooting
    - A new application `orange` is deployed. There is something wrong with it. Identify and fix the issue.
        
        $ `kubectl describe pod orange`
        
10. Service 생성하여 특정 애플리케이션 expose
    - Expose the `hr-web-app` as service `hr-web-app-service` application on port `30082` on the nodes on the cluster. 
    The web application listens on port `8080`.
        
        $ `kubectl get pods` → deployment로 배포되는 것을 확인
        
        $ `kubectl expose deployment hr-web-app --name=hr-web-app-service --port=8080 --dry-run=client -oyaml > hr-web-app-service.yaml`
        
        ```yaml
        # hr-web-app-service.yaml
        apiVersion: v1
        kind: Service
        metadata:
          labels:
            app: hr-web-app
          name: hr-web-app-service
        spec:
          type: NodePort # add
          ports:
          - port: 8080
            protocol: TCP
            nodePort: 30082 # 해당 옵션을 주기 위해선 spec.type이 NodePort여야 함.
          selector:
            app: hr-web-app
        ```
        
11. Node 특정 파라미터 값이 담긴 JSON 파일 생성
    - Use JSON PATH query to retrieve the `osImage`s of all the nodes and store it in a file `/opt/outputs/nodes_os_x43kj56.txt`.
    The `osImages` are under the `nodeInfo` section under `status` of each node.
        
        $ `kubectl get nodes -o json | jq '.items[].status.nodeInfo.osImage' > /opt/outputs/nodes_os_x43kj56.txt`
        
12. PV 생성
    - Create a `Persistent Volume` with the given specification.
        
        ```yaml
        apiVersion: v1
        kind: PersistentVolume
        metadata:
          name: pv-analytics
        spec:
          capacity:
            storage: 100Mi
          accessModes:
            - ReadWriteMany
          hostPath:
            path: "/pv/data-analytics"
        ```