package gr.uoa.di.temporal.obda;

public class TemporalVocabulary {

    /**
     * The temporal predicate prefix.
     */
    private static final String TEMPORAL_PREDICATE_PREFIX = "temporal_";

    public static class General {
        public static final String INTERMEDIATE_LITERAL = "_intermediate";
        public static final String PERIOD_COLUMN_NAME = "period";
    }

    /**
     * URIs
     */
    public static class URIs {
        public static final String TEMPORAL_URI_PREFIX = "http://www.semanticweb.org/ontologies/2011/4/TemporalMeeting.owl#";
        public static final String HAS_SERIALIZATION = TEMPORAL_URI_PREFIX + Predicates.HAS_SERIALIZATION_NO_PREFIX;
        public static final String HAS_PERIOD = TEMPORAL_URI_PREFIX + Predicates.HAS_PERIOD_NO_PREFIX;
        public static final String CONTAINS = TEMPORAL_URI_PREFIX + Predicates.CONTAINS_NO_PREFIX;
        public static final String CONTAINED_BY = TEMPORAL_URI_PREFIX + Predicates.CONTAINED_BY_NO_PREFIX;
        public static final String ADJACENT = TEMPORAL_URI_PREFIX + Predicates.ADJACENT_NO_PREFIX;
        public static final String OVERLAPS = TEMPORAL_URI_PREFIX + Predicates.OVERLAPS_NO_PREFIX;
        public static final String OVERLEFT = TEMPORAL_URI_PREFIX + Predicates.OVERLEFT_NO_PREFIX;
        public static final String OVERRIGHT = TEMPORAL_URI_PREFIX + Predicates.OVERRIGHT_NO_PREFIX;
        public static final String EQUALS = TEMPORAL_URI_PREFIX + Predicates.EQUALS_NO_PREFIX;
        public static final String NEQUALS = TEMPORAL_URI_PREFIX + Predicates.NEQUALS_NO_PREFIX;
        public static final String BEFORE = TEMPORAL_URI_PREFIX + Predicates.BEFORE_NO_PREFIX;
        public static final String AFTER = TEMPORAL_URI_PREFIX + Predicates.AFTER_NO_PREFIX;
    }


    /**
     * Predicate Names
     */
    public static class Predicates {
        public static final String HAS_PERIOD_NO_PREFIX = "hasPeriod";
        public static final String HAS_PERIOD = TEMPORAL_PREDICATE_PREFIX + HAS_PERIOD_NO_PREFIX;

        public static final String HAS_SERIALIZATION_NO_PREFIX = "hasSerialization";
        public static final String HAS_SERIALIZATION = TEMPORAL_PREDICATE_PREFIX + HAS_SERIALIZATION_NO_PREFIX;

        public static final String CONTAINS_NO_PREFIX = "contains";
        public static final String CONTAINS = TEMPORAL_PREDICATE_PREFIX + CONTAINS_NO_PREFIX;

        public static final String CONTAINED_BY_NO_PREFIX = "containedBy";
        public static final String CONTAINED_BY = TEMPORAL_PREDICATE_PREFIX + CONTAINED_BY_NO_PREFIX;

        public static final String ADJACENT_NO_PREFIX = "adjacent";
        public static final String ADJACENT = TEMPORAL_PREDICATE_PREFIX + ADJACENT_NO_PREFIX;

        public static final String OVERLAPS_NO_PREFIX = "overlaps";
        public static final String OVERLAPS = TEMPORAL_PREDICATE_PREFIX + OVERLAPS_NO_PREFIX;

        public static final String OVERLEFT_NO_PREFIX = "overleft";
        public static final String OVERLEFT = TEMPORAL_PREDICATE_PREFIX + OVERLEFT_NO_PREFIX;

        public static final String OVERRIGHT_NO_PREFIX = "overright";
        public static final String OVERRIGHT = TEMPORAL_PREDICATE_PREFIX + OVERRIGHT_NO_PREFIX;

        public static final String EQUALS_NO_PREFIX = "equals";
        public static final String EQUALS = TEMPORAL_PREDICATE_PREFIX + EQUALS_NO_PREFIX;

        public static final String NEQUALS_NO_PREFIX = "nequals";
        public static final String NEQUALS = TEMPORAL_PREDICATE_PREFIX + NEQUALS_NO_PREFIX;

        public static final String BEFORE_NO_PREFIX = "before";
        public static final String BEFORE = TEMPORAL_PREDICATE_PREFIX + BEFORE_NO_PREFIX;

        public static final String AFTER_NO_PREFIX = "after";
        public static final String AFTER = TEMPORAL_PREDICATE_PREFIX + AFTER_NO_PREFIX;
    }



    /**
     * Operators
     */
    public static class Operators {
        public static final String CONTAINS = "%s @> %s";
        public static final String CONTAINED_BY = "%s <@ %s";
        public static final String ADJACENT = "adjacent(%s, %s)";
        public static final String OVERLAPS = "%s && %s";
        public static final String OVERLEFT = "%s &< %s";
        public static final String OVERRIGHT = "%s &> %s";
        public static final String EQUALS = "%s = %s";
        public static final String NEQUALS = "%s != %s";
        public static final String BEFORE = "%s << %s";
        public static final String AFTER = "%s >> %s";
    }
}
