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
package com.github.exerrk.charts.base;

import com.github.exerrk.charts.JRCategoryDataset;
import com.github.exerrk.charts.JRCategorySeries;
import com.github.exerrk.engine.JRChartDataset;
import com.github.exerrk.engine.JRConstants;
import com.github.exerrk.engine.JRExpressionCollector;
import com.github.exerrk.engine.base.JRBaseChartDataset;
import com.github.exerrk.engine.base.JRBaseObjectFactory;
import com.github.exerrk.engine.design.JRVerifier;
import com.github.exerrk.engine.util.JRCloneUtils;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRBaseCategoryDataset extends JRBaseChartDataset implements JRCategoryDataset
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected JRCategorySeries[] categorySeries;

	
	/**
	 *
	 */
	public JRBaseCategoryDataset(JRChartDataset dataset)
	{
		super(dataset);
	}
	
	
	/**
	 *
	 */
	public JRBaseCategoryDataset(JRCategoryDataset dataset, JRBaseObjectFactory factory)
	{
		super(dataset, factory);

		/*   */
		JRCategorySeries[] srcCategorySeries = dataset.getSeries();
		if (srcCategorySeries != null && srcCategorySeries.length > 0)
		{
			categorySeries = new JRCategorySeries[srcCategorySeries.length];
			for(int i = 0; i < categorySeries.length; i++)
			{
				categorySeries[i] = factory.getCategorySeries(srcCategorySeries[i]);
			}
		}

	}

	
	@Override
	public JRCategorySeries[] getSeries()
	{
		return categorySeries;
	}


	/* (non-Javadoc)
	 * @see com.github.exerrk.engine.JRChartDataset#getDatasetType()
	 */
	@Override
	public byte getDatasetType() {
		return JRChartDataset.CATEGORY_DATASET;
	}

	
	@Override
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}


	@Override
	public void validate(JRVerifier verifier)
	{
		verifier.verify(this);
	}

	
	@Override
	public Object clone() 
	{
		JRBaseCategoryDataset clone = (JRBaseCategoryDataset)super.clone();
		clone.categorySeries = JRCloneUtils.cloneArray(categorySeries);
		return clone;
	}

}
