package org.example.lootlogkopalniany;

import org.example.lootlogkopalniany.Entities.DTO.EnemyDTO;
import org.example.lootlogkopalniany.Entities.DTO.ItemDTO;
import org.example.lootlogkopalniany.Entities.DTO.PlayerDTO;
import org.example.lootlogkopalniany.Entities.EnemyEntity;
import org.example.lootlogkopalniany.Entities.EqEntity;
import org.example.lootlogkopalniany.Entities.FightPlayerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LootMapper {

    // Mapowanie PlayerDTO na FightPlayerEntity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "playername", source = "playerDTO.playername")
    @Mapping(target = "prof", source = "playerDTO.prof")
    @Mapping(target = "lvl", source = "playerDTO.lvl")
    @Mapping(target = "icon", source = "playerDTO.icon")
    FightPlayerEntity toFightPlayerEntity(PlayerDTO playerDTO);

    // Mapowanie ItemDTO na EqEntity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itemname", source = "itemDTO.itemname")
    @Mapping(target = "itemnumber", source = "itemDTO.itemnumber")
    @Mapping(target = "rarity", source = "itemDTO.rarity")
    @Mapping(target = "ikona", source = "itemDTO.icon")
    EqEntity toEqEntity(ItemDTO itemDTO);

    // Mapowanie EnemyDTO na EnemyEntity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enemyname", source = "enemyDTO.enemyname")
    @Mapping(target = "enemyid", source = "enemyDTO.enemyid")
    @Mapping(target = "icon", source = "enemyDTO.icon")
    EnemyEntity toEnemyEntity(EnemyDTO enemyDTO);

    List<FightPlayerEntity> toFightPlayerEntities(List<PlayerDTO> players);
    List<EqEntity> toEqEntities(List<ItemDTO> items);
    List<EnemyEntity> toEnemyEntities(List<EnemyDTO> enemies);
}

