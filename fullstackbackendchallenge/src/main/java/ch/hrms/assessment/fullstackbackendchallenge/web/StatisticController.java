package ch.hrms.assessment.fullstackbackendchallenge.web;

import ch.hrms.assessment.fullstackbackendchallenge.api.Statistics;
import ch.hrms.assessment.fullstackbackendchallenge.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
@Slf4j
public class StatisticController {

    private final StatisticService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Statistics> get() {

        log.info("Get statistics");
        return ResponseEntity.ok(service.getStatistics());
    }
}
