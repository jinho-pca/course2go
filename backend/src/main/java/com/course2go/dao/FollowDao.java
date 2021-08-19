package com.course2go.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.course2go.model.follow.Follow;
@Repository
public interface FollowDao extends JpaRepository<Follow, Integer>{
	
	@Query(
			value = "SELECT u.uid" +
					" FROM follow f " + 
					" JOIN user u ON f.follow_from_uid = u.uid" +
					" WHERE f.follow_to_uid = :uid"
			, nativeQuery = true
			)
	List<String> getFollowers(@Param("uid") String uid); 
	
	@Query(
			value = "SELECT u.uid" +
					" FROM follow f " + 
					" JOIN user u ON f.follow_to_uid = u.uid" +
					" WHERE f.follow_from_uid = :uid"
			, nativeQuery = true
			)
	List<String> getFollowings(@Param("uid") String uid); 
	
	Optional<Follow> getFollowByFollowFromUidAndFollowToUid(String followFromUid, String followToUid);

	boolean existsByFollowFromUidAndFollowToUid(String followFromUid, String followToUid);
	
	void deleteAllByFollowFromUid(String followFromUid);
	void deleteAllByFollowToUid(String followToUid);
	
}
