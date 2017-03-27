
package za.co.reverside.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.reverside.demo.model.Pet;
import za.co.reverside.demo.repository.PetRepository;
import za.co.reverside.demo.schema.PetQuery;

@RestController
public class QueryService {
    @Autowired
    private PetRepository petRepository;
    
    @RequestMapping(path="api/pets", method= RequestMethod.POST, consumes="application/json")
    public void createPet(@RequestBody Pet pet){

    	this.petRepository.insert(pet);
    }
    
    @RequestMapping(path="api/pets/{petid}", method= RequestMethod.GET, produces="application/json")
    public PetQuery findPet(@PathVariable("petid") String id){
        Pet pet = this.petRepository.findOne(id);
        PetQuery petQ = new PetQuery();
        
        petQ.setId(pet.getId());
        petQ.setType(pet.getType());
        petQ.setName(pet.getName());
        petQ.setColor(pet.getColor());
        petQ.setPrice(pet.getPrice());
        return petQ;
    }
    
    @RequestMapping(path="api/pets", method= RequestMethod.GET, produces="application/json")
    public List<PetQuery> findPets(){
        List<Pet> pets = this.petRepository.findAll();
    
        List<PetQuery> petquery = new ArrayList<PetQuery>();
    
            for(Pet apet: pets){
                
                PetQuery petQuery = new PetQuery();
                
                petQuery.setId(apet.getId());
                petQuery.setType(apet.getType());
                petQuery.setName(apet.getName());
                petQuery.setColor(apet.getColor());
                petQuery.setPrice(apet.getPrice());
                petquery.add(petQuery);
        }
            return petquery;
    }
    
     @RequestMapping(path="api/pets/tpye/like/{bytype}", method= RequestMethod.GET, produces="application/json")
     public List<PetQuery> findbyType(@PathVariable("bytype") String type){
        List<Pet> pets = this.petRepository.findAll();
    
        List<PetQuery> petquery = new ArrayList<PetQuery>();
    
            for(Pet apet: pets){
                if(apet.getType().toLowerCase().equals(type.toLowerCase())){
                    
                PetQuery petQuery = new PetQuery();
                
                petQuery.setId(apet.getId());
                petQuery.setType(apet.getType());
                petQuery.setName(apet.getName());
                petQuery.setColor(apet.getColor());
                petQuery.setPrice(apet.getPrice());
                petquery.add(petQuery);
          }
        }
            return petquery;
    }
     
      @RequestMapping(path="api/petupdate/{petId}", method= RequestMethod.PUT, consumes="application/json")
    public void updatePet(@RequestBody Pet pet, @PathVariable("petId") String id){
        Pet pets = this.petRepository.findOne(id);
        
        if(pets!= null){
                pets.setName(pet.getName());
                pets.setType(pet.getType());
                pets.setColor(pet.getColor());
                pets.setPrice(pet.getPrice());
                petRepository.save(pets);
            }else
        {
            throw new RuntimeException ("No such Pet");
        }
     }
    
     @RequestMapping(path="api/pets/{petId}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable("petId") String id){
        Pet pet = this.petRepository.findOne(id);
        if( pet == null){
            throw new RuntimeException ("No Pet found");
        }else{
            petRepository.delete(id);
        }
    }
    
}
