package com.epam.antlr.example.node;

import com.epam.antlr.example.node.api.IASTNode;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@NodeEntity(label = "class")
public class NormalClassDeclarationNode extends IASTNode {
    private String className;
    @Relationship(type = "METHOD")
    private List<MethodDeclaratorNode> methods = new ArrayList<>();
}
