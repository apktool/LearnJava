#!/usr/bin/env bash

bin=$(dirname "${BASH_SOURCE-$0}")
bin=$(cd "$bin" > /dev/null || exit; pwd)
cd "$bin" || exit
cd ..

java -cp .:LearnCalcite-1.0-SNAPSHOT.jar com.calcite.adapter.App

:<<'!'
java -cp .:LearnCalcite-1.0-SNAPSHOT.jar sqlline.SqlLine
sqlline> !connect jdbc:calcite:model=conf/model.json admin admin
sqlline> SELECT * FROM DEPTS;
sqlline> !quit
!
