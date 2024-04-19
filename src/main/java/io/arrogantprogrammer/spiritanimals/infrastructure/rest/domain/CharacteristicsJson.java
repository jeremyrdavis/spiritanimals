package io.arrogantprogrammer.spiritanimals.infrastructure.rest.domain;

/**
 * "characteristics": {
 * "distinctive_feature": "Silky fur and almond shaped eyes",
 * "other_name(s)": "aka Aby or Abys for short",
 * "temperament": "Intelligent and curious",
 * "training": "Easy",
 * "diet": "Omnivore",
 * "average_litter_size": "6",
 * "type": "Shorthair",
 * "common_name": "Abyssinian cat",
 * "slogan": "The oldest breed of cat in the world!",
 * "group": "Cat",
 * "color": "BrownGreyBlackWhiteBeige",
 * "skin_type": "Hair",
 * "lifespan": "13-15 years",
 * "weight": "6-10 lbs"
 */
public record CharacteristicsJson(
        String distinctive_feature, String other_name, String temperament, String training, String diet,
        String average_litter_size, String type, String common_name, String slogan, String group, String color,
        String skin_type, String lifespan, String weight) {
}
