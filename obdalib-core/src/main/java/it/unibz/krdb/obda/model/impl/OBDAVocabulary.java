package it.unibz.krdb.obda.model.impl;

/*
 * #%L
 * ontop-obdalib-core
 * %%
 * Copyright (C) 2009 - 2014 Free University of Bozen-Bolzano
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

import it.unibz.krdb.obda.model.Constant;
import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.Predicate.COL_TYPE;

public class OBDAVocabulary {
	
	/*GeoSPARQL namespaces*/
	
	public static final String GEOSPARQL_FUNCTION_NS = "<http://www.opengis.net/def/function/geosparql/";

	/* Constants */

	public static final Constant NULL = new ValueConstantImpl("null", COL_TYPE.STRING);

	public static final Constant TRUE = new ValueConstantImpl("t", COL_TYPE.BOOLEAN);

	public static final Constant FALSE = new ValueConstantImpl("f", COL_TYPE.BOOLEAN);

	/* Numeric operations */

	public static final String MINUS_STR = "minus";
	
	public static final String ADD_STR = "add";
	
	public static final String SUBSTRACT_STR = "substract";
	
	public static final String MULTIPLY_STR = "multiply";
	
	/* Numeric operation predicates */
	
	public static final Predicate MINUS = new NumericalOperationPredicateImpl(
			MINUS_STR, 1);
	
	public static final Predicate ADD = new NumericalOperationPredicateImpl(
			ADD_STR, 2);
	
	public static final Predicate SUBSTRACT = new NumericalOperationPredicateImpl(
			SUBSTRACT_STR, 2);
	
	public static final Predicate MULTIPLY = new NumericalOperationPredicateImpl(
			MULTIPLY_STR, 2);
	
	/* Boolean predicate URIs */

	public static final String strAND = "AND";

	public static final String strEQ = "EQ";

	public static final String strGTE = "GTE";

	public static final String strGT = "GT";

	public static final String strLTE = "LTE";

	public static final String strLT = "LT";

	public static final String strNEQ = "NEQ";

	public static final String strNOT = "NOT";

	public static final String strOR = "OR";

	public static final String strIS_NULL = "IS_NULL";

	public static final String strIS_NOT_NULL = "IS_NOT_NULL";

	public static final String strIS_TRUE = "IS_TRUE";
	
	/*GeoSPARQL geometry topology functions*/
	
	public static final String strOverlaps = "OVERLAPS";
	
	public static final String sfEquals = "SF-EQUALS";
	public static final String sfDisjoint = "SF-DISJOINT";
	public static final String sfIntersects = "SF-INTERSECTS";
	public static final String sfTouches = "SF-TOUCHES";
	public static final String sfWithin = "SF-WITHIN";
	public static final String sfContains = "SF-CONTAINS";
	public static final String sfCrosses = "SF-CROSSES";
	
	public static final String ehEquals = "EH-EQUALS";
	public static final String ehDisjoint = "EH-DISJOINT";
	public static final String ehOverlap = "EH-OVERLAP";
	public static final String ehCovers = "EH-COVERS";
	public static final String ehCoveredBy = "EH-COVEREDBY";
	public static final String ehInside = "EH-INSIDE";
	public static final String ehContains = "EH-CONTAINS";

	public static final String sfdistance = "SF-DISTANCE";
	public static final String sfbuffer = "SF-BUFFER";
	public static final String sfconvexHull = "SF-CONVEXHULL";
	public static final String sfIntersection = "SF-INTERSECTION";
	public static final String sfUnion = "SF-UNION";
	public static final String sfDifference = "SF-DIFFERENCE";
	public static final String sfSymDifference = "SF-SYMDIFFERENCE";
	public static final String sfEnvelope = "SF-ENVELOPE";
	public static final String sfBoundary = "SF-BOUNDARY";
	public static final String sfGetSRID = "SF-SRID";


	public static final String strGeomFromWKT = "GEOMFROMWKT";
	
	/* Boolean predicates */

	public static final Predicate AND = new BooleanOperationPredicateImpl(
			strAND, 2);

	public static final Predicate EQ = new BooleanOperationPredicateImpl(
			strEQ, 2);
	
	public static final Predicate OVERLAPS = new BooleanOperationPredicateImpl(
			strOverlaps, 2);
	
	public static final Predicate SFCONTAINS  = new BooleanOperationPredicateImpl(
			sfContains, 2);
	
	public static final Predicate SFCROSSES = new BooleanOperationPredicateImpl(
			sfCrosses, 2);
	public static final Predicate SFDISJOINT = new BooleanOperationPredicateImpl(
			sfDisjoint, 2);
	public static final Predicate SFINTERSECTS = new BooleanOperationPredicateImpl(
			sfIntersects, 2);
	public static final Predicate SFTOUCHES = new BooleanOperationPredicateImpl(
			sfTouches, 2);
	public static final Predicate SFWITHIN = new BooleanOperationPredicateImpl(
			sfWithin, 2);
	public static final Predicate SFEQUALS = new BooleanOperationPredicateImpl(
			sfEquals, 2);

	public static final Predicate EHCOVEREDBY = new BooleanOperationPredicateImpl(
			ehCoveredBy, 2);
	public static final Predicate EHCOVERS = new BooleanOperationPredicateImpl(
			ehCovers, 2);
	public static final Predicate EHDISJOINT = new BooleanOperationPredicateImpl(
			ehDisjoint, 2);
	public static final Predicate EHEQUALS = new BooleanOperationPredicateImpl(
			ehEquals, 2);
	public static final Predicate EHINSIDE = new BooleanOperationPredicateImpl(
			ehInside, 2);
	public static final Predicate EHOVERLAPS = new BooleanOperationPredicateImpl(
			ehOverlap, 2);
	public static final Predicate EHCONTAINS = new BooleanOperationPredicateImpl(
			ehContains, 2);
	
	public static final Predicate SFDISTANCE = new BooleanOperationPredicateImpl(
			sfdistance, 2);
	public static final Predicate SFBUFFER = new BooleanOperationPredicateImpl(
			sfbuffer, 2);
	public static final Predicate SFCONVEXHULL = new BooleanOperationPredicateImpl(
			sfconvexHull, 2);
	public static final Predicate SFINTERSECTION = new BooleanOperationPredicateImpl(
			sfIntersection, 2);
	public static final Predicate SFUNION = new BooleanOperationPredicateImpl(
			sfUnion, 2);
	public static final Predicate SFDIFFERENCE = new BooleanOperationPredicateImpl(
			sfDifference, 2);
	public static final Predicate SFSYMDIFFERENCE = new BooleanOperationPredicateImpl(
			sfSymDifference, 2);
	public static final Predicate SFENVELOPE = new BooleanOperationPredicateImpl(
			sfEnvelope, 2);
	public static final Predicate SFBOUNDARY = new BooleanOperationPredicateImpl(
			sfBoundary, 2);
	public static final Predicate SFGETSRID = new BooleanOperationPredicateImpl(
			sfGetSRID, 2);
	
	
	public static final Predicate GEOMFROMWKT = new BooleanOperationPredicateImpl(
			strGeomFromWKT, 1);

	public static final Predicate GTE = new BooleanOperationPredicateImpl(
			strGTE, 2);

	public static final Predicate GT = new BooleanOperationPredicateImpl(
			strGT, 2);

	public static final Predicate LTE = new BooleanOperationPredicateImpl(
			strLTE, 2);

	public static final Predicate LT = new BooleanOperationPredicateImpl(
			strLT, 2);

	public static final Predicate NEQ = new BooleanOperationPredicateImpl(
			strNEQ, 2);

	public static final Predicate NOT = new BooleanOperationPredicateImpl(
			strNOT, 1);

	public static final Predicate OR = new BooleanOperationPredicateImpl(
			strOR, 2);

	public static final Predicate IS_NULL = new BooleanOperationPredicateImpl(
			strIS_NULL, 1);

	public static final Predicate IS_NOT_NULL = new BooleanOperationPredicateImpl(
			strIS_NOT_NULL, 1);
	
	public static final Predicate IS_TRUE = new BooleanOperationPredicateImpl(
			strIS_TRUE, 1);

	public static final String RDF_TYPE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";

	/* Data type predicate URIs */

	public static final String RDFS_LITERAL_URI = "http://www.w3.org/2000/01/rdf-schema#Literal";

	public static final String XSD_STRING_URI = "http://www.w3.org/2001/XMLSchema#string";

	public static final String XSD_INT_URI = "http://www.w3.org/2001/XMLSchema#int";

	public static final String XSD_INTEGER_URI = "http://www.w3.org/2001/XMLSchema#integer";

	public static final String XSD_DECIMAL_URI = "http://www.w3.org/2001/XMLSchema#decimal";

	public static final String XSD_FLOAT_URI = "http://www.w3.org/2001/XMLSchema#float";

	public static final String XSD_DOUBLE_URI = "http://www.w3.org/2001/XMLSchema#double";

	public static final String XSD_DATETIME_URI = "http://www.w3.org/2001/XMLSchema#dateTime";

	public static final String XSD_BOOLEAN_URI = "http://www.w3.org/2001/XMLSchema#boolean";
	
	public static final String GEOSPARQL_WKT_LITERAL_DATATYPE = "http://www.opengis.net/ont/geosparql#wktLiteral";

	/* Data type predicates */

	public static final Predicate RDFS_LITERAL = new DataTypePredicateImpl(
			RDFS_LITERAL_URI, new COL_TYPE[] { COL_TYPE.LITERAL });

	public static final Predicate RDFS_LITERAL_LANG = new DataTypePredicateImpl(
			RDFS_LITERAL_URI, new COL_TYPE[] { COL_TYPE.LITERAL, COL_TYPE.LITERAL });

	public static final Predicate XSD_STRING = new DataTypePredicateImpl(
			XSD_STRING_URI, COL_TYPE.STRING);

	public static final Predicate XSD_INTEGER = new DataTypePredicateImpl(
			XSD_INTEGER_URI, COL_TYPE.INTEGER);

	public static final Predicate XSD_DECIMAL = new DataTypePredicateImpl(
			XSD_DECIMAL_URI, COL_TYPE.DECIMAL);

	public static final Predicate XSD_DOUBLE = new DataTypePredicateImpl(
			XSD_DOUBLE_URI, COL_TYPE.DOUBLE);

	public static final Predicate XSD_DATETIME = new DataTypePredicateImpl(
			XSD_DATETIME_URI, COL_TYPE.DATETIME);

	public static final Predicate XSD_BOOLEAN = new DataTypePredicateImpl(
			XSD_BOOLEAN_URI, COL_TYPE.BOOLEAN);

	public static final Predicate[] QUEST_DATATYPE_PREDICATES = new Predicate[] {
			RDFS_LITERAL, XSD_STRING, XSD_INTEGER, XSD_DECIMAL, XSD_DOUBLE,
			XSD_DATETIME, XSD_BOOLEAN };
	
	public static final Predicate[] QUEST_NUMERICAL_DATATYPES = new Predicate[] {
			XSD_INTEGER, XSD_DECIMAL, XSD_DOUBLE };

	/* Common namespaces and prefixes */

	public static final String NS_XSD = "http://www.w3.org/2001/XMLSchema#";

	public static final String NS_RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

	public static final String NS_RDFS = "http://www.w3.org/2000/01/rdf-schema#";

	public static final String NS_OWL = "http://www.w3.org/2002/07/owl#";

	public static final String NS_QUEST = "http://obda.org/quest#";

	public static final String PREFIX_XSD = "xsd:";

	public static final String PREFIX_RDF = "rdf:";

	public static final String PREFIX_RDFS = "rdfs:";

	public static final String PREFIX_OWL = "owl:";

	public static final String PREFIX_QUEST = "quest:";

	/* Built-in function URIs */

	// The name of the function that creates URI's in Quest
	public static final String QUEST_URI = "URI";

	// The name of the function that creates URI's in Quest
	public static final String QUEST_BNODE = "BNODE";

	public static final String QUEST_TRIPLE_STR = "triple";

	public static final Predicate QUEST_TRIPLE_PRED = new PredicateImpl(
			QUEST_TRIPLE_STR, 3, new COL_TYPE[3]);

	public static final String QUEST_CAST_STR = "cast";

	public static final Predicate QUEST_CAST = new PredicateImpl(
			QUEST_CAST_STR, 2, new COL_TYPE[2]);
	
	public static final String QUEST_QUERY = "ans1";

	/* SPARQL Algebra vocabulary */

	public static final String SPARQL_JOIN_URI = "Join";

	public static final String SPARQL_LEFTJOIN_URI = "LeftJoin";

	public static final String SPARQL_LANGMATCHES_URI = "LangMatches";

	public static final String SPARQL_IS_LITERAL_URI = "isLiteral";

	public static final String SPARQL_IS_URI_URI = "isURI";

	public static final String SPARQL_IS_IRI_URI = "isIRI";

	public static final String SPARQL_IS_BLANK_URI = "isBlank";

	public static final String SPARQL_STR_URI = "str";

	public static final String SPARQL_DATATYPE_URI = "datatype";

	public static final String SPARQL_LANG_URI = "lang";

	public static final String SPARQL_REGEX_URI = "regex";
	
	public static final String SPARQL_LIKE_URI = "like";
	
	
	/*SPARQL spatial functions*/
	public static final String NS_STRDF = "<http://strdf.di.uoa.gr/ontology#>";
	
	public static final String overlap = "<http://strdf.di.uoa.gr/ontology#overlap>";
	

	/* SPARQL Algebra predicate */

	public static final Predicate SPARQL_JOIN = new AlgebraOperatorPredicateImpl(
			SPARQL_JOIN_URI, COL_TYPE.STRING);

	public static final Predicate SPARQL_LEFTJOIN = new AlgebraOperatorPredicateImpl(
			SPARQL_LEFTJOIN_URI, COL_TYPE.STRING);

	public static final Predicate SPARQL_IS_LITERAL = new BooleanOperationPredicateImpl(
			SPARQL_IS_LITERAL_URI);

	public static final Predicate SPARQL_IS_URI = new BooleanOperationPredicateImpl(
			SPARQL_IS_URI_URI);

	public static final Predicate SPARQL_IS_IRI = new BooleanOperationPredicateImpl(
			SPARQL_IS_IRI_URI);

	public static final Predicate SPARQL_IS_BLANK = new BooleanOperationPredicateImpl(
			SPARQL_IS_BLANK_URI);

	public static final Predicate SPARQL_LANGMATCHES = new BooleanOperationPredicateImpl(
			SPARQL_LANGMATCHES_URI, 2);

	public static final Predicate SPARQL_STR = new NonBooleanOperationPredicateImpl(
			SPARQL_STR_URI);

	public static final Predicate SPARQL_DATATYPE = new NonBooleanOperationPredicateImpl(
			SPARQL_DATATYPE_URI);

	public static final Predicate SPARQL_LANG = new NonBooleanOperationPredicateImpl(
			SPARQL_LANG_URI);

	public static final Predicate SPARQL_REGEX = new BooleanOperationPredicateImpl(
			SPARQL_REGEX_URI, 3);
	
	public static final Predicate SPARQL_LIKE = new BooleanOperationPredicateImpl(
			SPARQL_LIKE_URI, 2);



	
}
