package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser.MethodDeclarationContext;
import com.epam.antlr.core.Java8Parser.MethodHeaderContext;
import com.epam.antlr.example.node.api.IASTNode;

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
        return visitMethodHeader(ctx.methodHeader());
    }

    @Override
    public IASTNode visitMethodHeader(MethodHeaderContext ctx) {
        return new MethodHeaderVisitor().visitMethodHeader(ctx);
    }
}
