package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser.ClassDeclarationContext;
import com.epam.antlr.core.Java8Parser.NormalClassDeclarationContext;
import com.epam.antlr.example.node.api.IASTNode;

/**
 *  Class declaration visitor. Can be either regular [class] or [enum]
 */
public class ClassDeclarationVisitor extends Java8BaseVisitor<IASTNode> {

    @Override
    public IASTNode visitClassDeclaration(ClassDeclarationContext ctx) {
        return visitNormalClassDeclaration(ctx.normalClassDeclaration());
    }

    @Override
    public IASTNode visitNormalClassDeclaration(NormalClassDeclarationContext ctx) {
        return new NormalClassDeclarationVisitor().visitNormalClassDeclaration(ctx);
    }
}
