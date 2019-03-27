package eu.jstack.sample.kftt.order;

public interface OrderStatusPublicher {
	void orderStatusChanged(long orderId, OrderStatus newStatus);
}
