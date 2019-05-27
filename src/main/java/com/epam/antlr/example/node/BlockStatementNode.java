package com.epam.antlr.example.node;

import com.epam.antlr.example.node.api.IASTNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.NodeEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@NodeEntity(label = "blockStatement")
public class BlockStatementNode extends IASTNode {



}
