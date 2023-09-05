package ch.hrms.assessment.fullstackbackendchallenge.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"requestCount", "validRequestCount","wrongRequestCount", "wrongRequestMessages"})
public class Statistic {
    @Getter
    @Setter
    private long validRequestCount;
    @Getter
    private Map<String, Long> wrongRequestMessages = new HashMap<>();

    public long getWrongRequestCount(){
        return wrongRequestMessages.values().stream().reduce(0l, Long::sum);
    }

    public long getRequestCount(){
        return validRequestCount + getWrongRequestCount();
    }
}
