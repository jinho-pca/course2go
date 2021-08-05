package com.course2go.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import com.course2go.model.place.Place;

public interface PlaceDao extends JpaRepository<Place, Integer> {
	public List<Place> findAllById(Iterable<Integer> ids);
	public List<Place> findAllByPlaceNameLike(String word, Pageable pageable);
	public List<Place> findAllByPlaceTypeLike(String type);
}
