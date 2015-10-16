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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.openrdf.query.resultio.sparqlxml.SPARQLResultsXMLWriter;
import org.openrdf.query.resultio.text.tsv.SPARQLResultsTSVWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

import sesameWrapper.SesameVirtualRepo;

//import org.openrdf.query.resultio.sparqlkml.stSPARQLResultsKMLWriter;

public class SesameVirtualGeographicaTest extends TestCase {

	
	
	public void test() throws Exception
	{
		 String spatialDatatype = "<http://www.opengis.net/ont/geosparql#wktLiteral>";
		 String GIVEN_POLYGON_IN_WKT=" ";
		 String GIVEN_POINT_IN_WKT = "\"POINT(23.71622 37.97945)\"";
		 String TIMESTAMP = "	\"2007-09-30T11:30:00\"";
		 String TOPONYME = " ";
		 String GIVEN_RECTANGLE_IN_WKT = " ";
		 String givenPolygonFile = "givenPolygon.txt";
		 String givenLinesFile = "givenLine.txt";
		 String givenPolygon;
		 String givenLine, givenLine2, givenLine3;
		 String givenPoint;
		 String givenRadius;
		 
		//create a sesame repository
		RepositoryConnection con = null;
		Repository repo = null;
		
		try {
			
			String owlfile = "/home/constant/books.owl";
			String obdafile = "/home/constant/geographica/mappings/geographica-real.obda";
			//String owlfile = 	"/home/timi/ontologies/helloworld/helloworld.owl";
			repo = new SesameVirtualRepo("my_name", owlfile, obdafile, false, "TreeWitness");
	
			repo.initialize();
			
			con = repo.getConnection();
			
			String prefixes = 
					  " PREFIX : <http://meraka/moss/exampleBooks.owl#> \n"+
					  " PREFIX owl: <http://www.w3.org/2002/07/owl#> \n"+
					  " PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"+
					  " PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> \n"+
					  " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"+
					  " PREFIX quest: <http://obda.org/quest#> \n"+
					  " PREFIX strdf: <http://strdf.di.uoa.gr/ontology#> \n"+
					  " PREFIX geof: <http://www.opengis.net/def/function/geosparql/> \n"+
					  " PREFIX geo: <http://www.opengis.net/ont/geosparql#> \n"+
					  " PREFIX geo-sf: <http://www.opengis.net/ont/sf#> \n"+
					  " PREFIX landOwnership: <http://geographica.di.uoa.gr/generator/landOwnership/> \n"+
					  " PREFIX state: <http://geographica.di.uoa.gr/generator/state/> \n"+
					  " PREFIX gag: <http://geo.linkedopendata.gr/gag/ontology/> \n"+
					  " PREFIX lgd: <http://linkedgeodata.org/ontology/> \n"+
					  " PREFIX clc: <http://geo.linkedopendata.gr/corine/ontology#> \n"+
					  " PREFIX geonames: <http://www.geonames.org/ontology#> \n"+
					  " PREFIX dbpedia: <http://dbpedia.org/property/> \n" +
					  " PREFIX noa: <http://teleios.di.uoa.gr/ontologies/noaOntology.owl#> ";
			
			InputStream is = getClass().getResourceAsStream("/"+givenPolygonFile);
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			givenPolygon = in.readLine();
			givenPolygon = "\""+givenPolygon+"\"";
			in.close();
			in = null;
			is.close();
			is = null;
			is = getClass().getResourceAsStream("/"+givenLinesFile);
			in = new BufferedReader(new InputStreamReader(is));
			// <http://linkedgeodata.org/geometry/way168092715>
			givenLine = in.readLine();
			givenLine = "\""+givenLine+"\"";
			// <http://linkedgeodata.org/geometry/way31642973>
			givenLine2 = in.readLine();
			givenLine2 = "\""+givenLine2+"\"";
			// <http://linkedgeodata.org/geometry/way45476887>
			givenLine3 = in.readLine();
			givenLine3 = "\""+givenLine3+"\"";
			in.close();
			in = null;
			is.close();
			is = null;
			String GIVEN_LINE_IN_WKT = givenLine;
			GIVEN_POLYGON_IN_WKT = givenPolygon;
			///query repo
			 try {
				 
					
					 String gadm_clc_extra = prefixes 
							+ "\n select distinct ?s1 ?s2 where { \n"
							+ "	?s1 gag:asWKT ?o1 . \n"
							+ "	?s2 clc:asWKT ?o2 . \n"
							+ "?s2  rdf:type clc:Area . \n "
							+ "?s2 clc:hasLandUse ?type . "
							+ "?s2 clc:hasArea ?area . \n"
							+ "?s2 clc:hasShapeA ?shape .\n"		
							+ "  FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?o1, ?o2)).  \n"
							+ "} limit 10\n"
							;
					
						
					 String clc = prefixes 
							+ "\n select distinct ?p where { \n"
							+ "	?s1 ?p ?o . \n"
							+ "	?s1 clc:asWKT ?o2 . \n"
						//	+ "?s2 ?p ?o . \n "
							+ "?s2 rdf:type clc:Area . "
							+ "?s2 clc:hasLandUse ?type  . \n"
						//	+ "?s2 clc:hasArea ?area . \n"
						//	+ "?s2 clc:hasShapeA ?shape .\n"		
						//	+ "  FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?o1, ?o2)).  \n"
							+ "} \n"
							;
					 
					 String lgd_building_gadm = prefixes 
							+ "\n select ?s1 ?s2 where { \n"
							+ "	?s1 lgd:asWKT ?o1 . \n"
							+ "	?s2 gag:asWKT ?o2 . \n "
							+ "?s1 rdf:type lgd:Building . \n"
							+ "?s1 lgd:name ?name . \n"
							+ "?s1 lgd:type ?type . \n"
							+ "  FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?o1, ?o2)).  \n"
							+ "} \n"
							;	
					
				 
				 String geometries = prefixes + "select distinct ?g where { \n"
				 		+ "?s gag:asWKT ?g . \n"
				 		+ "} limit 10";
				 
				 		
				 		String join0 = prefixes +  "select distinct ?name  where { \n" +
				 						"SELECT ?s1 ?s2 \n"
				 						+ "WHERE { "
				 						+ "	?s1 geonames:asWKT ?o1 . \n"
				 						+ "?s2 dbpedia:asWKT ?o2 . \n"
				 						+ "FILTER(<http://www.opengis.net/def/function/geosparql/sfEquals>(?o1, ?o2)). \n";
				 			
				 String plain = prefixes 
						 + "SELECT distinct  ?type  \n"
						 + "WHERE { "
						 + "?s1 geo:asWKT ?o1 ."
						 + "?s1 rdf:type ?type . \n"
						 + "	FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>( " + GIVEN_POLYGON_IN_WKT + ", ?o1)). "
						 + "FILTER(?type >= lgd:Landuse  )\n"
						 + "} "
						 + "limit 10";
						 
				 
				 String join1 = prefixes 
						 + "SELECT ?s1 ?s2 \n"
						 + "WHERE { "
						 + "?s1 geonames:asWKT ?o1 . \n"
						 + "	?s2 lgd:asWKT ?o2 . \n"
						 + "FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?o1, ?o2)). \n " +
	 						"}\n";
				

				 
				 String selection0 = prefixes
						 + "SELECT ?s1 ?o1 \n"
						 + "WHERE { \n"
						 + "	?s1 lgd:asWKT ?o1 . \n"
						 + "	FILTER(<http://www.opengis.net/def/function/geosparql/sfEquals>(?o1, "+ GIVEN_LINE_IN_WKT +" )). \n"
						 		+ "}  \n";
				 

				
				//System.out.println("Query: " +  selection1);
						
				 TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, gadm_clc_extra);
			      
				 TupleQueryResultHandler handler = new SPARQLResultsTSVWriter(System.out);
				  
			//	  TupleQueryResultHandler spatialHandler  = new stSPARQLResultsKMLWriter(System.out);
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

