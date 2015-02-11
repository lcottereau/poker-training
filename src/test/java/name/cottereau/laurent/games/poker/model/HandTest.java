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
import static name.cottereau.laurent.games.poker.model.Suit.DIAMONDS;
import static name.cottereau.laurent.games.poker.model.Suit.HEARTS;
import static name.cottereau.laurent.games.poker.model.Suit.SPADES;
import static name.cottereau.laurent.games.poker.model.Value.ACE;
import static name.cottereau.laurent.games.poker.model.Value.FOUR;
import static name.cottereau.laurent.games.poker.model.Value.JACK;
import static name.cottereau.laurent.games.poker.model.Value.KING;
import static name.cottereau.laurent.games.poker.model.Value.QUEEN;
import static name.cottereau.laurent.games.poker.model.Value.TEN;
import static name.cottereau.laurent.games.poker.model.Value.THREE;
import static name.cottereau.laurent.games.poker.model.Value.TWO;
import static name.cottereau.laurent.games.poker.model.rank.Rank.Type.HIGH_CARD;
import static name.cottereau.laurent.games.poker.model.rank.Rank.Type.PAIR;
import static name.cottereau.laurent.games.poker.model.rank.Rank.Type.THREE_OF_A_KIND;
import static name.cottereau.laurent.games.poker.model.rank.Rank.Type.TWO_PAIRS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import org.junit.Test;

/**
 * Test of {@link Hand}.
 */
public class HandTest {

    @Test
    public void deal_in_order() {
        Hand h = deal(KING.of(CLUBS), TWO.of(HEARTS), FOUR.of(CLUBS), FOUR.of(
                HEARTS), FOUR.of(SPADES), TWO.of(SPADES), ACE.of(CLUBS));
        assertThat(h.getCards()).hasSize(7).containsExactly(KING.of(CLUBS),
                TWO.of(HEARTS), FOUR.of(CLUBS), FOUR.of(HEARTS), FOUR.of(SPADES),
                TWO.of(SPADES), ACE.of(CLUBS));
    }

    @Test
    public void cannot_overdeal() {
        try {
            deal(KING.of(CLUBS), TWO.of(HEARTS), FOUR.of(CLUBS), FOUR.of(HEARTS),
                    FOUR.of(SPADES), TEN.of(SPADES), ACE.of(CLUBS),
                    QUEEN.of(HEARTS));
            failBecauseExceptionWasNotThrown(IndexOutOfBoundsException.class);
        } catch (IndexOutOfBoundsException e) {
            assertThat(e).hasMessage("The hand is already fully dealt...");
        }
    }

    @Test
    public void cannot_deal_the_same_card_twice() {
        try {
            deal(QUEEN.of(SPADES), KING.of(CLUBS), QUEEN.of(SPADES));
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("Q♠ was already dealt...");
        }
    }

    @Test
    public void rank_of_pair_with_kickers() {
        Hand h = deal(TWO.of(CLUBS), KING.of(HEARTS), TWO.of(HEARTS), JACK.of(
                CLUBS));
        assertThat(h.getRank().getType()).isEqualTo(PAIR);
        assertThat(h.getRank().getSpecifics()).containsExactly(TWO, KING, JACK);
    }

    @Test
    public void rank_of_two_pairs() {
        Hand h = deal(TWO.of(CLUBS), KING.of(HEARTS), TWO.of(HEARTS), KING.of(
                CLUBS), THREE.of(SPADES));
        assertThat(h.getRank().getType()).isEqualTo(TWO_PAIRS);
        assertThat(h.getRank().getSpecifics()).containsExactly(KING, TWO, THREE);
    }

    @Test
    public void rank_of_two_pairs_without_kicker() {
        Hand h = deal(TWO.of(CLUBS), KING.of(HEARTS), TWO.of(HEARTS), KING.of(
                CLUBS));
        assertThat(h.getRank().getType()).isEqualTo(TWO_PAIRS);
        assertThat(h.getRank().getSpecifics()).containsExactly(KING, TWO);
    }

    /**
     * @TODO I need to determine if there should be a second KING as kicker.
     */
    @Test
    public void rank_of_high_card() {
        Hand h = deal(KING.of(CLUBS), QUEEN.of(CLUBS), ACE.of(
                SPADES));
        assertThat(h.getRank().getType()).isEqualTo(HIGH_CARD);
        assertThat(h.getRank().getSpecifics()).containsExactly(ACE, KING, QUEEN);
    }

    /**
     * No kicker as there cannot be two equal {@link THREE_OF_A_KIND}.
     */
    @Test
    public void rank_of_three_of_a_kind() {
        Hand h = deal(TWO.of(CLUBS), KING.of(HEARTS), TWO.of(HEARTS),
                QUEEN.of(CLUBS), QUEEN.of(SPADES), KING.of(DIAMONDS),
                KING.of(SPADES));
        assertThat(h.getRank().getType()).isEqualTo(THREE_OF_A_KIND);
        assertThat(h.getRank().getSpecifics()).containsExactly(KING);
    }

    @Test
    public void comparing_hands_with_rank() {
        Hand h1 = deal(KING.of(CLUBS), QUEEN.of(CLUBS), KING.of(HEARTS), ACE.of(
                SPADES));
        Hand h2 = deal(JACK.of(CLUBS), QUEEN.of(CLUBS), KING.of(HEARTS), TEN.of(
                SPADES));
        assertThat(h1).isGreaterThan(h2);
    }

}
