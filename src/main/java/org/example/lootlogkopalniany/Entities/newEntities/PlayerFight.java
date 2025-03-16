package org.example.lootlogkopalniany.Entities.newEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlayerFight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlayer;

    private String playername;
    private String playerid;
    private String charid;
}
