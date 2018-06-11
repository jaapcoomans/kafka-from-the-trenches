package eu.jstack.sample.kftt.order.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class KafkaProducerFactory {
    @Produces
    public KafkaProducer<Long, String> createProducer(){
        return new KafkaProducer<Long, String>(
                readConfig(),
                new LongSerializer(),
                new StringSerializer()
        );
    }

    private Map<String, Object> readConfig() {
        Map<String, Object> conf = new HashMap<>();
        conf.put("bootstrap.servers", "kafka-broker:29092");
        return conf;
    }
}
