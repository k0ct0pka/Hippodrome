import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Mock
    MockedStatic<Horse> horseMockedStatic;


    @Test
    void constructor_First_Param_NULL() {
        //given
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0));
    }

    @Test
    void constructor_Exception_Message() {
        //given
        //when
        //then
        try {
            new Horse(null, 0);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "     "})
    void constructor_Empty_String(String input) {
        //given
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(input, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "     "})
    void constructor_Empty_String_Message(String s) {
        //given
        //when
        //then
        try {
            new Horse(s, 0);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Name cannot be blank.", e.getMessage());
        }

    }

    @Test
    void constructor_Second_param_Less_Zero() {
        //given
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -2));
    }

    @Test
    void constructor_Second_param_Less_Zero_Message() {
        //given
        //when
        //then
        try {
            new Horse("horse2", -3);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    void constructor_Third_param_Less_Zero() {
        //given
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 1, -2));
    }

    @Test
    void constructor_Third_param_Less_Zero_Message() {
        //given
        //when
        //then
        try {
            new Horse("horse2", 1, -2);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    void getName_Return() {
        //given
        String name = "horse";
        Horse horse = new Horse(name, 1, 1);
        //when
        //then
        Assertions.assertEquals(name, horse.getName());
    }

    @Test
    void getSpeed_Return() {
        //given
        double speed = 0;
        Horse horse = new Horse("horse", speed);
        //when
        //then
        Assertions.assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistance_Return() {
        //given
        double distance = 1;
        Horse horse = new Horse("horse", 0, distance);
        //when
        //then
        Assertions.assertEquals(distance, horse.getDistance());
    }

    @Test
    void getDistance_Default_Return() {
        //given
        Horse horse = new Horse("horse", 1);
        //when
        //then
        Assertions.assertEquals(0, horse.getDistance());
    }

    @Test
    void move_GetRandom_Verify() {
        //given
        Horse horse = new Horse("horse", 1, 1);
        horse.move();
        //when
        //then
        horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), Mockito.times(1));
    }

    @Test
    void move_Correct_Value() {
        //given
        double distance = 1;
        double speed = 1;
        Horse horse = new Horse("horse", speed, distance);
        //when
        horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
        //then
        horse.move();
        Assertions.assertEquals(distance + speed * Horse.getRandomDouble(0.2, 0.9), horse.getDistance());
    }
}
