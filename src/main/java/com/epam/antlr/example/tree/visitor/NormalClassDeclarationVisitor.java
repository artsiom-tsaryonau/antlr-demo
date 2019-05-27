package com.epam.antlr.example.tree.visitor;

import com.epam.antlr.core.Java8BaseVisitor;
import com.epam.antlr.core.Java8Parser.ClassBodyDeclarationContext;
import com.epam.antlr.core.Java8Parser.NormalClassDeclarationContext;
import com.epam.antlr.example.node.MethodDeclaratorNode;
import com.epam.antlr.example.node.NormalClassDeclarationNode;
import com.epam.antlr.example.node.api.IASTNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Regular class declaration.
 *
 *  classModifier* 'class' Identifier typeParameters? superclass? superinterfaces? classBody
 *
 *  which roughly translates
 *  classModifier* -> annotation, public, protected, final, private etc.
 *  'class' -> keyword
 *  Identifier -> class name basically
 *  typeParameters? -> optional generic
 *  superclass? -> optional [extends]
 *  superinterfaces? -> optional [implements]
 *  classBody -> { }
 */
public class NormalClassDeclarationVisitor extends Java8BaseVisitor<IASTNode> {

    @Override
    public IASTNode visitNormalClassDeclaration(NormalClassDeclarationContext ctx) {
        NormalClassDeclarationNode node = new NormalClassDeclarationNode();
        node.setClassName(ctx.Identifier().getText());

        ClassBodyDeclarationVisitor classBodyDeclarationVisitor = new ClassBodyDeclarationVisitor();
        // TODO: I assume that there is no constructors and only methods
        List<MethodDeclaratorNode> methods = ctx.classBody().classBodyDeclaration()
            .stream()
            .map(ClassBodyDeclarationContext::classMemberDeclaration)
            .map(classBodyDeclarationVisitor::visitClassMemberDeclaration)
            .map(astNode -> (MethodDeclaratorNode) astNode)
            .collect(Collectors.toList());
        node.setMethods(methods);
        return node;
    }


}
