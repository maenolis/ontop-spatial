package gr.uoa.di.temporal.dependency;

import gr.uoa.di.temporal.factory.TemporalFactory;
import gr.uoa.di.temporal.factory.TemporalFactoryImpl;
import gr.uoa.di.temporal.sparql.predicate.TemporalBooleanOperationPredicate;
import gr.uoa.di.temporal.obda.TemporalVocabulary;
import it.unibz.krdb.obda.model.OBDADataFactory;
import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;

public class DIHolder {
    private final static TemporalFactory TEMPORAL_FACTORY = TemporalFactoryImpl.INSTANCE;
    private static final OBDADataFactory OBDA_FACTORY = OBDADataFactoryImpl.getInstance();

    public static final Predicate HAS_SERIALIZATION_PREDICATE = OBDA_FACTORY.getObjectPropertyPredicate(TemporalVocabulary.URIs.HAS_SERIALIZATION); //TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.URIs.HAS_SERIALIZATION);
    public static final TemporalBooleanOperationPredicate HAS_PERIOD_PREDICATE = TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.URIs.HAS_PERIOD);
    public static final TemporalBooleanOperationPredicate CONTAINS_PREDICATE = TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.Predicates.CONTAINS);
    public static final TemporalBooleanOperationPredicate CONTAINED_BY_PREDICATE = TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.Predicates.CONTAINED_BY);
    public static final TemporalBooleanOperationPredicate AFTER_PREDICATE = TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.Predicates.AFTER);
    public static final TemporalBooleanOperationPredicate BEFORE_PREDICATE = TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.Predicates.BEFORE);
    public static final TemporalBooleanOperationPredicate EQUALS_PREDICATE = TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.Predicates.EQUALS);
    public static final TemporalBooleanOperationPredicate NEQUALS_PREDICATE = TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.Predicates.NEQUALS);
    public static final TemporalBooleanOperationPredicate OVERRIGHT_PREDICATE = TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.Predicates.OVERRIGHT);
    public static final TemporalBooleanOperationPredicate OVERLEFT_PREDICATE = TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.Predicates.OVERLEFT);
    public static final TemporalBooleanOperationPredicate OVERLAPS_PREDICATE = TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.Predicates.OVERLAPS);
    public static final TemporalBooleanOperationPredicate ADJACENT_PREDICATE = TEMPORAL_FACTORY.createTemporalPredicate(TemporalVocabulary.Predicates.ADJACENT);
}
