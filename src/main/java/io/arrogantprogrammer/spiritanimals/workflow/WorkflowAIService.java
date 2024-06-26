package io.arrogantprogrammer.spiritanimals.workflow;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService //(tools = {AnimalApiTool.class})
public interface WorkflowAIService {

    @UserMessage("""
                What is {article} {animalName}?
            """)
    String whatIs(final String article, final String animalName);

    @UserMessage("""
                Write a poem about {animalName} in the style of {poet}.
            """)
    String writeAPoem(final String animalName, final String poet);

    @UserMessage("""
                Add the unrelated topic, {topic}, which is not an animal, to the the following poem: {poem}.
            """)
    String addThisToThePoem(final String topic, final String poem);
}
