/*
 * Copyright (C) 2015 Laurent Cottereau
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package name.cottereau.laurent.games.poker.model;

import static name.cottereau.laurent.games.poker.model.Suit.HEARTS;
import static name.cottereau.laurent.games.poker.model.Suit.SPADES;
import static name.cottereau.laurent.games.poker.model.Value.KING;
import static name.cottereau.laurent.games.poker.model.Value.THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import org.junit.Test;

/**
 * Test de {@link Suit}.
 */
public class SuitTest {

    @Test
    public void value_cannot_be_null() {
        try {
            THREE.of(null);
            failBecauseExceptionWasNotThrown(NullPointerException.class);
        } catch (NullPointerException e) {
            assertThat(e).hasMessage("suit");
        }
    }

    @Test
    public void nice_creation_of_card() {
        Card c = KING.of(HEARTS);
        assertThat(c.getValue()).isEqualTo(KING);
        assertThat(c.getSuit()).isEqualTo(HEARTS);
    }
    
    @Test
    public void to_string_with_icon() {
        assertThat(SPADES.toString()).isEqualTo("♠");
    }

}
