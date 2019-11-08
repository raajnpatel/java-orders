package local.raajn.javaorder.services;

import local.raajn.javaorder.models.Customers;
import local.raajn.javaorder.models.Orders;
import local.raajn.javaorder.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CustomersRepository custrepos;

    @Override
    public List<Customers> findall()
    {
        List<Customers> custList = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(custList::add);
        return custList;
    }
    @Transactional
    @Override
    public Customers save(Customers customers)
    {
         Customers newCustomer = new Customers();

         newCustomer.setCustname(customers.getCustname());
         newCustomer.setCustcity(customers.getCustcity());
         newCustomer.setWorkingarea(customers.getWorkingarea());
         newCustomer.setCustcountry(customers.getCustcountry());
         newCustomer.setGrade(customers.getGrade());
         newCustomer.setOpeningamt(customers.getOpeningamt());
         newCustomer.setReceiveamt(customers.getReceiveamt());
         newCustomer.setPaymentamt(customers.getPaymentamt());
         newCustomer.setPhone(customers.getPhone());

         newCustomer.setAgent((customers.getAgent()));

        for (Orders o : customers.getOrders())
        {
            newCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), newCustomer,
                    o.getOrddescription()));
        }

        return custrepos.save(newCustomer);
    }
    @Transactional
    @Override
    public Customers update(Customers customer, long id) {
        Customers currentCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (customer.getCustname() != null) {
            currentCustomer.setCustname(customer.getCustname());
        }
        if (customer.getCustcity() != null) {
            currentCustomer.setCustcity(customer.getCustcity());
        }
        if (customer.getWorkingarea() != null) {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getCustcountry() != null) {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }
        if (customer.getGrade() != null) {
            currentCustomer.setGrade(customer.getGrade());
        }
        if (customer.getOpeningamt() != 0) {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }
        if (customer.getReceiveamt() != 0) {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }
        if (customer.getPaymentamt() != 0) {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }
        if (customer.getOutstandingamt() != 0) {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }
        if (customer.getPhone() != null) {
            currentCustomer.setPhone(customer.getPhone());
        }

        // added
        if (customer.getAgent() != null) {
            currentCustomer.setAgent(customer.getAgent());
        }

        if (customer.getOrders().size() > 0) {
            for (Orders o : customer.getOrders()) {
                currentCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), currentCustomer,
                        o.getOrddescription()));
            }
        }

        return custrepos.save(currentCustomer);
    }

    @Override
    public void delete(long id) {
        if (custrepos.findById(id).isPresent()) {
            custrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Id " + id);
        }
    }
}
