package eu.jstack.sample.kftt.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory/{productId}")
public class InventoryApi {
    private Inventory inventory;

    @Autowired
    public InventoryApi(Inventory inventory) {
        this.inventory = inventory;
    }

    @RequestMapping("stock")
    public StockInfo getStockInfo(@PathVariable long productId) {
        int total = this.inventory.getTotalStock(productId);
        int available = this.inventory.getAvailableStock(productId);
        return new StockInfo(total, available);
    }

    @RequestMapping("reservations")
    public List<Reservation> getReservations(@PathVariable long productId) {
        return this.inventory.getReservations(productId);
    }

    public static class StockInfo {
        private int total;
        private int available;

        private StockInfo(int total, int available) {
            this.total = total;
            this.available = available;
        }

        public int getTotal() {
            return total;
        }

        public int getAvailable() {
            return available;
        }
    }
}
