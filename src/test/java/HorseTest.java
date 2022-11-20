import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    void whenFirstArgumentInConstructorIsNullThrowException() {

        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));

    }

    @Test
    void whenFirstArgumentNullDisplaysMessageNameCannotBeNull() {
        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));

        assertEquals("Name cannot be null.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t\t\t\t\t", "\n\n\n"})
    void whenFirstArgumentEmptyLineThrowException(String argument) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    ", "  ", "\t\t\t\t\t", "\n\n\n"})
    void whenFirstArgumentEmptyLineDisplaysAMessageNameCannotBeBlank(String argument) {
        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 1, 1));

        assertEquals("Name cannot be blank.", illegalArgumentException.getMessage());
    }

    @Test
    void whenSecondArgumentIsNegativeNumberThrowException() {

        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 1));

    }

    @Test
    void whenSecondArgumentIsNegativeNumberDisplaysAMessageSpeedCannotBeNegative() {
        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> new Horse("name", -10, 1));

        String expected = "Speed cannot be negative.";
        String actual = illegalArgumentException.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void whenThirdArgumentIsNegativeNumberThrowException() {

        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));

    }

    @Test
    void whenThirdArgumentIsNegativeNumberDisplaysAMessageDistanceCannotBeNegative() {
        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));

        String expected = "Distance cannot be negative.";
        String actual = illegalArgumentException.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void whenPassStringAsTheFirstArgumentMethodShouldReturnIt() {
        Horse horse = new Horse("Вишня", 1, 1);

        String expected = "Вишня";
        String actual = horse.getName();

        assertEquals(expected, actual);
    }

    @Test
    void whenPassNumberAsTheSecondArgumentMethodShouldReturnIt() {

        Horse horse = new Horse("name", 1, 1);

        double expected = 1;
        double actual = horse.getSpeed();

        assertEquals(expected, actual);
    }

    @Test
    void whenPassNumberAsTheThirdArgumentMethodShouldReturnIt() {

        Horse horse = new Horse("name", 1, 1);

        double expected = 1;
        double actual = horse.getDistance();

        assertEquals(expected, actual);
    }

    @Test
    void whenCreateObjectWithTwoArgumentsMethodShouldReturn0() {

        Horse horse = new Horse("name", 1);

        double expected = 0;
        double actual = horse.getDistance();

        assertEquals(expected, actual);

    }

    @Test
    void moveMethodCallsMethodGetRandomDoubleWithParameters02d09d() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 125, 369);
            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
    void moveMethodAssignsDistanceValueCalculatedByFormulaDistanceSpeedGetRandomDouble02d09d(double random) {
        try(MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 35,44);
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse.move();

            double expected = 44 + 35 * random;
            double actual = horse.getDistance();

            assertEquals(expected, actual);
        }
    }
}
