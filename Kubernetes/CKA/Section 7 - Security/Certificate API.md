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
