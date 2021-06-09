#### Rolling Update

* 파드 인스턴스를 점진적으로 새로운 것으로 업데이트하여 Deployment 업데이트가 서비스 중단 없이 이루어질 수 있도록 해준다. 새로운 파드는 가용한 자원을 보유한 노드로 스케줄될 것이다.

```yaml
kubectl create -f deployment-definition.yml
kubectl get deployments
kubectl apply -f deployment-definition.yml
kubectl set image deployment/myapp-deployment nginx=nginx:1.9.1
kubectl rollout status deployment/myapp-deployment
kubectl rollout history deployment/myapp-deployment
kubectl rollout undo deployment/myapp-deployment
```

