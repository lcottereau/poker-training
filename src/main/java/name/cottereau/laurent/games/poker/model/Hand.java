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
import java.util.Collections;
import java.util.List;
import static lombok.AccessLevel.PRIVATE;
import lombok.AllArgsConstructor;
import name.cottereau.laurent.games.poker.model.rank.Rank;
import static name.cottereau.laurent.games.poker.model.rank.Rank.Type.HIGH_CARD;
import static name.cottereau.laurent.games.poker.model.rank.Rank.Type.PAIR;
import static name.cottereau.laurent.games.poker.model.rank.Rank.Type.THREE_OF_A_KIND;
import static name.cottereau.laurent.games.poker.model.rank.Rank.Type.TWO_PAIRS;

/**
 * A set of cards, representing the pocket of a player, as well as the communal
 * cards. It is immutable.
 */
@lombok.Value
@AllArgsConstructor(access = PRIVATE)
public class Hand implements Comparable<Hand> {

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

    @Override
    public int compareTo(Hand o) {
        return getRank().compareTo(o.getRank());
    }

    /**
     * Returns the rank for this hand.
     *
     * @return the rank for this hand.
     */
    public Rank getRank() {
        final Value[] allValues = Value.values();
        List<Integer> handValues = new ArrayList<>(
                Collections.nCopies(allValues.length, 0));

        for (Card c : cards) {
            final int index = allValues.length - c.getValue().ordinal() - 1;
            if (handValues.get(index) == null) {
                handValues.set(index, 1);
            } else {
                handValues.set(index, handValues.get(index) + 1);
            }
        }

        if (handValues.contains(3)) {
            List<Value> specifics = new ArrayList<>(1);
            specifics.add(allValues[allValues.length - handValues.indexOf(3) - 1]);
            return new Rank(THREE_OF_A_KIND, specifics);
        } else if (handValues.contains(2)) {
            List<Value> specifics = new ArrayList<>(4);
            final int indexOfHighPair = handValues.indexOf(2);
            specifics.add(allValues[allValues.length - indexOfHighPair - 1]);
            Rank.Type type;
            int nbKickers;
            if (handValues.subList(indexOfHighPair + 1, handValues.size()).contains(
                    2)) {
                type = TWO_PAIRS;
                nbKickers = 1;
                int indexOfLowPair = handValues.subList(indexOfHighPair + 1,
                        handValues.size()).indexOf(2);
                specifics.add(
                        allValues[allValues.length - indexOfHighPair - 1 - indexOfLowPair - 1]);
            } else {
                type = PAIR;
                nbKickers = 3;
            }
            specifics.addAll(getKickers(handValues, nbKickers));
            return new Rank(type, specifics);

        } else if (handValues.contains(1)) {
            return new Rank(HIGH_CARD, getKickers(handValues, 4));
        } else {
            return null;
        }
    }

    /**
     * Détermine les kickers à partir des occurences des valeurs des cartes. Ce
     * sont les valeurs qui sont présentes en 1 exemplaire exactement, par ordre
     * inversehandValuesde rang.
     *
     * @param nbValues les occurences des cartes dans la main.
     * @return la liste des kickers.
     */
    private List<Value> getKickers(List<Integer> handValues, int max) {
        List<Value> kickers = new ArrayList<>(max);
        int i = handValues.indexOf(1);
        int j = 0;
        while (i > -1 && kickers.size() < max) {
            kickers.add(Value.values()[Value.values().length - j - i - 1]);
            j = i + j + 1;
            i = handValues.subList(j, handValues.size()).indexOf(1);
        }
        return kickers;
    }

}
