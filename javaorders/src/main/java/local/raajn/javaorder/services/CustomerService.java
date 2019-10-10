package local.raajn.javaorder.services;

import local.raajn.javaorder.models.Customers;

import java.util.List;

public interface CustomerService
{
    List<Customers> findall();

    Customers save(Customers customers);

    Customers update(Customers customers, long custcode);

    void delete(long custcode);
}
