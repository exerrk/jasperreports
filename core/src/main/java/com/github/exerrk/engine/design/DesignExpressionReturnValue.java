/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2016 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.exerrk.engine.design;

import com.github.exerrk.engine.ExpressionReturnValue;
import com.github.exerrk.engine.JRConstants;
import com.github.exerrk.engine.JRExpression;
import com.github.exerrk.engine.util.JRCloneUtils;

/**
 * Implementation of {@link com.github.exerrk.engine.ExpressionReturnValue ExpressionReturnValue}
 * to be used for report design purposes.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class DesignExpressionReturnValue extends DesignCommonReturnValue implements ExpressionReturnValue
{
	/**
	 * 
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_EXPRESSION = "expression";

	/**
	 * The expression producing the value to copy.
	 */
	protected JRExpression expression;

	/**
	 * Returns the expression whose value should be copied.
	 * 
	 * @return the expression whose value should be copied.
	 */
	@Override
	public JRExpression getExpression()
	{
		return expression;
	}

	/**
	 * Sets the expression.
	 * 
	 * @param expression the expression
	 * @see com.github.exerrk.engine.ExpressionReturnValue#getExpression()
	 */
	public void setExpression(JRExpression expression)
	{
		Object old = this.expression;
		this.expression = expression;
		getEventSupport().firePropertyChange(PROPERTY_EXPRESSION, old, this.expression);
	}
	
	@Override
	public Object clone()
	{
		DesignExpressionReturnValue clone = (DesignExpressionReturnValue)super.clone();
		clone.expression = JRCloneUtils.nullSafeClone(expression);
		return clone;
	}
}
