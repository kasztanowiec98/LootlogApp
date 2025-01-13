package org.example.lootlogkopalniany.RequestsClasses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {

    private String userid;
    private String newuserid;
}
