package org.mathevaluator.formulas;

import java.io.Serializable;

public class Formula implements Serializable {

    private static final long serialVersionUID = 2757891978086075554L;

    private String name;
    private String expression;
    private String[] variables;

    protected Formula() {}

    public Formula(String name, String expression, String... variables) {
	this.name = name;
	this.expression = expression;
	this.variables = variables;
    }

    public String getExpression() {
	return expression;
    }

    public String[] getVariables() {
	return variables;
    }

    @Override
    public String toString() {
	return name + "=" + expression;
    }

    public String getName() {
	return name;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	Formula other = (Formula) obj;
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!name.equals(other.name)) {
	    return false;
	}
	return true;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setExpression(String expression) {
	this.expression = expression;
    }

    public void setVariables(String[] variables) {
	this.variables = variables;
    }

}