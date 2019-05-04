package com.epam.antlr.example.node;

import lombok.Data;

@Data
public class MethodDeclarationNode implements IASTNode {
    private String methodName;
    @Override
    public String getContent() {
        return methodName;
    }
}
