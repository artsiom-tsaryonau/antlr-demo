package com.epam.antlr.example.node;

import lombok.Data;

@Data
public class ClassDeclarationNode implements IASTNode {
    private String className;

    @Override
    public String getContent() {
        return className;
    }
}
