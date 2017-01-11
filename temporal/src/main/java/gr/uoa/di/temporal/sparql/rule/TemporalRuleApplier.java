package gr.uoa.di.temporal.sparql.rule;

import gr.uoa.di.temporal.dependency.DIHolder;
import gr.uoa.di.temporal.sparql.predicate.TemporalPredicateEnum;
import it.unibz.krdb.obda.model.*;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;

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

                // Find predicate if temporal.
                final String predicateName = atom.getFunctionSymbol().getName();
                final Predicate predicate = TemporalPredicateEnum.getPredicate(predicateName);

                // Intervention decision.
                if (!TemporalPredicateEnum.translate(predicate) || t1.toString().startsWith("URI")) {
                    continue;
                }

                // Intermediate variables
                final Variable intermediateVar1 = OBDA_FACTORY.getVariable(t1.toString() + SERIALIZATION_LITERAL);
                final Variable intermediateVar2 = OBDA_FACTORY.getVariable(t2.toString() + SERIALIZATION_LITERAL);

                // Rule replacement
                final Function fuc = OBDA_FACTORY.getFunction(predicate , intermediateVar1, intermediateVar2);

                addIntermediatePred(t1, intermediateVar1, body);
                addIntermediatePred(t2, intermediateVar2, body);
                body.set(i, fuc);
            }
        }

    }

    private static void addIntermediatePred(final Term t, final Variable intermediateVar, final List<Function> body) {
        for (int i = 0; i < body.size(); i++) {
            final Function atom = body.get(i);
            final List<Term> terms = atom.getTerms();
            final String predicateName = atom.getFunctionSymbol().getName();
            if(terms.size() < 2 || !predicateName.equals(TemporalPredicateEnum.HAS_PERIOD.getPredicate().getName())) {
                continue;
            }

            final Term t1 = terms.get(0);
            final Term t2 = terms.get(1);

            if (t2.equals(t)) {
                body.add(OBDA_FACTORY.getFunction(DIHolder.HAS_SERIALIZATION_PRED, t1, intermediateVar));
                return;
            }

        }
    }
}
