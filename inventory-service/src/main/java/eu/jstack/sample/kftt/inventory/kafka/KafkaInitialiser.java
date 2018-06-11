package eu.jstack.sample.kftt.inventory.kafka;

import eu.jstack.sample.kftt.inventory.order.OrderStatusListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Startup
@Singleton
public class KafkaInitialiser {
    @Resource
    private ManagedScheduledExecutorService executorService;

    @Inject
    private KafkaConsumer<Long, String> kafkaConsumer;

    @Inject
    private OrderStatusListener listener;

    @PostConstruct
    public void init(){
        this.kafkaConsumer.subscribe(Collections.singleton("order_status_changed"));
        this.executorService.scheduleWithFixedDelay(
                new KafkaPoller(kafkaConsumer, listener),
                1,
                1,
                TimeUnit.MILLISECONDS
        );
    }
}
