package com.epam.antlr.example.node;

import com.epam.antlr.example.node.api.IASTNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NodeEntity(label = "methodInvocation")
public class MethodInvocationNode extends IASTNode {
    private String methodName;
    @Relationship(type = "ARGUMENT")
    private List<ArgumentNode> arguments;
}
