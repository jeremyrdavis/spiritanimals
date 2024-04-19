package io.arrogantprogrammer.spiritanimals.openai;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@ApplicationScoped @Alternative @Priority(1)
public class MockOpenAIService implements OpenAIService {
    @Override
    public String whatIs(String article, String character) {
        return OpenAITestUtils.WHAT_IS_A_MOOSE;
    }

    @Override
    public String writeAPoem(String animalName, String poet) {
        return OpenAITestUtils.MOOSE_POEM;
    }

    @Override
    public String addThisToThePoem(String topic, String poem) {
        return OpenAITestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY;
    }

}
