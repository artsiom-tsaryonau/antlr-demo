package com.epam.antlr.example.node;

import com.epam.antlr.example.node.api.IASTNode;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@NodeEntity(label = "package")
public class PackageDeclarationNode extends IASTNode {
    private String fullPackage;
}
