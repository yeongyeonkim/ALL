package kyy.api.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootInflearnBasicApplication {
    /*
    프로세스가 비정상적으로 종료되지 않아서 8080이 이미 점유된 포트라고 함.
    따라서 cmd에서 다음 두 명령어를 사용
    1. netstat -ano | findstr 8080
    2. taskkill /F /pid {port} 
    '/F' 명령어는 프로세스를 강제로 종료시키는 것
     */
    public static void main(String[] args) {
		SpringApplication.run(SpringBootInflearnBasicApplication.class, args);
    }

}
