package com.epam.antlr.example.node.api;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@Data
@NodeEntity // remove on 3.1.3 OGM, currently bug https://github.com/neo4j/neo4j-ogm/issues/437
public abstract class IASTNode {
    @Id @GeneratedValue
    private Long id;
}
