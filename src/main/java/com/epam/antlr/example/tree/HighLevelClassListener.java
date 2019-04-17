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

    private Stack<DefaultMutableTreeNode> nodeStack = new Stack<>();

    public HighLevelClassListener(DefaultMutableTreeNode root) {
        nodeStack.push(root);
    }

    // ---- package

    @Override
    public void enterPackageDeclaration(PackageDeclarationContext ctx) {
        DefaultMutableTreeNode packageNode = new DefaultMutableTreeNode(ctx.packageName().getText());
        nodeStack.peek().add(packageNode);
        nodeStack.push(packageNode);
    }

    // ---- class declaration

    @Override
    public void enterNormalClassDeclaration(NormalClassDeclarationContext ctx) {
        DefaultMutableTreeNode classNode = new DefaultMutableTreeNode(ctx.Identifier().getText());
        nodeStack.peek().add(classNode);
        nodeStack.push(classNode);
    }

    @Override
    public void exitNormalClassDeclaration(NormalClassDeclarationContext ctx) {
        nodeStack.pop();
    }

    // ---- method declaration

    @Override
    public void enterMethodDeclarator(MethodDeclaratorContext ctx) {
        DefaultMutableTreeNode methodNode = new DefaultMutableTreeNode(ctx.Identifier().getText());
        nodeStack.peek().add(methodNode);
        nodeStack.push(methodNode);
    }

    @Override
    public void exitMethodDeclarator(MethodDeclaratorContext ctx) {
        nodeStack.pop();
    }

    // ----- finalize

    @Override
    public void exitCompilationUnit(CompilationUnitContext ctx) {
        nodeStack.clear(); // clear stack (for no reason) after leaving the compilation unit
    }
}
