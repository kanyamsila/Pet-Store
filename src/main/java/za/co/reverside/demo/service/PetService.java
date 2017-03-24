package za.co.reverside.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.reverside.demo.model.Pet;
import za.co.reverside.demo.repository.PetRepository;

@RestController
public class PetService{

    @Autowired
    private PetRepository petRepository;

    @RequestMapping(path="api/pets", method= RequestMethod.POST, consumes="application/json")
    public void createPet(@RequestBody Pet pet){

    	this.petRepository.insert(pet);
    }
    @RequestMapping(path="api/pets/{petid}", method= RequestMethod.GET, produces="application/json")
    public Pet findPet(@PathVariable("petid") String id){
        return this.petRepository.findOne(id);
    }
    @RequestMapping(path="api/pets", method= RequestMethod.GET, produces="application/json")
    public List<Pet> findPets(){
    return this.petRepository.findAll();
    }
    @RequestMapping(path="api/pets/update/{petId}", method= RequestMethod.PUT, consumes="application/json")
    public void updatePet(@RequestBody Pet pet, @PathVariable("petId") String id){
        Pet pets = this.petRepository.findOne(id);
        
        if(pets!= null){
                pets.setName(pet.getName());
                pets.setPettype(pet.getPettype());
                pets.setColor(pet.getColor());
                pets.setPrice(pet.getPrice());
                petRepository.save(pets);
            }else
        {
            throw new RuntimeException ("No such group");
        }
     }
       @RequestMapping(path="api/pets/{petId}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable("petId") String id){
        Pet group = this.petRepository.findOne(id);
        if( group == null){
            throw new RuntimeException ("No group found");
        }else{
            petRepository.delete(id);
        }
    }

}
