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

import lombok.NonNull;
import static name.cottereau.laurent.games.poker.model.Suit.randomSuit;
import static name.cottereau.laurent.games.poker.model.Rank.randomRank;

/**
 * A card, with a suit and a value.
 */
@lombok.Value
public class Card implements Comparable {

    @NonNull private Suit suit;
    @NonNull private Rank value;
    
    @Override
    public String toString() {
        return value.toString() + suit.toString();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Card) {
            return ((Card)o).getValue().compareTo(value);
        } else {
            throw new ClassCastException("You can't compare Cards to something else...");
        }
    }
    
    /**
     * Gives a random valid card.
     * @return a random valid card.
     */
    public static Card deal() {
        return new Card(randomSuit(), randomRank());
    }

}
