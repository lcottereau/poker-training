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
import static name.cottereau.laurent.games.poker.model.Rank.ACE;
import static name.cottereau.laurent.games.poker.model.Rank.EIGHT;
import static name.cottereau.laurent.games.poker.model.Rank.FIVE;
import static name.cottereau.laurent.games.poker.model.Rank.FOUR;
import static name.cottereau.laurent.games.poker.model.Rank.JACK;
import static name.cottereau.laurent.games.poker.model.Rank.KING;
import static name.cottereau.laurent.games.poker.model.Rank.NINE;
import static name.cottereau.laurent.games.poker.model.Rank.QUEEN;
import static name.cottereau.laurent.games.poker.model.Rank.SEVEN;
import static name.cottereau.laurent.games.poker.model.Rank.SIX;
import static name.cottereau.laurent.games.poker.model.Rank.TEN;
import static name.cottereau.laurent.games.poker.model.Rank.THREE;
import static name.cottereau.laurent.games.poker.model.Rank.TWO;
import static name.cottereau.laurent.games.poker.model.Suit.CLUBS;
import static name.cottereau.laurent.games.poker.model.Suit.DIAMONDS;
import static name.cottereau.laurent.games.poker.model.Suit.HEARTS;
import static name.cottereau.laurent.games.poker.model.Suit.SPADES;

/**
 * A card, with a suit and a value.
 */
@lombok.Value
public class Card implements Comparable {
    
    public static final Card _2s = TWO.of(SPADES);
    public static final Card _3s = THREE.of(SPADES);
    public static final Card _4s = FOUR.of(SPADES);
    public static final Card _5s = FIVE.of(SPADES);
    public static final Card _6s = SIX.of(SPADES);
    public static final Card _7s = SEVEN.of(SPADES);
    public static final Card _8s = EIGHT.of(SPADES);
    public static final Card _9s = NINE.of(SPADES);
    public static final Card _Ts = TEN.of(SPADES);
    public static final Card _Js = JACK.of(SPADES);
    public static final Card _Qs = QUEEN.of(SPADES);
    public static final Card _Ks = KING.of(SPADES);
    public static final Card _As = ACE.of(SPADES);
    
    public static final Card _2c = TWO.of(CLUBS);
    public static final Card _3c = THREE.of(CLUBS);
    public static final Card _4c = FOUR.of(CLUBS);
    public static final Card _5c = FIVE.of(CLUBS);
    public static final Card _6c = SIX.of(CLUBS);
    public static final Card _7c = SEVEN.of(CLUBS);
    public static final Card _8c = EIGHT.of(CLUBS);
    public static final Card _9c = NINE.of(CLUBS);
    public static final Card _Tc = TEN.of(CLUBS);
    public static final Card _Jc = JACK.of(CLUBS);
    public static final Card _Qc = QUEEN.of(CLUBS);
    public static final Card _Kc = KING.of(CLUBS);
    public static final Card _Ac = ACE.of(CLUBS);
    
    public static final Card _2h = TWO.of(HEARTS);
    public static final Card _3h = THREE.of(HEARTS);
    public static final Card _4h = FOUR.of(HEARTS);
    public static final Card _5h = FIVE.of(HEARTS);
    public static final Card _6h = SIX.of(HEARTS);
    public static final Card _7h = SEVEN.of(HEARTS);
    public static final Card _8h = EIGHT.of(HEARTS);
    public static final Card _9h = NINE.of(HEARTS);
    public static final Card _Th = TEN.of(HEARTS);
    public static final Card _Jh = JACK.of(HEARTS);
    public static final Card _Qh = QUEEN.of(HEARTS);
    public static final Card _Kh = KING.of(HEARTS);
    public static final Card _Ah = ACE.of(HEARTS);
    
    public static final Card _2d = TWO.of(DIAMONDS);
    public static final Card _3d = THREE.of(DIAMONDS);
    public static final Card _4d = FOUR.of(DIAMONDS);
    public static final Card _5d = FIVE.of(DIAMONDS);
    public static final Card _6d = SIX.of(DIAMONDS);
    public static final Card _7d = SEVEN.of(DIAMONDS);
    public static final Card _8d = EIGHT.of(DIAMONDS);
    public static final Card _9d = NINE.of(DIAMONDS);
    public static final Card _Td = TEN.of(DIAMONDS);
    public static final Card _Jd = JACK.of(DIAMONDS);
    public static final Card _Qd = QUEEN.of(DIAMONDS);
    public static final Card _Kd = KING.of(DIAMONDS);
    public static final Card _Ad = ACE.of(DIAMONDS);

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

}
