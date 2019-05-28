package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.core.Java8Parser.ArgumentListContext;
import com.epam.antlr.example.node.ArgumentNode;
import com.epam.antlr.example.node.MethodInvocationNode;
import com.epam.antlr.example.node.api.IASTNode;

import java.util.List;
import java.util.stream.Collectors;

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
        ArgumentListContext context = ctx.argumentList();
        if (context.expression() != null) {
            List<ArgumentNode> argumentNodeList = context.expression()
                .stream()
                .map(expressionContext -> {
                    ArgumentNode node = new ArgumentNode();
                    node.setValue(expressionContext.getText());
                    return node; })
                .collect(Collectors.toList());
            methodInvocationNode.setArguments(argumentNodeList);
        }
        return methodInvocationNode;
    }
}
