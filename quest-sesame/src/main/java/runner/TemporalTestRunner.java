package runner;

import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResultHandler;
import org.openrdf.query.resultio.text.tsv.SPARQLResultsTSVWriter;
import org.openrdf.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;
import sesameWrapper.SesameVirtualRepo;

import java.lang.reflect.Field;

public class TemporalTestRunner {

    final static Logger LOGGER = LoggerFactory.getLogger(TemporalTestRunner.class);

    @SuppressWarnings("unchecked")
    public void test() throws Exception {

        // Filenames
        final String owlfileName = "/home/maenolis/git/spatialOntop/ontop-spatial/temporal/temporal-test-runner/src/main/resources/TemporalMeeting.owl";
        final String obdafileName = "/home/maenolis/git/spatialOntop/ontop-spatial/temporal/temporal-test-runner/src/main/resources/TemporalMeeting.obda";

        // Prefixes
        final String prefixes = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
                + "PREFIX strdf: <http://www.semanticweb.org/ontologies/2011/4/TemporalMeeting.owl#> \n";

        // Prefixes + query
        final String finalQuery = prefixes + getQuery();

        LOGGER.debug("Query to be executed:");
        LOGGER.debug("\n\n" + finalQuery + "\n");


        //create a sesame repository
        Repository repo;

        try {

            repo = new SesameVirtualRepo("temporal_name", owlfileName, obdafileName, false, "Temporal");
            repo.initialize();

            TupleQuery tupleQuery = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, finalQuery);
            TupleQueryResultHandler handler = new SPARQLResultsTSVWriter(System.out);
//            tupleQuery.evaluate(handler);

//            QuestDBVirtualStore virtualStore = getField(repo, QuestDBVirtualStore.class, "virtualStore"); //IllegalAccessException
//            Quest questInstance = getField(virtualStore, Quest.class, "questInstance"); //IllegalAccessException
//            OBDAModelImpl inputOBDAModel = getField(questInstance, OBDAModelImpl.class, "inputOBDAModel"); //IllegalAccessException
//            java.util.Hashtable mappings = getField(inputOBDAModel, java.util.Hashtable.class, "mappings"); //IllegalAccessException
//
//            Iterator foo = mappings.keySet().iterator();
//
//            while (foo.hasNext()) {
//                List axioms = (List) mappings.get(foo.next());
//                for (Object obj : axioms) {
//                    RDBMSMappingAxiomImpl current = (RDBMSMappingAxiomImpl) obj;
//                    for (Function func : current.getTargetQuery()) {
//                        if (func.getFunctionSymbol().getName().equals("http://www.semanticweb.org/ontologies/2011/4/TemporalMeeting.owl#hasSerialization")) {
//                            func.setPredicate(OBDADataFactoryImpl.getInstance().getPredicate("http://www.semanticweb.org/ontologies/2011/4/TemporalMeeting.owl#hasSerialization", new Predicate.COL_TYPE[] { Predicate.COL_TYPE.getQuestType(1), Predicate.COL_TYPE.getQuestType(3) }));
//                            LOGGER.info("niext");
//                            Function f = OBDADataFactoryImpl.getInstance().getFunction(new DatatypePredicateImpl("strdf:period", Predicate.COL_TYPE.getQuestType(3)), func.getTerm(1));
//                            func.getTerms().set(1, f);
//                        }
//                    }
//                }
//
//            }

            tupleQuery.evaluate(handler);

            LOGGER.info("Closing repository connection.");

            repo.getConnection().close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("Test finished.");
    }

    private <T> T getField(Object src, Class<T> type, String propName) throws NoSuchFieldException, IllegalAccessException {
        Field f = src.getClass().getDeclaredField(propName); //NoSuchFieldException
        f.setAccessible(true);
        return (T) f.get(src); //IllegalAccessException
    }

    protected String getQuery() {
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

    public static void main(String... args) throws Exception {
        final TemporalTestRunner ttr = new TemporalTestRunner();
        ttr.test();
    }
}
