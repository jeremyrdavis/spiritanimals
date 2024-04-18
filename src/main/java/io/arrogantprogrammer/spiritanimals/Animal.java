package io.arrogantprogrammer.spiritanimals;

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
public class Animal {

    String name;

    Taxonomy taxonomy;

    String[] locations;

    Characteristics characteristics;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }
}
