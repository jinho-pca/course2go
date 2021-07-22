package com.course2go.model.follow;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "FollowBuilder")
@Getter
public class Follow {
	
	@Id
	private String id;
	
	
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
