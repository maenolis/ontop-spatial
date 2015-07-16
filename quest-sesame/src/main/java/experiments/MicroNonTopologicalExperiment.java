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

import queries.MicroNonTopologicalQueriesSet;
import systemsundertest.SystemUnderTest;

/**
 * @author George Garbis <ggarbis@di.uoa.gr>
 */
public class MicroNonTopologicalExperiment extends Experiment {

	public MicroNonTopologicalExperiment(SystemUnderTest sut, int repetitions, int timeoutSecs, String logPath) {
		super(sut, repetitions, timeoutSecs, logPath);
		logger = Logger.getLogger(MicroNonTopologicalExperiment.class.getSimpleName());
		queriesSet = new MicroNonTopologicalQueriesSet(sut);
	}

	public MicroNonTopologicalExperiment(SystemUnderTest sut, int repetitions, int timeoutSecs, int[] queriesToRun, String logPath) {
		super(sut, repetitions, timeoutSecs, queriesToRun, logPath);
		logger = Logger.getLogger(MicroNonTopologicalExperiment.class.getSimpleName());
		queriesSet = new MicroNonTopologicalQueriesSet(sut);
	}
}
