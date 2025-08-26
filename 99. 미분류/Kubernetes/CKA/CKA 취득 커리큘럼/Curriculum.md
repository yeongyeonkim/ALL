참조 : https://github.com/walidshaari/Kubernetes-Certified-Administrator#readme

---

### [ Cluster Architecture, Installation, and Configuration](https://youtu.be/vS-wXo2qbPs) 25%
1. [Manage role based access control](https://kubernetes.io/docs/reference/access-authn-authz/rbac/)
    - [Lab: RBAC with Kubernetes in Minikube](https://medium.com/@HoussemDellai/rbac-with-kubernetes-in-minikube-4deed658ea7b)
1. [Use kubeadm to install a basic cluster](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/create-cluster-kubeadm/)
    - [Lab: Install Kubernetes On Ubuntu](https://phoenixnap.com/kb/install-kubernetes-on-ubuntu)
1. [Manage a highly available Kubernetes cluster](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/high-availability/)
    - [Options for Highly Available topology](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/ha-topology/)
    - [Weaveworks Kubeadm HA cluster](https://www.weave.works/blog/running-highly-available-clusters-with-kubeadm)
1. [Provision underlying infrastructure to deploy Kubernetes cluster](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/)
1. [Peform a version upgrade on Kubernetes cluster using kubeadm](https://kubernetes.io/docs/reference/setup-tools/kubeadm/kubeadm-upgrade/)
1. [implment etcd backup and restore](https://kubernetes.io/docs/tasks/administer-cluster/configure-upgrade-etcd/#backing-up-an-etcd-cluster)

[Kubecon Europe 2020: Kubeadm deep dive](https://youtu.be/DhsFfNSIrQ4)
<details>
<summary> sample commands used during backup/restore/update of nodes </summary>
<p>

```
#etcd backup and restore brief
export ETCDCTL_API=3  # needed to specify etcd api versions, not sure if it is needed anylonger with k8s 1.19+ 
etcdctl snapshot save -h   #find save options
etcdctl snapshot restore -h  #find restore options

## possible example of save, options will change depending on cluster context, as TLS is used need to give ca,crt, and key paths
etcdctl snapshot save /backup/snapshot.db  --cert=/etc/kubernetes/pki/etcd/server.crt  --key=/etc/kubernetes/pki/etcd/server.key --cacert=/etc/kubernetes/pki/etcd/ca.crt


# evicting pods/nodes and bringing back node back to cluster
kubectl drain  <node># to drain a node
kubectl uncordon  <node> # to return a node after updates back to the cluster from unscheduled state to Ready
kubectl cordon  <node>   # to not schedule new pods on a node

#backup/restore the cluster (e.g. the state of the cluster in etcd)


# upgrade kubernetes worker node
kubectl drain <node>
apt-get upgrade -y kubeadm=<k8s-version-to-upgrade>
apt-get upgrade -y kubelet=<k8s-version-to-upgrade>
kubeadm upgrade node config --kubelet-version <k8s-version-to-upgrade>
systemctl restart kubelet
kubectl uncordon <node>


#kubeadm upgrade steps
kubeadm upgrade plan
kubeadm upgrade apply


```

</p>
</details> 

### Workloads & Scheduling – 15%
1. [Understand deployments and how to perform rolling update and rollbacks](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/)
2. Use [ConfigMaps](https://kubernetes.io/docs/concepts/configuration/configmap/) and [Secrets](https://kubernetes.io/docs/concepts/configuration/secret/) to configure applications
  - [configure a pod with a configmap](https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/)
  - [configure a pod with a secret](https://kubernetes.io/docs/tasks/inject-data-application/distribute-credentials-secure/)
3. Know how to [scale applications](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/#scaling-a-deployment)
  - [scaling a statefulset](https://kubernetes.io/docs/tasks/run-application/scale-stateful-set/)
  - [scaling a replicaset](https://kubernetes.io/docs/concepts/workloads/controllers/replicaset/#scaling-a-replicaset)
4. Understand the primitives used to create robust, self-healing, application deployments
 - [Replicaset](https://kubernetes.io/docs/concepts/workloads/controllers/replicaset/)
 - [Deployments](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/)
 - [Statefulsets](https://kubernetes.io/docs/concepts/workloads/controllers/statefulset/)
 - [Daemonset](https://kubernetes.io/docs/concepts/workloads/controllers/daemonset/)
5. Understand [how resource limits can affect Pod scheduling](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/#how-pods-with-resource-requests-are-scheduled)
6. Awareness of manifest management and common templating tools
  * [Kustomize](https://kubernetes.io/docs/tasks/manage-kubernetes-objects/kustomization/)
    - [Kustomize Blog](https://kubernetes.io/blog/2018/05/29/introducing-kustomize-template-free-configuration-customization-for-kubernetes/)
  * [manage kubernetes objects](https://kubernetes.io/docs/tasks/manage-kubernetes-objects/)
  * [Install service catalog using helm](https://kubernetes.io/docs/tasks/service-catalog/install-service-catalog-using-helm/)
    - Non-k8s.io resource: CNCF Kubecon video: [An introduction to Helm - Bridget Kromhout, Microsoft & Marc Khouzam, City of Montreal](https://youtu.be/x2w6T0sE50w?list=PLj6h78yzYM2O1wlsM-Ma-RYhfT5LKq0XC)
   - Non-k8s.io resource: External resource: [templating-yaml-with-code](https://learnk8s.io/templating-yaml-with-code)

### Services & Networking – 20% 

1. Understand [host networking configuration on the cluster nodes](https://kubernetes.io/docs/concepts/cluster-administration/networking/)
2. Understand connectivity between Pods
  - [The concept of Pods networking](https://kubernetes.io/docs/concepts/workloads/pods/#pod-networking)
3. Understand ClusterIP, NodePort, LoadBalancer service types and endpoints
   - [service](https://kubernetes.io/docs/concepts/services-networking/service/)
4. Know how to use [Ingress controllers](https://kubernetes.io/docs/concepts/services-networking/ingress-controllers/) and [Ingress resources](https://kubernetes.io/docs/concepts/services-networking/ingress/#the-ingress-resource)
  - [Ingress concepts]((https://kubernetes.io/docs/concepts/services-networking/ingress/))
5. [Know how to configure and use CoreDNS](https://kubernetes.io/docs/tasks/administer-cluster/dns-custom-nameservers/)
6. [Choose an appropriate container network interface plugin](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/create-cluster-kubeadm/#pod-network)

- [Kubernetes Networking Intro and Deep-Dive - Bowei Du & Tim Hockin, Google](https://youtu.be/tq9ng_Nz9j8)
- [Kubernetes and Networks: why is this so dang hard?](https://youtu.be/xB190-yyJnY?t=241)
- [Kubecon Eu 2020 Tutorial: Communication Is Key - Understanding Kubernetes Networking - Jeff Poole, Vivint Smart Home](https://youtu.be/InZVNuKY5GY?list=PLj6h78yzYM2O1wlsM-Ma-RYhfT5LKq0XC)


### Storage – 10%

1. Understand [storage classes](https://kubernetes.io/docs/concepts/storage/storage-classes/), [persistent volumes](https://kubernetes.io/docs/concepts/storage/persistent-volumes/)
1. Understand [volume mode](https://kubernetes.io/docs/concepts/storage/persistent-volumes/#volume-mode), [access modes](https://kubernetes.io/docs/concepts/storage/persistent-volumes/#access-modes) and [reclaim policies](https://kubernetes.io/docs/concepts/storage/persistent-volumes/#reclaim-policy) for volumes
1. Understand [persistent volume claims](https://kubernetes.io/docs/concepts/storage/persistent-volumes/#persistentvolumeclaims) primitive
1. Know how to [configure applications with persistent storage](https://kubernetes.io/docs/tasks/configure-pod-container/configure-volume-storage/)

<details>
<summary> StorageClass, PersistentVolume, and PersitentVolumeClaim examples </summary>
<p>

- https://kubernetes.io/docs/tasks/administer-cluster/dns-debugging-resolution/)