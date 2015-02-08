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

import java.util.SortedSet;
import java.util.TreeSet;
import lombok.Data;

/**
 * A set of cards, representing the pocket of a player, as well as the communal
 * cards.
 */
@Data
public class Hand {

    private static final int POCKET_MAX_SIZE = 2;

    /**
     * @TODO should we keep this a set (with the overcost of it), since we
     * already check the unicty at {@link #deal()} ?
     */
    private SortedSet<Card> pocket = new TreeSet<>();

    private Hand deal(Card c) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (pocket.contains(c)) {
            throw new IllegalArgumentException(c + " was already dealt...");
        }
        if (pocket.size() < POCKET_MAX_SIZE) {
            pocket.add(c);
        } else {
            throw new IndexOutOfBoundsException("The hand is already fully dealt...");
        }
        return this;
    }

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
        Hand h = new Hand();
        for (Card c : values) {
            h.deal(c);
        }
        return h;
    }

}
