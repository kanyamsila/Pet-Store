
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

    @RequestMapping(path="api/query/{petid}", method= RequestMethod.GET, produces="application/json")
    public PetQuery findPet(@PathVariable("petid") String id){
        Pet pet = this.petRepository.findOne(id);
        PetQuery petQ = new PetQuery();
        
        petQ.setId(pet.getId());
        petQ.setPettype(pet.getPettype());
        petQ.setName(pet.getName());
        petQ.setColor(pet.getColor());
        petQ.setPrice(pet.getPrice());
        return petQ;
    }
    @RequestMapping(path="api/query", method= RequestMethod.GET, produces="application/json")
    public List<PetQuery> findPets(){
        List<Pet> pets = this.petRepository.findAll();
    
        List<PetQuery> petquery = new ArrayList<PetQuery>();
    
            for(Pet apet: pets){
                
                PetQuery petQuery = new PetQuery();
                
                petQuery.setId(apet.getId());
                petQuery.setPettype(apet.getPettype());
                petQuery.setName(apet.getName());
                petQuery.setColor(apet.getColor());
                petQuery.setPrice(apet.getPrice());
                petquery.add(petQuery);
        }
            return petquery;
    }
     @RequestMapping(path="api/query/tpye/like/{bytype}", method= RequestMethod.GET, produces="application/json")
     public List<PetQuery> findbyType(@PathVariable("bytype") String type){
        List<Pet> pets = this.petRepository.findAll();
    
        List<PetQuery> petquery = new ArrayList<PetQuery>();
    
            for(Pet apet: pets){
                if(apet.getPettype().toLowerCase().equals(type.toLowerCase())){
                    
                PetQuery petQuery = new PetQuery();
                
                petQuery.setId(apet.getId());
                petQuery.setPettype(apet.getPettype());
                petQuery.setName(apet.getName());
                petQuery.setColor(apet.getColor());
                petQuery.setPrice(apet.getPrice());
                petquery.add(petQuery);
          }
        }
            return petquery;
    }
    
}
