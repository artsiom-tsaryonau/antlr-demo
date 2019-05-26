package com.epam.antlr.example.node;

import com.epam.antlr.example.node.api.IASTNode;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MethodDeclarationNode implements IASTNode {
    private String methodName;
    private List<ParameterNode> parameters = new ArrayList<>();

    @Override
    public String getContent() {
        return methodName;
    }
}
