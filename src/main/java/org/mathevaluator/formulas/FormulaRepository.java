package org.mathevaluator.formulas;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.mathevaluator.util.Serializator;

public class FormulaRepository {

    private Set<Formula> formulas;
    private File fileData = new File(System.getProperty("user.dir") + "/data/formulas.ser");

    public FormulaRepository() {
	if (!fileData.exists()) {
	    fileData.getParentFile().mkdirs();
	}
	formulas = Serializator.unserialize(fileData);
	if (formulas == null) {
	    formulas = new HashSet<>();
	}
    }

    public void save(Formula formula) {
	formulas.add(formula);
	Serializator.serialize(formulas, fileData);
    }

    public boolean isFormula(String expression) {
	return formulas.stream().filter(f -> expression.startsWith(f.getName() + "(") && expression.endsWith(")"))
		.collect(toList()).size() > 0;
    }

    public Set<Formula> getFormulas() {
	return formulas;
    }

    public File getFileData() {
	return fileData;
    }

}
