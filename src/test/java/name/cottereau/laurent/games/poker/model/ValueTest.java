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

import static name.cottereau.laurent.games.poker.model.Value.ACE;
import static name.cottereau.laurent.games.poker.model.Value.TEN;
import static name.cottereau.laurent.games.poker.model.Value.THREE;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 * Test of {@link Value}.
 */
public class ValueTest {
    
    @Test
    public void formatted_value() {
        assertThat(ACE.toString()).isEqualTo("A");
        assertThat(TEN.toString()).isEqualTo("10");
        assertThat(THREE.toString()).isEqualTo("3");
    }
}
