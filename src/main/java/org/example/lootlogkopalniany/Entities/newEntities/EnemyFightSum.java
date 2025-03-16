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
public class EnemyFightSum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idFight")
    private Fight fight;

    @ManyToOne
    @JoinColumn(name = "idEnemy")
    private Enemy enemy;
}
