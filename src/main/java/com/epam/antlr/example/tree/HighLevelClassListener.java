package com.epam.antlr.example.tree;

import com.epam.antlr.core.Java8BaseListener;
import com.epam.antlr.core.Java8Parser.CompilationUnitContext;
import com.epam.antlr.core.Java8Parser.MethodDeclaratorContext;
import com.epam.antlr.core.Java8Parser.NormalClassDeclarationContext;
import com.epam.antlr.core.Java8Parser.PackageDeclarationContext;

import java.util.Stack;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Probably can be done via visitors.
 */
public class HighLevelClassListener  extends Java8BaseListener {
    private ParsingContext parsingContext;
    public HighLevelClassListener(DefaultMutableTreeNode root) {
        parsingContext = new ParsingContext(root);
    }

    // ---- package

    @Override
    public void enterPackageDeclaration(PackageDeclarationContext ctx) {
        parsingContext.aroundEnter(ctx.packageName().getText());
    }

    // ---- class declaration

    @Override
    public void enterNormalClassDeclaration(NormalClassDeclarationContext ctx) {
        parsingContext.aroundEnter(ctx.Identifier().getText());
    }

    @Override
    public void exitNormalClassDeclaration(NormalClassDeclarationContext ctx) {
        parsingContext.aroundExit();
    }

    // ---- method declaration

    @Override
    public void enterMethodDeclarator(MethodDeclaratorContext ctx) {
        parsingContext.aroundEnter(ctx.Identifier().getText());
    }

    @Override
    public void exitMethodDeclarator(MethodDeclaratorContext ctx) {
        parsingContext.aroundExit();
    }

    // ----- finalize

    @Override
    public void exitCompilationUnit(CompilationUnitContext ctx) {
        parsingContext.clearContext();
    }
}
