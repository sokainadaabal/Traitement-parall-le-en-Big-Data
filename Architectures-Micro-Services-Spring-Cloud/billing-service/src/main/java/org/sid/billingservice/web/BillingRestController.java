package org.sid.billingservice.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.sid.billingservice.models.Customer;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// Pour mieux injecter les dependances preferable au lieux d'utiliser @Autowired utiliser  @AllArgsConstructor mieux
@AllArgsConstructor
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;
    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
     Bill bill ;
     bill = billRepository.findById(id).get();
     bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerID()));
     bill.getProductItems().forEach(p->{
         p.setProduct(productItemRestClient.getProductById(p.getProductID()));
     });
     return bill;
    }

    @GetMapping(path = "/fullCustomerBill/{id}")
    public List<Bill> getBilLCustomer(@PathVariable(name = "id") Long id){
        List<Bill> bill;
        bill=billRepository.findByCustomerID(id);
        bill.forEach(p->{
            p=getBill(p.getId());
        });
        return bill;
    }


}
