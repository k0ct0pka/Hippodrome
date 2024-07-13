import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    @Test
    void constructor_Null_Value() {
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void constructor_Null_Message() {
        //given
        //when
        //then
        try {
            new Hippodrome(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }

    }

    @Test
    void constructor_Empty_List() {
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void constructor_Empty_List_Message() {
        //given
        //when
        //then
        try {
            new Hippodrome(new ArrayList<>());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    void getHorses() {
        //given
        List<Horse> horses = new ArrayList<>();
        //when
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        //then
        for (int i = 0; i < horses.size(); i++) {
            assertEquals(horses.get(i), hippodrome.getHorses().get(i));
        }

    }

    @Test
    void move() {
        //given
        List<Horse> horses = new ArrayList<>();
        //when
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock();
            horses.add(horse);
        }
        new Hippodrome(horses).move();
        //then
        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner() {
        //given
        List<Horse> horses = List.of(new Horse("horse1", 1, 1), new Horse("horse2", 3, 3), new Horse("horse3", 2, 2));
        //when
        //then
        assertEquals(3, new Hippodrome(horses).getWinner().getDistance());
    }
}
