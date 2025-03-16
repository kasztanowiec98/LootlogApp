package org.example.lootlogkopalniany.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class FightPlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "playername is required")
    private String playername;

    @NotEmpty(message = "prof is required")
    private String prof;

    @NotNull(message = "lvl is required")
    private Integer lvl;

    @NotEmpty(message = "icon is required")
    private String icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fight_id")
    private FightEntity fight;
}

