package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser.FormalParameterContext;
import com.epam.antlr.example.node.ParameterNode;
import com.epam.antlr.example.node.api.IASTNode;

/**
 * Method parameter.
 *
 * variableModifier* unannType variableDeclaratorId
 *
 * variableModifier -> annotation 'final'
 * unannType -> class/interface type, generic type variable, array or primitive or boolean
 * variableDeclartorId -> variable name
 */
public class FormalParameterVisitor extends Java8BaseVisitor<IASTNode> {

    @Override
    public IASTNode visitFormalParameter(FormalParameterContext ctx) {
        ParameterNode parameter = new ParameterNode();
        parameter.setName(ctx.variableDeclaratorId().Identifier().getText());

        // TODO: I know that there are two string variables in my sample
        parameter.setType(ctx.unannType().unannReferenceType().unannClassOrInterfaceType()
            .unannClassType_lfno_unannClassOrInterfaceType().Identifier().getText()); // whahahaha
        return parameter;
    }
}
