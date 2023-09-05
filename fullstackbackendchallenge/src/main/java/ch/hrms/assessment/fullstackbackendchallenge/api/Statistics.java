package ch.hrms.assessment.fullstackbackendchallenge.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"addRequestStatistic", "getRequestStatistic","updateRequestStatistic", "personCount"})
public class Statistics {
    private Statistic addRequestStatistic;
    private Statistic getRequestStatistic;
    private Statistic updateRequestStatistic;
    private long personCount;
}
