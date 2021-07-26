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

4. ㅇ

5. ㅇ

6. ㅇ

7. ㅇ

8. ㅇ

9. ㅇ

10. ㅇ

11. ㅇ

12. ㅇ

13. ㅇ

14. ㅇ

15. ㅇ

16. ㅇ

17. ㅇ

18. 

