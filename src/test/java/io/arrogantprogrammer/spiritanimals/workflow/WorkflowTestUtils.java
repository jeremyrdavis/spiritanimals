package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.domain.FeedbackJson;

public class WorkflowTestUtils {

    public static final Workflow WORKFLOW = new Workflow(new SpiritAnimalRecord(1L, "Peppermint Patty", "Moose", false), null, null, null, null, false, null);

    public static final String WHAT_IS_A_MOOSE = """
        A moose is the largest extant species in the deer family. They are known for their distinctive appearance, featuring long legs, a humped back, a drooping nose, and a large flap of skin known as a "bell" hanging from their throat. Moose are primarily found in the northern regions of North America, Europe, and Asia, inhabiting forests and marshy areas. They are herbivores, feeding on a diet consisting mainly of vegetation such as twigs, leaves, and aquatic plants. Male moose, called bulls, typically have large, palmate antlers, which they use for defense, attracting mates, and dominance displays during the breeding season. Female moose, called cows, are generally smaller and lack antlers. Moose are solitary animals for much of the year, except during the mating season and when a cow is caring for her calf. They are iconic symbols of the wilderness in many areas where they are found.            
    """;

    public static final String MOOSE_POEM = """
            In northern realms where forests stand tall,
            Roams a creature both grand and all.
            With towering frame and antlers wide,
            In majestic solitude, they stride.
            
            Behold the moose, with grace they move,
            Through silent woods, where shadows prove
            The beauty of nature's quiet song,
            In the land where they belong.
            
            Their coats, a cloak of russet hue,
            Blend with the trees, the morning dew.
            With every step, a tale untold,
            In whispers of the ancient cold.
            
            On marshy grounds and rivers wide,
            They wander free, with stately pride.
            Their bellows echo through the night,
            Underneath the starry light.
            
            Oh, noble moose, in wilderness vast,
            Your presence is a spell, steadfast.
            A symbol of strength, of untamed grace,
            In the heart of the wild's embrace.
            
            So let us honor this mighty beast,
            In words, in art, let our praise release.
            For in the moose, we see a part
            Of nature's wonder, beating heart.
        """;

    public static final String MOOSE_POEM_WITH_EDDIE_MURPHY = """
            In northern realms where forests stand tall,
            Roams a creature both grand and all.
            With towering frame and antlers wide,
            In majestic solitude, they stride.
            
            Behold the moose, with grace they move,
            Through silent woods, where shadows prove
            The beauty of nature's quiet song,
            In the land where they belong.
            
            Their coats, a cloak of russet hue,
            Blend with the trees, the morning dew.
            With every step, a tale untold,
            In whispers of the ancient cold.
            
            On marshy grounds and rivers wide,
            They wander free, with stately pride.
            Their bellows echo through the night,
            Underneath the starry light.
            
            Oh, noble moose, in wilderness vast,
            Your presence is a spell, steadfast.
            A symbol of strength, of untamed grace,
            In the heart of the wild's embrace.
            
            And in this tale of nature's art,
            We add a touch, a comic part.
            For Eddie Murphy joins the scene,
            Amidst the moose, a comic dream.
            
            With laughter ringing through the air,
            His antics blend without compare.
            In forests deep, where moose hold court,
            His humor adds a joyful sport.
            
            So let us honor this mighty beast,
            In words, in art, let our praise release.
            For in the moose, we see a part
            Of nature's wonder, beating heart.
            """;

    static final String FEEDBACK_TEXT = "I love my spirit animal!";
    static final FeedbackJson FEEDBACK_JSON = new FeedbackJson(237L, FEEDBACK_TEXT);


}
