package net.kiel.dnd.domain.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kiel.dnd.domain.Character;
import net.kiel.dnd.domain.Item;
import net.kiel.dnd.domain.Weapon;

import java.io.Serializable;


@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CharacterItemId implements Serializable {
    private Character character;

    private Item item;
}
