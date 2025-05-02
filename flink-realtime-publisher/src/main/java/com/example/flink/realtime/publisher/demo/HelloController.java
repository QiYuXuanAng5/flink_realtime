package flink;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.example.demo.HelloController
 * @Author guo.jia.hui
 * @Date 2025/4/22 20:32
 * @description: test
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "🎉 Spring Boot 启动成功！访问时间: " + java.time.LocalTime.now();
    }
}
