package com.epam.antlr.example.node;

import com.epam.antlr.example.node.api.IASTNode;

import lombok.Data;

@Data
public class PackageDeclarationNode implements IASTNode {
    private String fullPackage;

    @Override
    public String getContent() {
        return fullPackage;
    }
}
