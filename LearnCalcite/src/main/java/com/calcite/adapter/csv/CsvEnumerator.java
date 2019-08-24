/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.calcite.adapter.csv;

import com.calcite.adapter.csv.utils.RowConverter;
import com.opencsv.CSVReader;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.util.Source;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Enumerator that reads from a CSV file.
 *
 * @param <E> Row type
 */
public class CsvEnumerator<E> implements Enumerator<E> {


    private final CSVReader reader;
    private final String[] filterValues;
    private final AtomicBoolean cancelFlag;
    private final RowConverter<E> rowConverter;
    private E current;

    public CsvEnumerator(Source source, AtomicBoolean cancelFlag, String[] filterValues, RowConverter<E> rowConverter) {
        this.cancelFlag = cancelFlag;
        this.rowConverter = rowConverter;
        this.filterValues = filterValues;
        try {
            this.reader = new CSVReader(source.reader());
            this.reader.readNext(); // skip header row
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public E current() {
        return current;
    }

    @Override
    public boolean moveNext() {
        try {
            outer:
            for (; ; ) {
                if (cancelFlag.get()) {
                    return false;
                }
                final String[] strings = reader.readNext();
                if (strings == null) {
                    current = null;
                    reader.close();
                    return false;
                }
                if (filterValues != null) {
                    for (int i = 0; i < strings.length; i++) {
                        String filterValue = filterValues[i];
                        if (filterValue != null) {
                            if (!filterValue.equals(strings[i])) {
                                continue outer;
                            }
                        }
                    }
                }
                current = rowConverter.convertRow(strings);
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing CSV reader", e);
        }
    }
}
