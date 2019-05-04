package com.epam.antlr.example.node;

import lombok.Data;

@Data
public class PackageNode implements IASTNode {
    private String fullPackage;

    @Override
    public String getContent() {
        return fullPackage;
    }
}
