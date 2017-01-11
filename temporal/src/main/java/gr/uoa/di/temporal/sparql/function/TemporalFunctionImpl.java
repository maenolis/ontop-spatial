package gr.uoa.di.temporal.sparql.function;

import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.Term;
import it.unibz.krdb.obda.model.impl.FunctionalTermImpl;

import java.util.List;

public class TemporalFunctionImpl extends FunctionalTermImpl implements TemporalFunction {

    public TemporalFunctionImpl(Predicate functor, List<Term> terms) {
        super(functor, terms);
    }

    public TemporalFunctionImpl(Predicate functor, Term... terms) {
        super(functor, terms);
    }
}
