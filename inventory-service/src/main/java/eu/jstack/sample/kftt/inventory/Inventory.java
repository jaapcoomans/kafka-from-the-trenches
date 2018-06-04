package eu.jstack.sample.kftt.inventory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class Inventory {
    private Map<Long, Integer> totalStock = new HashMap<>();
    private Map<Long, Reservation> reservations = new HashMap<>();

    @PostConstruct
    public void initWithDemoData(){
        totalStock.put(101L, 1000);
        totalStock.put(102L, 1000);
        totalStock.put(103L, 1000);

        reservations.put(1L, new Reservation(1L, 101L, 20));
        reservations.put(2L, new Reservation(2L, 102L, 50));
        reservations.put(3L, new Reservation(3L, 103L, 125));
    }

    public void announceOrder(long productId, int amount, long orderId) {
        this.reservations.put(orderId, new Reservation(orderId, productId, amount));
    }

    public void cancelReservation(long orderId) {
        this.reservations.remove(orderId);
    }

    public void confirmReservation(long orderId) {
        this.reservations.get(orderId).confirm();
    }

    public void dispatchOrder(long orderId) {
        Reservation order = this.reservations.remove(orderId);
        totalStock.computeIfPresent(order.getProductId(), (p, s) -> s - order.getAmount());
    }

    public int getTotalStock(long productId) {
        return this.totalStock.getOrDefault(productId, 0);
    }

    public int getAvailableStock(long productId) {
        int unavailableStock = this.reservations.values().stream()
                .filter(r -> r.getProductId() == productId)
                .mapToInt(Reservation::getAmount)
                .sum();

        return this.getTotalStock(productId) - unavailableStock;
    }

    public List<Reservation> getReservations(long productId) {
        return this.reservations.values().stream()
                .filter(r -> r.getProductId() == productId)
                .collect(Collectors.toList());
    }
}
