package gr.uoa.di.ontop.temporal.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemporalBeforeTest extends AbstractTemporalTest {

    final static Logger LOGGER = LoggerFactory.getLogger(TemporalBeforeTest.class);

    @Override
    protected String getQuery() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasPeriod ?p2" +
                ". ?p1 strdf:before ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 < ?id2)" +
                "}";
        return query;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }


}
