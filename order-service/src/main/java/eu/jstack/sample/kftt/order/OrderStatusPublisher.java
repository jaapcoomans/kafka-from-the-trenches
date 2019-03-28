package eu.jstack.sample.kftt.order;

public interface OrderStatusPublisher {
	void orderStatusChanged(long orderId, OrderStatus newStatus);
}
