package eu.jstack.sample.kftt.inventory.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class KafkaConsumerFactory {
    @Produces
    public KafkaConsumer<Long, String> createConsumer(){
        return new KafkaConsumer<Long, String>(
                readConfig(),
                new LongDeserializer(),
                new StringDeserializer()
        );
    }

    private Map<String, Object> readConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("bootstrap.servers", "kafka-broker:29092");
        config.put("group.id", "inventory");
        return config;
    }
}
