Q) Create a pod with the ubuntu image to run a container to sleep for 5000 seconds. Modify the file `ubuntu-sleeper-2.yaml`.

A)

```yaml
apiVersion: v1
kind: Pod
metadata:
	name: ubuntu-sleeper-2
spec:
	containers:
	- image: ubuntu
	  name: ubuntu
	  command:
	    - "sleep"
	    - "5000"
```



Q) Create a pod. Set the given command line arguments to change it to 'green'

`Pod Name: webapp-green` `Image: kodekloud/webapp-color` `Command line arguments: --color=green`

A) `kubectl run webapp-green --image=kodekloud/webapp-color --dry-run -o yaml > pod.yaml`

```
apiVersion: v1
kind: Pod
metadata:
	labels:
		run: webapp-green
	name: webapp-green
spec:
	containers:
	- image: kodekloud/webapp-color
	  name: webapp-green
	  args: [ "color=green" ]
```



