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
import models.ReportConfig;
import repository.ReportConfigRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")


public class ReportsConfigController {
	@Autowired
	private ReportConfigRepository reportConfigRepository;
	
	
	@GetMapping("/t_reportconfig")
	public List<ReportConfig> getAllReportConfigs(){
		return reportConfigRepository.findAll();
		
	}
	
	@PostMapping("/t_reportconfig")
	public ReportConfig createReportConfig(@RequestBody ReportConfig reportConfig) {
		return reportConfigRepository.save(reportConfig);
	}
	
	
	@GetMapping("/t_reportconfig/{id}")
	public ResponseEntity<ReportConfig> getReportConfigById(@PathVariable Long configId) {
		ReportConfig reportConfig = reportConfigRepository.findById(configId)
				.orElseThrow(() -> new ResourceNotFoundException("ReportConfig not exist with id :" + configId));
		return ResponseEntity.ok(reportConfig);
		
	}
	
	
	
	@PutMapping("/t_reportconfig/{id}")
	public ResponseEntity<ReportConfig> updateReportConfig(@PathVariable Long configId, @RequestBody ReportConfig reportConfigDetails){
		ReportConfig reportConfig = reportConfigRepository.findById(configId)
				.orElseThrow(() -> new ResourceNotFoundException("ReportConfig not exist with id :" + configId));
		
		reportConfig.setDimensionStartCol(reportConfigDetails.getDimensionStartCol());
		reportConfig.setDimensionStartRow(reportConfigDetails.getDimensionStartRow());
		reportConfig.setDimensionLength(reportConfigDetails.getDimensionLength());
		reportConfig.setFormatType(reportConfigDetails.getFormatType());
		reportConfig.setPostTitle(reportConfigDetails.getPostTitle());
		reportConfig.setConfigId(reportConfigDetails.getConfigId());
		reportConfig.setcMapId(reportConfigDetails.getcMapId());
		reportConfig.setDimId(reportConfigDetails.getDimId());
		reportConfig.setDimValues(reportConfigDetails.getDimValues());
		reportConfig.setConfigGroupId(reportConfigDetails.getConfigGroupId());
		reportConfig.setAddedBy(reportConfigDetails.getAddedBy());
		reportConfig.setModifiedBy(reportConfigDetails.getModifiedBy());
		reportConfig.setStatus(reportConfigDetails.getStatus());
		reportConfig.setDeletedBy(reportConfigDetails.getDeletedBy());
		reportConfig.setGrouped(reportConfigDetails.getGrouped());
		reportConfig.setGroupOrder(reportConfigDetails.getGroupOrder());
		reportConfig.setExcelColumn(reportConfigDetails.getExcelColumn());
		reportConfig.setExcelRow(reportConfigDetails.getExcelRow());
		reportConfig.setDimRepId(reportConfigDetails.getDimRepId());
		
		
		ReportConfig updatedReportConfig = reportConfigRepository.save(reportConfig);
		return ResponseEntity.ok(updatedReportConfig);
	}
	
	
	@DeleteMapping("/t_reportconfig/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteReportConfig(@PathVariable Long configId){
		ReportConfig reportConfig = reportConfigRepository.findById(configId)
				.orElseThrow(() -> new ResourceNotFoundException("ReportConfig not exist with username :" + configId));
		
		reportConfigRepository.delete(reportConfig);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
