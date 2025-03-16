package org.example.lootlogkopalniany.Entities.newEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegisteredPlayer {
    @Id
    private String idAccount;

    @ManyToOne
    @JoinColumn(name = "Accounts_idAccounts")
    private Accounts account;
}
