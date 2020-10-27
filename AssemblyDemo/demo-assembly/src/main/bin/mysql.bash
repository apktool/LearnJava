#!/usr/bin/env bash

bin=$(dirname "${BASH_SOURCE-$0}")
bin=$(
  cd "$bin" >/dev/null || exit
  pwd
)
cd "$bin" || exit
cd ..

function init_mysql() {
  local mysql_username=${1}
  local mysql_password=${2}

  if [ -z "${mysql_username}" ] || [ -z "${mysql_password}" ]; then
    run_cmd echo "mysql username or password can't be null."
    exit 1
  fi

  run_cmd sed -i "s/{{mysql_username}}/${mysql_username}/g" "${app_scripts_path}/init.sql"
  run_cmd sed -i "s/{{mysql_password}}/${mysql_password}/g" "${app_scripts_path}/init.sql"
  run_cmd sed -i "s/{{mysql_username}}/${mysql_username}/g" "${app_scripts_path}/drop.sql"
}

function install_mysql() {
  local mysql_host=${1:-localhost}
  local mysql_port=${2:-3306}
  local mysql_username=${3:-root}
  local mysql_password=${4}

  if [ -z "${mysql_username}" ] || [ -z "${mysql_password}" ]; then
    run_cmd echo "mysql username or password can't be null."
    exit 1
  fi

  local cmd=mysql -h"${mysql_host}" -P"${mysql_port}" -u"${mysql_username}" -p"${mysql_password}" < "${app_scripts_path}/init.sql"

  if [ ! $(cmd) -eq 0 ]; then
    run_cmd echo "MySQL execute error"
    exit 1
  fi
}

function clean_mysql() {
  local mysql_host=${1:-localhost}
  local mysql_port=${2:-3306}
  local mysql_username=${3:-root}
  local mysql_password=${4}

  if [ -z "${mysql_username}" ] || [ -z "${mysql_password}" ]; then
    run_cmd echo "mysql username or password can't be null."
    exit 1
  fi

  local cmd=mysql -h"${mysql_host}" -P"${mysql_port}" -u"${mysql_username}" -p"${mysql_password}" < "${app_scripts_path}/drop.sql"

  if [ ! $(cmd) -eq 0 ]; then
    run_cmd echo "MySQL execute error"
    exit 1
  fi
}