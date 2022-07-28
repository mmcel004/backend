package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.ResourceNotFoundException;
import models.ReportAlias;

import repository.ReportAliasRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")


public class ReportAliasController {
	@Autowired
	private ReportAliasRepository aliasRepository;
	
	// get all alias
	@GetMapping("/t_alias")
	public List<ReportAlias> getAllAlias(){
		return aliasRepository.findAll();
	}		
	
	// create alias rest api
	@PostMapping("/t_alias")
	public ReportAlias createAlias(@RequestBody ReportAlias alias) {
		return aliasRepository.save(alias);
	}
	
	// get alias by id rest api
	@GetMapping("/t_alias/{id}")
	public ResponseEntity<ReportAlias> getAliasById(@PathVariable Long aliasId) {
		ReportAlias alias = aliasRepository.findById(aliasId)
				.orElseThrow(() -> new ResourceNotFoundException("Alias not exist with id :" + aliasId));
		return ResponseEntity.ok(alias);
	}
	
	// update employee rest api
	
	@PutMapping("/t_alias/{id}")
	public ResponseEntity<ReportAlias> updateAlias(@PathVariable Long aliasId, @RequestBody ReportAlias aliasDetails){
		ReportAlias alias = aliasRepository.findById(aliasId)
				.orElseThrow(() -> new ResourceNotFoundException("Alias not exist with id :" + aliasId));
		
		alias.setAlias(aliasDetails.getAlias());
		alias.setAliasId(aliasDetails.getAliasId());
		alias.setReportId(aliasDetails.getReportId());
		
		ReportAlias updatedAlias = aliasRepository.save(alias);
		return ResponseEntity.ok(updatedAlias);
	}
	
	// delete employee rest api
	@DeleteMapping("/t_alias/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAlias(@PathVariable Long aliasId){
		ReportAlias alias = aliasRepository.findById(aliasId)
				.orElseThrow(() -> new ResourceNotFoundException("Alias not exist with id :" + aliasId));
		
		aliasRepository.delete(alias);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
