package org.example.lootlogkopalniany.Entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private String playername;
    private String prof;
    private Integer lvl;
    private String icon;
}
