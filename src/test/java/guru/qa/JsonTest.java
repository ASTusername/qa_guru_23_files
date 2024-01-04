package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.Squad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;

public class JsonTest {
    private ClassLoader cl = JsonTest.class.getClassLoader();
    ObjectMapper mapper = new ObjectMapper();

    @DisplayName("проверка JSON")
    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("my_json.json")
        )) {
            Squad actual = mapper.readValue(reader, Squad.class);

            Assertions.assertEquals("Super hero squad", actual.getSquadName());
            Assertions.assertEquals("Metro City", actual.getHomeTown());
            Assertions.assertEquals(2016, actual.getFormed());
            Assertions.assertEquals("Super tower", actual.getSecretBase());
            Assertions.assertTrue(true, "Статус должен совпадать");
            Assertions.assertEquals("Radiation resistance", actual.getMembers()[0].getPowers()[0], "Данные первого участника");
            Assertions.assertArrayEquals(new String[]{"Million tonne punch", "Damage resistance", "Superhuman reflexes"},
                    actual.getMembers()[1].getPowers(), "Данные второго участника");
        }
    }
}
