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

import static name.cottereau.laurent.games.poker.model.Hand.deal;
import static name.cottereau.laurent.games.poker.model.Suit.CLUBS;
import static name.cottereau.laurent.games.poker.model.Suit.HEARTS;
import static name.cottereau.laurent.games.poker.model.Suit.SPADES;
import static name.cottereau.laurent.games.poker.model.Value.FOUR;
import static name.cottereau.laurent.games.poker.model.Value.KING;
import static name.cottereau.laurent.games.poker.model.Value.QUEEN;
import static name.cottereau.laurent.games.poker.model.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import org.junit.Test;

/**
 * Test of {@link Hand}.
 */
public class HandTest {

    @Test
    public void deal_in_order() {
        Hand h = deal(KING.of(CLUBS), TWO.of(HEARTS));
        assertThat(h.getPocket()).hasSize(2).containsExactly(KING.of(CLUBS), TWO.of(HEARTS));
    }

    @Test
    public void cannot_overdeal() {
        try {
            deal(KING.of(CLUBS), TWO.of(HEARTS), FOUR.of(CLUBS));
            failBecauseExceptionWasNotThrown(IndexOutOfBoundsException.class);
        } catch (IndexOutOfBoundsException e) {
            assertThat(e).hasMessage("The hand is already fully dealt...");
        }
    }
    
    @Test
    public void cannot_deal_the_same_card_twice() {
        try {
            deal(QUEEN.of(SPADES), KING.of(CLUBS), KING.of(CLUBS));
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("K♣ was already dealt...");
        }
    }

}
