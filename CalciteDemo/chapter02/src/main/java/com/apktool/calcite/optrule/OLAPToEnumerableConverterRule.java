package com.apktool.calcite.optrule;

import org.apache.calcite.adapter.enumerable.EnumerableConvention;
import org.apache.calcite.plan.RelTraitSet;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.convert.ConverterRule;

import com.apktool.calcite.relnode.OLAPRel;
import com.apktool.calcite.relnode.OLAPToEnumerableConverter;

public class OLAPToEnumerableConverterRule extends ConverterRule {

    public static final ConverterRule INSTANCE = new OLAPToEnumerableConverterRule();

    public OLAPToEnumerableConverterRule() {
        super(ConverterRule.Config.INSTANCE.withConversion(
                RelNode.class,
                OLAPRel.CONVENTION,
                EnumerableConvention.INSTANCE,
                "OLAPToEnumerableConverterRule")
        );
    }

    @Override
    public RelNode convert(RelNode rel) {
        RelTraitSet newTraitSet = rel.getTraitSet().replace(getOutConvention());
        return new OLAPToEnumerableConverter(rel.getCluster(), newTraitSet, rel);
    }
}
