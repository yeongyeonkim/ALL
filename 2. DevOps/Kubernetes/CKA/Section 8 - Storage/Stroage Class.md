스토리지 클래스를 사용하면 자동으로 프로비저닝할 수 있는 Google 스토리지와 같은 임시를 정의할 수 있다.

* storage-class docs와 pv, pvc docs 참고하여 해결</br>
  https://kubernetes.io/docs/concepts/storage/storage-classes/</br>
  https://kubernetes.io/docs/concepts/storage/persistent-volumes/

```yaml
# sc-definition.yaml
apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: google-storage
provisioner: kubernetes.io/gce-pd
parameters: 
  type: pd-standard | pd-ssd
  replication-type: none | regional-pd
```

```yaml
# pvc-definition.yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: myclaim
spec:
  accessModes:
    - ReadWriteOnce
  storageClassName: google-storage
  resources:
    requests:
      storage: 500Mi
```