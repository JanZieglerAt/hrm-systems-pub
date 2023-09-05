package ch.hrms.assessment.fullstackbackendchallenge.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Store of statistical data.
 */
@Component
@Slf4j
public class StatisticRepository {

    private final StatisticHolder addRequestStatistic = new StatisticHolder();
    private final StatisticHolder getRequestStatistic = new StatisticHolder();
    private final StatisticHolder updateRequestStatistic = new StatisticHolder();

    public void incrementValidRequestCount(String method) {
        getRequestStatistic(method).map(StatisticHolder::getValidRequestCounter).ifPresent(AtomicInteger::incrementAndGet);
    }
    public int getValidRequestCount(String method) {
        return getRequestStatistic(method).map(StatisticHolder::getValidRequestCounter).map(AtomicInteger::get).orElse(0);
    }
    public void addWrongRequestMessage(String method, String message){
        getRequestStatistic(method).map(StatisticHolder::getWrongRequestMessages).ifPresent(messages -> messages.add(message));
    }
    public List<String> getWrongRequestMessages(String method) {
        return getRequestStatistic(method).map(StatisticHolder::getWrongRequestMessages).map(List::copyOf).orElse(List.of());
    }

    private Optional<StatisticHolder> getRequestStatistic(String method){
        switch (method) {
            case "POST" -> {
                return Optional.of(addRequestStatistic);
            }
            case "GET" -> {
                return Optional.of(getRequestStatistic);
            }
            case "PUT" -> {
                return Optional.of(updateRequestStatistic);
            }
            default -> {
                log.warn("Unsupported method {}", method);
                return Optional.empty();
            }
        }
    }

    @Getter
    @Setter
    private static class StatisticHolder {
        private final AtomicInteger validRequestCounter = new AtomicInteger();
        private final List<String> wrongRequestMessages = new ArrayList<>();
    }
}
