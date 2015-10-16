/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright (C) 2013, Pyravlos Team
 *
 */
package queries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import systemsundertest.SystemUnderTest;

/**
 * @author George Garbis <ggarbis@di.uoa.gr>
 */
public class MicroSelectionsQueriesSet extends QueriesSet {

	static Logger logger = Logger.getLogger(MicroSelectionsQueriesSet.class.getSimpleName());
	
	// Template to create queries
	private final String queryTemplate = prefixes 
			+ "\n select ?s1  where { \n"
			+ "	?s1 ASWKT1 ?o1 . \n"
			+ "  FILTER(<http://www.opengis.net/def/function/geosparql/FUNCTION>(GIVEN_SPATIAL_LITERAL,?o1)).  \n" 
			+ "}  \n" 
	;

	// Template to create OBDA queries with unions
	private final String queryTemplateUnions = prefixes 
			+ "\n select distinct ?s1  where { \n"
			+ "	?s1 ASWKT1 ?o1 . \n"
			+ "?s1 rdf:type ?type .\n"
			+ "  FILTER(<http://www.opengis.net/def/function/geosparql/FUNCTION>(GIVEN_SPATIAL_LITERAL,?o1)). \n"
			+ "FILTER( ?type >= TYPE )  \n" 
			+ "}  \n" 
	;
	
	private final String clcPlusTwo = prefixes 
			+ "\n select ?s1  where { \n"
			+ "	?s1 ASWKT1 ?o1 . \n"
			+ "?s2  rdf:type clc:Area . \n "
			+ "?s2 clc:hasLandUse ?type . "
			+ "  FILTER(<http://www.opengis.net/def/function/geosparql/FUNCTION>(GIVEN_SPATIAL_LITERAL,?o1)).  \n" 
			+ "}  \n" 
	;
	
	
	private final String clcPlusFour = prefixes 
			+ "\n select ?s1 ?shape ?area where { \n"
			+ "	?s1 ASWKT1 ?o1 . \n"
			+ "?s2  rdf:type clc:Area . \n "
			+ "?s2 clc:hasLandUse ?type . "
			+ "?s2 clc:hasArea ?area . \n"
			+ "?s2 clc:hasShapeA ?shape .\n"	
			+ "  FILTER(<http://www.opengis.net/def/function/geosparql/FUNCTION>(GIVEN_SPATIAL_LITERAL,?o1)).  \n" 
			+ "}  \n" 
	;
	
	private final String lgdPlusTwo = prefixes 
			+ "\n select ?s1  ?name ?type where { \n"
			+ "	?s1 ASWKT1 ?o1 ."
			+ "?s1 rdf:type lgd:Building . \n"
			+ "?s1 lgd:name ?name . \n"
			+ "?s1 lgd:type ?type . \n"
			+ "  FILTER(<http://www.opengis.net/def/function/geosparql/FUNCTION>(GIVEN_SPATIAL_LITERAL,?o1)).  \n" 
			+ "}  \n" ;

	
	

	private String givenPolygonFile = "givenPolygon.txt";
	private String givenLinesFile = "givenLine.txt";
	private String givenPolygon;
	private String bigPolygonFile = "bigpolygon.txt";
	private String bigPolygon;
	private String givenLine, givenLine2, givenLine3;
	private String givenPoint;
	private String givenRadius;
	
	public MicroSelectionsQueriesSet(SystemUnderTest sut) throws IOException {
		super(sut);
		queriesN = 24; // IMPORTANT: Add/remove queries in getQuery implies changing queriesN
		
		//String spatialDatatype = "<http://www.opengis.net/ont/geosparql#wktLiteral>";
		givenPoint = "\"POINT(23.71622 37.97945)\"";
		givenRadius = "3000";
		
		InputStream is = getClass().getResourceAsStream("/"+givenPolygonFile);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		givenPolygon = in.readLine();
		givenPolygon = "\""+givenPolygon+"\"";
		in.close();
		in = null;
		is.close();
		is = null;
		is = getClass().getResourceAsStream("/"+bigPolygonFile);
		in = new BufferedReader(new InputStreamReader(is));
		bigPolygon = in.readLine();
		bigPolygon = "\""+bigPolygon+"\"";
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
	}
	
	@Override
	public QueryStruct getQuery(int queryIndex, int repetition) {
		
		String query = null, label = null;
		
		// IMPORTANT: Add/remove queries in getQuery implies changing queriesN and changing case numbers
		switch (queryIndex) {

		// -- Equals -- //
		/*case 0:
			// Line = GivenLine
			label = "Equals_GADM_GivenLine"; 
			query = queryTemplate;
			query = query.replace("GRAPH1", lgd);
			query = query.replace("ASWKT1", lgd_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenLine);
			query = query.replace("FUNCTION", "sfEquals");
			break;*/
	
		case 0:
			// Polygon = GivenPolygon
			label = "Equals_GADM_GivenPolygon"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", gadm);
			query = query.replace("ASWKT1", gadm_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfEquals");
			break;

		case 1:
			// Polygon = GivenPolygon
			label = "Contains_GADM_GivenPolygon"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", gadm);
			query = query.replace("ASWKT1", gadm_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfOverlaps");
			break;

		case 2:
			// Polygon = GivenPolygon
			label = "Contains_GADM_GivenPolygon"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", gadm);
			query = query.replace("ASWKT1", gadm_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfContains");
			break;
		case 3:
			// Polygon = GivenPolygon
			label = "Equals_GADM_givenLine"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", gadm);
			query = query.replace("ASWKT1", gadm_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenLine);
			query = query.replace("FUNCTION", "sfCrosses");
			break;

		case 4:
			// Polygon = GivenPolygon
			label = "Overlaps_GADM_givenLine"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", gadm);
			query = query.replace("ASWKT1", gadm_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenLine);
			query = query.replace("FUNCTION", "sfOverlaps");
			break;

		case 5:
			// Polygon = GivenPolygon
			label = "Contains_GADM_givenLine"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", gadm);
			query = query.replace("ASWKT1", gadm_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenLine);
			query = query.replace("FUNCTION", "sfContains");
			break;
		// -- Intersects -- //
		/*case 2:
			// Line & GivenPolygon
			label = "Intersects_LGD_GivenPolygon"; 
			query = queryTemplate;
			query = query.replace("GRAPH1", lgd);
			query = query.replace("ASWKT1", lgd_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;*/
	
		case 6:
			// Polygon & GivenLine
			label = "Intersects_CLC_GivenLine"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", clc);
			query = query.replace("ASWKT1", clc_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenLine);
			query = query.replace("FUNCTION", "sfIntersects");
			break;

		case 7:
			// Polygon & GivenLine
			label = "Contains_CLC_GivenLine"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", clc);
			query = query.replace("ASWKT1", clc_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenLine);
			query = query.replace("FUNCTION", "sfContains");
			break;

		case 8:
			// Polygon & GivenLine
			label = "Equals_CLC_GivenLine"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", clc);
			query = query.replace("ASWKT1", clc_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenLine);
			query = query.replace("FUNCTION", "sfCrosses");
			break;
			

		case 9:
			// Polygon & GivenLine
			label = "Overlaps_CLC_GivenLine"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", clc);
			query = query.replace("ASWKT1", clc_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenLine);
			query = query.replace("FUNCTION", "sfOverlaps");
			break;
			
		// -- Overlaps -- //
		case 10:
			label = "Overlaps_CLC_GivenPolygon"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", clc);
			query = query.replace("ASWKT1", clc_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfOverlaps");
			break;
			
		case 11:
			// Polygon & GivenLine
			label = "Intersects_CLC_givenPolygon"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", clc);
			query = query.replace("ASWKT1", clc_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;

		case 12:
			// Polygon & GivenLine
			label = "Contains_CLC_givenPolygon"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", clc);
			query = query.replace("ASWKT1", clc_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfContains");
			break;

		case 13:
			// Polygon & GivenLine
			label = "Equals_CLC_givenPolygon"; 
			query = queryTemplate;
			//query = query.replace("GRAPH1", clc);
			query = query.replace("ASWKT1", clc_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfCrosses");
			break;
			
			// -- Intersects -- //
		case 14:
			// Line & GivenPolygon
			label = "Intersects_LGD_GivenPolygon";
			query = queryTemplate;
			// query = query.replace("GRAPH1", lgd);
			query = query.replace("ASWKT1", lgd_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;
			
		case 15:
			label = "Intersects_LGD_B";
			query = queryTemplateUnions;
			// query = query.replace("GRAPH1", lgd);
			query = query.replace("ASWKT1", lgd_asWKT);
			query = query.replace("TYPE", lgd_building);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;
			
		case 16:
			label = "Intersects_LGD_PL";
			query = queryTemplateUnions;
			// query = query.replace("GRAPH1", lgd);
			query = query.replace("ASWKT1", lgd_asWKT);
			query = query.replace("TYPE", lgd_place);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;
			
		case 17:
			label = "Intersects_LGD_POINT";
			query = queryTemplateUnions;
			// query = query.replace("GRAPH1", lgd);
			query = query.replace("ASWKT1", lgd_asWKT);
			query = query.replace("TYPE", lgd_point);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;
			
		case 18:
			label = "Intersects_LGD_LU";
			query = queryTemplateUnions;
			// query = query.replace("GRAPH1", lgd);
			query = query.replace("ASWKT1", lgd_asWKT);
			query = query.replace("TYPE", lgd_landuse);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;
			
		case 19:
			label = "Intersects_LGD_ROAD";
			query = queryTemplateUnions;
			// query = query.replace("GRAPH1", lgd);
			query = query.replace("ASWKT1", lgd_asWKT);
			query = query.replace("TYPE", lgd_road);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;
			
		case 20:
			label = "Intersects_LGD_bigPolygon";
			query = queryTemplate;
			// query = query.replace("GRAPH1", lgd);
			query = query.replace("ASWKT1", lgd_asWKT);
			//query = query.replace("TYPE", lgd_building);
			query = query.replace("GIVEN_SPATIAL_LITERAL", bigPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;
			
		case 21: 
			label = "Intersects_CLC_PLUS2";
			query = clcPlusTwo;
			query = query.replace("ASWKT1", lgd_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;

		case 22: 
			label = "Intersects_CLC_PLUS4";
			query = clcPlusFour;
			query = query.replace("ASWKT1", lgd_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;
			
		case 23: 
			label = "Intersects_LGD_PLUS2";
			query = lgdPlusTwo;
			query = query.replace("ASWKT1", lgd_asWKT);
			query = query.replace("GIVEN_SPATIAL_LITERAL", givenPolygon);
			query = query.replace("FUNCTION", "sfIntersects");
			break;
			
		default:
			logger.error("No such query number exists:"+queryIndex);
		}
	
		String translatedQuery = sut.translateQuery(query, label);
		return new QueryStruct(translatedQuery, label);
	}

}
