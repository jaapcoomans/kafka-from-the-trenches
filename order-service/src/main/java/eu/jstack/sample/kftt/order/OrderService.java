package eu.jstack.sample.kftt.order;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

@Stateless
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService() {
    }

    @Inject
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void paymentConfirmed(long orderId) {
        Order order = this.orderRepository.getOrderById(orderId);
        order.confirm();
        this.orderRepository.persist(order);
    }

    public void paymentRejected(long orderId) {
        Order order = this.orderRepository.getOrderById(orderId);
        order.cancel();
        this.orderRepository.persist(order);
    }

    public Collection<Order> getAllOrders() {
        return this.orderRepository.getAll();
    }

    public Order getOrder(long orderId) {
        return this.orderRepository.getOrderById(orderId);
    }
}
