package com.web_service.security.services;
//package com.hoc.security.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.hoc.entity.UserEntity;
//import com.hoc.repository.UserRepository;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//	@Autowired
//	UserRepository userRepository;
//
//	@Override
//	@Transactional
//	public UserDetails loadUserByUserName(String username) throws UsernameNotFoundException {
//		UserEntity user = userRepository.findByUserName(username)
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//		
//		return UserDetailsImpl.build(user);
//	}
//	
//}
