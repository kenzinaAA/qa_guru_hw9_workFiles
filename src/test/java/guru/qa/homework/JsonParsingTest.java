package guru.qa.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.PersonData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class JsonParsingTest {
    private final ClassLoader cl = JsonParsingTest.class.getClassLoader();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @DisplayName("Проверка содержимого json файла")
    @Test
    void jsonParsingTest() throws Exception {
        try (InputStream inputStream = cl.getResourceAsStream("personData.json")){
            Assertions.assertNotNull(inputStream, "Файл не найден");

            PersonData[] personData = objectMapper.readValue(inputStream, PersonData[].class);

            Assertions.assertEquals("John Smith",personData[0].getFullName());
            Assertions.assertEquals(32,personData[0].getAge());
            Assertions.assertEquals("Height: 180 cm, Hair: Brown, Eyes: Blue",personData[0].getAppearance());
            Assertions.assertEquals("Photography",personData[0].getHobbies());
            Assertions.assertEquals(true,personData[0].getInsurance());

            Assertions.assertEquals("Phone: +1 (555) 987-6543, Email: emily.j@example.com",personData[1].getContacts());
            Assertions.assertEquals("Marketing Manager at AdAgency",personData[1].getWork());

            Assertions.assertEquals("Michael Brown",personData[2].getFullName());
            Assertions.assertEquals("Height: 175 cm, Hair: Black, Eyes: Brown",personData[2].getAppearance());
            Assertions.assertEquals("Chess",personData[2].getHobbies());
            Assertions.assertEquals(false,personData[2].getInsurance());



        }

    }
}
