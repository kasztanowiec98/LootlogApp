package org.example.lootlogkopalniany.RequestsClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
    private String itemname;
    private String icon;
    private String itemnumber;
    private String rarity;
}