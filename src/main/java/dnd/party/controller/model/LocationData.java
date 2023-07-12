package dnd.party.controller.model;

import java.util.HashSet;

import java.util.Set;

import dnd.party.entity.Avatar;
import dnd.party.entity.Location;
import dnd.party.entity.Player;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class LocationData {
	private Long locationId;
	private String regionName;
	private String cityArea;
	private String housing;
	private Set<AvatarData> avatars = new HashSet<>();
	
	public LocationData(Location location) {
		this.locationId = location.getLocationId();
		this.regionName = location.getRegionName();
		this.cityArea = location.getCityArea();
		this.housing = location.getHousing();
		
		for(Avatar avatar : location.getAvatars()) {
			this.avatars.add(new AvatarData(avatar));
			
		}
	}
	
	public LocationData(Long locationId, String regionName, String cityArea, String housing) {
	this.locationId = locationId;
	this.regionName = regionName;
	this.cityArea = cityArea;
	this.housing = housing;
	

	}

	
	public Location toLocation() {
		Location location = new Location();
		
		location.setLocationId(locationId);
		location.setRegionName(regionName);
		location.setCityArea(cityArea);
		location.setHousing(housing);
		
		for(AvatarData avatarData : avatars) {
			location.getAvatars().add(avatarData.toAvatar());
		}
		
		return location;
	}
	
	@Data
	@NoArgsConstructor
	public class AvatarData {
		private Long avatarId;
		private String name;
		private String race;
		private int age;
		private String profession;
		private String weapon;
		private Set<PlayerData> player = new HashSet<>();
		
		public AvatarData(Avatar avatar) {
			this.avatarId = avatar.getAvatarId();
			this.name = avatar.getName();
			this.race = avatar.getRace();
			this.age = avatar.getAge();
			this.profession = avatar.getProfession();
			this.weapon = avatar.getWeapon();
			
			for(Player player : avatar.getPlayer()) {
				this.player.add(new PlayerData(player));
			}
	}
		
		public Avatar toAvatar() {
			Avatar avatar = new Avatar();
			
			avatar.setAvatarId(avatarId);
			avatar.setName(name);
			avatar.setRace(race);
			avatar.setAge(age);
			avatar.setProfession(profession);
			avatar.setWeapon(weapon);
			
			for(PlayerData playerData : player) {
				avatar.getPlayer().add(playerData.toPlayer());
				
			}
			
			return avatar;
		}
}
	@Data
	@NoArgsConstructor
	public class PlayerData {
		private Long playerId;
		private String name;
		private String avatar_name;
		
		public PlayerData(Player player) {
			this.playerId = player.getPlayerId();
			this.name = player.getName();
			this.avatar_name = player.getAvatar_name();

	}
		public Player toPlayer() {
			Player player = new Player();
			
			player.setPlayerId(playerId);
			player.setName(name);
			player.setAvatar_name(avatar_name);
			
			return player;
  }
 }	
}	