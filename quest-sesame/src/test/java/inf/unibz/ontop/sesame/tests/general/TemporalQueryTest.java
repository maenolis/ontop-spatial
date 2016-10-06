package inf.unibz.ontop.sesame.tests.general;

/*
 * #%L
 * ontop-quest-sesame
 * %%
 * Copyright (C) 2009 - 2013 Free University of Bozen-Bolzano
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import it.unibz.krdb.obda.owlrefplatform.core.QuestConstants;
import it.unibz.krdb.obda.owlrefplatform.core.QuestPreferences;
import junit.framework.TestCase;
import org.openrdf.model.impl.TreeModel;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResultHandler;
import org.openrdf.query.resultio.text.tsv.SPARQLResultsTSVWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.Rio;
import org.openrdf.rio.helpers.StatementCollector;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import sesameWrapper.SesameVirtualRepo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class TemporalQueryTest extends TestCase {



	public void test() throws Exception
	{


		//create a sesame repository
		RepositoryConnection con = null;
		Repository repo = null;

		try {

			String owlfile = "src/test/resources/temporal/HelloWorld.owl";
			String obdafile = "src/test/resources/temporal/HelloWorld.obda";
			repo = new SesameVirtualRepo("temporal_name", owlfile, obdafile, false, "Temporal");

			repo.initialize();

			con = repo.getConnection();

			String prefixes = "prefix ex: <http://meraka/moss/exampleBooks.owl#> \n "
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
					+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
					+ "PREFIX : <http://www.semanticweb.org/ontologies/2011/4/HelloWorld.owl#> \n" ;

			///query repo
			try {

				String query = prefixes + "select distinct ?x where {?x rdf:type :Person}";

				TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, query);
				TupleQueryResultHandler handler = new SPARQLResultsTSVWriter(System.out);
				tupleQuery.evaluate(handler);

				System.out.println("Closing...");

				con.close();

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}



		} catch (Exception e1) {
			e1.printStackTrace();
		}


		System.out.println("Done.");
	}

}

