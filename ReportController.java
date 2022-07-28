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
import models.Report;
import repository.ReportRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")


public class ReportController {
	@Autowired
	private ReportRepository reportRepository;
	
	
	@GetMapping("/t_report")
	public List<Report> getAllReports(){
		return reportRepository.findAll();
		
	}
	
	@PostMapping("/t_report")
	public Report createReport(@RequestBody Report report) {
		return reportRepository.save(report);
	}
	
	
	@GetMapping("/t_report/{id}")
	public ResponseEntity<Report> getReportById(@PathVariable Long reportId) {
		Report report = reportRepository.findById(reportId)
				.orElseThrow(() -> new ResourceNotFoundException("Report not exist with id :" + reportId));
		return ResponseEntity.ok(report);
		
	}
	
	
	
	@PutMapping("/t_report/{id}")
	public ResponseEntity<Report> updateReport(@PathVariable Long reportId, @RequestBody Report reportDetails){
		Report report = reportRepository.findById(reportId)
				.orElseThrow(() -> new ResourceNotFoundException("Report not exist with id :" + reportId));
		
		report.setReportId(reportDetails.getReportId());
		report.setReportName(reportDetails.getReportName());
		report.setHistoryMaxDays(reportDetails.getHistoryMaxDays());
		report.setNonspoolFile(reportDetails.getNonspoolFile());
		report.setDescription(reportDetails.getDescription());
		report.setTitle(reportDetails.getTitle());
		report.setAbstractName(reportDetails.getAbstractName());
		report.setSkipPages(reportDetails.getSkipPages());
		report.setUploadedFileName(reportDetails.getUploadedFileName());
		report.setCreationDate(reportDetails.getCreationDate());
		report.setAllowPrnt(reportDetails.getAllowPrnt());
		report.setAddedBy(reportDetails.getAddedBy());
		report.setModifiedBy(reportDetails.getModifiedBy());
		report.setAs400Id(reportDetails.getAs400Id());
		report.setStatus(reportDetails.getStatus());
		report.setDeletedBy(reportDetails.getDeletedBy());
		report.setMultiReports(reportDetails.getMultiReports());
		report.setFileName(reportDetails.getFileName());
		report.setAllowOverRides(reportDetails.getAllowOverRides());
		report.setNumColumns(reportDetails.getNumColumns());
		report.setConfidential(reportDetails.getConfidential());
		report.setBiReport(reportDetails.getBiReport());
		report.setHelpUrl(reportDetails.getHelpUrl());
		report.setHelp(reportDetails.getHelp());
		report.setDetailDescription(reportDetails.getDetailDescription());
		report.setCombinePdfReport(reportDetails.getCombinePdfReport());
		
		
		Report updatedReport = reportRepository.save(report);
		return ResponseEntity.ok(updatedReport);
	}
	
	
	@DeleteMapping("/t_report/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteReport(@PathVariable Long reportId){
		Report report = reportRepository.findById(reportId)
				.orElseThrow(() -> new ResourceNotFoundException("Report not exist with username :" + reportId));
		
		reportRepository.delete(report);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
