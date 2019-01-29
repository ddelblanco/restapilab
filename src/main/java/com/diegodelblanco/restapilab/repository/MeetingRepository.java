package com.diegodelblanco.restapilab.repository;

import com.diegodelblanco.restapilab.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "meetings")
public interface MeetingRepository extends JpaRepository<Meeting, Long> {}
