package gr.uoa.di.ontop.temporal.test;

    import org.openrdf.query.QueryLanguage;
    import org.openrdf.query.TupleQuery;
    import org.openrdf.query.TupleQueryResultHandler;
    import org.openrdf.query.resultio.text.tsv.SPARQLResultsTSVWriter;
    import org.openrdf.repository.Repository;

    import junit.framework.TestCase;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import sesameWrapper.SesameVirtualRepo;

public class TemporalMeetingTest extends TestCase {

    final static Logger LOGGER = LoggerFactory.getLogger(TemporalQueryTest.class);

    public void test() throws Exception {

        // Filenames
        final String owlfileName = "src/test/resources/temporal/TemporalMeeting.owl";
        final String obdafileName = "src/test/resources/temporal/TemporalMeeting.obda";

        // Prefixes
        final String prefixes = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
                + "PREFIX strdf: <http://www.semanticweb.org/ontologies/2011/4/TemporalMeeting.owl#> \n";

        // Query
        final String query = "select distinct *" +
                "where " +
                "{" +
                "?x1 a strdf:Meeting . ?x2 a strdf:Meeting" +
                ". ?x1 strdf:hasPeriod ?p1 . ?x2 strdf:hasPeriod ?p2" +
                ". ?p1 strdf:overlaps ?p2" +
                ". ?x1 strdf:hasId ?id1 . ?x2 strdf:hasId ?id2" +
                ". FILTER(?id1 < ?id2)" +
                "}";

        // Prefixes + query
        final String finalQuery = prefixes + query;

        LOGGER.debug("Query to be executed:");
        LOGGER.debug("\n\n" + finalQuery + "\n");


        //create a sesame repository
        Repository repo = null;

        try {

            repo = new SesameVirtualRepo("temporal_name", owlfileName, obdafileName, false, "Temporal");
            repo.initialize();

            TupleQuery tupleQuery = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, finalQuery);
            TupleQueryResultHandler handler = new SPARQLResultsTSVWriter(System.out);
            tupleQuery.evaluate(handler);

            LOGGER.info("Closing repository connection.");

            repo.getConnection().close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("Test finished.");
    }

}

