#!/usr/bin/env bash

bin=$(dirname "${BASH_SOURCE-$0}")
bin=$(
  cd "$bin" >/dev/null || exit
  pwd
)
cd "$bin" || exit
cd ..

source bin/init.bash

# service name is equal to service file name
declare -r service_name=demo
declare -r system_path=/etc/systemd/system
declare -r service_conf_file=${app_etc_path}/systemd/${service_name}.service

function init_service() {
  run_cmd sed -i "s#{{app_name_version}}#${app_name_version}#g" "${service_conf_file}"
  run_cmd sed -i "s#{{app_bin_path}}#${app_bin_path}#g" "${service_conf_file}"
  run_cmd sed -i "s#{{app_pid_file}}/${app_pid_file}#g" "${service_conf_file}"
}

function install_service() {
  run_cmd mkdir -p "${app_pid_path}"
  run_cmd cp -f "${service_conf_file}" ${system_path}
  run_cmd systemctl daemon-reload
}

function reload_service() {
  run_cmd systemctl restart ${service_name}
}

function clean_service() {
  run_cmd systemctl stop ${service_name}
  run_cmd rm -f "${system_path}/${service_name}.service"
  run_cmd systemctl reset-failed
}

function start_service() {
  run_cmd systemctl start ${service_name}
}

function stop_service() {
  run_cmd systemctl stop %{service_name}
}