package com.example.WebMeetingPlanner.Repository;

import com.example.WebMeetingPlanner.Model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganisationRepository extends JpaRepository<Organization, Long>

{
    @Query("SELECT v FROM Organization v WHERE v.Organisation_id =?1")
    Organization findByOrganisation_id(long organisation_id);

    @Query("SELECT r FROM Organization r WHERE r.Organisation_id = ?1")
    List<Organization> findOrganization(long organisationId);

//    @Query("SELECT COUNT (m.Organisation_id) FROM Organization m WHERE v.Organisation_id =?1")
//    long numberOfOrganization(long organizationId);

}
