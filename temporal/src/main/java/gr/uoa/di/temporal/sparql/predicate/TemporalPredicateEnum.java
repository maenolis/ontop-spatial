package gr.uoa.di.temporal.sparql.predicate;

import gr.uoa.di.temporal.dependency.DIHolder;
import gr.uoa.di.temporal.obda.TemporalVocabulary;
import it.unibz.krdb.obda.model.Predicate;

import java.util.Arrays;
import java.util.List;


public enum TemporalPredicateEnum {
    HAS_SERIALIZATION(DIHolder.HAS_SERIALIZATION_PREDICATE, null, TemporalVocabulary.URIs.HAS_SERIALIZATION),
    HAS_PERIOD(DIHolder.HAS_PERIOD_PREDICATE, null, TemporalVocabulary.URIs.HAS_PERIOD),
    CONTAINS(DIHolder.CONTAINS_PREDICATE, TemporalVocabulary.Operators.CONTAINS, TemporalVocabulary.URIs.CONTAINS),
    CONTAINED_BY(DIHolder.CONTAINED_BY_PREDICATE, TemporalVocabulary.Operators.CONTAINED_BY, TemporalVocabulary.URIs.CONTAINED_BY),
    ADJACENT(DIHolder.ADJACENT_PREDICATE, TemporalVocabulary.Operators.ADJACENT, TemporalVocabulary.URIs.ADJACENT),
    OVERLAPS(DIHolder.OVERLAPS_PREDICATE, TemporalVocabulary.Operators.OVERLAPS, TemporalVocabulary.URIs.OVERLAPS),
    OVERLEFT(DIHolder.OVERLEFT_PREDICATE, TemporalVocabulary.Operators.OVERLEFT, TemporalVocabulary.URIs.OVERLEFT),
    OVERRIGHT(DIHolder.OVERRIGHT_PREDICATE, TemporalVocabulary.Operators.OVERRIGHT, TemporalVocabulary.URIs.OVERRIGHT),
    EQUALS(DIHolder.EQUALS_PREDICATE, TemporalVocabulary.Operators.EQUALS, TemporalVocabulary.URIs.EQUALS),
    NEQUALS(DIHolder.NEQUALS_PREDICATE, TemporalVocabulary.Operators.NEQUALS, TemporalVocabulary.URIs.NEQUALS),
    BEFORE(DIHolder.BEFORE_PREDICATE, TemporalVocabulary.Operators.BEFORE, TemporalVocabulary.URIs.BEFORE),
    AFTER(DIHolder.AFTER_PREDICATE, TemporalVocabulary.Operators.AFTER, TemporalVocabulary.URIs.AFTER);

    private static final List<Predicate> exclusions = Arrays.asList(HAS_SERIALIZATION.getPredicate(), HAS_PERIOD.getPredicate());

    private final Predicate predicate;
    private final String operator;
    private final String uri;

    TemporalPredicateEnum(final Predicate predicate, final String operator, final String uri) {
        this.predicate = predicate;
        this.operator = operator;
        this.uri = uri;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public String getOperator() {
        return operator;
    }

    public String getUri() {
        return uri;
    }

    public static TemporalPredicateEnum get(final String predicateName) {
        for (TemporalPredicateEnum val: TemporalPredicateEnum.values()) {
            if (val.getUri().equals(predicateName) || val.getPredicate().getName().equals(predicateName)) {
                return val;
            }
        }
        return null;
    }

    public static Predicate getPredicate(final String predicateName) {
        final TemporalPredicateEnum value = get(predicateName);
        return value != null ? value.getPredicate() : null;
    }

    public static String getOperator(final String predicateName) {
        final TemporalPredicateEnum value = get(predicateName);
        return value != null ? value.getOperator() : null;
    }

    public static boolean translate(Predicate predicate) {
        if (predicate == null) {
            return false;
        }
//        return !HAS_SERIALIZATION.getPredicate().equals(predicate) && !HAS_PERIOD.getPredicate().equals(predicate);
        return !exclusions.contains(predicate);
    }
}
