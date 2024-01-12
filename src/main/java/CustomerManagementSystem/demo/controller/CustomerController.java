package CustomerManagementSystem.demo.controller;

import CustomerManagementSystem.demo.exception.ResourceNotFoundException;
import CustomerManagementSystem.demo.model.Customers;
import CustomerManagementSystem.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/customer-mang-sys/")
public class CustomerController {
       @Autowired
       private CustomerRepository customerRepository;

       //get all // use one type of exception handling
      @GetMapping("customers")
      public ResponseEntity<List<Customers>> getAllCustomers(){
          try {
              List<Customers> customers = customerRepository.findAll();
              return ResponseEntity.ok(customers);
          } catch (Exception e) {
              // Log the exception or perform additional handling
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }
      }

      //get by id
    @GetMapping("customers/{id}")
      public ResponseEntity<Customers> getCustomerById(@PathVariable(value = "id")UUID customerId) throws ResourceNotFoundException {
          Customers customers = customerRepository.findById(customerId)
                  .orElseThrow(()-> new ResourceNotFoundException("Customer not found with for UUID :: " + customerId));
            return  ResponseEntity.ok().body(customers);
    }


      //create by id
    @PostMapping("customer")
    public ResponseEntity<Customers> createCustomer(@RequestBody Customers customers){
       Customers customers1 = this.customerRepository.save(customers);
       return new ResponseEntity<>(customers1, HttpStatus.CREATED);
    }

      //create multiple
    @PostMapping("customers")
    public ResponseEntity<List<Customers>> createMutipleCustomer(@RequestBody List<Customers> customersList){
          List<Customers> savedCustomers = this.customerRepository.saveAll(customersList);
          return new ResponseEntity<>(savedCustomers, HttpStatus.CREATED); // satus code 201
    }

      //update by id
    @PutMapping("customers/{id}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable(value = "id") UUID customerId,
                                                     @RequestBody Customers customers)
                              throws ResourceNotFoundException{
          Customers customers1 = this.customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer not found with for UUID :: " + customerId));
         // customers1.setId(customers.getId());
          customers1.setFirstName(customers.getFirstname());
       customers1.setLastName(customers.getLastname());
       customers1.setAge(customers.getAge());
       customers1.setGender(customers.getGender());
       customers1.setPhoneNumber(customers.getPhoneNumber());

        return  ResponseEntity.ok(this.customerRepository.save(customers1));
    }

      //delete by id
    @DeleteMapping("customers/{id}")
     public Map<String, Boolean> deleteCustomer(@PathVariable (value = "id") UUID customerId) throws ResourceNotFoundException{

          Customers customers = this.customerRepository.findById(customerId).orElseThrow( ()-> new ResourceNotFoundException("Customer not found by id :: "+ customerId));

          this.customerRepository.deleteById(customerId);

          Map<String, Boolean> response = new HashMap<>();
          response.put("deleted", Boolean.TRUE);
          return response;

    }


      //delete all
      @DeleteMapping("customers")
      public Map<String, Boolean> deleteCustomer() throws ResourceNotFoundException{

          List<Customers> customers = this.customerRepository.findAll();
          if(!customers.isEmpty()){
              this.customerRepository.deleteAll();
              Map<String, Boolean> response = new HashMap<>();
              response.put("deleted", Boolean.TRUE);
              return response;
          }else{
              Map<String, Boolean> response = new HashMap<>();
              response.put("deleted", Boolean.FALSE);
              return response;
          }

      }
}

