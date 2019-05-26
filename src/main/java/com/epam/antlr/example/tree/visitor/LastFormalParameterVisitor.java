package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8Parser.LastFormalParameterContext;
import com.epam.antlr.example.node.FormalParameterVisitor;
import com.epam.antlr.example.node.api.IASTNode;

/**
 * Method parameter.
 *
 * variableModifier* unannType annotation* '...' variableDeclaratorId
 *
 * variableModifier -> annotation 'final'
 * unannType -> class/interface type, generic type variable, array or primitive or boolean
 * annotation -> annotation ? need investigation though
 * ... -> varargs
 * variableDeclartorId -> variable name
 */
public class LastFormalParameterVisitor extends FormalParameterVisitor {

    // TODO: for now that's enough
    @Override
    public IASTNode visitLastFormalParameter(LastFormalParameterContext ctx) {
        return super.visitFormalParameter(ctx.formalParameter());
    }
}
