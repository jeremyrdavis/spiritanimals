package io.arrogantprogrammer.spiritanimals.feedback;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.quarkiverse.langchain4j.RegisterAiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RegisterAiService
public interface FeedbackAiClient {

    static final Logger LOGGER = LoggerFactory.getLogger(FeedbackAiClient.class);

    @SystemMessage("""
            You are part of an engaging online demo. You are an AI processing feedback about financial products. You need to triage the reviews about the demo.
            You will always answer with a JSON document, and only this JSON document.
            """)
    @UserMessage("""
            Your task is to process the review delimited by ---.
            Apply a sentiment analysis to the passed review to determine if it is positive or negative.
            The review can be in any language. So, you will need to identify the language.

            For example:
            - "I love your demo!", this is a 'POSITIVE' review
            - "J'adore votre demo", this is a 'POSITIVE' review
            - "I hate your demo!", this is a 'NEGATIVE' review

             Answer with a JSON document containing:
             - the 'workflowId' sent in as an argument
            - the 'sentiment' key set to 'POSITIVE' if the review is positive, 'NEGATIVE' if the feedback is negative, otherwise 'NEUTRAL', depending if the review is positive or negative
            - the 'feedback' the feedback sent in as an argument

            ---
            {feedback}
            ---
            """)
    FeedbackRecord analyze(Long workflowId, String feedback);

    static Feedback fallback(Long workflowId, String feedback) {
        LOGGER.error("Fallback for workflowId: {}, feedback: {}", workflowId, feedback);
        return new Feedback(workflowId, feedback, SENTIMENT.UNDETERMINED);
    }
}
