#!/usr/bin/env bash

bin=$(dirname "${BASH_SOURCE-$0}")
bin=$(
  cd "$bin" >/dev/null || exit
  pwd
)
cd "$bin" || exit
cd ..

declare app_name=demo
declare app_version=1.0.0
declare app_name_version=${app_name}-${app_version}
declare app_root_path=$(pwd)
declare app_bin_path=${app_root_path}/bin
declare app_config_path=${app_root_path}/config
declare app_scripts_path=${app_root_path}/scripts
declare app_etc_path=${app_root_path}/etc
declare app_jars_path=${app_root_path}/jars
declare app_lib_path=${app_root_path}/lib
declare app_doc_path=${app_root_path}/docs
declare app_log_path=${app_root_path}/logs
declare app_log_file=${app_log_path}/${app_name}-${app_version}.out
declare app_pid_path=${app_root_path}/pid
declare app_pid_file=${app_log_path}/${app_name_version}.pid

function run_cmd() {
  if [ ! -d "${app_log_path}" ]; then
    mkdir -p "${app_log_path}"
  fi

  local date_format="+%Y-%m-%d %H:%M:%S"
  echo -e "$(date "${date_format}") INFO [${app_name}] $*" | tee -a "${app_log_file}"

  local cmd=$1
  shift

  $cmd "$@"
}