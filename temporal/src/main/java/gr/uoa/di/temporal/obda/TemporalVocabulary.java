package gr.uoa.di.temporal.obda;

public class TemporalVocabulary {

    /**
     * Columns
     */
    public static final String PERIOC_COLUMN_NAME = "period";

    /**
     * URIs
     */
    public static class URIs {
        public static final String TEMPORAL_URI_PREFIX = "http://www.semanticweb.org/ontologies/2011/4/TemporalMeeting.owl#";
        public static final String HAS_SERIALIZATION = TEMPORAL_URI_PREFIX + Predicates.HAS_SERIALIZATION;
        public static final String HAS_PERIOD = TEMPORAL_URI_PREFIX + Predicates.HAS_PERIOD;
        public static final String CONTAINS = TEMPORAL_URI_PREFIX + Predicates.CONTAINS;
        public static final String CONTAINED_BY = TEMPORAL_URI_PREFIX + Predicates.CONTAINED_BY;
        public static final String ADJACENT = TEMPORAL_URI_PREFIX + Predicates.ADJACENT;
        public static final String OVERLAPS = TEMPORAL_URI_PREFIX + Predicates.OVERLAPS;
        public static final String OVERLEFT = TEMPORAL_URI_PREFIX + Predicates.OVERLEFT;
        public static final String OVERRIGHT = TEMPORAL_URI_PREFIX + Predicates.OVERRIGHT;
        public static final String EQUALS = TEMPORAL_URI_PREFIX + Predicates.EQUALS;
        public static final String NEQUALS = TEMPORAL_URI_PREFIX + Predicates.NEQUALS;
        public static final String BEFORE = TEMPORAL_URI_PREFIX + Predicates.BEFORE;
        public static final String AFTER = TEMPORAL_URI_PREFIX + Predicates.AFTER;
    }


    /**
     * Predicate Names
     */
    public static class Predicates {
        public static final String HAS_PERIOD = "hasPeriod";
        public static final String HAS_SERIALIZATION = "hasSerialization";
        public static final String CONTAINS = "contains";
        public static final String CONTAINED_BY = "contained_by";
        public static final String ADJACENT = "adjacent";
        public static final String OVERLAPS = "overlaps";
        public static final String OVERLEFT = "overleft";
        public static final String OVERRIGHT = "overright";
        public static final String EQUALS = "equals";
        public static final String NEQUALS = "nequals";
        public static final String BEFORE = "before";
        public static final String AFTER = "after";
    }



    /**
     * Operators
     */
    public static class Operators {
        public static final String CONTAINS = "%s @> %s";
        public static final String CONTAINED_BY = "%s <@ %s";
        public static final String ADJACENT = "TBD";
        public static final String OVERLAPS = "%s && %s";
        public static final String OVERLEFT = "%s &< %s";
        public static final String OVERRIGHT = "%s &> %s";
        public static final String EQUALS = "%s = %s";
        public static final String NEQUALS = "%s != %s";
        public static final String BEFORE = "%s << %s";
        public static final String AFTER = "%s >> %s";
    }
}
