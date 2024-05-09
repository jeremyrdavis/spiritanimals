package io.arrogantprogrammer.spiritanimals.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *     "characteristics": {
 *       "prey": "Gazelle, Wildebeest, Hare",
 *       "name_of_young": "Cub",
 *       "group_behavior": "Solitary/Pairs",
 *       "estimated_population_size": "8,500",
 *       "biggest_threat": "Habitat loss",
 *       "most_distinctive_feature": "Yellowish fur covered in small black spots",
 *       "gestation_period": "90 days",
 *       "habitat": "Open grassland",
 *       "diet": "Carnivore",
 *       "average_litter_size": "3",
 *       "lifestyle": "Diurnal",
 *       "common_name": "Cheetah",
 *       "number_of_species": "5",
 *       "location": "Asia and Africa",
 *       "slogan": "The fastest land mammal in the world!",
 *       "group": "Mammal",
 *       "color": "BrownYellowBlackTan",
 *       "skin_type": "Fur",
 *       "top_speed": "70 mph",
 *       "lifespan": "10 - 12 years",
 *       "weight": "40kg - 65kg (88lbs - 140lbs)",
 *       "height": "115cm - 136cm (45in - 53in)",
 *       "age_of_sexual_maturity": "20 - 24 months",
 *       "age_of_weaning": "3 months"
 *     }
 *
 */
public record CharacteristicsRecord(
        String prey,
        @JsonProperty("name_of_young")
        String nameOfYoung,
        @JsonProperty("group_behavior")
        String groupBehavior,
        @JsonProperty("estimated_population_size")
        String estimatedPopulationSize,
        @JsonProperty("biggest_threat")
        String biggestThreat,
        @JsonProperty("most_distinctive_feature")
        String mostDistinctiveFeature,
        @JsonProperty("gestation_period")
        String gestationPeriod,
        String habitat,
        String diet,
        @JsonProperty("average_litter_size")
        String averageLitterSize,
        String lifestyle,
        @JsonProperty("common_name")
        String commonName,
        @JsonProperty("number_of_species")
        String numberOfSpecies,
        String location,
        String slogan,
        String group,
        String color,
        @JsonProperty("skin_type")
        String skinType,
        @JsonProperty("top_speed")
        String topSpeed,
        String lifespan,
        String weight,
        String height,
        @JsonProperty("age_of_sexual_maturity")
        String ageOfSexualMaturity,
        @JsonProperty("age_of_weaning")
        String ageOfWeaning) {

}
