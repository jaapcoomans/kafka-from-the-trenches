package eu.jstack.sample.kftt.order;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

@Stateless
public class OrderService {
    private OrderRepository orderRepository;
    private OrderStatusPublisher publisher;

    public OrderService() {
    }

    @Inject
    public OrderService(OrderRepository orderRepository, OrderStatusPublisher publisher) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    public void paymentConfirmed(long orderId) {
        Order order = this.orderRepository.getOrderById(orderId);
        order.confirm();
        this.orderRepository.persist(order);
        this.publisher.orderStatusChanged(orderId, order.getStatus());
    }

    public void paymentRejected(long orderId) {
        Order order = this.orderRepository.getOrderById(orderId);
        order.cancel();
        this.orderRepository.persist(order);
        this.publisher.orderStatusChanged(orderId, order.getStatus());
    }

    public Collection<Order> getAllOrders() {
        return this.orderRepository.getAll();
    }

    public Order getOrder(long orderId) {
        return this.orderRepository.getOrderById(orderId);
    }
}
