package org.obda.owlrefplatform.core.abox;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owl.model.OWLAxiom;
import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLEntity;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLSubClassAxiom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Methods to create, serialize and deserialize SemanticIndex
 * 
 * @author Sergejs Pugacs
 * 
 */
public class SemanticIndexBuilder {
	private static Logger log = LoggerFactory
			.getLogger(SemanticIndexBuilder.class);

	/**
	 * @param ontologies
	 * 
	 * @return dictionary of OWL entity and its index range
	 */
	public static Map<OWLEntity, SemanticIndexRange> build(HashSet<OWLOntology> ontologies) {
		HashMap<OWLEntity, SemanticIndexRange> index_range = new HashMap<OWLEntity, SemanticIndexRange>();

		Iterator<OWLOntology> ontologyIterator = ontologies.iterator();

		while (ontologyIterator.hasNext()) {
			OWLOntology onto = ontologyIterator.next();
			log.debug("Generating SemanticIndex for ontology: " + onto);

			// subclass relations = edges in the DAG
			Set<OWLAxiom> nodes = onto.getAxioms();
			for (OWLAxiom ax : nodes) {
				if (ax instanceof OWLSubClassAxiom) {
					OWLSubClassAxiom edge = (OWLSubClassAxiom) ax;
					if (!edge.getSubClass().isAnonymous()
							&& !edge.getSuperClass().isAnonymous()
							&& !edge.getSubClass().isOWLNothing()
							&& !edge.getSuperClass().isOWLThing()) {
						OWLClass subClass = edge.getSubClass().asOWLClass();
						OWLClass superClass = edge.getSuperClass().asOWLClass();
						// TODO add edge between sub and super to the DAG
						System.out.println(subClass+" "+ superClass);
					}

				}
			}
		}

		return index_range;
	}
}
