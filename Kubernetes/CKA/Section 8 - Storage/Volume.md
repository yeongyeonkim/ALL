##### Persistent Volume

* PV는 동적으로 프로비저닝한 클러스터의 스토리지.
* pod와는 별개의 lifecycle을 가지는 node와 같은 클러스터 리소스.

```yaml
#pv-definition.yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: pv-voll
spec:
  accessModes:
    - ReadWriteOnce # ReadOnlyMany, ReadWriteOnce, ReadWriteMany 가 있다.
  capacity:
    storage: 1Gi
  awsElasticBlockStore:
    volumeID: <volume-id>
    fsType: ext4
```

`kubectl create -f pv-definition.yaml`

`kubectl get persistentvolume`

---

##### Persistent Volume Claim

* 사용자의 스토리지에 대한 요청. pvc는 특정 크기 및 accessMode를 요청할 수 있다.

```yaml
#pvc-definition.yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: myclaim
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 500Mi
```

기본적으로 delete Claim하면 Claim만 지워지는데 `persistentVolumeReclaimPolicy: Delete` 명령어로 볼륨까지 제거 할 수 있다.

##### Pod에서 PVC 사용

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: mypod
spec:
  containers:
    - name: myfrontend
      image: nginx
      volumeMounts:
      - mountPath: "/var/www/html"
        name: mypd
  volumes:
    - name: mypd
      persistentVolumeClaim:
        claimName: myclaim
```



---

##### Volume Mode

쿠버네티스는 pv의 두 가지 `volumeModes`인 `Filesystem`과 `Block`을 지원한다.

`volumeMode`는 선택적 API 파라미터이다. `Filesystem`은 `volumeMode` 파라미터가 생략될 때 사용되는 기본 모드.

`volumeMode: Filesystem`이 있는 볼륨은 디렉터리에 마운트된다. 볼륨이 장치에 의해 지원되고 그 장치가 비어 있으면 쿠버네티스는 장치를 처음 마운트하기 전에 장치에 파일시스템을 만든다.

볼륨을 원시 블록 장치로 사용하려면 `volumeMode`의 값을 `Block`으로 설정할 수 있다.

---

##### Access Mode

리소스 제공자가 지원하는 방식으로 호스트에 pv를 마운트할 수 있다.

AccessMode는 다음 세개가 있다.

* ReadWriteOnce : 하나의 노드에서 볼륨을 읽기-쓰기로 마운트할 수 있다.
* ReadOnlyMany : 여러 노드에서 볼륨을 읽기 전용으로 마운트할 수 있다.
* ReadWriteMany : 여러 노드에서 볼륨을 읽기-쓰기로 마운트할 수 있다.