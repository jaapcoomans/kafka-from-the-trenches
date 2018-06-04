package eu.jstack.sample.kftt.order;

public class Order {
    private long id;
    private long productId;
    private int amount;
    private OrderStatus status = OrderStatus.CREATED;

    public Order(long id, long productId, int amount) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
    }

    public void confirm() {
        this.status = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = OrderStatus.CANCELLED;
    }

    public long getId() {
        return id;
    }

    public long getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
