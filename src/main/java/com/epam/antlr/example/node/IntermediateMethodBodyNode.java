package com.epam.antlr.example.node;

import com.epam.antlr.example.node.api.IASTNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Specific kind of node that wraps multiple expression inside method body
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IntermediateMethodBodyNode extends IASTNode {
    private List<MethodInvocationNode> methodInvocations;
}
