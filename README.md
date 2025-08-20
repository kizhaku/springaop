About the app:
- A further enhancement to the simple CRUD app built in Spring boot running on a server with observability setup locally. 
- No database connection. Stores data in a static map. Gets reset with restart.
- Use Prometheus and Loki for health metrics, alerts and logs collection.
- Use Grafana for metrics and logs visualization.
- Further iteration will be containerise app and tools. Then shift application and tools in Kubernetes setup.

Requirements:
- Collect app health metrics display in Prometheus.
- Collect logs Promtail + Loki. Visualize in Grafana. 
- This setup for standalone environment.
- Alerts will be good to have.

Setup:
Application changes:
    - Add dependency for actuator and micrometer-prometheus
        <!-- Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!-- Prometheus registry -->
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>
    - Enable actuator for health, prometheus endpoint
        management.endpoints.web.exposure.include=health,info,prometheus
        management.endpoint.health.show-details=always
    - Health info at: http://localhost:8080/actuator/health
        Prometheus info at: http://localhost:8080/actuator/prometheus

Prometheus:
    - Go to https://prometheus.io/download/ to download the correct binaries for machine and unzip.
    - Create prometheus.yml with the scrape config.
    - Within the prometheus directory, run the executable - ./prometheus --config.file=<<absolute path to prometheus.yml>>
    - Prometheus dashboard can be viewed at http://localhost:9090/
        - Within the dashboard you can query using promql expression
            eg: rate(http_server_requests_seconds_count[5m])
                http_server_requests_seconds_count{job="spring-aop-app", status="404"}[10m] : Check 404 errors with given job name.
        - Alerts: You can view alerts under http://localhost:9090/alerts
            - Alerts can be setup by adding a rules.yml and providing its reference under rule_files
            - Check prometheus.yml and rules.yml under directory observability.
            - Alerts can also be configured to send notification email etc. This can be done by installing the alert manager.
Promtail & Loki:
    - Promtail will scrape the logs at the logs/app.log and push to loki.
    - Download and install Loki and Promtail, follow instruction at https://grafana.com/docs/loki/latest/setup/install/local/
    - Create the loki.yml and promtail.yml. Refer observability directory.
    - From the Loki executable location run ./loki --config.file=<<absolute path to loki-config.yml>>
    - Loki status can be checked at http://localhost:3100/ready
    - Create the promtail.yml. This will define the scrape job, the logs path, the label and the loki endpoint to push. Refer observability directory.
    - From the Promtail executable location run ./promtail --config.file=<<absolute path to promtail.yml>>
Grafana:
    - Install and run Grafana https://grafana.com/docs/grafana/latest/setup-grafana/installation/. In Mac Grafana can be installed through Homebrew.
    - In Mac run Grafana as: brew services start grafana
    - Access Grafana at http://localhost:3000/
    - Go to data source and add both Prometheus and Loki. Provide the local url for both services.
    - Once added, go to Explore from side menu. 
        - From the dashboard, select Prometheus to view time series metrics, query etc
        - From the dashboard, select Loki. You can provide app name, job name etc to pull the logs. Further drill down can be done using the query. 

Links:
Prometheus: http://localhost:9090/alerts
Actuator: http://localhost:8080/actuator/prometheus
Grafana: http://localhost:3000/
Loki: http://localhost:3100/ready






