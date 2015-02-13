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

import static name.cottereau.laurent.games.poker.model.Card.deal;
import static name.cottereau.laurent.games.poker.model.Suit.CLUBS;
import static name.cottereau.laurent.games.poker.model.Suit.HEARTS;
import static name.cottereau.laurent.games.poker.model.Suit.SPADES;
import static name.cottereau.laurent.games.poker.model.Rank.ACE;
import static name.cottereau.laurent.games.poker.model.Rank.KING;
import static name.cottereau.laurent.games.poker.model.Rank.QUEEN;
import static name.cottereau.laurent.games.poker.model.Rank.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import org.junit.Test;

/**
 * Test de {@link Card}.
 *
 */
public class CardTest {

    @Test
    public void cannot_compare_card_with_non_card() {
        try {
            ACE.of(CLUBS).compareTo(new Object());
            failBecauseExceptionWasNotThrown(ClassCastException.class);
        } catch (ClassCastException e) {
            assertThat(e).hasMessage("You can't compare Cards to something else...");
        }
    }
    
    @Test
    public void same_value_different_suit_is_equal() {
        assertThat(KING.of(HEARTS).compareTo(KING.of(CLUBS))).isZero();
    }
    
    @Test
    public void compare_to_gives_difference_of_values() {
        assertThat(QUEEN.of(SPADES).compareTo(TEN.of(HEARTS))).isEqualTo(-2);
    }
    
    @Test
    public void nice_format_for_strings() {
        assertThat(KING.of(CLUBS).toString()).isEqualTo("K♣");
        assertThat(TEN.of(HEARTS).toString()).isEqualTo("10♥");
    }
    
    @Test
    public void deal_gives_a_non_null_card() {
        Card c = deal();
        assertThat(c.getSuit()).isNotNull();
        assertThat(c.getValue()).isNotNull();
    }

}
