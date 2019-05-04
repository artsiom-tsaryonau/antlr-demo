package com.epam.antlr.example.tree;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.example.node.ClassDeclarationNode;
import com.epam.antlr.example.node.IASTNode;
import com.epam.antlr.example.node.MethodDeclarationNode;
import com.epam.antlr.example.node.PackageNode;

import java.util.List;
import java.util.stream.Collectors;

public class HighLevelClassVisitor extends Java8BaseVisitor<IASTNode> {
    @Override
    public IASTNode visitPackageDeclaration(Java8Parser.PackageDeclarationContext ctx) {
        PackageNode packageNode = new PackageNode();
        packageNode.setFullPackage(ctx.packageName().getText());
        return packageNode;
    }

    @Override
    public IASTNode visitNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        ClassDeclarationNode classDeclarationNode = new ClassDeclarationNode();
        classDeclarationNode.setClassName(ctx.Identifier().getText());
        return classDeclarationNode;
    }

    @Override
    public IASTNode visitMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        MethodDeclarationNode methodDeclarationNode = new MethodDeclarationNode();
        methodDeclarationNode.setMethodName(ctx.Identifier().getText());
        return methodDeclarationNode;
    }

    @Override
    public IASTNode visitFormalParameterList(Java8Parser.FormalParameterListContext ctx) {
        List<IASTNode> parameters = ctx.formalParameters()
                .formalParameter()
                .stream()
                .map(visitor -> visitor.accept(new MethodParameterVisitor()))
                .collect(Collectors.toList());
        return null;
    }
}
