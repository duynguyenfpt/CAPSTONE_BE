package com.web_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.web_service.entity.AccountRightEntity;

public interface AccountRightRepository extends JpaRepository<AccountRightEntity, Long>{
	@Modifying
	@Query(value = "delete from account_right where account_id=?1 and right_id=?2",
			nativeQuery = true)
	void deleteRight(Long accountId, Long rightId);
	
	@Query(value = "select account_right.* from account_right inner join "
			+ "rights on rights.id = account_right.right_id "
			+ "where rights.deleted = false and account_right.account_id = ?1",
			nativeQuery = true)
	List<AccountRightEntity> findAllByAccountId(Long accountId);
}
