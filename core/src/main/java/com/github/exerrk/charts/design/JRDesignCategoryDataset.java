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
package com.github.exerrk.charts.design;

import java.util.ArrayList;
import java.util.List;

import com.github.exerrk.charts.JRCategoryDataset;
import com.github.exerrk.charts.JRCategorySeries;
import com.github.exerrk.engine.JRChartDataset;
import com.github.exerrk.engine.JRConstants;
import com.github.exerrk.engine.JRExpressionCollector;
import com.github.exerrk.engine.design.JRDesignChartDataset;
import com.github.exerrk.engine.design.JRVerifier;
import com.github.exerrk.engine.util.JRCloneUtils;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRDesignCategoryDataset extends JRDesignChartDataset implements JRCategoryDataset
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_CATEGORY_SERIES = "categorySeries";

	private List<JRCategorySeries> categorySeriesList = new ArrayList<JRCategorySeries>();


	/**
	 *
	 */
	public JRDesignCategoryDataset(JRChartDataset dataset)
	{
		super(dataset);
	}


	@Override
	public JRCategorySeries[] getSeries()
	{
		JRCategorySeries[] categorySeriesArray = new JRCategorySeries[categorySeriesList.size()];
		
		categorySeriesList.toArray(categorySeriesArray);

		return categorySeriesArray;
	}
	

	/**
	 * 
	 */
	public List<JRCategorySeries> getSeriesList()
	{
		return categorySeriesList;
	}

	
	/**
	 *
	 */
	public void addCategorySeries(JRCategorySeries categorySeries)
	{
		categorySeriesList.add(categorySeries);
		getEventSupport().fireCollectionElementAddedEvent(PROPERTY_CATEGORY_SERIES, 
				categorySeries, categorySeriesList.size() - 1);
	}
	
	/**
	 *
	 */
	public void addCategorySeries(int index, JRCategorySeries categorySeries)
	{
		categorySeriesList.add(index, categorySeries);
		getEventSupport().fireCollectionElementAddedEvent(PROPERTY_CATEGORY_SERIES, 
				categorySeries, index);
	}

	/**
	 *
	 */
	public JRCategorySeries removeCategorySeries(JRCategorySeries categorySeries)
	{
		if (categorySeries != null)
		{
			int idx = categorySeriesList.indexOf(categorySeries);
			if (idx >= 0)
			{
				categorySeriesList.remove(idx);
				getEventSupport().fireCollectionElementRemovedEvent(PROPERTY_CATEGORY_SERIES, 
						categorySeries, idx);
			}
		}
		
		return categorySeries;
	}


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
		JRDesignCategoryDataset clone = (JRDesignCategoryDataset)super.clone();
		clone.categorySeriesList = JRCloneUtils.cloneList(categorySeriesList);
		return clone;
	}
	
}
