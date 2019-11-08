package local.raajn.javaorder.repositories;

import local.raajn.javaorder.models.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customers, Long>
{
}
