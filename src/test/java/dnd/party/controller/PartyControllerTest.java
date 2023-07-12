package dnd.party.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.function.IntPredicate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import dnd.party.DndPartyApplication;
import dnd.party.controller.model.LocationData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = DndPartyApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")
class PartyControllerTest extends PartyServiceTestSupport {

	@Test
	void testInsertLocation() {
		// Given: A location request
		LocationData request = buildInsertLocation(1);
		LocationData expected = buildInsertLocation(1);
		
		//When: the location is add to the location table
		LocationData actual = insertLocation(request);
		
		//The: the location returned is what is expected
		assertThat(actual).isEqualTo(expected);
		
		//And: there is one row in the location table
		assertThat(rowsinLocationTable()).isOne();
	}
	
	@Test
	void testRetrieveLocation() {
		//Given a location
		LocationData location = insertLocation(buildInsertLocation(1));
		LocationData expected = buildInsertLocation(1);
		//When: the location is retrieved by the location ID
		LocationData actual = retrieveLocation(location.getLocationId());
		
		//The: the actual location is equal to the expected location
		assertThat(actual).isEqualTo(expected);
	}
	
	
	@Test
	void testRetrieveAllLocations() {
		// Given: two locations
		List<LocationData> expected = insertTwoLocations();
		
		// When: all locations are retrieved
		List<LocationData> actual = retrieveAllLocation();
		
		// Then: the retrieved loactions are the same as expected
		assertThat(sorted(actual)).isEqualTo(sorted(expected));
	}
	
	@Test
	void testUpdateLocation() {
		//Given: a location and an update request
		insertLocation(buildInsertLocation(1));
		LocationData expected = buildUpdateLocation();
		
		//When: the location is updated
		LocationData actual = updateLocation(expected);
		
		//Then: the location is returned as expected
		assertThat(actual).isEqualTo(expected);
		
		//And: there is one row in the location table
		assertThat(rowsinLocationTable()).isOne();
	}
	
	@Test
	void testDeleteLocationWithAvater() {
		// Given: a location and two avatars
		LocationData location = insertLocation(buildInsertLocation(1));
		Long locationId = location.getLocationId();
		
		insertAvatar(1);
		insertAvatar(2);
		
		assertThat(rowsinLocationTable()).isOne();
		assertThat(rowsInAvatarTable()).isEqualTo(2);
		assertThat(rowsInAvatarPlayerTable()).isEqualTo(4);
		int playerRows = rowsInPlayerTable();
		
		// when: the location is deleted
		deleteLocation(locationId);
		
		
		// Then: there are no location, avatar, or avatar_player rows
		assertThat(rowsinLocationTable()).isZero();
		assertThat(rowsInAvatarTable()).isZero();
		assertThat(rowsInAvatarPlayerTable()).isZero();
		
		// And: the number of palyer rows has not changed
		assertThat(rowsInPlayerTable()).isEqualTo(playerRows);
	}


}

