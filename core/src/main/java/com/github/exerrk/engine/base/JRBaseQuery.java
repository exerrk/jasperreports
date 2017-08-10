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
package com.github.exerrk.engine.base;

import java.io.Serializable;

import com.github.exerrk.engine.JRConstants;
import com.github.exerrk.engine.JRQuery;
import com.github.exerrk.engine.JRQueryChunk;
import com.github.exerrk.engine.JRRuntimeException;
import com.github.exerrk.engine.query.JRJdbcQueryExecuterFactory;
import com.github.exerrk.engine.util.JRCloneUtils;
import com.github.exerrk.engine.util.JRQueryParser;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRBaseQuery implements JRQuery, Serializable
{
	
	
	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	private JRQueryChunk[] chunks;
	
	protected String language = JRJdbcQueryExecuterFactory.QUERY_LANGUAGE_SQL;


	/**
	 *
	 */
	protected JRBaseQuery()
	{
	}


	/**
	 *
	 */
	protected JRBaseQuery(JRQuery query, JRBaseObjectFactory factory)
	{
		factory.put(query, this);
		
		/*   */
		JRQueryChunk[] jrChunks = query.getChunks();
		if (jrChunks != null && jrChunks.length > 0)
		{
			chunks = new JRQueryChunk[jrChunks.length];
			for(int i = 0; i < chunks.length; i++)
			{
				chunks[i] = factory.getQueryChunk(jrChunks[i]);
			}
		}
		
		language = query.getLanguage();
	}
		

	@Override
	public JRQueryChunk[] getChunks()
	{
		return this.chunks;
	}


	@Override
	public String getText()
	{
		return JRQueryParser.instance().asText(getChunks());
	}


	@Override
	public String getLanguage()
	{
		return language;
	}
	

	@Override
	public Object clone() 
	{
		JRBaseQuery clone = null;

		try
		{
			clone = (JRBaseQuery)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}
		
		clone.chunks = JRCloneUtils.cloneArray(chunks);

		return clone;
	}
	
	
}
