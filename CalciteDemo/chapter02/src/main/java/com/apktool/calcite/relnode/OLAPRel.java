package com.apktool.calcite.relnode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.calcite.adapter.enumerable.EnumerableRel;
import org.apache.calcite.adapter.enumerable.EnumerableRelImplementor;
import org.apache.calcite.plan.Convention;
import org.apache.calcite.rel.RelNode;

import com.google.common.collect.Lists;

public interface OLAPRel extends RelNode {
    public static final Convention CONVENTION = new Convention.Impl("OLAP", OLAPRel.class);

    public static class JavaImplementor extends EnumerableRelImplementor {
        public JavaImplementor(EnumerableRelImplementor enumImplementor) {
            super(enumImplementor.getRexBuilder(), new LinkedHashMap<>());
        }

        public EnumerableRel createEnumerable(OLAPRel parent) {
            ArrayList<EnumerableRel> enumInputs = null;
            List<RelNode> children = parent.getInputs();
            if (children != null) {
                enumInputs = Lists.newArrayListWithCapacity(children.size());
                for (RelNode child : children) {
                    enumInputs.add(createEnumerable((OLAPRel) child));
                }
            }

            EnumerableRel result = parent.implementEnumerable(enumInputs);
            return result;
        }

        @Override
        public EnumerableRel.Result visitChild(EnumerableRel parent, int ordinal, EnumerableRel child,
                                               EnumerableRel.Prefer prefer) {
            return super.visitChild(parent, ordinal, child, prefer);
        }
    }

    public EnumerableRel implementEnumerable(List<EnumerableRel> inputs);
}