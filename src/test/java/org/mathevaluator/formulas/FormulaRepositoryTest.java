package org.mathevaluator.formulas;

import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mathevaluator.interpreter.InvalidExpressionException;

public class FormulaRepositoryTest {

    private FormulaRepository formulaRepository = new FormulaRepository();

    @Before
    public void setup() {
	formulaRepository.save(new Formula("rectangleArea", "base*height", "base", "height"));
	formulaRepository.save(new Formula("diamondArea", "largestDiagonal*smallestDiagonal/2", "largestDiagonal", "smallestDiagonal"));
	System.out.println(formulaRepository.getFileData());
    }

    @Test
    public void rectangleArea() {
	Assert.assertTrue(new FormulaRepository().isFormula("rectangleArea()"));
    }

    @Test
    public void diamondArea() {
	Assert.assertTrue(new FormulaRepository().isFormula("diamondArea()"));
    }

    @Test
    public void formulaNotFound() throws InvalidExpressionException {
	assertFalse(new FormulaRepository().isFormula("area"));
    }

    @Test
    public void saveFormula() throws InvalidExpressionException {
	formulaRepository.save(new Formula("triangleArea", "(1/2)*(base*height)", "base", "height"));
	assertFalse(new FormulaRepository().isFormula("triangleArea"));
    }

}
