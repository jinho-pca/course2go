package com.course2go.model.boardlike;

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
@Builder(builderMethodName = "BoardlikeBuilder")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Boardlike {
    @Id
    private Integer id;
    private String likeUid;
    private Integer likeBid;

    public static BoardlikeBuilder builder( String likeUid, Integer likeBid) {
		return BoardlikeBuilder()
				.likeUid(likeUid)
				.likeBid(likeBid);
	}
}
