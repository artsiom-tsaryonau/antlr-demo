package com.epam.antlr.example;

import com.epam.antlr.core.Java8Lexer;
import com.epam.antlr.core.Java8Parser;
import com.epam.antlr.example.tree.HighLevelClassListener;
import com.epam.antlr.example.ui.ClassTreeExample;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.tree.DefaultMutableTreeNode;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream stream = String.class.getResourceAsStream("/sample-function.java");

        Java8Lexer lexer = new Java8Lexer(CharStreams.fromStream(stream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(tokens);
        ParseTree tree = parser.compilationUnit();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("/sample-function.java");

        HighLevelClassListener listener = new HighLevelClassListener(root);
        ParseTreeWalker.DEFAULT.walk(listener, tree);

        ClassTreeExample.display(root);
    }

}
