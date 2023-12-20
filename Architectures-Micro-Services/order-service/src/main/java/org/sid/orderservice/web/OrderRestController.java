package org.sid.orderservice.web;

import org.sid.orderservice.entities.Order;
import org.sid.orderservice.entities.ProductItem;
import org.sid.orderservice.models.Customer;
import org.sid.orderservice.models.Product;
import org.sid.orderservice.repositories.OrderRepository;
import org.sid.orderservice.repositories.ProductItemRepository;
import org.sid.orderservice.services.CustomerRestClientService;
import org.sid.orderservice.services.InventoryRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private CustomerRestClientService customerRestClientService;

    @Autowired
    private InventoryRestClientService inventoryRestClientService;
    @GetMapping("/fullOrder/{id}")
    public Order getorder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        Customer customer = customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(p->{
           Product product= inventoryRestClientService.productById(p.getProductId());
           p.setProduct(product);
        });

        return order;
    }
}
