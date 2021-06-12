package com.web_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.PagingOutput;
import com.web_service.api.output.ServerOutput;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.services.IServerInfoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ServerInfoAPI {
	@Autowired
	private IServerInfoService serverInfoService;

	@GetMapping(value = "/api/server_infors")
	public ServerOutput showServerInfors(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		ServerOutput result = new ServerOutput();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(serverInfoService.findAll(pageable));
		int totalPage = (int) Math.ceil((double) (serverInfoService.totalItem()) / limit);
		int totalItem = serverInfoService.totalItem();
		result.setMeta(new PagingOutput(page, totalPage, totalItem));

		return result;
	}
	
	@GetMapping(value = "/api/server_infors/{id}")
	public ServerInfoDTO showServerInfo(@PathVariable("id") long id) {
		return serverInfoService.getById(id);
	}
	
	@PostMapping(value = "/api/server_infors")
	public ServerInfoDTO createDatabaseInfo(@RequestBody ServerInfoDTO model) {
		return serverInfoService.save(model);
	}

	@PutMapping(value = "/api/server_infors/{id}")
	public ServerInfoDTO updateServerInfo(@RequestBody ServerInfoDTO model, @PathVariable("id") long id) {
		model.setId(id);
		
		return serverInfoService.save(model);
	}
	
	@DeleteMapping(value = "/api/server_infors/{id}")
	public void deleteServerInfo(@PathVariable("id") long id) {
		serverInfoService.delete(id);
	}
}
