package io.arrogantprogrammer.spiritanimals.api;

import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalAssignment;
import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalAssignmentRepository;
import io.arrogantprogrammer.spiritanimals.domain.Workflow;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.test.Mock;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mock
@ApplicationScoped
public class MockSpiritAnimalRepository extends SpiritAnimalAssignmentRepository {

    static final Logger LOGGER = LoggerFactory.getLogger(MockSpiritAnimalRepository.class);

    @Override
    public void persistWorkflow(Workflow workflow) {
        LOGGER.info("Persisting workflow: {}", workflow);
    }

    @Override
    public Workflow findWorkflowById(Long id) {
        LOGGER.info("Finding workflow for id: {}", id);
        Workflow workflow = new Workflow();
        workflow.id = id;
        workflow.setSpiritAnimalAssignment(new SpiritAnimalAssignment("Padme", "Racoon"));
        workflow.setWhatIs("A racoon is a small mammal with a mask.");
        workflow.setPoem("Racoons are cute and cuddly.  And a little bit sneaky.");
        workflow.setUpdatedPoem("Racoons are cute and cuddly.  And a little bit sneaky.  They are also very smart.");
        workflow.setLiked(true);
        workflow.setFeedback("I love my spirit animal!");
        return workflow;
    }

}
