/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright (C) 2013, Pyravlos Team
 *
 */
package experiments;

import org.apache.log4j.Logger;

import queries.MicroJoinsQueriesSet;
import queries.MicroJoinsUnifiedQueriesSet;
import systemsundertest.SystemUnderTest;

/**
 * @author George Garbis <ggarbis@di.uoa.gr>
 */
public class MicroJoinsUnifiedExperiment extends Experiment {
		   
	public MicroJoinsUnifiedExperiment(SystemUnderTest sut, int repetitions, int timeoutSecs, String logPath) {
		super(sut, repetitions, timeoutSecs, logPath);
		logger = Logger.getLogger(MicroJoinsUnifiedExperiment.class.getSimpleName());
		queriesSet = new MicroJoinsQueriesSet(sut);

	}
	
	public MicroJoinsUnifiedExperiment(SystemUnderTest sut, int repetitions, int timeoutSecs, int[] queriesToRun, String logPath) {
		super(sut, repetitions, timeoutSecs, queriesToRun, logPath);
		logger = Logger.getLogger(MicroJoinsExperiment.class.getSimpleName());
		queriesSet = new MicroJoinsUnifiedQueriesSet(sut);

	}
}
