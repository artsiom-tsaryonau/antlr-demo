package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.core.Java8Parser.MethodDeclarationContext;
import com.epam.antlr.core.Java8Parser.MethodHeaderContext;
import com.epam.antlr.example.node.IntermediateMethodBodyNode;
import com.epam.antlr.example.node.MethodDeclaratorNode;
import com.epam.antlr.example.node.api.IASTNode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Method declaration
 *
 * methodModifier* methodHeader methodBody
 *
 * methodModifier* -> annotation. public, protected etc.
 * methodHeader -> returnType methodName throws ? with optional generics
 * methodBody -> method content
 */
public class MethodDeclarationVisitor extends Java8BaseVisitor<IASTNode> {

    @Override
    public IASTNode visitMethodDeclaration(MethodDeclarationContext ctx) {
        IntermediateMethodBodyNode methodBodyNode =
            (IntermediateMethodBodyNode) visitMethodBody(ctx.methodBody());
        MethodDeclaratorNode methodDeclaratorNode =
            (MethodDeclaratorNode) visitMethodHeader(ctx.methodHeader());

        if (methodBodyNode.getMethodInvocations() == null) {
            methodDeclaratorNode.setInvocations(Collections.emptyList());
        } else {
            methodDeclaratorNode.setInvocations(new ArrayList<>(methodBodyNode.getMethodInvocations()));
        }
        return methodDeclaratorNode;
    }

    @Override
    public IASTNode visitMethodHeader(MethodHeaderContext ctx) {
        // method always have a single header
        return new MethodHeaderVisitor().visitMethodHeader(ctx);
    }

    @Override
    public IASTNode visitMethodBody(Java8Parser.MethodBodyContext ctx) {
        // method body can have 0+ expressions
        return new MethodBodyVisitor().visitMethodBody(ctx);
    }
}
