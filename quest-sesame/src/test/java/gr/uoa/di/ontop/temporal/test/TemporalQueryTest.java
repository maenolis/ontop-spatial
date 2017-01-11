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

public class TemporalQueryTest extends TestCase {

	final static Logger LOGGER = LoggerFactory.getLogger(TemporalQueryTest.class);

	public void test() throws Exception {

		// Filenames
		final String owlfileName = "src/test/resources/temporal/HelloWorld.owl";
		final String obdafileName = "src/test/resources/temporal/HelloWorld.obda";

		// Prefixes
		final String prefixes = "prefix ex: <http://meraka/moss/exampleBooks.owl#> \n "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
				+ "PREFIX : <http://www.semanticweb.org/ontologies/2011/4/HelloWorld.owl#> \n";

		// Query
		final String query = "select distinct ?x ?name ?time where {?x :fullName ?name . ?x :time ?time}";

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

