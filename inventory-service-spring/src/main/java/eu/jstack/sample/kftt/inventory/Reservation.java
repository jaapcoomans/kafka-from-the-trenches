package eu.jstack.sample.kftt.inventory;

public class Reservation {
    private long orderNumber;
    private long productId;
    private int amount;
    private ReservationStatus status;

    public Reservation(long orderNumber, long productId, int amount) {
        this(orderNumber, productId, amount, ReservationStatus.ANNOUNCED);
    }

    public Reservation(long orderNumber, long productId, int amount, ReservationStatus status) {
        this.orderNumber = orderNumber;
        this.productId = productId;
        this.amount = amount;
        this.status = status;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public long getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void confirm() {
        this.status = ReservationStatus.CONFIRMED;
    }
}
