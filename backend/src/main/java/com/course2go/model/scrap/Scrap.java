package com.course2go.model.scrap;

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
@Builder(builderMethodName = "ScrapBuilder")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Scrap {
    @Id
    private Integer sid;
    private String scrapUid;
    private Integer scrapBid;
    private boolean scrapType;

    public static ScrapBuilder builder(String scrapUid, Integer scrapBid, boolean scrapType) {
		return ScrapBuilder()
				.scrapUid(scrapUid)
				.scrapBid(scrapBid)
				.scrapType(scrapType);
	}
}
