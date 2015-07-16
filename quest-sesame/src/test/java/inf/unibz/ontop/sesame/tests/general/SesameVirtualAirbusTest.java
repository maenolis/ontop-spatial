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
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import junit.framework.TestCase;

import org.openrdf.model.Statement;
import org.openrdf.query.BindingSet;
import org.openrdf.query.GraphQuery;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResultHandler;
import org.openrdf.query.resultio.TupleQueryResultWriterFactory;
//import org.openrdf.query.resultio.sparqlkml.stSPARQLResultsKMLWriter;
//import org.openrdf.query.resultio.sparqlkml.stSPARQLResultsKMLWriterFactory;
import org.openrdf.query.resultio.sparqlxml.SPARQLResultsXMLWriter;
import org.openrdf.query.resultio.text.tsv.SPARQLResultsTSVWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

import sesameWrapper.SesameVirtualRepo;

//import org.openrdf.query.resultio.sparqlkml.stSPARQLResultsKMLWriter;

public class SesameVirtualAirbusTest extends TestCase {

	
	
	public void test() throws Exception
	{
		
		
		//create a sesame repository
		RepositoryConnection con = null;
		Repository repo = null;
		
		try {
			
			//String owlfile = "/home/constant/Vista/urban_atlas_melod.owl";
			//String owlfile = "/home/constant/books.owl";
			String owlfile = "/home/constant/gisat/CityDistricts.owl";
		//	String owlfile = "/home/constant/airbus/DMARitime_gis.owl";
			String obdafile = "/home/constant/airbus/DMARitime_gis.obda";
			//String owlfile = 	"/home/timi/ontologies/helloworld/helloworld.owl";
			repo = new SesameVirtualRepo("my_name", owlfile, obdafile, false, "TreeWitness");
	
			repo.initialize();
			
			con = repo.getConnection();
			
			String prefixes = "prefix ex: <http://meraka/moss/exampleBooks.owl#> \n "
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
					+ "PREFIX cd: <http://melodiesproject.eu/CityDistricts/ontology#> \n"
					+ "PREFIX geo: <http://www.opengis.net/ont/geosparql#> \n"
					+ "PREFIX ua: <http://geo.linkedopendata.gr/urban/ontology#> \n"
					+ "PREFIX bio: <http://melodiesproject.eu/vista/biotop/ontology#> \n"
					+ "PREFIX bsph: <http://melodiesproject.eu/vista/bsph/ontology#> \n"
					+ "PREFIX ffh: <http://melodiesproject.eu/vista/ffh/ontology#> \n"
					+ "PREFIX nap: <http://melodiesproject.eu/vista/nap/ontology#> \n"
					+ "PREFIX nsg: <http://melodiesproject.eu/vista/nsg/ontology#> \n"
					+ "PREFIX ntp: <http://melodiesproject.eu/vista/ntp/ontology#> \n"
					+ "PREFIX twsg: <http://melodiesproject.eu/vista/twsg/ontology#> \n"
					+ "PREFIX bpa: <http://melodiesproject.eu/vista/bpa/ontology#> \n"
					+ "PREFIX f: <http://melodiesproject.eu/field/ontology#> \n"; 
			
			///query repo
			 try {
			
				 String SimpleVesselQuery = "PREFIX : <http://www.rmsas.de/DMARitime#>   \n" +
						 "select ?x ?y where {   \n"
						 + "?x a :Vessel .  \n"
						 + "?x :hasName ?y .  \n"
						 + "}  \n";

				String SimplePositionQuery = "PREFIX : <http://www.rmsas.de/DMARitime#>  \n" +
						"select ?x ?y ?z ?a where {   \n"
						+ "?x a :Position.  \n"
						+ " ?x :hasDateTime ?y .  \n"
						+ " ?x :hasLatitude ?z .  \n"
						+ " ?x :hasLongitude ?a . "
						+ "}  \n";	 
				
				String SimplePositionWKT = prefixes + "PREFIX : <http://www.rmsas.de/DMARitime#>  \n" +
						"select ?x ?y ?z    \n"
						+ " where {   \n"
						+ "?x rdf:type :Position.  \n"
						+ " ?x :hasDateTime ?y .  \n"
						+ "?x :hasGeometry ?g .\n"
						+ "?g geo:asWKT ?z .  \n"
						+ "}  \n";	 
				
				
				String FindLocationPerBoat = prefixes +  "PREFIX : <http://www.rmsas.de/DMARitime#>   \n" + 
						"select distinct  ?x ?wkt  \n"
						+ " where {    \n"
						+ "?x rdf:type :Vessel .  \n"
						+ "?x :hasName ?y .  \n"
						+ "?x :hasLocation ?z . \n"
						+ "?z rdf:type :Position .\n"
						+ "?z :hasGeometry ?g .\n"
						+ "?g ?p ?wkt .  \n"
						+ "}  \n"; 
				
				
				String FindOlafJuhlsPositions = prefixes + "PREFIX : <http://www.rmsas.de/DMARitime#>   \n" +
						"PREFIX geos: <http://www.opengis.net/ont/geosparql#>   \n" +
						"select distinct ?x  ?wkt  ?timestamp  "
						+ "where {   \n"
						+ "?x a :Vessel . "
						+ "?x :hasName \"Olaf Juhl\" .   \n"
						+ "?x :hasLocation ?z . "
						+ "?z rdf:type :Position .\n"
						+ "?z :hasGeometry ?g .\n"
						+ "?z :hasDateTime ?timestamp ."
						+ "?g geos:asWKT ?wkt . \n "
						+ "}  \n" 
					+	"order by desc(?timestamp)  \n";
				
				String FindGISPositions = "PREFIX : <http://www.rmsas.de/DMARitime#>  \n" +
						"PREFIX geos: <http://www.opengis.net/ont/geosparql#>   \n"
						+ "select ?x ?y ?b where {  \n"
						+ "?x a :Vessel .  \n"
						+ "?x :hasName ?y .  \n"
						+ "?x :hasLocation ?z .  \n"
						+ "?z :hasGeometry ?a .  \n"
						+ "?a geos:asWKT ?b . \n"
						+ "}";
				
				String testbed = "PREFIX : <http://www.rmsas.de/DMARitime#>  \n"
						+ "PREFIX geos: <http://www.opengis.net/ont/geosparql#>  \n"
						+ "PREFIX geof: <http://www.opengis.net/def/geosparql/function>  \n"
						+ "select ?x   \n"
						+ "where {  \n"
						+ "?x a :Vessel .  \n"
						+ "	?x :hasLocation ?z .  \n"
						+ "	?z :hasGeometry ?a .  \n"
						+ "	?z :hasLatitude ?c . \n"
						+ "	?z :hasLongitude ?d .  \n"
						+ "?a geos:asWKT ?b .  \n"
						+ "FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(\"POLYGON((51.913574 19.289005,51.913574 19.229953,51.886321 19.029953,51.886321 19.089005,51.913574 19.089005, 51.913574 19.289005  ))\", ?b))"
     		+ "} ";
				//^^geos:wktLiteral
				
				
			      TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, FindOlafJuhlsPositions);
			      FileOutputStream f = new FileOutputStream("/home/constant/ontop-kml/airbus.kml");
				  TupleQueryResultHandler handler = new SPARQLResultsTSVWriter(System.out);
				  //TupleQueryResultWriterFactory kml = new stSPARQLResultsKMLWriterFactory();

				  //TupleQueryResultHandler spatialHandler  = new stSPARQLResultsKMLWriter(f);
			       tupleQuery.evaluate(handler);
			   
			      
			     /* queryString =  "CONSTRUCT {?s ?p ?o} WHERE {?s ?p ?o}";
			      GraphQuery graphQuery = con.prepareGraphQuery(QueryLanguage.SPARQL, queryString);
			      GraphQueryResult gresult = graphQuery.evaluate();
			      while(gresult.hasNext())
			      {
			    	  Statement s = gresult.next();
			    	  System.out.println(s.toString());
			      }
			      */
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

