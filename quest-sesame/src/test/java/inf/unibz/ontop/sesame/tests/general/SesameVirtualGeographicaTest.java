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
			String obdafile = "/home/constant/mappings-ontop/geographica-real.obda";
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
				 String join0 = prefixes +  "select distinct ?name  where { \n" +
				 						"SELECT ?s1 ?s2 \n"
				 						+ "WHERE { "
				 						+ "	?s1 geonames:asWKT ?o1 . \n"
				 						+ "?s2 dbpedia:asWKT ?o2 . \n"
				 						+ "FILTER(<http://www.opengis.net/def/function/geosparql/sfEquals>(?o1, ?o2)). \n";
				 						
				 
				 String join1 = prefixes 
						 + "SELECT ?s1 ?s2 \n"
						 + "WHERE { "
						 + "?s1 geonames:asWKT ?o1 . \n"
						 + "	?s2 lgd:asWKT ?o2 . \n"
						 + "FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?o1, ?o2)). \n " +
	 						"}\n";
				

				 String join2 = prefixes
						 +"SELECT ?s1 ?s2\n"
						 + "WHERE { \n"
						 + "	?s1 geonames:asWKT ?o1 . \n"
						 + "    ?s2 gag:asWKT ?o2 . \n"
						 + "FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?o1, ?o2)).  \n" +
	 						"} \n";
				 
				 String join3 = prefixes + 
						 "SELECT ?s1 ?s2 \n"
						 + "WHERE { \n"
						 + "?s1 lgd:asWKT ?o1 . \n"
						 + "?s2 gag:asWKT ?o2 . \n"
						 + "FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?o1, ?o2)).  \n" +
	 						"}\n";
				 
				 String join4 = prefixes
						 + "SELECT ?s1 ?s2\n"
						 + "WHERE { \n"
						 + "?s1 geonames:asWKT ?o1 . \n"
						 + "?s2 gag:asWKT ?o2 . \n"
						 + "	FILTER(<http://www.opengis.net/def/function/geosparql/sfWithin>(?o1, ?o2)).  \n" +
	 						"}\n";
				 
				 String join5 = prefixes
						 + "SELECT ?s1 ?s2 \n"
						 + "WHERE { \n"
						 + "?s1 lgd:asWKT ?o1 . \n"
						 + "?s2 gag:asWKT ?o2 . \n"
						 + "FILTER(<http://www.opengis.net/def/function/geosparql/sfWithin>(?o1, ?o2)). \n " +
	 						"}";
				 
				 String join6 = prefixes
						 + "SELECT ?s1 ?s2 \n"
						 + "WHERE { \n"
						 + "?s1 clc:asWKT ?o1 . \n"
						 + "?s2 gag:asWKT ?o2 . \n"
						 + "FILTER(<http://www.opengis.net/def/function/geosparql/sfWithin>(?o1, ?o2)).  \n" +
	 						"} \n";
				 
				 String join7 = prefixes  
						 + "SELECT ?s1 ?s2 \n"
						 + "WHERE { \n"
						 + "?s1 lgd:asWKT ?o1 . \n"
						 + "?s2 gag:asWKT ?o2 . \n"
						 + "FILTER(<http://www.opengis.net/def/function/geosparql/sfCrosses>(?o1, ?o2)).  \n" +
	 						"} \n";
				 
				 String join8 = prefixes
						 + "SELECT ?s1 ?s2 \n"
						 + "WHERE { \n"
						 + "?s1 gag:asWKT ?o1 . \n"
						 + "?s2 gag:asWKT ?o2 . \n"
						 + "FILTER(?s1 != ?s2). \n"
						 + "FILTER(<http://www.opengis.net/def/function/geosparql/sfTouches>(?o1, ?o2)).  \n" +
	 						"} \n";
				 
				 String join9 = prefixes
						 + "SELECT ?s1 ?s2 \n"
						 + "WHERE { \n"
						 + "?s1 gag:asWKT ?o1 . \n"
						 + "?s2 clc:asWKT ?o2 . \n"
						 + "FILTER(<http://www.opengis.net/def/function/geosparql/sfOverlaps>(?o1, ?o2)). \n " +
	 						"} \n";
				 
				 String selection0 = prefixes
						 + "SELECT ?s1 ?o1 \n"
						 + "WHERE { \n"
						 + "	?s1 lgd:asWKT ?o1 . \n"
						 + "	FILTER(<http://www.opengis.net/def/function/geosparql/sfEquals>(?o1, "+ GIVEN_LINE_IN_WKT +" )). \n"
						 		+ "}  \n";
				 
				 String selection1 = prefixes
						 + "SELECT ?s1 ?o1 \n"
						 + "WHERE { \n"
						 + "	?s1 gag:asWKT ?o1 . \n"
						 + "	FILTER(<http://www.opengis.net/def/function/geosparql/sfEquals>( " + GIVEN_POLYGON_IN_WKT + ", ?o1)). \n"
						 		+ " }\n";
				 
				 String selection2 = prefixes
						 + "SELECT ?s1 ?o1 \n"
						 + "WHERE { \n"
						 + "	?s1 lgd:asWKT ?o1 . \n"
						 + "	FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?o1, " + GIVEN_POLYGON_IN_WKT + ")). \n"
						 		+ "} \n";

				 String selection3 = prefixes
						 + "SELECT ?s1 ?o1 \n"
						 + "WHERE { \n"
						 + "?s1 clc:asWKT ?o1 . \n"
						 + "FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>( " +  GIVEN_LINE_IN_WKT + ", ?o1)). \n"
						 		+ "} \n";
						 
				String selection4 = prefixes
						+ "SELECT ?s1 ?o1 \n"
						+ "WHERE {  \n"
						+ "	?s1 clc:asWKT ?o1 . \n"
						+ "	FILTER(<http://www.opengis.net/def/function/geosparql/sfOverlaps>( " + GIVEN_POLYGON_IN_WKT + ", ?o1)).\n"
								+ "}\n";
				
				String selection5 = prefixes
						+"SELECT ?s1 ?o1 \n"
						+ "WHERE { \n"
						+ "?s1 lgd:asWKT ?o1 . \n"
						+ "	FILTER(<http://www.opengis.net/def/function/geosparql/sfCrosses>(?o1, " + GIVEN_LINE_IN_WKT + ")). \n"
								+ "} \n";
				
				String selection6 = prefixes
						+ "SELECT ?s1 ?o1 \n"
						+ "WHERE { \n"
						+ "	?s1 geonames:asWKT ?o1 . \n"
						+ "	FILTER(<http://www.opengis.net/def/function/geosparql/sfWithin>(?o1, " + GIVEN_POLYGON_IN_WKT + ")). \n"
								+ "}  \n";
				
				String selection7 = prefixes
						+ "SELECT ?s1 \n"
						+ "WHERE { \n"
						+ "	?s1 geonames:asWKT ?o1 . \n"
						+ "	FILTER(<http://www.opengis.net/def/function/geosparql/sfWithin>(?o1, geof:buffer(" + GIVEN_POINT_IN_WKT + ", 3000, opengis:metre))). \n "
								+ "} \n";
				
				String selection8 = prefixes
						+ "SELECT ?s1 \n"
						+ "WHERE { \n"
						+ "	?s1 geonames:asWKT ?o1 . \n "
						+ "	FILTER(<http://www.opengis.net/def/function/geosparql/distance>(?o1, " + GIVEN_POINT_IN_WKT + ", opengis:metre) <= 3000).  \n"
								+ "} \n";
				
				String selection9 = prefixes 
						+ "SELECT ?s1 ?o1 \n"
						+ "WHERE { \n"
						+ "	?s1 geonames:asWKT ?o1 . \n"
						+ "	FILTER(<http://www.opengis.net/def/function/geosparql/sfDisjoint>(?o1, " + GIVEN_POLYGON_IN_WKT + ")). \n"
								+ "} \n ";
				
				String selection10 = prefixes
						+ "SELECT ?s1 ?o1 \n"
						+ "WHERE { \n"
						+ "	?s1 lgd:asWKT ?o1 . \n"
						+ "	FILTER(<http://www.opengis.net/def/function/geosparql/sfDisjoint>(?o1, " + GIVEN_POLYGON_IN_WKT + ")). \n"
								+ "} \n";
				
				
				String nontop0 = prefixes
						+ "SELECT (<http://www.opengis.net/def/function/geosparql/boundary>(?o1) AS ?ret) \n"
						+ "WHERE { \n"
						+ "	?s1 clc:asWKT ?o1 }\n ";
				
				String nontop1 = prefixes
						+ "SELECT (<http://www.opengis.net/def/function/geosparql/envelope>(?o1) AS ?ret) \n"
						+ "WHERE { \n"
						+ "	?s1 clc:asWKT ?o1 \n"
						+ "} \n ";
				
				String nontop2 = prefixes
						+ "SELECT (<http://www.opengis.net/def/function/geosparql/convexHull>(?o1) AS ?ret) \n"
						+ "WHERE { \n"
						+ "	?s1 clc:asWKT ?o1 \n"
						+ "} \n";
				
				String nontop3 = prefixes
						+ "SELECT (<http://www.opengis.net/def/function/geosparql/buffer>(?o1, 4, opengis:metre) AS ?ret) \n"
						+ "WHERE { \n"
						+ "  ?s1 geonames:asWKT ?o1 \n"
						+ "} \n";
				
				String nontop4 = prefixes
						+ "SELECT (<http://www.opengis.net/def/function/geosparql/buffer>(?o1, 4, opengis:metre) AS ?ret) \n"
						+ "WHERE { \n"
						+ "	?s1 lgd:asWKT ?o1 \n"
						+ "} \n";
				
				String nontop5 = prefixes
						+ "SELECT (<http://www.opengis.net/def/function/geosparql/area>(?o1) as ?ret) \n"
						+ "WHERE { \n"
						+ "	?s1 clc:asWKT ?o1 \n"
						+ "} \n";
				
				String rapidmapping0 = prefixes
						+ "SELECT  ?a  ?aGeoWKT \n"
						+ "WHERE { \n"
						+ "?a rdf:type clc:Area."
						+ "?a clc:hasID ?aID."
						+ "	?a clc:hasLandUse ?aLandUse."
	//					+ "	?a clc:hasGeometry ?aGeo."
						+ "		?a clc:asWKT ?aGeoWKT. "
						+ "	FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>( "+  GIVEN_POLYGON_IN_WKT + ", ?aGeoWKT))"
								+ "}";
				
				String rapidmapping1 = prefixes
						+ "SELECT ?r ?rName ?rGeoWKT"
						+ "WHERE { "
						+ "		?r rdf:type lgd:HighwayThing."
						+ "		?r rdfs:label ?rName."
						+ "		?r lgd:hasGeometry ?rGeo."
						+ "		?rGeo lgd:asWKT ?rGeoWKT."
						+ "		FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?rGeoWKT, " + GIVEN_POLYGON_IN_WKT + "^^geo:wktLiteral))"
								+ "} ";
				
				String rapidmapping2 = prefixes
						+ "SELECT (geof:boundary(?gGeoWKT) as ?boundary) ?gLabel "
						+ "WHERE { "
						+ "	?g rdf:type gag:Municipality."
						+ "	?g rdfs:label ?gLabel."
						+ "	?g gag:hasGeometry ?gGeo."
						+ "	?gGeo gag:asWKT ?gGeoWKT."
						+ "		FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?gGeoWKT, " + GIVEN_POLYGON_IN_WKT + "^^geo:wktLiteral))"
								+ "} ";
				
				String rapidmapping3 = prefixes
						+ "SELECT  ?h \n"
						+ "WHERE {  "
						+ "?h rdf:type noa:Hotspot."
						+ "	?h noa:isDerivedFromSensor ?sensor."
						+ "	?h noa:hasConfidence ?confidence."
						+ "	?h noa:isProducedBy ?producer."
						+ "?h noa:isDerivedFromSatellite ?satellite."
						+ "	?h noa:producedFromProcessingChain ?chain."
						+ "	?h noa:hasConfirmation ?confirmation ."
						+ "?h noa:hasAcquisitionTime \"2007-09-30T11:30:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime> ."
						+ "		?h noa:hasGeometry ?geometry. "
						+ "	OPTIONAL {?h noa:refinedBy ?r} "
						+ "		FILTER(!bound(?r)) ."
				//	+"?h noa:hasAcquisitionTime ?o ."
						+ "		?h noa:asWKT ?wkt . " //CHANGED THIS
						+ "		FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>( " + GIVEN_POLYGON_IN_WKT + " , ?wkt))"
								+ "}";
				
				String rapidmapping4 = prefixes
						+"SELECT distinct ?a \n"
						+ "WHERE { \n"
						+ "		?h rdf:type noa:Hotspot."
					  + "		?h noa:hasGeometry ?hGeo."
						+ "		?h noa:hasAcquisitionTime " + TIMESTAMP + "^^xsd:dateTime. "
								+ "	?h noa:asWKT ?hWKT. " //CHANGED THIS!
								+ "	?a rdf:type clc:Area."
								+ "	?a clc:hasGeometry ?aGeo."
								+ "?a clc:hasLandUse clc:ConiferousForest. "
								+ "	?a clc:asWKT ?aWKT. "
								+ "	FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?hWKT, ?aWKT)) . "
								+ "	FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>( " + GIVEN_POLYGON_IN_WKT + ", ?aWKT))"
										+ "} limit 10";
				
				String rapidmapping5 = prefixes
						+"SELECT ?r (geof:difference(?rWKT, ?hWKT) as ?diff) "
						+ "WHERE { "
						+ "		?h rdf:type noa:Hotspot."
						+ "	?h noa:hasGeometry ?hGeo."
						+ "		?h noa:hasAcquisitionTime ?hAcqTime. "
						+ "		?hGeo noa:asWKT ?hWKT. "
						+ "		?r rdf:type lgd:HighwayThing."
						+ "		?r lgd:hasGeometry ?rGeo."
						+ "	?rGeo lgd:asWKT ?rWKT. "
						+ "	FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?rWKT, ?hWKT)) .  "
						+ "	FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?rWKT, " + GIVEN_POLYGON_IN_WKT + "^^geo:wktLiteral))"
								+ "} ";
				
				String 	mapsearch0 = prefixes
						+ "SELECT ?f ?name ?geo ?wkt"
						+ "WHERE { "
						+ "	?f geonames:name ?name."
						+ "	?f geonames:hasGeometry ?geo."
						+ "	?geo geonames:asWKT ?wkt."
						+ "		FILTER(?name = " + TOPONYME + "^^xsd:string) "
								+ "}";
				
				String mapsearch1 = prefixes
						+ "SELECT ?f ?name ?fGeo ?code ?parent ?class ?fGeoWKT"
						+ "WHERE { "
						+ "	?f geonames:name ?name. "
						+ "	?f geonames:hasGeometry ?fGeo."
						+ "	?f geonames:featureCode ?code."
						+ "		?f geonames:parentFeature ?parent."
						+ "		?f geonames:featureClass ?class. "
						+ "		?fGeo geonames:asWKT ?fGeoWKT. "
						+ "		FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?fGeoWKT, " + GIVEN_RECTANGLE_IN_WKT + "^^geo:wktLiteral))."
								+ "} ";
				
				String mapsearch2 = prefixes
						+ "SELECT ?r ?label ?rGeo ?rGeoWKT"
						+ "WHERE { "
						+ "		?r rdf:type ?type. "
						+ "		OPTIONAL{ ?r rdfs:label ?label }. "
						+ "		?r lgd:hasGeometry ?rGeo. "
						+ "		?rGeo lgd:asWKT ?rGeoWKT. "
						+ "		FILTER(<http://www.opengis.net/def/function/geosparql/sfIntersects>(?rGeoWKT, " + GIVEN_RECTANGLE_IN_WKT + "^^geo:wktLiteral))."
								+ "}";
				
				//System.out.println("Query: " +  selection1);
						
				 TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, rapidmapping4);
			      
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

