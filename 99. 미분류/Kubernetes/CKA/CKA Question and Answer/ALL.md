* help 옵션을 많이 사용해서 힌트를 얻자.

  `kubectl get -h`   `kubectl run -h` 

1. List all the pods sorted by name

   ```shell
   kubectl get pods --sort-by=metadata.name
   ```

2. Create a pod with image nginx called nginx and allow traffic on port 80

   ```shell
   kubectl run nginx --image=nginx:latest --port=80
   ```

3. Create and configure the service front-end-service so it's accessible through NodePort and routes to the existing pod named front-end.

   ```shell
   kubectl expose pod front-end --name=front-end-service --port=80 --target-port=80 --type=NodePort
   ```

4. Set the node labelled with name=ek8s-node-1 as unavailable and reschedule all the pods running on it

   ```shell
   # https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#drain
   kubectl cordon ke8s-node-1
   kubectl drain ek8s-node-1 --delete-local-data --ignore-daemonsets --force
   ```

   

5.  Create 2 nginx image pods in which one of them is labelled with `env=prod` and another one labelled with `env=dev` and verify the same.

   ```shell
   kubectl run nginx-prod --image=nginx --labels="env=prod" --dry-run -o yaml > prod.yaml
   kubectl run nginx-dev --image=nginx --labels="env=dev" --dry-run -o yaml > dev.yaml
   vi
   ```

6. Create a busybox pod that runs the command "env" and save the output to "envpod" file

   ```shell
   kubectl run busybox --image=busybox --rm -it -- env > envpod.yaml
   ```

7. Create a namespace called 'development' and a pod with image nginx called nginx on this namespace

   ```shell
   kubectl create namespace development
   kubectl run nginx --image=nginx -n development
   ```

8. Create an nginx pod with container Port 80 and it should only receive traffic only it checks the endpoint / on port 80 and verify and delete the pod

   ```shell
   kubectl run nginx --image=nginx --port=80 --dry-run -o yaml > nginx.yaml
   vi nginx.yaml
   ```

9. ㅇ

10. ㅇ

11. ㅇ

12. ㅇ

13. ㅇ

14. ㅇ

15. ㅇ

16. ㅇ

17. ㅇ

18. ㅇ

19. 

