package it.unibz.krdb.obda.model;

import java.io.Serializable;
import java.net.URI;

public interface OBDAMappingListener extends Serializable {
	
	/**
	 * Called when a mapping has been inserted into the currently selected data source.
	 */
	public void mappingInserted(URI srcid, String mapping_id);
	
	/**
	 * Called when a mapping has been deleted into the currently selected data source.
	 */
	public void mappingDeleted(URI srcid, String mapping_id);
		
	/**
	 * Called when a mapping has been updated into the currently selected datasource.
	 */
	public void mappingUpdated(URI srcid, String mapping_id, OBDAMappingAxiom mapping);

	/**
	 * Called when the current data sources has changed.
	 */
	public void currentSourceChanged(URI oldsrcid, URI newsrcid);
	
	/**
	 * Called when all mappings were removed, for all datasources.
	 */
	public void allMappingsRemoved();
}
