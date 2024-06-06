package com.serviceusergql.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphqlConfig {
    String query = """
            CREATE OR REPLACE FUNCTION insert_agent_and_user(ta_name CHARACTER VARYING,ta_mobile CHARACTER VARYING,ta_password CHARACTER VARYING)
            \tRETURNS INT
            \tAS
            \t$BODY$
            \tDECLARE
            \t\t\tcreated_on TIMESTAMP;
            \tBEGIN
            \t\t
            \t\tcreated_on:=current_timestamp;
            \t\t
            \t\tINSERT INTO cdm.tbl_agent("name",mobile_number,created_on) VALUES(ta_name,ta_mobile,created_on);
            \t\tINSERT INTO cdm.tbl_user("name",mobile,"password", created_on) VALUES(ta_name,ta_mobile,ta_password,created_on);\s
            \t\t
            \t\tRETURN 0;
            \t\t
            \tEND;
            \t$BODY$
            \tLANGUAGE plpgsql""";
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.DateTime)
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.GraphQLBigInteger);
    }
}
