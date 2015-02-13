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

import static name.cottereau.laurent.games.poker.model.Card._2h;
import static name.cottereau.laurent.games.poker.model.Card._2s;
import static name.cottereau.laurent.games.poker.model.Card._4c;
import static name.cottereau.laurent.games.poker.model.Card._4h;
import static name.cottereau.laurent.games.poker.model.Card._4s;
import static name.cottereau.laurent.games.poker.model.Card._Ac;
import static name.cottereau.laurent.games.poker.model.Card._Kc;
import static name.cottereau.laurent.games.poker.model.Card._Qh;
import static name.cottereau.laurent.games.poker.model.Card._Qs;
import static name.cottereau.laurent.games.poker.model.Card._Ts;
import static name.cottereau.laurent.games.poker.model.Hand.deal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import org.junit.Test;

/**
 * Test of {@link Hand}.
 */
public class HandTest {

    @Test
    public void deal_in_order() {
        Hand h = deal(_Kc, _2h, _4c, _4h, _4s, _2s, _Ac);
        assertThat(h.getCards()).hasSize(7).containsExactly(_Kc, _2h, _4c, _4h,
                _4s, _2s, _Ac);
    }

    @Test
    public void cannot_overdeal() {
        try {
            deal(_Kc, _2h, _4c, _4h, _4s, _Ts, _Ac, _Qh);
            failBecauseExceptionWasNotThrown(IndexOutOfBoundsException.class);
        } catch (IndexOutOfBoundsException e) {
            assertThat(e).hasMessage("The hand is already fully dealt...");
        }
    }

    @Test
    public void cannot_deal_the_same_card_twice() {
        try {
            deal(_Qs, _Kc, _Qs);
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("Q♠ was already dealt...");
        }
    }
}
