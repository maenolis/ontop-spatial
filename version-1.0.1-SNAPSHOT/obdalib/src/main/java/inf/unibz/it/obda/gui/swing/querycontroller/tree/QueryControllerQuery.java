package inf.unibz.it.obda.gui.swing.querycontroller.tree;

import inf.unibz.it.obda.api.controller.QueryController;
import inf.unibz.it.obda.api.controller.QueryControllerEntity;

import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

public class QueryControllerQuery extends QueryControllerEntity{

	private String id = "";
	private String query = "";
	
	public QueryControllerQuery(String id) {
		this.id = id;
		
	}
	
	public String getID() {
		return id;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public String getNodeName() {
		return id + ": " + query.toString();
	}
	
	public String toString() {
		return getNodeName();
	}

	
}