package local.raajn.javaorder.services;

import local.raajn.javaorder.models.Customers;
import local.raajn.javaorder.models.Orders;
import local.raajn.javaorder.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Customers update(Customers customers, long custcode)
    {
        return null;
    }

    @Override
    public void delete(long custcode)
    {

    }
}
