package com.calcite.linq4j;

import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.Function0;
import org.apache.calcite.linq4j.function.Function1;
import org.apache.calcite.linq4j.function.Functions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        Employee[] EMPS = {
            new Employee(100, "Fred", 10),
            new Employee(110, "Bill", 30),
            new Employee(120, "Eric", 10),
            new Employee(130, "Janet", 10),
        };

        Function1<Employee, Integer> EMP_DEPTNO_SELECTOR = employee -> employee.getDeptno();

        String s = Linq4j.asEnumerable(EMPS)
            .groupBy(
                EMP_DEPTNO_SELECTOR,
                (Function0<String>) () -> null,
                (v1, e0) -> v1 == null ? e0.getName() : (v1 + "+" + e0.getName()),
                (v1, v2) -> v1 + ": " + v2)
            .orderBy(Functions.identitySelector())
            .toList()
            .toString();

        logger.debug("[10: Fred+Eric+Janet, 30: Bill]");
        logger.info(s);
    }
}
