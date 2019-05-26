package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser.PackageDeclarationContext;
import com.epam.antlr.core.Java8Parser.TypeDeclarationContext;
import com.epam.antlr.example.node.api.IASTNode;

/**
 * Compilation unit declaration structure (according to specification)
 *
 * packageDeclaration? importDeclaration* typeDeclaration* EOF
 *
 * Which rougly translates into
 *
 *  package XXX -> packageDeclaration? (optional)
 *  import|static XXX*; -> imports static, with * optional
 *  class|interface {} -> enum is part of class declaration
 *  EOF;
 *
 */
public class CompilationUnitVisitor extends Java8BaseVisitor<IASTNode> {

    @Override
    public IASTNode visitPackageDeclaration(PackageDeclarationContext ctx) {
        return new PackageDeclarationVisitor().visitPackageDeclaration(ctx);
    }

    @Override
    public IASTNode visitTypeDeclaration(TypeDeclarationContext ctx) {
        return new ClassDeclarationVisitor().visitClassDeclaration(ctx.classDeclaration());
    }
}
