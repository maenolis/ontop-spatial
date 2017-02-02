package gr.uoa.di.ontop.temporal.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemporalOverlapsLiteralTest extends AbstractTemporalTest {

    final static Logger LOGGER = LoggerFactory.getLogger(TemporalOverlapsLiteralTest.class);

    @Override
    protected String getQuery() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1" +
                ". ?p1 strdf:overlaps  \"[2008-01-01 15:00:00+02, 2008-01-01 15:30:00+02)\"^^strdf:period" +
                ". ?x1 strdf:hasId ?id1" +
                "}";
        return query;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
