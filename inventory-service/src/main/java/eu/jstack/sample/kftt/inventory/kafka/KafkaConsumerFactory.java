package eu.jstack.sample.kftt.inventory.kafka;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

@Singleton
public class KafkaConsumerFactory {
	@Produces
	public KafkaConsumer<Long, String> createKafkaConsumer() {
		return new KafkaConsumer<Long, String>(
			getConfiguration(),
			new LongDeserializer(),
			new StringDeserializer()
		);
	}

	private Map<String, Object> getConfiguration() {
		Map<String, Object> config = new HashMap<>();
		config.put("bootstrap.servers", "kafka-broker:9092");
		config.put("group.id", "inventory");
		return config;
	}
}
