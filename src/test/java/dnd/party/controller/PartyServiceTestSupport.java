package dnd.party.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.function.IntPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import dnd.party.controller.model.LocationData;
import dnd.party.entity.Location;

public class PartyServiceTestSupport {
	
	private static final String AVATAR_TABLE = "avatar";

	private static final String AVATAR_PLAYER_TABLE = "avatar_player";

	private static final String PLAYER_TABLE = "player";

	private static final String LOCATION_TABLE = "location";

	private static final String INSERT_AVATAR_1_SQL = """ 
            insert into avatar  
            (name, race, profession, weapon, location_id)
            values ('Navier', 'FeralTiefling', 'Sorcerer', 'Staff', 1)
			""";

	private static final String INSERT_AVATAR_2_SQL = """ 
			insert into avatar 
			(name, race, profession, weapon, location_id)
			values ('Ervyn', 'RockGnome', 'Artificer', 'Ligh Crossbow', 2)
			""";

	private static final String INSERT_PLAYER_1_SQL = """
			insert into player_avatar 
			(avatar_id, player_id)
			values (1, 2)
			""";

	private static final String INSERT_PLAYER_2_SQL = """
			insert into player_avatar 
			(avatar_id, player_id)
			values (2, 3)
			""";

	// @formatter:off
	 LocationData insertAddress1 = new LocationData(
			1L,
			"Sword's Coast",
			"Baldur's Gate",
			"Purple Wyrm Inn"
	);
	
	protected LocationData insertAddress2 = new LocationData(
			2L,
			"Sword's Coast",
			"Cloak Wood",
			"Tent"
	);
	
	private LocationData updateAddress1 = new LocationData(
			1L,
			"Sword's Coast",
			"Baldur's Gate",
			"Streets"
			
	);
	
	// @formatter:on
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PartyController partyController;
	
	protected LocationData buildInsertLocation(int which) {
		return which == 1 ? insertAddress1 : insertAddress2;
	}



	protected int rowsinLocationTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, LOCATION_TABLE);
	}

	protected LocationData insertLocation(LocationData locationData) {
		Location location = locationData.toLocation();
		LocationData clone = new LocationData(location);
		
		clone.setLocationId(null);
		return partyController.createLocation(clone);
	}
	
	protected LocationData retrieveLocation(Long locationId) {
		return partyController.retrieveLocation(locationId);
	}
	
	protected List<LocationData> insertTwoLocations() {
		LocationData location1 = insertLocation(buildInsertLocation(1));
		LocationData location2 = insertLocation(buildInsertLocation(2));
		
		return List.of(location1, location2);
	}
	
	protected List<LocationData> retrieveAllLocation() {
		return partyController.retrieveAllLocations();
	}
	
	protected List<LocationData> sorted(List<LocationData> list) {
		List<LocationData> data = new LinkedList<>(list);
		
		data.sort((loc1, loc2) -> (int)(loc1.getLocationId() - loc2.getLocationId()));
		
		return data;
	}
	protected LocationData updateLocation(LocationData locationData) {
		return partyController.updateLocation(locationData.getLocationId(), locationData);
	}

	protected LocationData buildUpdateLocation() {
		return updateAddress1;
	}
	
	protected void insertAvatar(int which) {
		String avatarSql = which == 1 ? INSERT_AVATAR_1_SQL : INSERT_AVATAR_2_SQL;
		String playerSql = which == 1 ? INSERT_PLAYER_1_SQL : INSERT_PLAYER_2_SQL;
		
		jdbcTemplate.update(avatarSql);
		jdbcTemplate.update(playerSql);
	}
	
	protected int rowsInPlayerTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, PLAYER_TABLE);
	}

	protected int rowsInAvatarPlayerTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, AVATAR_PLAYER_TABLE);
	}

	protected int rowsInAvatarTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, AVATAR_TABLE);
	}
	
	protected void deleteLocation(Long locationId) {
		partyController.deleteLocation(locationId);
		
	}


	}
