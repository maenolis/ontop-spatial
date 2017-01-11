package gr.uoa.di.temporal.sparql.predicate;

import it.unibz.krdb.obda.model.impl.BooleanOperationPredicateImpl;

public class TemporalBooleanOperationPredicateImpl extends BooleanOperationPredicateImpl implements TemporalBooleanOperationPredicate {

    public TemporalBooleanOperationPredicateImpl(String name) {
        super(name, 2);
    }
}
