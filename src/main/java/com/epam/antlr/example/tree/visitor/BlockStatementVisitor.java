package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.example.node.api.IASTNode;

/**
 * Block statement either variable declaration or class declaration or regular statement.
 */
public class BlockStatementVisitor extends Java8BaseVisitor<IASTNode> {

    @Override
    public IASTNode visitBlockStatement(Java8Parser.BlockStatementContext ctx) {
        return visitStatement(ctx.statement());
    }

    @Override
    public IASTNode visitStatement(Java8Parser.StatementContext ctx) {
        return new StatementWithoutTrailingSubstatementVisitor()
            .visitStatementWithoutTrailingSubstatement(ctx.statementWithoutTrailingSubstatement());
    }
}
