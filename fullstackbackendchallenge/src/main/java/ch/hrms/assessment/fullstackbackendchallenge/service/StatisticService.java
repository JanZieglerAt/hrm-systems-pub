package ch.hrms.assessment.fullstackbackendchallenge.service;

import ch.hrms.assessment.fullstackbackendchallenge.api.Statistic;
import ch.hrms.assessment.fullstackbackendchallenge.api.Statistics;
import ch.hrms.assessment.fullstackbackendchallenge.repository.PersonRepository;
import ch.hrms.assessment.fullstackbackendchallenge.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {

    private final StatisticRepository repository;
    private final PersonRepository personRepository;

    public void incrementValidRequestCount(String method){
        repository.incrementValidRequestCount(method);
    }

    public void addWrongRequestMessage(String method, String msg){
        repository.addWrongRequestMessage(method, msg);
    }

    public Statistics getStatistics(){

        Statistics result = new Statistics();
        result.setAddRequestStatistic(createStatistic("POST"));
        result.setGetRequestStatistic(createStatistic("GET"));
        result.setUpdateRequestStatistic(createStatistic("PUT"));
        result.setPersonCount(personRepository.count());

        return result;
    }

    private Statistic createStatistic(String method){

        Statistic stat = new Statistic();

        stat.setValidRequestCount(repository.getValidRequestCount(method));
        Map<String, Long> messages = repository.getWrongRequestMessages(method).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        stat.getWrongRequestMessages().putAll(messages);

        return stat;
    }
}
