package com.epam.antlr.example.tree;

import com.epam.antlr.core.Java8BaseListener;
import com.epam.antlr.core.Java8Parser.ArgumentListContext;
import com.epam.antlr.core.Java8Parser.AssignmentContext;
import com.epam.antlr.core.Java8Parser.AssignmentExpressionContext;
import com.epam.antlr.core.Java8Parser.BlockContext;
import com.epam.antlr.core.Java8Parser.BlockStatementContext;
import com.epam.antlr.core.Java8Parser.CompilationUnitContext;
import com.epam.antlr.core.Java8Parser.ExpressionContext;
import com.epam.antlr.core.Java8Parser.ExpressionStatementContext;
import com.epam.antlr.core.Java8Parser.FormalParameterContext;
import com.epam.antlr.core.Java8Parser.FormalParameterListContext;
import com.epam.antlr.core.Java8Parser.FormalParametersContext;
import com.epam.antlr.core.Java8Parser.InterfaceMethodDeclarationContext;
import com.epam.antlr.core.Java8Parser.MethodBodyContext;
import com.epam.antlr.core.Java8Parser.MethodDeclarationContext;
import com.epam.antlr.core.Java8Parser.MethodDeclaratorContext;
import com.epam.antlr.core.Java8Parser.MethodHeaderContext;
import com.epam.antlr.core.Java8Parser.MethodInvocationContext;
import com.epam.antlr.core.Java8Parser.MethodNameContext;
import com.epam.antlr.core.Java8Parser.NormalClassDeclarationContext;
import com.epam.antlr.core.Java8Parser.PackageDeclarationContext;
import com.epam.antlr.core.Java8Parser.PackageOrTypeNameContext;
import com.epam.antlr.core.Java8Parser.ReceiverParameterContext;
import com.epam.antlr.core.Java8Parser.StatementExpressionContext;
import com.epam.antlr.core.Java8Parser.StatementWithoutTrailingSubstatementContext;
import com.epam.antlr.core.Java8Parser.TypeArgumentsContext;
import com.epam.antlr.core.Java8Parser.TypeNameContext;
import com.epam.antlr.core.Java8Parser.UnannTypeContext;
import com.epam.antlr.core.Java8Parser.VariableDeclaratorIdContext;

import java.util.Stack;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Probably can be done via visitors.
 */
public class HighLevelClassListener  extends Java8BaseListener {
    private ParsingContext parsingContext;
    private boolean noExit = false;
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
    public void enterMethodDeclaration(MethodDeclarationContext ctx) {
        parsingContext.aroundEnter(new DefaultMutableTreeNode("Method"));
    }

    @Override
    public void exitMethodDeclaration(MethodDeclarationContext ctx) {
        parsingContext.aroundExit();
    }

    @Override
    public void enterMethodHeader(MethodHeaderContext ctx) {
        parsingContext.aroundEnter(new DefaultMutableTreeNode("method signature"));
    }

    @Override
    public void exitMethodHeader(MethodHeaderContext ctx) {
        parsingContext.aroundExit();
    }

    @Override
    public void enterMethodDeclarator(MethodDeclaratorContext ctx) {
        parsingContext.aroundEnter("name: " + ctx.Identifier().getText());
    }

    @Override
    public void exitMethodDeclarator(MethodDeclaratorContext ctx) {
        parsingContext.aroundExit();
    }

    // ---- method parameters

    @Override
    public void enterFormalParameterList(FormalParameterListContext ctx) {
        // technically can move to higher level
        parsingContext.aroundEnter(new DefaultMutableTreeNode("method parameters"));
    }

    @Override
    public void exitFormalParameterList(FormalParameterListContext ctx) {
        parsingContext.aroundExit();
    }

    @Override
    public void enterFormalParameter(FormalParameterContext ctx) {
        parsingContext.aroundEnter(new DefaultMutableTreeNode("param:"));
    }

    @Override
    public void exitFormalParameter(FormalParameterContext ctx) {
        parsingContext.aroundExit();
    }

    // ---- parameter type

    @Override
    public void enterUnannType(UnannTypeContext ctx) {
        parsingContext.aroundEnter("type: " + ctx.getText());
    }

    @Override
    public void exitUnannType(UnannTypeContext ctx) {
        parsingContext.aroundExit();
    }

    // ---- parameter name

    @Override
    public void enterVariableDeclaratorId(VariableDeclaratorIdContext ctx) {
        parsingContext.aroundEnter("name: " + ctx.Identifier().getText());
    }

    @Override
    public void exitVariableDeclaratorId(VariableDeclaratorIdContext ctx) {
        parsingContext.aroundExit();
    }

    // ---- method body

    @Override
    public void enterMethodBody(MethodBodyContext ctx) {
        parsingContext.aroundEnter(new DefaultMutableTreeNode("method body"));
    }

    @Override
    public void exitMethodBody(MethodBodyContext ctx) {
        parsingContext.aroundExit();
    }

    // ---- method call (requires specific processing)

    @Override
    public void enterMethodInvocation(MethodInvocationContext ctx) {
        DefaultMutableTreeNode completeNode = new DefaultMutableTreeNode("method-call");
        if (null != ctx.Identifier()) {
            completeNode.add(new DefaultMutableTreeNode("method-name: " + ctx.Identifier().getText()));
            parsingContext.aroundEnter(completeNode);
        } else {
            parsingContext.aroundEnter(new DefaultMutableTreeNode("method-call"));
            noExit = true;
        }
    }

    @Override
    public void exitMethodInvocation(MethodInvocationContext ctx) {
        // if (!noExit) {
            parsingContext.aroundExit();
        // }
    }

    // ---- method call like system.out.print

    @Override
    public void enterTypeName(TypeNameContext ctx) { // method invocation with type like System.out
        // parsingContext.aroundEnter("static-reference: " + ctx.Identifier().getText());
        // parsingContext.aroundExit();
    }

    @Override
    public void enterPackageOrTypeName(PackageOrTypeNameContext ctx) {
        // parsingContext.aroundEnter("method-package: " + ctx.Identifier().getText());
    }

    @Override
    public void exitPackageOrTypeName(PackageOrTypeNameContext ctx) {
        // parsingContext.aroundExit();
    }

    // ---- plain method call

    @Override
    public void enterMethodName(MethodNameContext ctx) {
        if (noExit) {
            parsingContext.aroundEnter("method-name: " + ctx.Identifier().getText());
        } else {
            parsingContext.aroundEnter(ctx.Identifier().getText());
        }
    }

    @Override
    public void exitMethodName(MethodNameContext ctx) {
        parsingContext.aroundExit();
    }

    // ---- method arguments

    @Override
    public void enterArgumentList(ArgumentListContext ctx) {
        parsingContext.aroundEnter("parameters");
    }

    @Override
    public void exitArgumentList(ArgumentListContext ctx) {
        parsingContext.aroundExit();
    }

    @Override
    public void enterAssignmentExpression(AssignmentExpressionContext ctx) {
        parsingContext.aroundEnter("parameter: " + ctx.getText());
    }

    @Override
    public void exitAssignmentExpression(AssignmentExpressionContext ctx) {
        parsingContext.aroundExit();
    }

    @Override
    public void enterAssignment(AssignmentContext ctx) {
        parsingContext.aroundEnter(ctx.getText());
    }


    @Override
    public void exitAssignment(AssignmentContext ctx) {
        parsingContext.aroundExit();
    }

    // ----- finalize

    @Override
    public void exitCompilationUnit(CompilationUnitContext ctx) {
        parsingContext.clearContext();
    }
}
