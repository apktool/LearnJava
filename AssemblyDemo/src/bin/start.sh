#!/usr/bin/env bash

bin=$(dirname "${BASH_SOURCE-$0}")
bin=$(cd "$bin" > /dev/null || exit; pwd)
cd "$bin" || exit

cd ..

java -cp .:assembly-artifactId-1.0-SNAPSHOT.jar com.tmp.App
