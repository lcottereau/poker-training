/*
 * Copyright (C) 2015 clark
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

import java.util.ArrayList;
import java.util.List;
import static lombok.AccessLevel.PRIVATE;
import lombok.AllArgsConstructor;

/**
 * A set of cards, representing the pocket of a player, as well as the communal
 * cards. It is immutable.
 */
@lombok.Value
@AllArgsConstructor(access = PRIVATE)
public class Hand {

    /**
     * A hand contains, at the most, the pocket (2 cards), the flop (3 cards),
     * the turn and the river.
     */
    private static final int MAX_SIZE = 7;

    private List<Card> cards;

    /**
     * Returns a new hand dealt with all the cards passed as arguments. It deals
     * them in order, in the Pocket, then the Flop, then the Turn and finally
     * the River. It will throw and {@link IndexOutOfBoundsException} if you try
     * to deal more.
     *
     * @param values the cards to be dealt for this hand.
     * @return the corresponding hand
     */
    public static Hand deal(Card... values) throws IndexOutOfBoundsException, IllegalArgumentException {
        List<Card> cards = new ArrayList<>(values.length);

        for (Card c : values) {
            if (cards.size() >= MAX_SIZE) {
                throw new IndexOutOfBoundsException(
                        "The hand is already fully dealt...");
            } else if (cards.contains(c)) {
                throw new IllegalArgumentException(c + " was already dealt...");
            } else {
                cards.add(c);
            }
        }
        
        return new Hand(cards);
    }

}
