package eu.jstack.sample.kftt.order;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("")
public class OrderRestService {
    private OrderService orderService;

    @Inject
    public OrderRestService(OrderService orderService){
        this.orderService = orderService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Order> getAllOrders() {
        return this.orderService.getAllOrders();
    }

    @GET
    @Path("{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder(@PathParam("orderId") long orderId) {
        return this.orderService.getOrder(orderId);
    }
}
