package org.example.lootlogkopalniany.RequestsClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnemyRequest {
    private String enemyid;
    private String enemyname;
    private String icon;
}
