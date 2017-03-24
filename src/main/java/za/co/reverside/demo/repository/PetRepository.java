package za.co.reverside.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import za.co.reverside.demo.model.Pet;

@Repository
public interface PetRepository extends MongoRepository<Pet, String>{
 
}