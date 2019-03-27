package eu.jstack.sample.kftt.order.kafka;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

@Singleton
public class KafkaProducerFactory {
	@Produces
	public KafkaProducer<Long, String> createProducer() {
		return new KafkaProducer<Long, String>(
			getConfiguration(),
			new LongSerializer(),
			new StringSerializer()
		);
	}

	private Map<String, Object> getConfiguration() {
		Map<String, Object> config = new HashMap<>();
		config.put("bootstrap.servers", "kafka-broker:9092");
		return config;
	}
}
