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

import java.util.List;
import lombok.NonNull;
import name.cottereau.laurent.games.poker.model.Value;

/**
 * Winning combination of cards.
 */
@lombok.Value
public class Rank implements Comparable<Rank>{
    
    public enum Type {
        HIGH_CARD, PAIR, TWO_PAIRS, THREE_OF_A_KIND, FOUR_OF_A_KIND
    };
    @NonNull private Type type;
    @NonNull private List<Value> specifics;

    @Override
    public int compareTo(Rank o) {
        if (type == o.getType()) {
            final int nbKickersCompared = Math.min(specifics.size(), o.getSpecifics().size());
            for (int i = 0; i < nbKickersCompared; i++) {
                int difference = specifics.get(i).compareTo(o.getSpecifics().get(i));
                if (difference != 0) {
                    return difference;
                }
            }
            return specifics.size() - o.getSpecifics().size();
        } else {
            return type.ordinal() - o.getType().ordinal();
        }
    }
    
}
