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
package com.github.exerrk.engine.util.json;

import java.io.StringReader;
import java.util.List;

import com.github.exerrk.engine.JRException;
import com.github.exerrk.engine.JRRuntimeException;
import com.github.exerrk.engine.json.JRJsonNode;
import com.github.exerrk.engine.json.JsonNodeContainer;
import com.github.exerrk.engine.json.expression.JsonQLExpression;
import com.github.exerrk.engine.json.expression.JsonQLExpressionEvaluator;
import com.github.exerrk.engine.json.parser.JsonQueryLexer;
import com.github.exerrk.engine.json.parser.JsonQueryParser;
import com.github.exerrk.engine.json.parser.JsonQueryWalker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Narcis Marcu (narcism@users.sourceforge.net)
 */
public class DefaultJsonQLExecuter implements JsonQLExecuter {
    private static final Log log = LogFactory.getLog(DefaultJsonQLExecuter.class);

    private JsonQLExpressionEvaluator evaluator;

    public DefaultJsonQLExecuter() {
        evaluator = new JsonQLExpressionEvaluator();
    }

    @Override
    public List<JRJsonNode> selectNodes(JRJsonNode rootNode, String expression) throws JRException {
        JsonNodeContainer container;

        if (expression != null && expression.trim().length() > 0) {
            container = evaluator.evaluate(getJsonQLExpression(expression), rootNode);

            if (container != null) {
                return container.getContainerNodes();
            }
        } else {
            container = new JsonNodeContainer(rootNode);

            return container.getContainerNodes();
        }

        return null;
    }

    @Override
    public JRJsonNode selectNode(JRJsonNode contextNode, JRJsonNode rootNode, String expression) throws JRException {
        if (expression != null  && expression.trim().length() > 0) {
            JsonQLExpression jsonQLExpression = getJsonQLExpression(expression);
            JRJsonNode node = contextNode;

            if (jsonQLExpression.isAbsolute()) {
                node = rootNode;
            }

            JsonNodeContainer container = evaluator.evaluate(jsonQLExpression, node);

            if (container != null) {
                return container.getNodes().get(0);
            }
        } else {
            return contextNode;
        }

        return null;
    }

    public JsonNodeContainer evaluateExpression(JRJsonNode jrJsonNode, String expression) {
        if (expression != null && expression.trim().length() > 0) {
            return evaluator.evaluate(getJsonQLExpression(expression), jrJsonNode);
        }

        return null;
    }

    public JsonQLExpressionEvaluator getEvaluator() {
        return evaluator;
    }

    protected JsonQLExpression getJsonQLExpression(String expression) {
        try {
            JsonQueryLexer lexer = new JsonQueryLexer(new StringReader(expression.trim()));

            JsonQueryParser parser = new JsonQueryParser(lexer);
            parser.pathExpr();

            JsonQueryWalker walker = new JsonQueryWalker();
            return walker.jsonQLExpression(parser.getAST());

        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("Exception is of type: " + e.getClass());
            }
            throw new JRRuntimeException(e);
        }
    }

}
