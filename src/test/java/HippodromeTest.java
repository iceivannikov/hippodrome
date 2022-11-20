import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void whenPassingToConstructorNullThrowException() {

        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

    }

    @Test
    void whenPassingToConstructorNullDisplaysMessageHorsesCannotBeNull() {

        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", illegalArgumentException.getMessage());

    }

    @Test
    void whenPassingToConstructorEmptyListThrowException() {

        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

    }

    @Test
    void whenPassingToConstructorEmptyListDisplaysMessageHorsesCannotBeEmpty() {

        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty.", illegalArgumentException.getMessage());

    }

    @Test
    void whenPass30HorsesInConstructorTestReturnsSameObjectsAndInSameSequence() {

        int countHorses = 30;
        List<Horse> expected = new ArrayList<>();
        for (int i = 0; i < countHorses; i++) {
            expected.add(new Horse("name" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(expected);

        List<Horse> actual = hippodrome.getHorses();

        assertEquals(expected, actual);
    }

    @Test
    void whenCallMoveMethodItCalledForAllHorsesPassedToHippodromeConstructor() {

        int countHorses = 50;

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < countHorses; i++) {
            Horse mock = mock(Horse.class);
            horses.add(mock);
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void testReturnsHorseWithLargestDistanceValue () {

        Horse horse1 = new Horse("name1", 1, 1);
        Horse horse2 = new Horse("name1", 1, 2);
        Horse horse3 = new Horse("name1", 1, 3);
        Horse horse4 = new Horse("name1", 1, 4);
        Horse horse5 = new Horse("name1", 1, 5);

        List<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        horses.add(horse4);
        horses.add(horse5);

        Hippodrome hippodrome = new Hippodrome(horses);

        Horse actual = hippodrome.getWinner();

        assertSame(horse5, actual);

    }

}