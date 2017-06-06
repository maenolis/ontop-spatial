package runner;

import com.google.common.base.Stopwatch;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResultHandler;
import org.openrdf.query.resultio.text.tsv.SPARQLResultsTSVWriter;
import org.openrdf.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;
import sesameWrapper.SesameVirtualRepo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class TemporalTestRunner {

    final static Logger LOGGER = LoggerFactory.getLogger(TemporalTestRunner.class);

    private String projectPath;

    public TemporalTestRunner(String projectPath) {
        this.projectPath = projectPath;
    }

    @SuppressWarnings("unchecked")
    public void test() throws Exception {

        // Filenames
        final String owlfileName = projectPath + "/ontop-spatial/temporal/temporal-test-runner/src/main/resources/TemporalMeeting.owl";
        final String obdafileName = projectPath +  "/ontop-spatial/temporal/temporal-test-runner/src/main/resources/TemporalMeeting.obda";

        // Prefixes
        final String prefixes = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
                + "PREFIX strdf: <http://www.semanticweb.org/ontologies/2011/4/TemporalMeeting.owl#> \n";

        Map<String, String> queries = composeQueriesList();

        for (Map.Entry<String, String> query: queries.entrySet()) {
            // Prefixes + query
            final String currentQuery = prefixes + query.getValue();

            LOGGER.trace("Query to be executed:");
            LOGGER.trace("\n\n" + currentQuery + "\n");
            LOGGER.debug("next query: " + query.getKey());

            //create a sesame repository
            Repository repo;

            try {
                repo = new SesameVirtualRepo("temporal_name", owlfileName, obdafileName, false, "Temporal");
                repo.initialize();
                TupleQuery tupleQuery = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, currentQuery);
                OutputStream os = new ByteOutputStream();
                TupleQueryResultHandler handler = new SPARQLResultsTSVWriter(os);
                Stopwatch stopwatch = Stopwatch.createStarted();

                tupleQuery.evaluate(handler);

                stopwatch.stop();
                PrintStream ps = new PrintStream(generateFileName(query.getKey()));

                ps.println(stopwatch.toString());
                ps.close();
                os.close();
                LOGGER.info("Closing repository connection.");
                repo.getConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LOGGER.info("Test finished.");
    }

    private Map<String, String> composeQueriesList() {
        final Map<String, String> result = new HashMap<>();
        result.put("overlaps", getQueryOverlaps());
        result.put("adjacent", getQueryAdjacent());
        result.put("after", getQueryAfter());
        result.put("before", getQueryBefore());
        result.put("containedBy", getQueryContainedBy());
        result.put("contains", getQueryContains());
        result.put("containsTimestamp", getQueryContainsTimestamp());
        result.put("equals", getQueryEquals());
        result.put("nequals", getQueryNequals());
        result.put("overlapsLiteral", getQueryOverlapsLiteral());
        result.put("overleft", getQueryOverleft());
        result.put("overright", getQueryOverright());
        return result;
    }

    private String generateFileName(final String testName) {
        SimpleDateFormat sdf =  new SimpleDateFormat("yyMMdd-HHmmsss-SSS", Locale.ENGLISH);
        return "/home/manvar/results/" + testName + "-" + sdf.format(Calendar.getInstance().getTime()) + ".log";
    }

    private <T> T getField(Object src, Class<T> type, String propName) throws NoSuchFieldException, IllegalAccessException {
        Field f = src.getClass().getDeclaredField(propName); //NoSuchFieldException
        f.setAccessible(true);
        return (T) f.get(src); //IllegalAccessException
    }

    protected String getQueryOverlaps() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasPeriod ?p2" +
                ". ?p1 strdf:overlaps ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 < ?id2)" +
                "}";
        return query;
    }

    protected String getQueryAdjacent() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasPeriod ?p2" +
                ". ?p1 strdf:adjacent ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 < ?id2)" +
                "}";
        return query;
    }

    protected String getQueryAfter() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasPeriod ?p2" +
                ". ?p1 strdf:after ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 < ?id2)" +
                "}";
        return query;
    }

    protected String getQueryBefore() {
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

    protected String getQueryContainedBy() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasPeriod ?p2" +
                ". ?p1 strdf:containedBy ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 < ?id2)" +
                "}";
        return query;
    }

    protected String getQueryContains() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasPeriod ?p2" +
                ". ?p1 strdf:contains ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 != ?id2)" +
                ". FILTER(?id2 < 428166)" +
                ". FILTER(?id1 < 428166)" +
                "}";
        return query;
    }

    protected String getQueryContainsTimestamp() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasCreationDate ?p2" +
                ". ?p1 strdf:contains ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 < ?id2)" +
                "}";
        return query;
    }

    protected String getQueryEquals() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasPeriod ?p2" +
                ". ?p1 strdf:equals ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 < ?id2)" +
                "}";
        return query;
    }

    protected String getQueryNequals() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasPeriod ?p2" +
                ". ?p1 strdf:nequals ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 < ?id2)" +
                "}";
        return query;
    }

    protected String getQueryOverlapsLiteral() {
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

    protected String getQueryOverleft() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasPeriod ?p2" +
                ". ?p1 strdf:overleft ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 < ?id2)" +
                "}";
        return query;
    }

    protected String getQueryOverright() {
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasPeriod ?p2" +
                ". ?p1 strdf:overright ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 < ?id2)" +
                "}";
        return query;
    }

    public static void main(String[] args) throws Exception {
        final TemporalTestRunner ttr = new TemporalTestRunner(args[0]);
        ttr.test();
    }
}
