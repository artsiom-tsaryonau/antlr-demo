package com.epam.antlr.example.tree;

import java.util.Stack;

import javax.swing.tree.DefaultMutableTreeNode;

public class ParsingContext {
    private Stack<DefaultMutableTreeNode> nodeStack;

    public ParsingContext(DefaultMutableTreeNode root) {
        nodeStack = new Stack<>();
        nodeStack.push(root);
    }

    public void aroundEnter(String nodeText) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeText);
        aroundEnter(node);
    }

    public void aroundEnter(DefaultMutableTreeNode node) {
        nodeStack.peek().add(node);
        nodeStack.push(node);
    }

    public void aroundExit() {
        nodeStack.pop();
    }

    public void clearContext() {
        nodeStack.clear();
    }
}
