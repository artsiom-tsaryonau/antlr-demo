package com.epam.antlr.example;

import com.epam.antlr.core.Java8Lexer;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.example.node.NormalClassDeclarationNode;
import com.epam.antlr.example.node.CompilationUnitNode;
import com.epam.antlr.example.node.PackageDeclarationNode;
import com.epam.antlr.example.tree.visitor.CompilationUnitVisitor;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream stream = String.class.getResourceAsStream("/simplest-example.java");

        Java8Lexer lexer = new Java8Lexer(CharStreams.fromStream(stream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(tokens);

        CompilationUnitNode rootNode = new CompilationUnitNode();
        rootNode.setFilename("simplest-example.java");

        // visit package declaration
        CompilationUnitVisitor rootVisitor = new CompilationUnitVisitor();
        PackageDeclarationNode packageDeclarationNode =
            (PackageDeclarationNode) rootVisitor.visitPackageDeclaration(parser.packageDeclaration());
        rootNode.setPackageNode(packageDeclarationNode);

        // 100% guarantee that it will be normal class. Not interface, nor enum.
        // in real-world has to do some checks.
        NormalClassDeclarationNode normalClassDeclarationNode =
            (NormalClassDeclarationNode) rootVisitor.visitTypeDeclaration(parser.typeDeclaration());
        rootNode.setClassNode(normalClassDeclarationNode);


        // we can store it now in DB
        Configuration configuration = new Configuration.Builder()
            .uri("bolt://localhost")
            .credentials("neo4j", "password")
            .build();

        SessionFactory sessionFactory = new SessionFactory(configuration, "com.epam.antlr.example.node");
        Session session = sessionFactory.openSession();
        session.save(rootNode);
        sessionFactory.close(); // need to close that
    }
}
