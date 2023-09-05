package ch.hrms.assessment.fullstackbackendchallenge;

import ch.hrms.assessment.fullstackbackendchallenge.api.Person;
import ch.hrms.assessment.fullstackbackendchallenge.api.Persons;
import ch.hrms.assessment.fullstackbackendchallenge.web.StatisticFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {

    private final StatisticFilter statisticFilter;

    @Bean
    public MarshallingHttpMessageConverter marshallingHttpMessageConverter() {
        MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
        converter.setMarshaller(jaxb2Marshaller());
        converter.setUnmarshaller(jaxb2Marshaller());
        return converter;
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(Persons.class, Person.class);
        jaxb2Marshaller.setSchema(new ClassPathResource("fullstack-backend-challenge.xsd"));
        return jaxb2Marshaller;
    }

    @Bean
    public FilterRegistrationBean<StatisticFilter> personStatistics() {
        FilterRegistrationBean<StatisticFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(statisticFilter);
        registrationBean.addUrlPatterns("/api/persons/*");
        registrationBean.setOrder(Integer.MAX_VALUE);

        return registrationBean;
    }
}
