#### CPU 가장 높은 Pod 확인 및 적재

1. `kubectl top po -l [name=label]`

2. `vi [/path/test.txt]`

#### Log 확인 및 적재

1. `kubectl describe pod <pod-name>` 해서 container를 알아낸 뒤

2. `kubectl logs {pod name} -c {container name}`

3. `kubectl logs {pod name} -c {container-name} | grep {ERROR} > {/path/test.txt}`
