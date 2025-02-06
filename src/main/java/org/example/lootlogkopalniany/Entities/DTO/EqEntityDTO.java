package org.example.lootlogkopalniany.Entities.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EqEntityDTO {
    private String itemname;
    private String rarity;
    private String itemnumber;
    private String username;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Europe/Warsaw")
    private ZonedDateTime insertDate = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
}
