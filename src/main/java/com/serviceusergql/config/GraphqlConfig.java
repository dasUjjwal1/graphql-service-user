package com.serviceusergql.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphqlConfig {
    String query = "CREATE OR REPLACE FUNCTION insert_agent_and_user(ta_name CHARACTER VARYING,ta_mobile CHARACTER VARYING,ta_password CHARACTER VARYING)\n" +
            "\tRETURNS INT\n" +
            "\tAS\n" +
            "\t$BODY$\n" +
            "\tDECLARE\n" +
            "\t\t\tcreated_on TIMESTAMP;\n" +
            "\tBEGIN\n" +
            "\t\t\n" +
            "\t\tcreated_on:=current_timestamp;\n" +
            "\t\t\n" +
            "\t\tINSERT INTO cdm.tbl_agent(\"name\",mobile_number,created_on) VALUES(ta_name,ta_mobile,created_on);\n" +
            "\t\tINSERT INTO cdm.tbl_user(\"name\",mobile,\"password\", created_on) VALUES(ta_name,ta_mobile,ta_password,created_on); \n" +
            "\t\t\n" +
            "\t\tRETURN 0;\n" +
            "\t\t\n" +
            "\tEND;\n" +
            "\t$BODY$\n" +
            "\tLANGUAGE plpgsql";
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.DateTime)
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.GraphQLBigInteger);
    }
}
