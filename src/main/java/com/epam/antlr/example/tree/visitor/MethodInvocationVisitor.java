package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.example.node.MethodInvocationNode;
import com.epam.antlr.example.node.api.IASTNode;

/**
 * Method call.
 * Maybe regular methodName(args), may be system.out.println(xx) with or without params.
 */
public class MethodInvocationVisitor extends Java8BaseVisitor<IASTNode> {

    @Override
    public IASTNode visitMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        // test sample uses regular method calls
        MethodInvocationNode methodInvocationNode = new MethodInvocationNode();
        methodInvocationNode.setMethodName(ctx.methodName().Identifier().getText());
        // method invocation node should have list of nodes
        visitArgumentList(ctx.argumentList());
        return methodInvocationNode;
    }

    @Override
    public IASTNode visitArgumentList(Java8Parser.ArgumentListContext ctx) {
        // TBD
        return super.visitArgumentList(ctx);
    }
}
