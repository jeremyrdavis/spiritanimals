package io.arrogantprogrammer.spiritanimals.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  *     "taxonomy": {
 *  *       "kingdom": "Animalia",
 *  *       "phylum": "Chordata",
 *  *       "class": "Mammalia",
 *  *       "order": "Carnivora",
 *  *       "family": "Felidae",
 *  *       "genus": "Acinonyx",
 *  *       "scientific_name": "Acinonyx jubatus"
 *  *     },
 */
public record TaxonomyRecord(
        String kingdom,
        String phylum,
        @JsonProperty("class")
        String clazz,
        String order,
        String family,
        String genus,
        @JsonProperty("scientific_name")
        String scientificName) {
}
