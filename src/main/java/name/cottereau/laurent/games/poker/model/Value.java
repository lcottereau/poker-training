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
import lombok.NonNull;
import static name.cottereau.laurent.games.poker.model.Suit.values;

/**
 * The value of a card.
 */
@AllArgsConstructor(access = PRIVATE)
public enum Value {

    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
    JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

    private final String label;

    public Card of(@NonNull Suit suit) {
        return new Card(suit, this);
    }

    @Override
    public String toString() {
        return label;
    }

    /**
     * Returns a random value, with evenly distributed chances. It is actually
     * pseudo-random.
     *
     * @TODO refactor with the same {@link Suit#randomSuit() }.
     * @return a random value
     */
    public static Value randomValue() {
        return values()[(int) (random() * values().length)];
    }

}
