package eu.jstack.sample.kftt.order.payment;

import eu.jstack.sample.kftt.order.OrderService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("{orderId}/payment")
@Stateless
public class PaymentRestService {
    private OrderService orderService;

    @Inject
    public PaymentRestService(OrderService orderService) {
        this.orderService = orderService;
    }

    @PUT
    @Path("status")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePayment(@PathParam("orderId") long orderId, PaymentStatus status) {
        if (status == PaymentStatus.CONFIRMED) {
            this.orderService.paymentConfirmed(orderId);
        } else if (status == PaymentStatus.REJECTED) {
            this.orderService.paymentRejected(orderId);
        }
    }
}
