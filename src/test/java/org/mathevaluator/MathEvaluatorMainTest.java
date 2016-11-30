package org.mathevaluator;

import org.junit.Test;
import org.mathevaluator.interpreter.InvalidExpressionException;

public class MathEvaluatorMainTest {

    @Test
    public void commandHelp() throws InvalidExpressionException {
	MathEvaluatorMain.main(new String[] {});
    }

    @Test
    public void commandSuccess() throws InvalidExpressionException {
	MathEvaluatorMain.main(new String[] {"1+1"});
    }

}
