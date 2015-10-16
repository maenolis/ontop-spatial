/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright (C) 2013, Pyravlos Team
 *
 */

package systemsundertest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

import sesameWrapper.SesameVirtualRepo;
import systemsundertest.SystemUnderTest;


/**
 * @author Konstantina Bereta <konstantina.bereta@di.uoa.gr>
 * 
 */
public class OntopSpatialSUT implements SystemUnderTest {

	static Logger logger = Logger.getLogger(OntopSpatialSUT.class.getSimpleName());
	
	private BindingSet firstBindingSet;

	String owlfile = null;
	String obdafile = null;
	RepositoryConnection conn=null;
	Repository repo = null;
	
	public OntopSpatialSUT(String owlfile, String obdafile) throws Exception {
		
		this.owlfile = owlfile;
		this.obdafile = obdafile;	}

	public BindingSet getFirstBindingSet() {return firstBindingSet;}
	
	public Object getSystem()  {
		return repo;
	}   
	
	public void initialize()  {

		try {
			repo = new SesameVirtualRepo("my_name", owlfile, obdafile, false, "TreeWitness");
			repo.initialize();
		} catch (Exception e) {
			logger.fatal("Cannot initialize Ontop");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.fatal(stacktrace);
		}
	}

	static class Executor implements Runnable {
		private String query;
		private Repository repo;
		private long[] returnValue;
		private BindingSet firstBindingSet;
		
		public Executor(String query, Repository repo, int timeoutSecs) throws RepositoryException {
			this.query = query;
			this.repo = repo;
			this.returnValue = new long[]{timeoutSecs+1, timeoutSecs+1, timeoutSecs+1, -1};
			this.repo.initialize();
		}
		public long[] getRetValue() {return returnValue;}
		public BindingSet getFirstBindingSet() {return firstBindingSet;}
		
	    public void run() {	try {
			runQuery();
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TupleQueryResultHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }

		public void runQuery() throws MalformedQueryException, QueryEvaluationException, TupleQueryResultHandlerException, IOException, RepositoryException {

			logger.info("Evaluating query...");
			repo.initialize();
			TupleQuery tupleQuery = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, query);
		    tupleQuery.evaluate();

			
			long results = 0;
			
			long t1 = System.nanoTime();
			TupleQueryResult result = tupleQuery.evaluate();
			long t2 = System.nanoTime();
			
			if (result.hasNext()) { 
				this.firstBindingSet = result.next();
				results++;
			}
			while (result.hasNext()) {
				results++;
				result.next();
			}
			long t3 = System.nanoTime();
			
			logger.info("Query evaluated");
			this.returnValue = new long[]{t2-t1, t3-t2, t3-t1, results};
		}   
	}
	

	public long[] runQueryWithTimeout(String query, int timeoutSecs) throws RepositoryException {
        //maintains a thread for executing the doWork method
        final ExecutorService executor = Executors.newFixedThreadPool(1);
        //set the executor thread working
        Executor runnable = new Executor(query, repo, timeoutSecs);
        
        final Future<?> future = executor.submit(runnable);
        boolean isTimedout = false;
        //check the outcome of the executor thread and limit the time allowed for it to complete
		long tt1 = System.nanoTime();
        try {
        	logger.debug("Future started");
			future.get(timeoutSecs, TimeUnit.SECONDS);
			logger.debug("Future end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			isTimedout = true;
			logger.info("time out!");
			logger.info("Restarting Ontop...");
        	this.restart();
			logger.info("Closing Ontop...");
			this.close();
		}
        finally {
        	logger.debug("Future canceling...");
        	future.cancel(true);
        	logger.debug("Executor shutting down...");
            executor.shutdown();
            try {
            	logger.debug("Executor waiting for termination...");
				executor.awaitTermination(timeoutSecs, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.gc();
        }
        
        logger.debug("RetValue: "+runnable.getRetValue());
		
        if (isTimedout) {
			long tt2 = System.nanoTime();
			return new long[]{tt2-tt1, tt2-tt1, tt2-tt1, -1};
		} else {
			this.firstBindingSet = runnable.getFirstBindingSet();
			return runnable.getRetValue();
		}
    }
	
	public long[] runUpdate(String query) throws MalformedQueryException {

		logger.error("ERROR: Updates are not supported in Ontop!");
		return null;
	}

	public void close() {

		logger.info("Closing..");
		try {
			repo.shutDown();
			repo = null;
			firstBindingSet = null;
		} catch (Exception e) {}
		// TODO
//		Runtime run = Runtime.getRuntime();
//
//		Process pr = run.exec(restart_script);
//		pr.waitFor();
//
		System.gc();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.fatal("Cannot clear caches");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.fatal(stacktrace);
		}
		logger.info("Closed (caches not cleared)");
	}

	public void restart() {
		
		String[] restart_postgres = {"/bin/sh", "-c" , "echo  \"anylatdi\" |  sudo -S  /usr/sbin/service postgresql restart"};


    	Process pr;
    	
		try {
			logger.info("Restarting Ontop (Postgres) ...");

	    	pr = Runtime.getRuntime().exec(restart_postgres);
			pr.waitFor();
			if ( pr.exitValue() != 0) {
				logger.error("Something went wrong while stoping postgres");
			}

			Thread.sleep(5000);
			
			if (repo != null) {
				try{
					repo.shutDown();;
				} catch (Exception e) {
					logger.error("Exception occured while restarting Ontop. ");
					e.printStackTrace();
				} finally {
					repo = null;
				}
			}	
			firstBindingSet = null;
			repo = new SesameVirtualRepo("my_name", owlfile, obdafile, false, "TreeWitness");
			repo.initialize();
			logger.info("Ontop (Postgres) restarted");
		} catch (Exception e) {
			logger.fatal("Cannot restart Strabon");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.fatal(stacktrace);
		}
	}
	
	public void clearCaches() {
		
		String[] stop_postgres = {"/bin/sh", "-c" , " echo \"anylatdi\" | sudo -S service postgresql stop"};
    	String[] clear_caches = {"/bin/sh", "-c" , " echo \"anylatdi\" | sync && echo 3 > sudo -S /proc/sys/vm/drop_caches"};
    	String[] start_postgres = {"/bin/sh", "-c" , " echo \"anylatdi\" | sudo -S  service postgresql start"};

    	Process pr;
    	
		try {
			logger.info("Clearing caches...");

	    	pr = Runtime.getRuntime().exec(stop_postgres);
			pr.waitFor();
			System.out.println(pr.exitValue());
			if ( pr.exitValue() != 0) {
				logger.error("Something went wrong while stoping postgres");
			}
//			System.in.read();
			 
	    	pr = Runtime.getRuntime().exec(clear_caches);
			pr.waitFor();
			System.out.println(pr.exitValue());
			if ( pr.exitValue() != 0) {
				logger.error("Something went wrong while clearing caches");
			}
//			System.in.read();
			
			pr = Runtime.getRuntime().exec(start_postgres);
			pr.waitFor();
			System.out.println(pr.exitValue());
			if ( pr.exitValue() != 0) {
				logger.error("Something went wrong while starting postgres");
			}
			
			Thread.sleep(5000);
			logger.info("Caches cleared");
		} catch (Exception e) {
			logger.fatal("Cannot clear caches");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.fatal(stacktrace);
		}
	}

	public String translateQuery(String query, String label) {
		String translatedQuery = null;
		translatedQuery = query;
		
		translatedQuery = translatedQuery.replaceAll("geof:distance", "strdf:distance");
		
		if (label.matches("Get_CLC_areas")
				|| label.matches("Get_highways")
				|| label.matches("Get_municipalities")
				|| label.matches("Get_hotspots")
				|| label.matches("Get_coniferous_forests_in_fire")
				|| label.matches("Get_road_segments_affected_by_fire")) {
			translatedQuery = translatedQuery.replaceAll("<http://www.opengis.net/ont/geosparql#wktLiteral>", "strdf:WKT");			
		}
		
		if (label.matches("List_GeoNames_categories_per_CLC_category")
			|| label.matches("Count_GeoNames_categories_in_ContinuousUrbanFabric")) {
			translatedQuery = translatedQuery.replaceAll(
					" } \\n	FILTER\\(geof:sfIntersects\\(\\?clcWkt, \\?fWkt\\)\\)\\. \\\n",
					" \n	FILTER(geof:sfIntersects(?clcWkt, ?fWkt)). } \n");
		}
		
		return translatedQuery;
	}
}
