package com.course2go.model.like;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "LikeBuilder")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Like {
    @Id
    private Integer id;
    private String likeUid;
    private Integer likeBid;

    public static LikeBuilder builder( String likeUid, Integer likeBid) {
		return LikeBuilder()
				.likeUid(likeUid)
				.likeBid(likeBid);
	}
}
