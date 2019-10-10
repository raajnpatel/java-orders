package local.raajn.javaorder.controllers;

import local.raajn.javaorder.models.Customers;
import local.raajn.javaorder.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    // GET http://localhost:3456/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers()
    {
        List<Customers> customersList = customerService.findall();
        return new ResponseEntity<>(customersList, HttpStatus.OK);
    }

    // POST http://localhost:3456/customer/new
    @PostMapping(value = "/new", consumes = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customers newCustomer)
    {
        customerService.save(newCustomer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // PUT http://localhost:3456/customer/update/{custcode}
    @PutMapping(value = "/data/customer/update/{custcode}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateCustomer(@RequestBody Customers updateCustomer, @PathVariable long custcode)
    {
        customerService.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:3456/custoer/delete/{custcode}
    @DeleteMapping(value = "/data/customer/update/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode) {
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
