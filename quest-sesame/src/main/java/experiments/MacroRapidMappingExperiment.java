/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright (C) 2013, Pyravlos Team
 *
 */
package experiments;

import java.io.IOException;

import org.apache.log4j.Logger;

import queries.MacroRapidMappingQueriesSet;
import systemsundertest.SystemUnderTest;

/**
 * @author George Garbis <ggarbis@di.uoa.gr>
 */
public class MacroRapidMappingExperiment extends MacroExperiment {
	
	public MacroRapidMappingExperiment(SystemUnderTest sut, int repetitions,
			int timeoutSecs, int runTimeInMinutes, String logPath) throws IOException {
		super(sut, repetitions, timeoutSecs, runTimeInMinutes, logPath);
		logger = Logger.getLogger(MacroRapidMappingExperiment.class.getSimpleName());
		queriesSet = new MacroRapidMappingQueriesSet(sut);
		this.runTimeInMinutes = runTimeInMinutes;
	}
	
	public MacroRapidMappingExperiment(SystemUnderTest sut, int repetitions,
			int timeoutSecs, int runTimeInMinutes, int[] queriesToRun, String logPath) throws IOException {
		super(sut, repetitions, timeoutSecs, runTimeInMinutes, logPath);
		logger = Logger.getLogger(MacroRapidMappingExperiment.class.getSimpleName());
		queriesSet = new MacroRapidMappingQueriesSet(sut);
		this.runTimeInMinutes = runTimeInMinutes;
		this.queriesToRun = queriesToRun;
	}
}

