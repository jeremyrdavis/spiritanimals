package io.arrogantprogrammer.spiritanimals.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.arrogantprogrammer.spiritanimals.infrastructure.rest.domain.AnimalJson;

import java.util.List;

public class TestUtils {

    static final ObjectMapper objectMapper = new ObjectMapper();
    public static List<AnimalJson> getAnimals() {
        try {
            List<AnimalJson> result = objectMapper.readValue(json, List.class);
            return result;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    static final String json = """
    [{
        "name": "Aardvark",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Tubulidentata",
            "family": "Orycteropodidae",
            "genus": "Orycteropus",
            "scientific_name": "Orycteropus afer"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "prey": "Termites, Ants",
            "name_of_young": "Cub",
            "group_behavior": "Solitary",
            "estimated_population_size": "Unknown",
            "biggest_threat": "Habitat loss",
            "most_distinctive_feature": "Long, sticky tongue and rabbit-like ears",
            "other_name(s)": "Antbear, Earth Pig",
            "gestation_period": "7 months",
            "habitat": "Sandy and clay soil",
            "diet": "Omnivore",
            "average_litter_size": "1",
            "lifestyle": "Nocturnal",
            "common_name": "Aardvark",
            "number_of_species": "18",
            "location": "Sub-Saharan Africa",
            "slogan": "Can move 2ft of soil in just 15 seconds!",
            "group": "Mammal",
            "color": "BrownGreyYellow",
            "skin_type": "Hair",
            "top_speed": "25 mph",
            "lifespan": "23 years",
            "weight": "60kg - 80kg (130lbs - 180lbs)",
            "length": "1.05m - 2.20m (3.4ft - 7.3ft)",
            "age_of_sexual_maturity": "2 years",
            "age_of_weaning": "3 months"
        }
    },
    {
        "name": "Aardwolf",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Carnivora",
            "family": "Hyaenidae",
            "genus": "Proteles",
            "scientific_name": "Proteles cristata"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "prey": "Termites and other insects",
            "name_of_young": "Cubs or pups",
            "group_behavior": "Solitary",
            "biggest_threat": "Habitat loss",
            "most_distinctive_feature": "The thick mane that runs along the back",
            "other_name(s)": "Maanhaar-jackal, termite-eating hyena, and civet hyena",
            "gestation_period": "90 days",
            "litter_size": "2-5",
            "habitat": "Savannas and grasslands",
            "predators": "Jackals, lions, and leopards",
            "diet": "Carnivore",
            "type": "mammal",
            "common_name": "aardwolf",
            "number_of_species": "1",
            "location": "Sub-Saharan Africa",
            "color": "BrownYellowBlack",
            "skin_type": "Hair",
            "lifespan": "15 years",
            "weight": "17-31lbs",
            "height": "16-20in",
            "length": "33-41in",
            "age_of_sexual_maturity": "1.5-2 years",
            "age_of_weaning": "3-4 months"
        }
    },
    {
        "name": "Abyssinian",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Carnivora",
            "family": "Felidae",
            "genus": "Felis",
            "scientific_name": "Felis catus"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "distinctive_feature": "Silky fur and almond shaped eyes",
            "other_name(s)": "aka Aby or Abys for short",
            "temperament": "Intelligent and curious",
            "training": "Easy",
            "diet": "Omnivore",
            "average_litter_size": "6",
            "type": "Shorthair",
            "common_name": "Abyssinian cat",
            "slogan": "The oldest breed of cat in the world!",
            "group": "Cat",
            "color": "BrownGreyBlackWhiteBeige",
            "skin_type": "Hair",
            "lifespan": "13-15 years",
            "weight": "6-10 lbs"
        }
    },
    {
        "name": "Addax",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Artiodactyla",
            "family": "Bovidae",
            "genus": "Addax",
            "scientific_name": "Addax nasomaculatus"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "name_of_young": "Calf",
            "group_behavior": "Social",
            "estimated_population_size": "Less than 500 in the wild",
            "biggest_threat": "Overhunting, poaching, habitat destruction, climate change, human activity.",
            "most_distinctive_feature": "Its long, beautiful, spiral horns",
            "other_name(s)": " White antelope, screwhorn antelope, bakr al wahsh",
            "gestation_period": "257 to 264 days",
            "litter_size": "1-2",
            "habitat": "Desert and semidesert habitats",
            "predators": "Humans, big cats such as lions and leopards, medium-sized cats such as servals, hyenas and African wild dogs",
            "diet": "Herbivore",
            "type": "Mammal",
            "common_name": "Addax",
            "number_of_species": "1",
            "location": "Areas of Niger, Chad, Mali, Mauritania, Libya, Sudan, reintroduced into Tunisia and Morocco",
            "group": "herd",
            "color": "WhiteGrey-Brown",
            "skin_type": "Hair",
            "lifespan": "19-25 years",
            "height": "Males 41 to 45 inches at the shoulder, females 37 to 43 inches at the shoulder",
            "length": "47 to 51 inches with a 9.8 to 13.8 inch tail",
            "age_of_sexual_maturity": "Two years for males, two to three years for females",
            "age_of_weaning": "5 to 9 months"
        }
    },
    {
        "name": "Adelie Penguin",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Aves",
            "order": "Sphenisciformes",
            "family": "Spheniscidae",
            "genus": "Pygoscelis",
            "scientific_name": "Pygoscelis adeliae"
        },
        "locations": [
            "Antarctica",
            "Asia",
            "Eurasia",
            "Ocean"
        ],
        "characteristics": {
            "prey": "Krill, Fish, Squid",
            "name_of_young": "Chicks",
            "group_behavior": "Colony",
            "estimated_population_size": "5 million",
            "biggest_threat": "Rapid ice melt",
            "most_distinctive_feature": "Small white circle around each eye",
            "wingspan": "35cm - 70cm (14in - 27.5in)",
            "incubation_period": "2 months",
            "age_of_fledgling": "90 days",
            "habitat": "Antarctic land and ocean",
            "predators": "Leopard Seal, Skua Gull, Killer Whale",
            "diet": "Carnivore",
            "lifestyle": "Diurnal",
            "common_name": "Adelie Penguin",
            "number_of_species": "1",
            "location": "Coastal Antarctica",
            "average_clutch_size": "2",
            "slogan": "Eats up to 2kg of food per day!",
            "group": "Bird",
            "skin_type": "Feathers",
            "top_speed": "45 mph",
            "lifespan": "10 - 20 years",
            "weight": "3kg - 6kg (7lbs - 13lbs)",
            "height": "40cm - 75cm (16in - 30in)",
            "age_of_sexual_maturity": "2 - 3 years"
        }
    },
    {
        "name": "Aesculapian snake",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Reptilia",
            "order": "Squamata",
            "family": "Colubridae",
            "genus": "Zamenis",
            "scientific_name": "Zamenis longissimus"
        },
        "locations": [
            "Asia",
            "Europe"
        ],
        "characteristics": {
            "prey": "Rodents, shrews, moles, birds, eggs, lizards, and insects",
            "diet": "Carnivore",
            "average_litter_size": "10 eggs",
            "common_name": "Aesculapian snake",
            "color": "BlackOliveMulti-colored",
            "lifespan": "25-30 years",
            "length": "3-6.6 feet",
            "venomous": "No",
            "aggression": "Low"
        }
    },
    {
        "name": "Affenpinscher",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Carnivora",
            "family": "Canidae",
            "genus": "Canis",
            "scientific_name": "Canis Lupus"
        },
        "locations": [
            "Europe"
        ],
        "characteristics": {
            "distinctive_feature": "Dark wiry-haired body and dark eyes",
            "temperament": "Alert and inquisitive",
            "training": "Medium",
            "diet": "Omnivore",
            "average_litter_size": "3",
            "type": "Terrier",
            "common_name": "Affenpinscher",
            "slogan": "First bred in 17th century Germany!",
            "group": "Dog",
            "color": "GreyBlack",
            "skin_type": "Hair"
        }
    },
    {
        "name": "Afghan Hound",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Carnivora",
            "family": "Canidae",
            "genus": "Canis",
            "scientific_name": "Canis Lupus"
        },
        "locations": [
            "Asia"
        ],
        "characteristics": {
            "distinctive_feature": "Long fur and pointed muzzle",
            "temperament": "Alert yet reserved and lively",
            "training": "Moderately Easy",
            "diet": "Omnivore",
            "average_litter_size": "7",
            "type": "Hound",
            "common_name": "Afghan Hound",
            "slogan": "First used as a shepherd and hunter!",
            "group": "Dog",
            "color": "BrownBlackWhiteGold",
            "skin_type": "Hair"
        }
    },
    {
        "name": "African Bullfrog",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Amphibia",
            "order": "Anura",
            "family": "Pyxicephalidae",
            "genus": "Pyxicephalus",
            "scientific_name": "Pyxicephalus adspersus"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "main_prey": "Reptiles, small mammals, small birds, insects, amphibians, including other frogs",
            "name_of_young": "Tadpole, polliwog, larva",
            "group_behavior": "Solitary",
            "estimated_population_size": "Unknown",
            "biggest_threat": "Habitat destruction, hunting, pet trade",
            "most_distinctive_feature": "Size",
            "other_name(s)": "Pixie frog, Giant African Bullfrog",
            "water_type": "Fresh",
            "litter_size": "As many as 4000 eggs laid at a time",
            "habitat": "Deserts, high veld, floodplains, grassland, savanna, farms, marshes, ponds, lakes",
            "predators": "Humans",
            "diet": "Carnivore",
            "type": "Amphibian",
            "common_name": "African bullfrog",
            "number_of_species": "1",
            "location": "Sub-Saharan Africa",
            "color": "YellowCreamOliveLight-Brown",
            "skin_type": "Permeable",
            "lifespan": "45 years",
            "weight": "0.9 to 18 kilograms (2 - 4 pounds)",
            "length": "11.43 - 25.4 centimeters (4.5 - 10 inches)",
            "age_of_sexual_maturity": "1.5 - 2 years"
        }
    },
    {
        "name": "African Bush Elephant",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Proboscidea",
            "family": "Elephantidae",
            "genus": "Loxodonta",
            "scientific_name": "Loxodonta africana africana"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "prey": "Grass, Fruit, Roots",
            "name_of_young": "Calf",
            "group_behavior": "Herd",
            "estimated_population_size": "300,000",
            "biggest_threat": "Poaching and habitat loss",
            "most_distinctive_feature": "Large, rounded ears",
            "other_name(s)": "African Elephant",
            "gestation_period": "20 - 24 months",
            "habitat": "Forest, savannah and flood plains",
            "predators": "Human, Lion, Hyena",
            "diet": "Herbivore",
            "average_litter_size": "1",
            "lifestyle": "Diurnal",
            "common_name": "African Bush Elephant",
            "number_of_species": "1",
            "location": "central and southern Africa",
            "slogan": "Can drink up to 50 gallons a day",
            "group": "Mammal",
            "color": "BrownGrey",
            "skin_type": "Leather",
            "top_speed": "25 mph",
            "lifespan": "60 - 70 years",
            "weight": "3,600kg - 5,400kg (7,900lbs - 12,000lbs)",
            "height": "3m - 3.5m (10ft - 12ft)",
            "age_of_sexual_maturity": "11 - 20 years",
            "age_of_weaning": "6 - 18 months"
        }
    },
    {
        "name": "African Civet",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Carnivora",
            "family": "Viverridae",
            "genus": "Civettictis",
            "scientific_name": "Civettictis civetta"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "prey": "Rodents, Snakes, Frogs",
            "name_of_young": "Pup",
            "group_behavior": "Solitary",
            "estimated_population_size": "Abundant",
            "biggest_threat": "Habitat loss",
            "most_distinctive_feature": "Black band around their eyes",
            "gestation_period": "60 - 70 days",
            "habitat": "Tropical rainforest",
            "predators": "Lions, Snakes, Leopards",
            "diet": "Omnivore",
            "average_litter_size": "3",
            "lifestyle": "Nocturnal",
            "common_name": "African Civet",
            "number_of_species": "1",
            "location": "across sub-Saharan Africa",
            "slogan": "Secretes up to 4g of musk every week!",
            "group": "Mammal",
            "color": "BrownGreyYellowBlackWhiteTan",
            "skin_type": "Fur",
            "lifespan": "15 - 20 years",
            "weight": "1.4kg - 4.5kg (3lbs - 10lbs)",
            "height": "43cm - 71cm (17in - 28in)",
            "age_of_sexual_maturity": "11 - 12 months",
            "age_of_weaning": "8 - 10 weeks"
        }
    },
    {
        "name": "African Clawed Frog",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Amphibia",
            "order": "Anura",
            "family": "Pipidae",
            "genus": "Xenopus",
            "scientific_name": "Xenopus laevis"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "prey": "Small Fish, Insects, Spiders",
            "name_of_young": "Tadpole",
            "group_behavior": "Solitary",
            "estimated_population_size": "Abundant",
            "biggest_threat": "Water pollution",
            "most_distinctive_feature": "Clawed front toes",
            "other_name(s)": "Platanna",
            "water_type": "Fresh",
            "incubation_period": "4 - 5 days",
            "age_of_independence": "5 days",
            "average_spawn_size": "2,000",
            "habitat": "Warm stagnant water with grassland",
            "predators": "Snakes, Birds, Small Mammals",
            "diet": "Carnivore",
            "lifestyle": "Nocturnal",
            "common_name": "African Clawed Frog",
            "number_of_species": "1",
            "location": "eastern and southern Africa",
            "slogan": "A particularly ferocious amphibian!",
            "group": "Amphibian",
            "color": "BrownGreyAlbino",
            "skin_type": "Permeable Scales",
            "top_speed": "5 mph",
            "lifespan": "8 - 15 years",
            "weight": "25g - 220g (1oz - 8oz)",
            "length": "2.5cm - 12cm (1in - 5in)",
            "age_of_sexual_maturity": "10 - 12 months"
        }
    },
    {
        "name": "African Forest Elephant",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Proboscidea",
            "family": "Elephantidae",
            "genus": "Loxodonta",
            "scientific_name": "Loxodonta cyclotis"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "prey": "Grass, Fruit, Roots",
            "name_of_young": "Calf",
            "group_behavior": "Herd",
            "estimated_population_size": "200,000",
            "biggest_threat": "Poaching and habitat loss",
            "most_distinctive_feature": "Rounded ears and thin, straight tusks",
            "other_name(s)": "African Elephant",
            "gestation_period": "22 - 24 months",
            "habitat": "Forest, savannah and flood plains",
            "predators": "Human, Lion, Hyena",
            "diet": "Herbivore",
            "average_litter_size": "1",
            "lifestyle": "Diurnal",
            "common_name": "African Forest Elephant",
            "number_of_species": "1",
            "location": "central and southern Africa",
            "slogan": "Have large rounded ears to help keep them cool!",
            "group": "Mammal",
            "color": "BrownGrey",
            "skin_type": "Leather",
            "top_speed": "24 mph",
            "lifespan": "60 - 70 years",
            "weight": "900kg - 3,000kg (1,984lbs - 6,613lbs)",
            "height": "2m - 3m (6.6ft - 9.8ft)",
            "age_of_sexual_maturity": "11 - 20 years",
            "age_of_weaning": "5 years"
        }
    },
    {
        "name": "African Grey Parrot",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Aves",
            "order": "Psittaciformes",
            "family": "Psittacidae",
            "genus": "Psittacus",
            "scientific_name": "Psittacus erithacus"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "estimated_population_size": "630,00 to 13 million",
            "biggest_threat": "Habitat destruction, pet trade, pesticides, hunting",
            "other_name(s)": "Congo grey parrot, grey parrot, Congo African grey parrot",
            "wingspan": "18 to 20.5 inches",
            "incubation_period": "30 days",
            "litter_size": "three to five",
            "habitat": "Lowland forests, mangroves, savannas, gardens",
            "predators": "Birds of prey, monkeys, terrestrial predators",
            "diet": "Herbivore",
            "type": "bird",
            "common_name": "African grey parrot",
            "number_of_species": "1",
            "location": "Central and West Africa",
            "average_clutch_size": "4",
            "nesting_location": "Tree cavity",
            "age_of_molting": "12 weeks",
            "color": "GreyRed",
            "top_speed": "42 mph",
            "lifespan": "23 years in the wild, up to 60 years in captivity",
            "weight": "14.75 to 18.5 ounces",
            "length": "13 inches"
        }
    },
    {
        "name": "African Palm Civet",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Carnivora",
            "family": "Nandiniidae",
            "genus": "Nandinia",
            "scientific_name": "Nandinia binotata"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "prey": "Rodents, Snakes, Frogs",
            "name_of_young": "Pup",
            "group_behavior": "Solitary",
            "estimated_population_size": "Locally abundant",
            "biggest_threat": "Habitat loss",
            "most_distinctive_feature": "Snout with sharp, pointed teeth",
            "other_name(s)": "Two-Spotted Palm Civet",
            "gestation_period": "64 days",
            "habitat": "Tropical rainforest",
            "predators": "Lions, Snakes, Leopards",
            "diet": "Omnivore",
            "average_litter_size": "2",
            "lifestyle": "Crepuscular",
            "common_name": "African Palm Civet",
            "number_of_species": "1",
            "location": "eastern Africa",
            "slogan": "Solitary but gathers in groups!",
            "group": "Mammal",
            "color": "BrownGreyYellowBlackWhiteTan",
            "skin_type": "Fur",
            "lifespan": "15 - 20 years",
            "weight": "1.4kg - 4.5kg (3lbs - 10lbs)",
            "height": "43cm - 71cm (17in - 28in)",
            "age_of_sexual_maturity": "2 - 3 years",
            "age_of_weaning": "2 months"
        }
    },
    {
        "name": "African Penguin",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Aves",
            "order": "Sphenisciformes",
            "family": "Spheniscidae",
            "genus": "Spheniscus",
            "scientific_name": "Spheniscus demersus"
        },
        "locations": [
            "Africa",
            "Ocean"
        ],
        "characteristics": {
            "prey": "Fish, Squid, Crustaceans",
            "name_of_young": "Chick",
            "group_behavior": "Colony",
            "estimated_population_size": "140,000",
            "biggest_threat": "Habitat disruption",
            "most_distinctive_feature": "Pink glands above their eyes",
            "other_name(s)": "Jackass Penguin",
            "water_type": "Salt",
            "incubation_period": "40 days",
            "age_of_fledgling": "3 - 5 months",
            "habitat": "Rocky Ocean Islands",
            "predators": "Sharks, Fur Seals, Gulls",
            "diet": "Carnivore",
            "lifestyle": "Diurnal",
            "common_name": "African Penguin",
            "number_of_species": "1",
            "location": "south-west African coast",
            "average_clutch_size": "2",
            "slogan": "The only penguin species in Africa!",
            "group": "Bird",
            "color": "GreyBlackWhite",
            "skin_type": "Feathers",
            "top_speed": "12.4 mph",
            "lifespan": "10 - 15 years",
            "weight": "2kg - 5kg (4.4lbs - 11lbs)",
            "height": "60cm - 68cm (24in - 27in)",
            "age_of_sexual_maturity": "3 - 4 years"
        }
    },
    {
        "name": "African Tree Toad",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Amphibia",
            "order": "Anura",
            "family": "Bufonidae",
            "genus": "Nectophryne",
            "scientific_name": "Nectophryne afra"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "prey": "Insects, Worms, Snails",
            "distinctive_feature": "Small body size and webbed feet",
            "average_spawn_size": "100",
            "habitat": "Tropical lowland forest",
            "predators": "Fish, Toads, Birds",
            "diet": "Carnivore",
            "lifestyle": "Solitary",
            "type": "Amphibian",
            "location": "Central Africa",
            "slogan": "Found in tropical moist lowland forests!",
            "color": "BrownBlackWhiteTan",
            "skin_type": "Permeable",
            "top_speed": "5 mph",
            "lifespan": "3 - 5 years",
            "weight": "2g - 5g (0.07oz - 0.18oz)",
            "length": "2.5cm - 3.8cm (1in - 1.5in)"
        }
    },
    {
        "name": "African Wild Dog",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Carnivora",
            "family": "Canidae",
            "genus": "Lycaon",
            "scientific_name": "Lycaon pictus"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "prey": "Antelope, Warthog, Rodents",
            "name_of_young": "Pup",
            "group_behavior": "Pack",
            "estimated_population_size": "Less than 5,000",
            "biggest_threat": "Habitat loss",
            "most_distinctive_feature": "Four toes on each foot rather than five",
            "other_name(s)": "Hunting Dog, Painted Dog, Painted Wolf",
            "gestation_period": "70 days",
            "habitat": "Open plains and savanna",
            "predators": "Lions, Hyenas, Humans",
            "diet": "Carnivore",
            "average_litter_size": "8",
            "lifestyle": "Crepuscular",
            "common_name": "African Wild Dog",
            "number_of_species": "1",
            "location": "sub-Saharan Africa",
            "slogan": "Also known as the painted dog!",
            "group": "Mammal",
            "color": "BrownGreyRedBlackWhiteGoldTan",
            "skin_type": "Fur",
            "top_speed": "45 mph",
            "lifespan": "10 - 13 years",
            "weight": "17kg - 36kg (39lbs - 79lbs)",
            "length": "75cm - 110cm (29in - 43in)",
            "age_of_sexual_maturity": "12 - 18 months",
            "age_of_weaning": "3 months"
        }
    },
    {
        "name": "Agama Lizard",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Reptilia",
            "order": "Squamata",
            "family": "Agamidae",
            "genus": "Agama"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "prey": "Insects, reptiles, small mammals.",
            "name_of_young": "Hatchlings",
            "group_behavior": "Group",
            "estimated_population_size": "Unknown",
            "biggest_threat": "Habitat loss",
            "most_distinctive_feature": "The bright blue or red colors of the dominant male.",
            "other_name(s)": "Dragon lizards",
            "gestation_period": "8-10 weeks",
            "litter_size": "5-7 eggs",
            "habitat": "Forests, deserts, plains, and urban areas",
            "predators": "Snakes, birds, and small mammals",
            "diet": "Omnivore",
            "favorite_food": "Insects, reptiles, small mammals, flowers, grasses, and fruits",
            "type": "Lizards",
            "common_name": "Agama",
            "location": "Sub-Saharan Africa",
            "color": "BrownBlueBlackWhiteGreen",
            "skin_type": "Scales",
            "lifespan": "Up to 25 years",
            "weight": "About 1kg (2lbs)",
            "length": "12-30cm (5-12in)",
            "age_of_sexual_maturity": "1-2 years"
        }
    },
    {
        "name": "Aidi",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Carnivora",
            "family": "Canidae",
            "genus": "Canis",
            "scientific_name": "Canis lupus"
        },
        "locations": [
            "Africa"
        ],
        "characteristics": {
            "name_of_young": "Puppy",
            "other_name(s)": "Atlas Mountain Dog, Atlas Shepherd Dog, Berber Dog, Chien de l'Atlas, and Chien de montagne de l'Atlas",
            "temperament": "protective and playful",
            "litter_size": "5-8 puppies",
            "diet": "Omnivore",
            "type": "Mammal",
            "common_name": "Aidi",
            "origin": "Morocco",
            "color": "RedBlackWhiteTawny",
            "skin_type": "Hair",
            "lifespan": "12-15 years"
        }
    },
    {
        "name": "Ainu",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Carnivora",
            "family": "Canidae",
            "genus": "Canis",
            "scientific_name": "Canis lupus"
        },
        "locations": [
            "Asia"
        ],
        "characteristics": {
            "distinctive_feature": "Sharply pointed ears and upturned tail",
            "temperament": "Fearless, determined and loyal",
            "training": "Hard",
            "diet": "Omnivore",
            "average_litter_size": "7",
            "type": "Working",
            "common_name": "Ainu Dog",
            "slogan": "An innate sense of direction!",
            "group": "Dog",
            "color": "BrownGreyWhiteTan",
            "skin_type": "Hair"
        }
    },
    {
        "name": "Airedale Terrier",
        "taxonomy": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Carnivora",
            "family": "Canidae",
            "genus": "Canis",
            "scientific_name": "Canis Lupus"
        },
        "locations": [
            "Europe"
        ],
        "characteristics": {
            "distinctive_feature": "Long muzzle and square body",
            "temperament": "Friendly, adaptable and courageous",
            "training": "Fairly Easy",
            "diet": "Omnivore",
            "average_litter_size": "8",
            "type": "Terrier",
            "common_name": "Airedale Terrier",
            "slogan": "Very intelligent, independent and strong-minded!",
            "group": "Dog",
            "color": "BrownBlackTan",
            "skin_type": "Hair"
        }
    }]
            """;
}
