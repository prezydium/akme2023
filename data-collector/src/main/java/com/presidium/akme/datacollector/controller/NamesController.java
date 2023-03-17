package com.presidium.akme.datacollector.controller;

import com.presidium.akme.datacollector.component.NameDataProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/name")
@RequiredArgsConstructor
public class NamesController {

    private final NameDataProvider nameDataProvider;

    @GetMapping("/{name}")
    public Long getCountOfName(@PathVariable String name) {
        log.info("Fetching data for name {}", name);
        return nameDataProvider.getByName(name).count();
    }

}
