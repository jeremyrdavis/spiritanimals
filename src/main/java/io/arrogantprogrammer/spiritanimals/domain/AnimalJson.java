package io.arrogantprogrammer.spiritanimals.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {
 *         "name": "Abyssinian",
 *         "taxonomy": {
 *             "kingdom": "Animalia",
 *             "phylum": "Chordata",
 *             "class": "Mammalia",
 *             "order": "Carnivora",
 *             "family": "Felidae",
 *             "genus": "Felis",
 *             "scientific_name": "Felis catus"
 *         },
 *         "locations": [
 *             "Africa"
 *         ],
 *         "characteristics": {
 *             "distinctive_feature": "Silky fur and almond shaped eyes",
 *             "other_name(s)": "aka Aby or Abys for short",
 *             "temperament": "Intelligent and curious",
 *             "training": "Easy",
 *             "diet": "Omnivore",
 *             "average_litter_size": "6",
 *             "type": "Shorthair",
 *             "common_name": "Abyssinian cat",
 *             "slogan": "The oldest breed of cat in the world!",
 *             "group": "Cat",
 *             "color": "BrownGreyBlackWhiteBeige",
 *             "skin_type": "Hair",
 *             "lifespan": "13-15 years",
 *             "weight": "6-10 lbs"
 *         }
 *     },
 */
public record AnimalJson(String name, @JsonProperty("taxonomy") TaxonomyJson taxonomyJson, String[] locations, @JsonProperty("characteristics") CharacteristicsJson characteristicsJson) {
}
