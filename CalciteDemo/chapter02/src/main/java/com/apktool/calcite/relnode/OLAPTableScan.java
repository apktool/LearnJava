package com.apktool.calcite.relnode;

import java.util.Collections;
import java.util.List;

import org.apache.calcite.adapter.enumerable.EnumerableRel;
import org.apache.calcite.adapter.enumerable.EnumerableRelImplementor;
import org.apache.calcite.adapter.enumerable.PhysType;
import org.apache.calcite.adapter.enumerable.PhysTypeImpl;
import org.apache.calcite.linq4j.tree.Blocks;
import org.apache.calcite.linq4j.tree.Expressions;
import org.apache.calcite.linq4j.tree.MethodCallExpression;
import org.apache.calcite.plan.RelOptCluster;
import org.apache.calcite.plan.RelOptPlanner;
import org.apache.calcite.plan.RelOptTable;
import org.apache.calcite.plan.RelTraitSet;
import org.apache.calcite.rel.core.TableScan;

import com.apktool.calcite.optrule.OLAPToEnumerableConverterRule;
import com.apktool.calcite.schema.OLAPTable;

public class OLAPTableScan extends TableScan implements OLAPRel, EnumerableRel {

    public OLAPTableScan(RelOptCluster cluster, RelTraitSet traitSet, RelOptTable table) {
        super(cluster, traitSet, Collections.emptyList(), table);
    }

    @Override
    public Result implement(EnumerableRelImplementor implementor, Prefer prefer) {
        PhysType physType = PhysTypeImpl.of(implementor.getTypeFactory(), getRowType(), prefer.preferArray());
        MethodCallExpression exprCall = Expressions.call(
                table.getExpression(OLAPTable.class),
                "executeMemQuery",
                implementor.getRootExpression()
        );
        return implementor.result(physType, Blocks.toBlock(exprCall));
    }

    @Override
    public void register(RelOptPlanner planner) {
        planner.addRule(OLAPToEnumerableConverterRule.INSTANCE);
    }

    @Override
    public EnumerableRel implementEnumerable(List<EnumerableRel> inputs) {
        return this;
    }
}
