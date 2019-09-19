package com.calcite.adapter.chapter1;

import org.apache.calcite.config.Lex;
import org.apache.calcite.plan.RelTraitDef;
import org.apache.calcite.rel.RelRoot;
import org.apache.calcite.rel.type.RelDataTypeSystem;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.tools.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.calcite.plan.Contexts.EMPTY_CONTEXT;

public class QueryPlanner {
    private final Planner planner;

    public QueryPlanner(SchemaPlus schema) {
        List<RelTraitDef> traitDefs = new ArrayList<>();

        /*
         * Lexical configuration defines how identifiers are quoted, whether they are converted to upper or lower case
         * when they are read, and whether identifiers are matched case-sensitively.
         *
         * .defaultSchema(schema)        // Sets the schema to use by the planner
         * .context(EMPTY_CONTEXT)       // Context can store data within the planner session for access by planner rules
         * .ruleSets(RuleSets.ofList())  // Rule sets to use in transformation phases
         * .costFactory(null)            // Custom cost factory to use during optimization
         */

        FrameworkConfig calciteFrameworkConfig = Frameworks.newConfigBuilder()
            .parserConfig(SqlParser.configBuilder().setLex(Lex.MYSQL).build())
            .defaultSchema(schema)
            .traitDefs(traitDefs)
            .context(EMPTY_CONTEXT)
            .ruleSets(RuleSets.ofList())
            .costFactory(null)
            .typeSystem(RelDataTypeSystem.DEFAULT)
            .build();

        this.planner = Frameworks.getPlanner(calciteFrameworkConfig);
    }

    public RelRoot getRelRoot(String query) {
        RelRoot relRoot = null;

        try {
            SqlNode sqlNode = planner.parse(query);
            SqlNode validatedSqlNode = planner.validate(sqlNode);
            relRoot = planner.rel(validatedSqlNode);
        } catch (SqlParseException e) {
            throw new RuntimeException("Query parsing error.", e);
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (RelConversionException e) {
            e.printStackTrace();
        }

        return relRoot;
    }
}
