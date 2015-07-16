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

public class SesameVirtualNPDShpTest extends TestCase {
	
	public void test() throws Exception
	{
		
		
		//create a sesame repository
		RepositoryConnection con = null;
		Repository repo = null;
		
		try {
			
			//String owlfile = "/home/constant/gisat/urban_atlas_melod.owl";
			//String owlfile = "/home/constant/books.owl";
			String owlfile = "/home/constant/gisat/CityDistricts.owl";
			String obdafile = "/home/constant/mappings-ontop/npd-rhodes.obda";
			//String owlfile = 	"/home/timi/ontologies/helloworld/helloworld.owl";
			repo = new SesameVirtualRepo("my_name", owlfile, obdafile, false, "TreeWitness");
	
			repo.initialize();
			
			con = repo.getConnection();
			
			String prefixes = "prefix ex: <http://meraka/moss/exampleBooks.owl#> \n "
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
					+ "PREFIX cd: <http://melodiesproject.eu/CityDistricts/ontology#> \n"
					+ "PREFIX geosparql: <http://www.opengis.net/ont/geosparql#> \n"
					+ "PREFIX : <http://meraka/moss/exampleBooks.owl#> \n"
					+ "PREFIX ua: <http://geo.linkedopendata.gr/urban/ontology#> \n"
					+ "PREFIX npd: <http://geo.linkedopendata.gr/npd/ontology#> \n";
			
			///query repo
			 try {
				 String preds = prefixes +  "select distinct ?p  where {" +
				 						"?s ?p ?o . " +
				 						//" ?x2 ex:hasSerialization ?g2 . " +
				 					//	"FILTER(SpatialOverlap(?g1,?g2))" +
				 					//	"FILTER(!sameTerm(?g1,?g2))" +
				 						"} ";
				 
				 String spatialConstantQuery = "select distinct ?x1 ?g1 where {" +
	 						"?x1 <http://meraka/moss/exampleBooks.owl#hasSerialization> ?g1 . " +
	 						"FILTER(SpatialOverlap(\"POINT(2.497514 56.847728)\",?g1))" +
	 					//	"FILTER(!sameTerm(?g1,?g2))" +
	 						"} limit 10";
				

				  String wellboresInDiscoveries = prefixes + " SELECT distinct ?x1 ?g1 \n"
				  		+ "where {"
				  		+ "?x1 rdf:type npd:Wellbore . \n"
				  		+ "?x1 geosparql:aswkt ?g1 . \n"
				  		+ "?x2 rdf:type npd:DiscoveryArea . \n"
				  		+ "?x2 geosparql:aswkt ?g2 . \n"
				  		+ "FILTER(<http://www.opengis.net/def/function/geosparql/sfContains>(?g2, ?g1)) \n"
				  		+ "}";
				
				  String wellboresInProductionLicenseAreas = prefixes + " SELECT distinct ?x1 ?g1 \n"
					  		+ "where {"
					  		+ "?x1 rdf:type npd:Wellbore . \n"
					  		+ "?x1 geosparql:aswkt ?g1 . \n"
					  		+ "?x2 rdf:type npd:productionLicenseArea . \n"
					  		+ "?x2 geosparql:aswkt ?g2 . \n"
					  		+ "FILTER(<http://www.opengis.net/def/function/geosparql/sfContains>(?g2, ?g1)) \n"
					  		+ "}";
					
				 /* String wellboresInDiscoveries = prefixes + " SELECT distinct ?x1 ?g1 \n"
					  		+ "where {"
					  		+ "?x1 rdf:type npd:Wellbore . \n"
					  		+ "?x1 <http://geo.linkedopendata.gr/npd/ontology#wellbore/aswkt> ?g1 . \n"
					  		+ "?x2 rdf:type npd:DiscoveryArea . \n"
					  		+ "?x2 <http://geo.linkedopendata.gr/npd/ontology#dscarea/aswkt> ?g2 . \n"
					  		+ "FILTER(<http://www.opengis.net/def/function/geosparql/sfContains>(?g2, ?g1)) \n"
					  		+ "}";*/
				  
				  String wellboresInFields = prefixes + " SELECT distinct ?x1 ?g1 \n"
					  		+ "where {"
					  		+ "?x1 rdf:type npd:Wellbore . \n"
					  		+ "?x1 geosparql:aswkt ?g1 . \n"
					  		+ "?x2 rdf:type npd:fieldArea. \n"
					  		+ "?x2 geosparql:aswkt ?g2 . \n"
					  		+ "FILTER(<http://www.opengis.net/def/function/geosparql/sfContains>(?g2, ?g1)) \n"
					  		+ "}";
				  
				  String BaareasAndPipelines = prefixes + " SELECT distinct ?x1  \n"
					  		+ "where {"
					  		+ "?x1 rdf:type npd:BusinessArrangementArea . \n"
					  		+ "?x1 geosparql:aswkt ?g1 . \n"
					  		+ "?x2 rdf:type npd:Pipeline . \n"
					  		+ "?x2 geosparql:aswkt ?g2 . \n"
					  		+ "FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?g2, ?g1)) \n"
					  		+ "}";
				  
						  
			      TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, wellboresInDiscoveries);
			      FileOutputStream f = new FileOutputStream("/home/constant/ontop-kml/gisat.kml");
				  TupleQueryResultHandler handler = new SPARQLResultsTSVWriter(System.out);
				//  TupleQueryResultWriterFactory kml = new stSPARQLResultsKMLWriterFactory();

				//  TupleQueryResultHandler spatialHandler  = new stSPARQLResultsKMLWriter(f);
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

