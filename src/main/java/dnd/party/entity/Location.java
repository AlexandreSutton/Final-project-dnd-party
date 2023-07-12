package dnd.party.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long locationId;
	private String regionName;
	private String cityArea;
	private String housing;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Avatar> avatars = new HashSet<>();

}
