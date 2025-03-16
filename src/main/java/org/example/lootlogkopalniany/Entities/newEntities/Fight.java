package org.example.lootlogkopalniany.Entities.newEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Fight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFight;

    @OneToMany(mappedBy = "fight")
    private List<ItemFightSum> itemFightSum;

    @OneToMany(mappedBy = "fight")
    private List<EnemyFightSum> enemyFightSum;

    @OneToMany(mappedBy = "fight")
    private List<PlayerFightSum> playerFightSum;
}
