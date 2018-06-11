package eu.jstack.sample.kftt.inventory.kafka;

import eu.jstack.sample.kftt.inventory.order.OrderStatus;
import eu.jstack.sample.kftt.inventory.order.OrderStatusListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.stream.StreamSupport;

public class KafkaPoller implements Runnable {
    private KafkaConsumer<Long, String> kafkaConsumer;
    private OrderStatusListener listener;

    public KafkaPoller(KafkaConsumer<Long, String> kafkaConsumer, OrderStatusListener listener) {
        this.kafkaConsumer = kafkaConsumer;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            ConsumerRecords<Long, String> records = this.kafkaConsumer.poll(1000);

            StreamSupport.stream(records.spliterator(), false)
                    .forEach(this::consume);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void consume(ConsumerRecord<Long, String> record) {
        this.listener.orderStatusChanged(record.key(), OrderStatus.valueOf(record.value()));
    }
}
