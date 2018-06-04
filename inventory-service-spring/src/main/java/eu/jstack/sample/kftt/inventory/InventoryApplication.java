package eu.jstack.sample.kftt.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableKafka
public class InventoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class);
    }
}
