package org.example.lootlogkopalniany.RequestsClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveLootRequest {
    private String user;
    private String itemnumber;
    private String item;
    private String ikona;
    private String enemyid;
    private String enemyname;
    private String rarity;
}
