package org.example.lootlogkopalniany.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EqEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "itemname is required")
    private String itemname;

    @NotEmpty(message = "itemnumber is required")
    private String itemnumber;

    @NotEmpty(message = "rariry is required")
    private String rarity;

    private String username;

    @NotEmpty(message = "ikona is required")
    private String ikona;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Europe/Warsaw")
    private ZonedDateTime insertDate = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
}
