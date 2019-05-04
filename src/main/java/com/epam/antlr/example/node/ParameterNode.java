package com.epam.antlr.example.node;

import lombok.Data;

@Data
public class ParameterNode implements IASTNode {
    private String name;
    private String type;

    @Override
    public String getContent() {
        return name + ":" + type;
    }
}
