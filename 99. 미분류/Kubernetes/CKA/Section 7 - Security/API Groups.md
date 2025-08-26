kube-apiserver를 통해 api를 호출하면 다음과 같은 api group들을 시작으로 하위 여러 api들을 호출할 수 있다.

`/metrics, /healthz, /version, /api, /apis, /logs`

쿠버네티스 자원을 표현하고 컨트롤하는 클러스터 기능을 담당하는 API는 `/api` 와 `/apis` 이다.

![-](img/3.png)

![](img/4.png)

