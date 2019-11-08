package local.raajn.javaorder.repositories;

import local.raajn.javaorder.models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long>
{
}
