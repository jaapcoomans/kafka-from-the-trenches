package eu.jstack.sample.kftt.inventory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("{productId}")
public class InventoryApi {
    private Inventory inventory;

    @Inject
    public InventoryApi(Inventory inventory) {
        this.inventory = inventory;
    }

    @Path("stock")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StockInfo getStockInfo(@PathParam("productId") long productId) {
        int total = this.inventory.getTotalStock(productId);
        int available = this.inventory.getAvailableStock(productId);
        return new StockInfo(total, available);
    }

    @Path("reservations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations(@PathParam("productId") long productId) {
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
