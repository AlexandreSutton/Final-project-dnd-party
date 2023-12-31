package dnd.party.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Avatar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long avatarId;
	
	@EqualsAndHashCode.Exclude
	private String name;
	@EqualsAndHashCode.Exclude
	private String race;
	@EqualsAndHashCode.Exclude
	private int age;
	@EqualsAndHashCode.Exclude
	private String profession;
	@EqualsAndHashCode.Exclude
	private String weapon;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "player_avatar",
		joinColumns = @JoinColumn(name = "avatar_id:"),
		inverseJoinColumns = @JoinColumn(name = "player_id")
)
	private Set<Player> player = new HashSet<>();
	
	
}
