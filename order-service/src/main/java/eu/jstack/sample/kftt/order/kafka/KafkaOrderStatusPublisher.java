package eu.jstack.sample.kftt.order.kafka;

import eu.jstack.sample.kftt.order.OrderStatus;
import eu.jstack.sample.kftt.order.OrderStatusPublisher;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class KafkaOrderStatusPublisher implements OrderStatusPublisher {
    @Inject
    private KafkaProducer<Long, String> kafkaProducer;

    @Override
    public void orderStatusChanged(long orderId, OrderStatus status) {
        this.kafkaProducer.send(new ProducerRecord<>("order_status_changed", orderId, status.toString()));
    }
}
