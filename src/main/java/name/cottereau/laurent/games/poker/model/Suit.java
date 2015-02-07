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

import static java.lang.Math.random;
import static lombok.AccessLevel.PRIVATE;
import lombok.AllArgsConstructor;

/**
 * Suit of a card
 */
@AllArgsConstructor(access = PRIVATE)
public enum Suit {

    CLUBS("♣"), DIAMONDS("♦"), HEARTS("♥"), SPADES("♠");

    private final String label;

    @Override
    public String toString() {
        return label;
    }

    /**
     * Returns a random suit, with evenly distributed chances. It is actually
     * pseudo-random.
     *
     * @TODO refactor with the same {@link Value#randomValue()  }.
     * @return a random suit
     */
    public static Suit randomSuit() {
        return values()[(int) (random() * values().length)];
    }
}
