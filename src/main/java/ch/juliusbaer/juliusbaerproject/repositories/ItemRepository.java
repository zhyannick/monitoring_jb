package ch.juliusbaer.juliusbaerproject.repositories;

import ch.juliusbaer.juliusbaerproject.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
