package eu.jstack.sample.kftt.order;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Stateless
public class OrderRepository {
    private Map<Long, Order> memoryStore = new HashMap<>();

    @PostConstruct
    public void initialiseSampleData() {
        Stream.of(
                new Order(1, 101, 20),
                new Order(2, 102, 50),
                new Order(3, 103, 125)
        )
                .forEach(o -> memoryStore.put(o.getId(), o));
    }

    public Order getOrderById(long orderId) {
        return memoryStore.get(orderId);
    }

    public void persist(Order order) {
        memoryStore.put(order.getId(), order);
    }

    public Collection<Order> getAll() {
        return memoryStore.values();
    }
}
