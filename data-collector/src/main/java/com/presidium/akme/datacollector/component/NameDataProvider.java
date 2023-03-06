package com.presidium.akme.datacollector.component;

import com.presidium.akme.datacollector.exception.NoNameFoundException;
import com.presidium.akme.datacollector.model.people.NameData;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

@Slf4j
@Component
public class NameDataProvider {

    private static final String COMMA_SEPARATOR = ",";
    private final Map<String, Long> namesData = new HashMap<>();

    @Value("classpath:names_pesel.csv")
    private Resource namesFile;


    public NameData getByName(String name) {
        Optional<Long> nameCount = Optional.ofNullable(namesData.get(name));
        return new NameData(name, nameCount.orElseThrow(NoNameFoundException::new));
    }

    @PostConstruct
    private void loadDataFromFile() throws IOException {
        String contentAsString = namesFile.getContentAsString(Charset.defaultCharset());
        contentAsString.lines().forEach(line -> {
            String[] singleLineSplit = line.split(COMMA_SEPARATOR);
            namesData.put(singleLineSplit[0], Long.valueOf(singleLineSplit[2]));
        });
        log.info("Loaded {} distinct names", namesData.keySet().size());
    }
}
