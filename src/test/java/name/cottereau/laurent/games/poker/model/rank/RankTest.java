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
package name.cottereau.laurent.games.poker.model.rank;

import java.util.Arrays;
import static name.cottereau.laurent.games.poker.model.Value.JACK;
import static name.cottereau.laurent.games.poker.model.Value.KING;
import static name.cottereau.laurent.games.poker.model.Value.QUEEN;
import static name.cottereau.laurent.games.poker.model.Value.TWO;
import static name.cottereau.laurent.games.poker.model.rank.Rank.Type.HIGH_CARD;
import static name.cottereau.laurent.games.poker.model.rank.Rank.Type.PAIR;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 * Test de {@link Rank}.
 */
public class RankTest {
    
    @Test
    public void order_between_pair_and_high_card() {
        Rank pairOf2ByKing = new Rank(PAIR, Arrays.asList(TWO, KING, QUEEN));
        Rank KingHigh = new Rank(HIGH_CARD, Arrays.asList(KING, QUEEN));
        assertThat(pairOf2ByKing).isGreaterThan(KingHigh);
    }
    
    @Test
    public void kickers_determine_higher_between_pairs() {
        Rank pairOf2ByQueen = new Rank(PAIR, Arrays.asList(TWO, QUEEN, JACK));
        Rank pairOf2ByKing = new Rank(PAIR, Arrays.asList(TWO, KING));
        assertThat(pairOf2ByQueen).isLessThan(pairOf2ByKing);
    }
    
    @Test
    public void number_of_kickers_determine_higher_if_all_else_equal() {
        Rank pairOf2ByKing2Kickers = new Rank(PAIR, Arrays.asList(TWO, KING,
                JACK));
        Rank pairOf2ByKing1Kicker = new Rank(PAIR, Arrays.asList(TWO, KING));
        assertThat(pairOf2ByKing2Kickers).isGreaterThan(pairOf2ByKing1Kicker);
    }
    
    @Test
    public void equality_when_everything_the_same() {
        Rank pairOf2ByKing2Kickers = new Rank(PAIR, Arrays.asList(TWO, KING,
                JACK));
        assertThat(pairOf2ByKing2Kickers.compareTo(pairOf2ByKing2Kickers)).
                isEqualTo(0);
    }
    
}
