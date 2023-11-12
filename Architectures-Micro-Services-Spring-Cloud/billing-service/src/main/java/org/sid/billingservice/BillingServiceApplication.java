package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.sid.billingservice.models.Customer;
import org.sid.billingservice.models.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductItemRestClient productItemRestClient, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Bill.class);
        return  args -> {
            Customer customer = customerRestClient.getCustomerById(2L);
            Customer customer2 = customerRestClient.getCustomerById(1L);
            Bill bill= billRepository.save(new Bill(null,new Date(),null,customer.getId(), customer));
            Bill bill2= billRepository.save(new Bill(null,new Date(),null,customer2.getId(), customer2));
            PagedModel<Product> productPagedModel = productItemRestClient.pageProduct(0,10);
            productPagedModel.forEach(p->{
                ProductItem productItem = new ProductItem();
                productItem.setPrice(p.getPrix());
                productItem.setBill(bill);
                productItem.setProduct(p);
                productItem.setQuantity(new Random().nextInt(30)); // ENTRE 1 ET 30
                productItem.setProductID(p.getId());
                productItemRepository.save(productItem);
            });
            PagedModel<Product> productPagedModel2 = productItemRestClient.pageProduct(0,10);
            productPagedModel2.forEach(p->{
                ProductItem productItem = new ProductItem();
                productItem.setPrice(p.getPrix());
                productItem.setBill(bill2);
                productItem.setProduct(p);
                productItem.setQuantity(new Random().nextInt(30)); // ENTRE 1 ET 30
                productItem.setProductID(p.getId());
                productItemRepository.save(productItem);
            });
            /*
            System.out.println("********   Client Information     *********");
            System.out.println(customer.getId());
            System.out.println(customer.getName());
            System.out.println(customer.getEmail());
            System.out.println("*******************************************");
             **/
        };
    }
}
