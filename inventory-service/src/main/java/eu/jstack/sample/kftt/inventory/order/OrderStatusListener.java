package eu.jstack.sample.kftt.inventory.order;

import eu.jstack.sample.kftt.inventory.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class OrderStatusListener {
    private static final Logger LOG = LoggerFactory.getLogger(OrderStatusListener.class);

    private Inventory inventory;

    public OrderStatusListener() {
    }

    @Inject
    public OrderStatusListener(Inventory inventory) {
        this.inventory = inventory;
    }

    public void orderStatusChanged(long orderId, OrderStatus status) {
        LOG.info("Order {} status changed to {}", orderId, status);
        switch (status) {
            case CONFIRMED:
                this.inventory.confirmReservation(orderId);
                break;
            case CANCELLED:
                this.inventory.cancelReservation(orderId);
                break;
            case DISPATCHED:
                this.inventory.dispatchOrder(orderId);
                break;
        }
    }
}
