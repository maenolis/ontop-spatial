package systemsundertest;

import org.apache.log4j.Logger;

import systemsundertest.RunSystemUnderTest;

public class RunOntopSpatial extends RunSystemUnderTest {

	private static Logger logger = Logger.getLogger(RunOntopSpatial.class.getSimpleName());
	
	@Override
	public void addOptions() {
		super.addOptions();

		options.addOption("owl", "owlfile", true, "owlfile");
		options.addOption("map", "obdafile", true, "obdafile");
	}

	@Override
	public void logOptions() {
		super.logOptions();
		
		logger.info("Excluded options");
		logger.info("owlfile:\t"+cmd.getOptionValue("owlfile"));
		logger.info("obdafile:\t"+cmd.getOptionValue("obdafile"));
	}

	
	public void initSystemUnderTest() throws Exception {
		String owlfile = cmd.getOptionValue("owlfile");
		String obdafile = cmd.getOptionValue("obdafile");

		sut = new OntopSpatialSUT(owlfile, obdafile);		
	}

	public static void main(String[] args) throws Exception {
		RunSystemUnderTest RunStrabon = new RunOntopSpatial();
		
		RunStrabon.run(args);
	}
}
