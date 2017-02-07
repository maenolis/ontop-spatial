package gr.uoa.di.temporal.sparql.rule;

import gr.uoa.di.temporal.dependency.DIHolder;
import gr.uoa.di.temporal.sparql.predicate.TemporalPredicateEnum;
import it.unibz.krdb.obda.model.*;
import it.unibz.krdb.obda.model.impl.FunctionalTermImpl;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;
import it.unibz.krdb.obda.model.impl.OBDAVocabulary;
import it.unibz.krdb.obda.model.impl.VariableImpl;

import java.util.ArrayList;
import java.util.List;

public final class TemporalRuleApplier {

    private static final OBDADataFactory OBDA_FACTORY = OBDADataFactoryImpl.getInstance();
    private static final String SERIALIZATION_LITERAL = "_serial";

    public static void applyRules(final List<CQIE> rules, final DatalogProgram pr) {

        for (CQIE rule: rules) {
            final List<Function> body = rule.getBody();

            for (int i = 0; i < body.size(); i++) {
                final Function atom = body.get(i);
                final List<Term> terms = atom.getTerms();
                if(terms.size() < 2) {
                    continue;
                }

                // Original query variables.
                final Term t1 = terms.get(0);
                final Term t2 = terms.get(1);

                if (t1.toString().endsWith(SERIALIZATION_LITERAL) || t2.toString().endsWith(SERIALIZATION_LITERAL)) {
                    continue;
                }

                // Find predicate if temporal.
                final String predicateName = atom.getFunctionSymbol().getName();
                final Predicate predicate = TemporalPredicateEnum.getPredicateByUri(predicateName);

                // Intervention decision.
                if (!TemporalPredicateEnum.translate(predicate) || (t1 instanceof Function)) {
                    continue;
                }

                // Intermediate variables
                final List<Function> additionFunctions = new ArrayList<>();
                final Term intermediateVar1 = OBDA_FACTORY.getVariable(t1.toString() + SERIALIZATION_LITERAL);
                additionFunctions.add(OBDA_FACTORY.getFunction(DIHolder.HAS_SERIALIZATION_PREDICATE, t1, intermediateVar1));;

                final Term intermediateVar2;
                if (!translate(body, t2)) {
                    intermediateVar2 = t2;
                } else {
                    intermediateVar2 = OBDA_FACTORY.getVariable(t2.toString() + SERIALIZATION_LITERAL);
                    additionFunctions.add(OBDA_FACTORY.getFunction(DIHolder.HAS_SERIALIZATION_PREDICATE, t2, intermediateVar2));
                }

                // Rule replacement
                final Function fuc = OBDA_FACTORY.getFunction(predicate , intermediateVar1, intermediateVar2);

                body.set(i, fuc);

                body.addAll(additionFunctions);
            }
        }

    }

    private static boolean isPeriodLiteralVariable(final Term term) {
        if (term instanceof FunctionalTermImpl) {
            final FunctionalTermImpl t = (FunctionalTermImpl) term;
            return t.getFunctionSymbol().getName().equals(OBDAVocabulary.TEMPORAL_DATATYPE);
        }
        return false;
    }

    private static boolean translate(final List<Function> body, final Term term) {
        if (term instanceof VariableImpl) {
            final Variable var = (VariableImpl) term;
            for (int i = 0; i < body.size(); i++) {
                final Function atom = body.get(i);
                final List<Term> terms = atom.getTerms();
                if (terms.size() < 2) {
                    continue;
                }

                if (terms.get(1).toString().equals(var.getName()) &&
                        atom.getFunctionSymbol().getName().equals(TemporalPredicateEnum.HAS_PERIOD.getUri())) {
                    return true;
                }
            }
        }
        return false;
    }
}
