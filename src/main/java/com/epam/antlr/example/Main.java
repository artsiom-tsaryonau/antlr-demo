package com.epam.antlr.example;

import com.epam.antlr.core.Java8Lexer;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.example.node.IASTNode;
import com.epam.antlr.example.tree.HighLevelClassVisitor;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.InputStream;


public class Main {

    public static void main(String[] args) throws IOException {
        InputStream stream = String.class.getResourceAsStream("/simplest-example.java");

        Java8Lexer lexer = new Java8Lexer(CharStreams.fromStream(stream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(tokens);

        HighLevelClassVisitor visitor = new HighLevelClassVisitor();

        // follows the order

        IASTNode node = visitor.visitPackageDeclaration(parser.packageDeclaration());
        System.out.println(node.getContent());

        node = visitor.visitNormalClassDeclaration(parser.normalClassDeclaration());
        System.out.println(node.getContent());

        node = visitor.visitMethodDeclarator(parser.methodDeclarator());
        System.out.println(node.getContent());

        node = visitor.visitFormalParameterList(parser.formalParameterList());
        System.out.println(node.getContent());


    }
}
