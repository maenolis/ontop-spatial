package gr.uoa.di.temporal.factory;

import gr.uoa.di.temporal.sparql.function.TemporalFunction;
import gr.uoa.di.temporal.sparql.predicate.TemporalBooleanOperationPredicate;
import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.Term;

import java.util.List;

public interface TemporalFactory {
    TemporalBooleanOperationPredicate createTemporalPredicate(final String name);

    TemporalFunction createTemporalFunction(Predicate functor, Term... terms);

    TemporalFunction createTemporalFunction(Predicate functor, List<Term> terms);
}
