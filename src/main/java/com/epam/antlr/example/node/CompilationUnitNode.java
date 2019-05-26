package com.epam.antlr.example.node;

import com.epam.antlr.example.node.api.IASTNode;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@NodeEntity(label = "compilationUnit")
public class CompilationUnitNode extends IASTNode {
    private String filename;
    @Relationship(type = "PACKAGE")
    private PackageDeclarationNode packageNode;
    @Relationship(type = "CLASS")
    private NormalClassDeclarationNode classNode;
}
