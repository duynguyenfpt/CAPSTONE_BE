//package com.web_service.api;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.web_service.api.output.ListObjOutput;
//import com.web_service.services.IChangeSchemaDetectionService;
//
//@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
//@RestController
//public class ChangeSchemaDetectionAPI {
//	@Autowired
//	IChangeSchemaDetectionService changeSchemaDetectionService;
//	
//	@GetMapping(value = "/api/change_schema_detection")
//	public ResponseEntity<ListObjOutput<String>> showCurrentTableSchema() {
//		
//		ListObjOutput<String> result = new ListObjOutput<String>();
////		try {
//			changeSchemaDetectionService.checkSchema(null, null, null, null, null);
//			
//			result.setCode("200");
//			result.setMessage("Get column successfully");
//			return new ResponseEntity<ListObjOutput<String>>(result, HttpStatus.OK);
////		}catch (NullPointerException e) {
////			result.ssetCode("404");
////			result.setMessage("Not found");
////			return new ResponseEntity<ListObjOutput<String>>(result, HttpStatus.NOT_FOUND);
////		}catch (Exception e) {
////			result.setCode("500");
////			result.setMessage("Can not get columns");
////			return new ResponseEntity<ListObjOutput<String>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
////		}	
//	}
//}
