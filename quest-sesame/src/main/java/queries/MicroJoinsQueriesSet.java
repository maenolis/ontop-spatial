/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright (C) 2013, Pyravlos Team
 *
 */
package queries;

import org.apache.log4j.Logger;

import systemsundertest.SystemUnderTest;

/**
 * @author George Garbis <ggarbis@di.uoa.gr>
 */
public class MicroJoinsQueriesSet extends QueriesSet {

	static Logger logger = Logger.getLogger(MicroJoinsQueriesSet.class.getSimpleName());
		
	public  MicroJoinsQueriesSet(SystemUnderTest sut) {
		super(sut);
		queriesN = 9; // IMPORTANT: Add/remove queries in getQuery implies changing queriesN
	}

	@Override
	public int getQueriesN() { return queriesN; }
	
	private String queryTemplate = prefixes 
			+ "\n select ?s1 ?s2 where { \n"
			+ "	?s1 ASWKT1 ?o1 . \n"
			+ "	?s2 ASWKT2 ?o2 . \n"
			+ "  FILTER(<http://www.opengis.net/def/function/geosparql/FUNCTION>(?o1, ?o2)).  \n"
			+ "} \n"
			;
	
	private String queryTemplate2 = prefixes 
			+ "\n select ?s1 ?s2 where { \n"
			+ "	?s1 ASWKT1 ?o1 . \n"
			+ "?s1 rdf:type <http://linkedgeodata.org/ontology/Road> . \n"
			+ "	?s2 ASWKT2 ?o2 . \n"
			+ "?s2 rdf:type <http://linkedgeodata.org/ontology/Road> . \n"
		//	+ " FILTER(?s1 != ?s2).  \n"
			+ " FILTER(<http://www.opengis.net/def/function/geosparql/FUNCTION>(?o1, ?o2)).  \n"
			+ "} \n"
			;

	private String queryTemplate3 = prefixes 
			+ "\n select ?s1 ?s2 where { \n"
			+ "	?s1 ASWKT1 ?o1 . \n"
			+ "?s1 rdf:type <http://linkedgeodata.org/ontology/Building> . \n"
			+ "?s1 <http://linkedgeodata.org/ontology/type> \"Museum\" . \n"
			+ "	?s2 ASWKT2 ?o2 . \n"
			+ "?s2 rdf:type <http://linkedgeodata.org/ontology/Landuse> . \n"
		//	+ " FILTER(?s1 != ?s2).  \n"
			+ " FILTER(<http://www.opengis.net/def/function/geosparql/FUNCTION>(?o1, ?o2)).  \n"
			+ "} \n"
			;
	
	private String queryTemplate4 = prefixes 
			+ "\n select ?s1 ?s2 where { \n"
			+ "	?s1 ASWKT1 ?o1 . \n"
			+ "	?s2 ASWKT2 ?o2 . \n "
			+ "?s2 rdf:type TYPE . \n"
			+ "  FILTER(<http://www.opengis.net/def/function/geosparql/FUNCTION>(?o1, ?o2)).  \n"
			+ "} \n"
			;
	
	private String gadm_clc_extra = prefixes 
			+ "\n select ?s1 ?s2 where { \n"
			+ "	?s1 ASWKT1 ?o1 . \n"
			+ "	?s2 ASWKT2 ?o2 . \n "
			+ "?s2  rdf:type clc:Area . \n "
			+ "?s2 clc:hasLandUse ?type . "
			+ "?s2 clc:hasArea ?area . \n"
			+ "?s2 clc:hasShapeA ?shape .\n"		
			+ "  FILTER(<http://www.opengis.net/def/function/geosparql/FUNCTION>(?o1, ?o2)).  \n"
			+ "} \n"
			;
	
	private String lgd_building_gadm = prefixes 
			+ "\n select ?s1 ?s2 where { \n"
			+ "	?s1 ASWKT1 ?o1 . \n"
			+ "	?s2 ASWKT2 ?o2 . \n "
			+ "?s1 rdf:type lgd:Building . \n"
			+ "?s1 lgd:name ?name . \n"
			+ "?s1 lgd:type ?type . \n"
			+ "  FILTER(<http://www.opengis.net/def/function/geosparql/FUNCTION>(?o1, ?o2)).  \n"
			+ "} \n"
			;	
	
	@Override
	public QueryStruct getQuery(int queryIndex, int repetition) {
	
		String query = null, label = null;
		
		// IMPORTANT: Add/remove queries in getQuery implies changing queriesN and changing case numbers
		switch (queryIndex) {
		
				
			case 0:
				// Q13 Areas contained in a country
				label = "Within_CLC_GADM";
				query = queryTemplate;
				//query = query.replace("GRAPH1", clc);
				query = query.replace("ASWKT1", clc_asWKT);
				//query = query.replace("GRAPH2", gadm);
				query = query.replace("ASWKT2", gadm_asWKT);
				query = query.replace("FUNCTION", "sfWithin");
				break;
				

				
				
			// -- Touches -- //
			case 1:
				// Q11 Countries with sharing borders
				label = "Intersects_GADM_GADM"; 
				query = queryTemplate;
				//query = query.replace("GRAPH1", gadm);
				query = query.replace("ASWKT1", gadm_asWKT);
				//query = query.replace("GRAPH2", gadm);
				query = query.replace("ASWKT2", gadm_asWKT);
				query = query.replace("FUNCTION", "sfIntersects");
				break;
			
			// -- Overlaps -- //
			case 2:
				// Q12 Areas overlaping countries
				label = "Overlaps_GADM_CLC"; 
				query = queryTemplate;
				//query = query.replace("GRAPH1", gadm);
				query = query.replace("ASWKT1", gadm_asWKT);
				//query = query.replace("GRAPH2", clc);
				query = query.replace("ASWKT2", clc_asWKT);
				query = query.replace("FUNCTION", "sfOverlaps");
				break;
	
					// -- Crosses -- //
			case 3:
				// Q7 Roads leaving/reaching an area
				label = "Intersects_LGD_GADM"; 
				query = queryTemplate4;
				//query = query.replace("GRAPH1", lgd);
				query = query.replace("ASWKT1", gadm_asWKT);
				//query = query.replace("GRAPH2", gadm);
				query = query.replace("ASWKT2", lgd_asWKT);
				query = query.replace("TYPE", lgd_building);
				query = query.replace("FUNCTION", "sfIntersects");
				break;
				
			case 4:
				// Q9 Intercrossing roads
				label = "Intersects_LGD_LGD_Museum"; 
				query = queryTemplate3;
				//query = query.replace("GRAPH1", lgd);
				query = query.replace("ASWKT1", lgd_asWKT);
				//query = query.replace("GRAPH2", lgd);
				query = query.replace("ASWKT2", lgd_asWKT);
				query = query.replace("FUNCTION", "sfIntersects");
				break;
				
			case 5:
				// Q7 Roads leaving/reaching an area
				label = "LGD_GADM"; 
				query = queryTemplate;
				//query = query.replace("GRAPH1", lgd);
				query = query.replace("ASWKT1", lgd_asWKT);
				//query = query.replace("GRAPH2", gadm);
				query = query.replace("ASWKT2", gadm_asWKT);
				query = query.replace("FUNCTION", "sfIntersects");
				break;
				
			case 6:
				// Q12 Areas overlaping countries
				label = "queryTemplate"; 
				query = queryTemplate;
				//query = query.replace("GRAPH1", gadm);
				query = query.replace("ASWKT1", lgd_asWKT);
				//query = query.replace("GRAPH2", clc);
				query = query.replace("ASWKT2", clc_asWKT);
				query = query.replace("FUNCTION", "sfOverlaps");
				break;
				
			
			case 7:
				// Q9 Intersectjng lgd
				label = "Intersects_LGD_LGD"; 
				query = queryTemplate;
				//query = query.replace("GRAPH1", lgd);
				query = query.replace("ASWKT1", lgd_asWKT);
				//query = query.replace("GRAPH2", lgd);
				query = query.replace("ASWKT2", lgd_asWKT);
				query = query.replace("FUNCTION", "sfIntersects");
				break;
				
			case 8:
				// Q9 Intercrossing roads
				label = "Crosses_LGD_LGD_Roads"; 
				query = queryTemplate2;
				//query = query.replace("GRAPH1", lgd);
				query = query.replace("ASWKT1", lgd_asWKT);
				//query = query.replace("GRAPH2", lgd);
				query = query.replace("ASWKT2", lgd_asWKT);
				query = query.replace("FUNCTION", "sfCrosses");
				break;
				

				
				
			default:
			logger.error("No such query number exists:"+queryIndex);
		}
		
		String translatedQuery = sut.translateQuery(query, label);
		return new QueryStruct(translatedQuery, label);
	}
	
}
