package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.example.node.api.IASTNode;

/*
    Block, empty statement, assert, switch, do, break, basic expression (method call) etc.
 */
public class StatementWithoutTrailingSubstatementVisitor extends Java8BaseVisitor<IASTNode> {

    @Override
    public IASTNode visitStatementWithoutTrailingSubstatement(Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
        // TODO: for now I know I have only single method call
        return visitStatementExpression(ctx.expressionStatement().statementExpression());
    }

    @Override
    public IASTNode visitStatementExpression(Java8Parser.StatementExpressionContext ctx) {
        return new MethodInvocationVisitor().visitMethodInvocation(ctx.methodInvocation());
    }
}

