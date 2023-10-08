package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/** Created by jt, Spring Framework Guru. */
@Service
public class CustomerServiceImpl implements CustomerService {

  private Map<UUID, Customer> customerMap;

  public CustomerServiceImpl() {
    Customer customer1 =
        Customer.builder()
            .id(UUID.randomUUID())
            .name("Customer 1")
            .version(1)
            .createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

    Customer customer2 =
        Customer.builder()
            .id(UUID.randomUUID())
            .name("Customer 2")
            .version(1)
            .createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

    Customer customer3 =
        Customer.builder()
            .id(UUID.randomUUID())
            .name("Customer 3")
            .version(1)
            .createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

    customerMap = new HashMap<>();
    customerMap.put(customer1.getId(), customer1);
    customerMap.put(customer2.getId(), customer2);
    customerMap.put(customer3.getId(), customer3);
  }

  @Override
  public void patchCustomerById(UUID customerId, Customer customer) {
    Customer existing = customerMap.get(customerId);

    if (StringUtils.hasText(customer.getName())) {
      existing.setName(customer.getName());
    }
  }

  @Override
  public Customer saveNewCustomer(Customer customer) {

    Customer savedCustomer =
        Customer.builder()
            .id(UUID.randomUUID())
            .version(1)
            .updateDate(LocalDateTime.now())
            .createdDate(LocalDateTime.now())
            .name(customer.getName())
            .build();

    customerMap.put(savedCustomer.getId(), savedCustomer);

    return savedCustomer;
  }

  @Override
  public void updateCustomerById(UUID customerId, Customer customer) {
    Customer existingCustomer = this.customerMap.get(customerId);
    existingCustomer.setName(customer.getName());
    existingCustomer.setVersion(customer.getVersion());
    existingCustomer.setCreatedDate(customer.getCreatedDate());
    existingCustomer.setUpdateDate(LocalDateTime.now());
    this.customerMap.put(customerId, existingCustomer);
  }

  @Override
  public void deleteCustomerById(UUID customerId) {
      this.customerMap.remove(customerId);
  }

  @Override
  public Customer getCustomerById(UUID uuid) {
    return customerMap.get(uuid);
  }

  @Override
  public List<Customer> getAllCustomers() {
    return new ArrayList<>(customerMap.values());
  }
}
