### Opentelemetry (Otel)

* Traces, Metrics, Logs 같은 데이터를 instrumenting, generating, collecting, exporting 할 수 있는 Observability 프레임 워크
* 데이터를 저장하거나 쿼리하는 방법을 제공하지 않고, 오픈 소스 백엔드(Jaeger, Prometheus, Grafana)로 데이터를 전송하여 데이터를 저장한다.

### FluentBit - Opentelemetry 연동

* https://fluentbit.io/opentelemetry/

#### FluentBit DataPipeline

* https://docs.fluentbit.io/manual/concepts/data-pipeline

* FluentBit에는 데이터 파이프라인이 존재하고, Filters, Inputs, Outputs, Parses 등을 설정할 수 있다.

※ Pipeline workflows
 - Input -> Parser -> Filter -> Buffer
 

