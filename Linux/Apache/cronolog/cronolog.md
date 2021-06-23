##### CronoLog 프로그램은 아파치 로그(Access 및 Error)를 날짜별로 나눠주는 프로그램.

```shell
ErrorLog "|/usr/local/sbin/setup/sbin/cronolog /usr/local/apache2/logs/error_log.%Y%m%d"

CustomLog "|/usr/local/sbin/setup/sbin/cronolog /usr/local/apache2/logs/access_log.%Y%m%d" combined
```

* `|{cronolog.sh가 설치되어있는 경로} {로그가 자동으로 저장될 곳의 경로 및 원하는 형식의 파일 명}`

