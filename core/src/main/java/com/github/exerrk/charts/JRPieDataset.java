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
package com.github.exerrk.charts;

import com.github.exerrk.annotations.properties.Property;
import com.github.exerrk.annotations.properties.PropertyScope;
import com.github.exerrk.engine.JRChartDataset;
import com.github.exerrk.engine.JRExpression;
import com.github.exerrk.engine.JRHyperlink;
import com.github.exerrk.engine.JRPropertiesUtil;
import com.github.exerrk.properties.PropertyConstants;


/**
 * This dataset is useful for rendering Pie or Pie 3D charts. Data required for such charts 
 * comes in the form of key-value pairs. Each pair represents a slice in the pie chart.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public interface JRPieDataset extends JRChartDataset
{
	/**
	 * 
	 */
	@Property(
			category = PropertyConstants.CATEGORY_FILL,
			valueType = Boolean.class,
			defaultValue = PropertyConstants.BOOLEAN_FALSE,
			scopes = {PropertyScope.CONTEXT, PropertyScope.REPORT, PropertyScope.DATASET},
			sinceVersion = PropertyConstants.VERSION_6_0_2
			)	
	public static final String PROPERTY_IGNORE_DUPLICATED_KEY = JRPropertiesUtil.PROPERTY_PREFIX + "chart.pie.ignore.duplicated.key";
	
	/**
	 * 
	 */
	public Float getMinPercentage();

	/**
	 * 
	 */
	public void setMinPercentage(Float minPercentage);

	/**
	 * 
	 */
	public Integer getMaxCount();

	/**
	 * 
	 */
	public void setMaxCount(Integer maxCount);

	/**
	 * @return an array of {@link JRPieSeries} objects representing the 
	 * series for the Pie or Pie 3D chart
	 * @see JRPieSeries
	 */
	public JRPieSeries[] getSeries();

	/**
	 * 
	 */
	public JRExpression getOtherKeyExpression();
	
	/**
	 * 
	 */
	public JRExpression getOtherLabelExpression();
	
	/**
	 * Returns the hyperlink specification for the special Other chart section, if present.
	 * 
	 * @return hyperlink specification for the Other chart section
	 */
	public JRHyperlink getOtherSectionHyperlink();

}
