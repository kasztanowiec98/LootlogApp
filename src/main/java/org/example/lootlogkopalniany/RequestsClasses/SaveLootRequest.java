package org.example.lootlogkopalniany.RequestsClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveLootRequest {
    private String user;
    private List<PlayerDTO> players;
    private List<ItemDTO> items;
    private List<EnemyDTO> enemies;

}
