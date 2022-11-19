package sk.mesko;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class AddNewUserTest {

    @ParameterizedTest
    @CsvSource(value = {"1,a1,Robert,1"})
    void whenSetnewUserThenGetCorrectUser(int id, String guid, String name,int expectedResult)
    {
        AddNewUser add = new AddNewUser(id, guid, name);
        int actuallId = add.userId;
        String actualName = add.userName;
        String actualGuid = add.userGuid;
        assertThat(actuallId).isEqualTo(expectedResult);
        assertEquals("Robert", actualName);
        assertThat(actualGuid).isEqualTo("a1");
    }

}