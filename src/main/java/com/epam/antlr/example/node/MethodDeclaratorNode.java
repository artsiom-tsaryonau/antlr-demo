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
@NodeEntity(label = "method")
public class MethodDeclaratorNode extends IASTNode {
    private String methodName;
    @Relationship(type = "PARAMETER")
    private List<ParameterNode> parameters = new ArrayList<>();
    @Relationship(type = "METHOD_INVOCATION")
    private List<MethodInvocationNode> invocations = new ArrayList<>();
}
