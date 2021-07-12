//package com.web_service.api;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.web_service.entity.mongo.Bosses;
//import com.web_service.repository.BossesRepository;
//
//@CrossOrigin
//@RestController
//public class BossesAPI {
//	@Autowired
//	private BossesRepository repository;
//	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public List<Bosses> getAllBosses() {
//		return repository.findAll();
//	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public Bosses getBossById(@PathVariable("id") ObjectId id) {
//		return repository.findById(id);
//	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public void updateBossById(@PathVariable("id") ObjectId id, @Valid @RequestBody Bosses bosses) {
//		bosses.set_id(id);
//		repository.save(bosses);
//	}
//	
//	@PostMapping(value = "/bosses")
//	public Bosses createBoss(@Valid @RequestBody Bosses bosses) {
//		bosses.set_id(ObjectId.get());
//		repository.save(bosses);
//		return bosses;
//	}
//	
//	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//	  public void deleteBoss(@PathVariable ObjectId id) {
//	   repository.delete(repository.findById(id));
//	  }
//	
//}
