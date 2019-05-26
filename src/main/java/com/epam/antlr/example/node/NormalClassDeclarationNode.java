package com.epam.antlr.example.node;

import com.epam.antlr.example.node.api.IASTNode;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class NormalClassDeclarationNode implements IASTNode {
    private String className;
    private List<MethodDeclarationNode> methods = new ArrayList<>();

    @Override
    public String getContent() {
        return className;
    }
}
