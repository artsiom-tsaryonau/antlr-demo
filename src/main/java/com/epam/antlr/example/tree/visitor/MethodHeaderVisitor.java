package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser.MethodDeclaratorContext;
import com.epam.antlr.core.Java8Parser.MethodHeaderContext;
import com.epam.antlr.example.node.FormalParameterVisitor;
import com.epam.antlr.example.node.MethodDeclarationNode;
import com.epam.antlr.example.node.ParameterNode;
import com.epam.antlr.example.node.api.IASTNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Method header context. Either default or with generic types.
 *
 * result methodDeclarator throws _?
 *
 * result -> void, any other type
 * methodDeclaration -> method name + ( params)
 * throws _? -> optional throws
 */
public class MethodHeaderVisitor extends Java8BaseVisitor<IASTNode> {

    @Override
    public IASTNode visitMethodHeader(MethodHeaderContext ctx) {
        return visitMethodDeclarator(ctx.methodDeclarator());
    }

    @Override
    public IASTNode visitMethodDeclarator(MethodDeclaratorContext ctx) {
        MethodDeclarationNode method = new MethodDeclarationNode();
        method.setMethodName(ctx.Identifier().getText());

        FormalParameterVisitor formalParameterVisitor = new FormalParameterVisitor();
        // TODO: we ignore receiver here, so we need formal parameters and last formal parameter

        List<ParameterNode> parameters = new ArrayList<>();
        if (ctx.formalParameterList() != null) {
            parameters = ctx.formalParameterList().formalParameters().formalParameter()
                .stream()
                .map(formalParameterVisitor::visitFormalParameter)
                .map(param -> (ParameterNode) param)
                .collect(Collectors.toList());
            // probably it would be good to have set to filter duplicates parameters if any
            LastFormalParameterVisitor lastFormalParameterVisitor = new LastFormalParameterVisitor();
            ParameterNode lastParameter = (ParameterNode) lastFormalParameterVisitor.visitLastFormalParameter(
                ctx.formalParameterList().lastFormalParameter());
            parameters.add(lastParameter);
        }

        method.setParameters(parameters);
        return method;
    }
}
