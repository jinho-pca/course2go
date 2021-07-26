package com.course2go.model.follow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.course2go.model.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MappedFollow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="follow_from_uid" )
	private User followFromUser;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="follow_to_uid")
	private User followToUser;
}
