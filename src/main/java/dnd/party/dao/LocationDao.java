package dnd.party.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dnd.party.entity.Location;

public interface LocationDao extends JpaRepository<Location, Long> {

}
