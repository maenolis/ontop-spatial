package gr.uoa.di.temporal.factory;

import gr.uoa.di.temporal.dependency.DIHolder;
import gr.uoa.di.temporal.obda.TemporalVocabulary;
import gr.uoa.di.temporal.sparql.function.TemporalFunction;
import gr.uoa.di.temporal.sparql.function.TemporalFunctionImpl;
import gr.uoa.di.temporal.sparql.predicate.TemporalBooleanOperationPredicate;
import gr.uoa.di.temporal.sparql.predicate.TemporalBooleanOperationPredicateImpl;
import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.Term;

import java.util.List;

public enum TemporalFactoryImpl implements TemporalFactory {

    INSTANCE;

    @Override
    public TemporalBooleanOperationPredicate createTemporalPredicate(final String name) {
        return new TemporalBooleanOperationPredicateImpl(name);
    }

    @Override
    public TemporalFunction createTemporalFunction(Predicate functor, Term... terms) {
        return new TemporalFunctionImpl(functor, terms);
    }

    @Override
    public TemporalFunction createTemporalFunction(Predicate functor, List<Term> terms) {
        return new TemporalFunctionImpl(functor, terms);
    }



}
