package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser.ClassMemberDeclarationContext;
import com.epam.antlr.core.Java8Parser.MethodDeclarationContext;
import com.epam.antlr.example.node.api.IASTNode;

/**
 * Class body statements - methods, static {}, instance {}, constructors.
 */
public class ClassBodyDeclarationVisitor extends Java8BaseVisitor<IASTNode> {

    @Override
    public IASTNode visitClassMemberDeclaration(ClassMemberDeclarationContext ctx) {
        // TODO: I assume there is only methods
        return visitMethodDeclaration(ctx.methodDeclaration());
    }

    @Override
    public IASTNode visitMethodDeclaration(MethodDeclarationContext ctx) {
        return new MethodDeclarationVisitor().visitMethodDeclaration(ctx);
    }
}
