package org.example.lootlogkopalniany.Entities.newEntities;

import jakarta.persistence.*;

@Entity
public class ItemFightSum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idFight")
    private Fight fight;

    @ManyToOne
    @JoinColumn(name = "idItem")
    private Item item;
}
