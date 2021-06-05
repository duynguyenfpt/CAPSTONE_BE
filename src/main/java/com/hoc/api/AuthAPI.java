//package com.hoc.api;
//
//import java.util.List;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hoc.payload.request.LoginRequest;
//import com.hoc.payload.response.JwtResponse;
//import com.hoc.repository.RoleRepository;
//import com.hoc.security.jwt.JwtUtils;
//import com.hoc.security.services.UserDetailsImpl;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("api/auth")
//public class AuthAPI {
//	@Autowired
//	AuthenticationManager authenticationManager;
//	
//	@Autowired
//	RoleRepository roleRepository;
//	
//	@Autowired
//	PasswordEncoder encoder;
//	
//	@Autowired
//	JwtUtils jwtUtils;
//	
//	@PostMapping("/sigin")
//	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
//		Authentication authentication = authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassowrd()));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String jwt = jwtUtils.generateJwtToken(authentication);
//		
//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//		List<String> roles = userDetails.getAuthorities().stream()
//				.map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//		
//		return ResponseEntity.ok(new JwtResponse(jwt, 
//				 userDetails.getId(), 
//				 userDetails.getUsername(), 
//				 userDetails.getEmail(), 
//				 roles));
//				
//	}
//}
