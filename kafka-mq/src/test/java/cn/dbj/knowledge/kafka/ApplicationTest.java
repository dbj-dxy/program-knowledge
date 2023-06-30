package cn.dbj.knowledge.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(count = 3, ports = {9001, 9002, 9003})
public class ApplicationTest {

    @Test
    public void culTest() {
        System.out.println(1 + 1);
    }


}