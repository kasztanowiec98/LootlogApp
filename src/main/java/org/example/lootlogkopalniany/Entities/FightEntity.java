package org.example.lootlogkopalniany.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Europe/Warsaw")
    private ZonedDateTime fightDate = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));

    private String userId; // Użytkownik, który rozpoczął walkę

    // Relacja do zdobytych przedmiotów
    @OneToMany(mappedBy = "fight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EqEntity> loot;

    // Relacja do graczy uczestniczących w walce
    @OneToMany(mappedBy = "fight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FightPlayerEntity> players;
}
