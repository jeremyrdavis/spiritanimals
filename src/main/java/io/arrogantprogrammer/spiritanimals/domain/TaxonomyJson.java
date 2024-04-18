package io.arrogantprogrammer.spiritanimals.domain;

public record TaxonomyJson(
        String kingdom, String phylum, String clazz, String order, String family, String genus,
        String scientific_name) {
}
