package com.epam.antlr.example.node;

import com.epam.antlr.example.node.api.IASTNode;

import lombok.Data;

@Data
public class CompilationUnitNode implements IASTNode {
    private String filename;
    private PackageDeclarationNode packageNode;
    private NormalClassDeclarationNode classNode;

    @Override
    public String getContent() {
        return filename;
    }
}
