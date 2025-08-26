1. Create CertificateSigningRequest(CSR) Object
2. Review Requests
3. Approve Requests
4. Share Certs to Users

---

* OpenSSL을 사용하여 키(KEY)를 생성.
  
  `openssl genrsa -out jane.key 2048`

* 인증서 서명 요청(CSR) 생성
  
  `openssl req -new -key jane.key -subj "/CN=jane" -out jane.csr`

* 인증서 (CRT) 생성
  
  `openssl x509 -req -in jane.csr -CA {ca.crt} -CAkey {ca.key} -out jane.crt -days 500`

* base64 명령을 사용하여 인코딩
  
  `cat jane.csr | base64`

* 모든 인증서 서명 요청을 확인
  
  `kubectl get csr`

* 새 요청을 식별하고 요청을 승인한다
  
  `kubectl certificate approve jane `

* 요청에 대한 상세 정보 확인
  
  ``kubectl get csr jane -o yaml`

* 요청을 거절
  
  `kubectl certificate deny jane`

* CSR object 삭제
  
  `kubectl delete csr jane`

---

새로 합류한 팀원의 certificate 발급을 위해 csr, key를 받았다.

해당 팀원에 대한 CSR을 생성하기 위해서, CSR의 request에는 base64로 인코딩된 csr파일 내용이 들어가야 하기에 다음과 같이 입력한다.

`cat akshay.csr | base64 | tr -d "\n"`

[Certificate Signing Requests | Kubernetes](https://kubernetes.io/docs/reference/access-authn-authz/certificate-signing-requests/#create-certificatesigningrequest)

```yaml
apiVersion: certificates.k8s.io/v1
kind: CertificateSigningRequest
metadata:
  name: akshay
spec:
  groups:
  - system:authenticated
  request: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURSBSRVFVRVNULS0tLS0KTUlJQ1ZqQ0NBVDRDQVFBd0VURVBNQTBHQTFVRUF3d0dZV3R6YUdGNU1JSUJJakFOQmdrcWhraUc5dzBCQVFFRgpBQU9DQVE4QU1JSUJDZ0tDQVFFQXY4azZTTE9HVzcrV3JwUUhITnI2TGFROTJhVmQ1blNLajR6UEhsNUlJYVdlCmJ4RU9JYkNmRkhKKzlIOE1RaS9hbCswcEkwR2xpYnlmTXozL2lGSWF3eGVXNFA3bDJjK1g0L0lqOXZQVC9jU3UKMDAya2ZvV0xUUkpQbWtKaVVuQTRpSGxZNDdmYkpQZDhIRGFuWHM3bnFoenVvTnZLbWhwL2twZUVvaHd5MFRVMAo5bzdvcjJWb1hWZTVyUnNoMms4dzV2TlVPL3BBdEk4VkRydUhCYzRxaHM3MDI1ZTZTUXFDeHUyOHNhTDh1blJQCkR6V2ZsNVpLcTVpdlJNeFQrcUo0UGpBL2pHV2d6QVliL1hDQXRrRVJyNlMwak9XaEw1Q0ErVU1BQmd5a1c5
emQKTmlXbnJZUEdqVWh1WjZBeWJ1VzMxMjRqdlFvbndRRUprNEdoayt2SU53SURBUUFCb0FBd0RRWUpLb1pJaHZjTgpBUUVMQlFBRGdnRUJBQi94dDZ2d2EweWZHZFpKZ1k2ZDRUZEFtN2ZiTHRqUE15OHByTi9WZEdxN25oVDNUUE5zCjEwRFFaVGN6T21hTjVTZmpTaVAvaDRZQzQ0QjhFMll5Szg4Z2lDaUVEWDNlaDFYZnB3bnlJMVBDVE1mYys3cWUKMkJZTGJWSitRY040MDU4YituK24wMy9oVkN4L1VRRFhvc2w4Z2hOaHhGck9zRUtuVExiWHRsK29jQ0RtN3I3UwpUYTFkbWtFWCtWUnFJYXFGSDd1dDJveHgxcHdCdnJEeGUvV2cybXNqdHJZUXJ3eDJmQnErQ2Z1dm1sVS9rME4rCml3MEFjbVJsMy9veTdqR3ptMXdqdTJvNG4zSDNKQ25SbE41SnIyQkZTcFVQU3dCL1lUZ1ZobHVMNmwwRERxS3MKNTdYcEYxcjZWdmJmbTRldkhDNnJCSnNiZmI2ZU1KejZPMUU9Ci0tLS0tRU5EIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLQo=
  signerName: kubernetes.io/kube-apiserver-client
  usages:
  - client auth
```

`kubectl certificate approve akshay`
