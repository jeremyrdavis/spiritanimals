package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpiritAnimalAssignmentRepository implements PanacheRepository<SpiritAnimalAssignment> {
}
