package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity @Table(name = "animalnames")
public class AnimalName extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    public String getName() {
        return name;
    }
}
