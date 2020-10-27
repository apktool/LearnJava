#!/usr/bin/env bash

bin=$(dirname "${BASH_SOURCE-$0}")
bin=$(
  cd "$bin" >/dev/null || exit
  pwd
)
cd "$bin" || exit
cd ..

source bin/init.bash

function init_app() {
  local mysql_host=${1:-localhost}
  local mysql_port=${2:-3306}
  local mysql_username=${3}
  local mysql_password=${4}

  run_cmd sed -i "s/{{mysql_host}}/${mysql_host]}/g" config/application*.properties
  run_cmd sed -i "s/{{mysql_port}}/${mysql_port}/g" config/application*.properties
  run_cmd sed -i "s/{{mysql_username}}/${mysql_username}/g" config/application*.properties
  run_cmd sed -i "s/{{mysql_password}}/${mysql_password}/g" config/application*.properties
}

function start_app() {
  run_cmd mkdir -p "${app_pid_path}"

  local java_ops="-Xmx1024m -Xms512m -XX:MaxPermSize=200m -XX:PermSize=100m"
  local java_exec=$("echo ${JAVA_HOME}/bin/java")
  java_exec=$("readlink -f ${java_exec}")

  local cmd="${java_exec} ${java_ops} -cp .:${app_jars_path}/*:${app_lib_path}/* com.apktool.demo.App"
  local nohup_cmd="nohup ${cmd} >> ${app_log_path}/${app_name_version}_startup.out 2>&1 &"

  run_cmd echo "${nohup_cmd}"
  run_cmd $(nohup_cmd) & echo $! > "${app_pid_file}"
}

function stop_app() {
  if [ ! -f "${app_pid_file}" ]; then
    run_cmd echo "${app_pid_file} doesn't exists, ${app_name_version} have been ended."
    return 0
  fi
  run_cmd kill -9 $(cat "${app_pid_file}")
  run_cmd rm -f "${app_pid_file}"
}