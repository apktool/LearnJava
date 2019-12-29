# Calcite

[Calcite](https://calcite.apache.org)

# Usage

```bash
java -cp .:LearnCalcite-1.0-SNAPSHOT.jar com.calcite.adapter.csv.App
```

```bash
java -cp .:LearnCalcite-1.0-SNAPSHOT.jar sqlline.SqlLine
sqlline> !connect jdbc:calcite:model=conf/model.json admin admin
sqlline> SELECT * FROM DEPTS;
sqlline> !quit
```

# Note

Maybe you should modify resources/model.json file when you are using IntelliJ IDEA.
