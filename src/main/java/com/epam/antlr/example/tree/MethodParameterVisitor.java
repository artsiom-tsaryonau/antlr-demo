package com.epam.antlr.example.tree;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.example.node.IASTNode;
import com.epam.antlr.example.node.ParameterNode;

public class MethodParameterVisitor extends Java8BaseVisitor<IASTNode> {
    private ParameterNode parameterNode = new ParameterNode();

    @Override
    public IASTNode visitUnannType(Java8Parser.UnannTypeContext ctx) {
        parameterNode.setType(ctx.getText());
        return parameterNode;
    }

    @Override
    public IASTNode visitVariableDeclaratorId(Java8Parser.VariableDeclaratorIdContext ctx) {
        parameterNode.setType(ctx.Identifier().getText());
        return parameterNode;
    }
}
