package com.course2go.model.follow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import com.course2go.model.user.User;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "FollowBuilder")
@Getter
public class Follow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
//	@ManyToOne(targetEntity = User.class)
//	@JoinColumn(name="follow_from_uid" )
//	private User followFromUser;
//	
//	@ManyToOne(targetEntity = User.class)
//	@JoinColumn(name="follow_to_uid")
//	private User followToUser;
	
	@NotNull
	private String followFromUid;
	
	@NotNull
	private String followToUid;
	
	public static FollowBuilder builder(String followFromUid, String followToUid) {
		return FollowBuilder()
				.followFromUid(followFromUid)
				.followToUid(followToUid);
	}
	
}
