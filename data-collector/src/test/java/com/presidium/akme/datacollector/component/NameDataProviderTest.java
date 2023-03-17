package com.presidium.akme.datacollector.component;

import com.presidium.akme.datacollector.exception.NoNameFoundException;
import com.presidium.akme.datacollector.model.people.NameData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class NameDataProviderTest {

    @Autowired
    private NameDataProvider underTest;

    @Test
    void shouldLoadSuccessfully() {
        NameData janCapitalLettersCount = underTest.getByName("JAN");
        assertThat(janCapitalLettersCount.count()).isEqualTo(1089394);

        NameData janNotCapitalLettersCount = underTest.getByName("jan");
        assertThat(janNotCapitalLettersCount.count()).isEqualTo(1089394);
    }

    @Test
    void shouldThrowExceptionOnNoNameFound() {
        assertThatThrownBy(
                () -> underTest.getByName("KACZKA")
        ).isInstanceOf(NoNameFoundException.class);
    }
}