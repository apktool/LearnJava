package com.apktool.calcite.relnode;

import java.util.List;

import org.apache.calcite.adapter.enumerable.EnumerableRel;
import org.apache.calcite.adapter.enumerable.EnumerableRelImplementor;
import org.apache.calcite.plan.ConventionTraitDef;
import org.apache.calcite.plan.RelOptCluster;
import org.apache.calcite.plan.RelTraitSet;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.convert.ConverterImpl;

public class OLAPToEnumerableConverter extends ConverterImpl implements EnumerableRel {
    public OLAPToEnumerableConverter(RelOptCluster cluster, RelTraitSet traits, RelNode input) {
        super(cluster, ConventionTraitDef.INSTANCE, traits, input);
    }

    @Override
    public RelNode copy(RelTraitSet traitSet, List<RelNode> inputs) {
        return new OLAPToEnumerableConverter(getCluster(), traitSet, sole(inputs));
    }

    @Override
    public Result implement(EnumerableRelImplementor enumerableRelImplementor, Prefer prefer) {
        OLAPRel.JavaImplementor impl = new OLAPRel.JavaImplementor(enumerableRelImplementor);
        EnumerableRel inputAsEnum = impl.createEnumerable((OLAPRel) getInput());
        replaceInput(0, inputAsEnum);
        return impl.visitChild(this, 0, inputAsEnum, prefer);
    }
}
