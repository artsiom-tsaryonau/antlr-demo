package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.example.node.IntermediateMethodBodyNode;
import com.epam.antlr.example.node.MethodInvocationNode;
import com.epam.antlr.example.node.api.IASTNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Method body is a block of statements between { }.
 */
public class MethodBodyVisitor extends Java8BaseVisitor<IASTNode> {

    @Override
    public IASTNode visitMethodBody(Java8Parser.MethodBodyContext ctx) {
        return visitBlock(ctx.block());
    }

    @Override
    public IASTNode visitBlock(Java8Parser.BlockContext ctx) {
        BlockStatementVisitor blockStatementVisitor = new BlockStatementVisitor();
        IntermediateMethodBodyNode methodBodyNode = new IntermediateMethodBodyNode();
        if (ctx.blockStatements() != null) {
            List<MethodInvocationNode> methodNodes = ctx.blockStatements().blockStatement()
                .stream()
                .map(blockStatementVisitor::visitBlockStatement)
                .map(iastNode -> (MethodInvocationNode) iastNode)
                .collect(Collectors.toList());
            methodBodyNode.setMethodInvocations(methodNodes);
        }
        return methodBodyNode;
    }
}
