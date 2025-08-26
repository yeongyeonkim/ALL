```yaml
apiVersion: v1
kind: Pod
metadata:
  labels:
    run: yellow
  name: yellow
spec:
  containers:
  - image: busybox
    name: lemon
    command:
    - sleep
    - "1000"
  - image: redis
    name: gold
```

위의 내용을 명령어로 아래와 같이 생성하여 수정

`kubectl run yellow --image=busybox --dry-run=client -o yaml --command -- sleep 1000 > {filename}.yaml`

---

Q) Inspect the log file inside the pod.
The application outputs logs to the file `/log/app.log`. View the logs and try to identify the user having issues with Login.

`kubectl exec -it <pod> -n <namespace> -- cat /log/app.log`

---

Q) Edit the pod to add a sidecar container to send logs to Elastic Search. Mount the log volume to the sidecar container.

Only add a new container. Do not modify anything else. Use the spec provided below.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: app
  namespace: elastic-stack
  labels:
    name: app
spec:
  containers:
  - name: app
    image: kodekloud/event-simulator
    volumeMounts:
    - mountPath: /log
      name: log-volume

  - name: sidecar
    image: kodekloud/filebeat-configured
    volumeMounts:
    - mountPath: /var/log/event-simulator/
      name: log-volume

  volumes:
  - name: log-volume
    hostPath:
      # directory location on host
      path: /var/log/webapp
      # this field is optional
      type: DirectoryOrCreate
```

* Name: app
* Container Name: sidecar
* Container Image: kodekloud/filebeat-configured
* Volume Mount: log-volume
* Mount Path: /var/log/event-simulator/
* Existing Container Name: app
* Existing Container Image: kodekloud/event-simulator