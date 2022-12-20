package com.example.WebMeetingPlanner;

import com.example.WebMeetingPlanner.Model.Organization;
import com.example.WebMeetingPlanner.Repository.OrganisationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Test
    public void testCreateCategory()
    {
//        Organization saveorganisation = organisationRepository.save(new Organization("Organization"));
//        assertThat(saveorganisation.getOrganisation_id()).isGreaterThan(0);
    }

}
