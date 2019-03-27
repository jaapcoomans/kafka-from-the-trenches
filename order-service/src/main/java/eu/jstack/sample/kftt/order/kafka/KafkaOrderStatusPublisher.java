package eu.jstack.sample.kftt.order.kafka;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import eu.jstack.sample.kftt.order.OrderStatus;
import eu.jstack.sample.kftt.order.OrderStatusPublicher;

@ApplicationScoped
public class KafkaOrderStatusPublisher implements OrderStatusPublicher {
	private KafkaProducer<Long, String> kafkaProducer;

	@Inject
	public KafkaOrderStatusPublisher(final KafkaProducer<Long, String> kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	@Override
	public void orderStatusChanged(final long orderId, final OrderStatus newStatus) {
		this.kafkaProducer.send(
			new ProducerRecord<>("order_status_changed",
				orderId, newStatus.toString())
		);
	}
}
