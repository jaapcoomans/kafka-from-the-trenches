package eu.jstack.sample.kftt.inventory;

import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import eu.jstack.sample.kftt.inventory.order.OrderStatus;
import eu.jstack.sample.kftt.inventory.order.OrderStatusListener;

public class KafkaPoller implements Runnable {
	private KafkaConsumer<Long, String> kafkaConsumer;
	private OrderStatusListener orderStatusListener;

	public KafkaPoller(final KafkaConsumer<Long, String> kafkaConsumer,
		final OrderStatusListener orderStatusListener) {
		this.kafkaConsumer = kafkaConsumer;
		this.orderStatusListener = orderStatusListener;
	}

	@Override
	public void run() {
		try {
			ConsumerRecords<Long, String> records = this.kafkaConsumer.poll(1000);
			StreamSupport.stream(records.spliterator(), false)
				.forEach(r -> this.orderStatusListener.orderStatusChanged(r.key(), OrderStatus.valueOf(r.value())));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
