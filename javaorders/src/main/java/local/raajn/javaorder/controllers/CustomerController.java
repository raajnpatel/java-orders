package local.raajn.javaorder.controllers;


import local.raajn.javaorder.models.Customers;
import local.raajn.javaorder.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // GET http://localhost:3456/customers/order
    // returns all customers with their orders
    @GetMapping(value = "/customers/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers() {
        List<Customers> myCustomers = customerService.findall();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    // POST http://localhost:3456/data/customers/new
    // adds a new customer including any new orders
    @PostMapping(value = "/data/customers/new", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customers newCustomer) throws URISyntaxException {
        newCustomer = customerService.save(newCustomer);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{custcode}").buildAndExpand(newCustomer.getCustcode()).toUri();
        responseHeaders.setLocation(newCustomerURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:3456/data/customers/update/{custcode}
    // updates the customer based off of custcode
    // does not have to do anything with orders
    @PutMapping(value = "/data/customers/update/{custcode}", produces = {"application/json"}, consumes = {
            "application/json"})
    public ResponseEntity<?> updateCustomer(@RequestBody Customers updateCustomer, @PathVariable long custcode) {
        customerService.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:3456/data/customers/update/{custcode}
    // deletes the customer based off of custcode
    // this should also delete the orders of that customer
    @DeleteMapping(value = "/data/customers/update/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode) {
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
