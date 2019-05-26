package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.example.node.api.IASTNode;
import com.epam.antlr.example.node.PackageDeclarationNode;

/**
 * Visits package declaration in compilation unit.
 *
 * packageModifier* 'package' packageName ';'
 *
 * Which roughly translates
 * packageModifier* -> annotation
 * 'package' -> keyword
 * packageName -> full package name that can be fully processed
 */
public class PackageDeclarationVisitor extends Java8BaseVisitor<IASTNode> {
    @Override
    public IASTNode visitPackageDeclaration(Java8Parser.PackageDeclarationContext ctx) {
        PackageDeclarationNode packageNode = new PackageDeclarationNode();
        packageNode.setFullPackage(ctx.packageName().getText());
        return packageNode;
    }
}
