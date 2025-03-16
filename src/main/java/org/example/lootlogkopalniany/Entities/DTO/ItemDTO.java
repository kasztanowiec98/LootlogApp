package org.example.lootlogkopalniany.Entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private String itemname;
    private String itemnumber;
    private String rarity;
    private String icon;
}