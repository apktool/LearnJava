#!/usr/bin/env bash

bin=$(dirname "${BASH_SOURCE-$0}")
bin=$(
  cd "$bin" >/dev/null || exit
  pwd
)
cd "$bin" || exit
cd ..

source bin/init.bash

declare -r conf_name=demo
declare -r nginx_etc_path=/etc/nginx/conf.d
declare -r nginx_conf_file=${app_etc_path}/nginx/${conf_name}.conf
declare -r nginx_ca_path=/etc/pki/nginx
declare -r nginx_ca_file=${nginx_ca_path}/${app_name}

function init_nginx() {
  local app_ip=${1:-localhost}
  local app_port=${2:-8080}

  if [ -z "${app_ip}" ] || [ -z "${app_port}" ]; then
    run_cmd echo "Ip and port is null"
    exit 1
  fi

  run_cmd mkdir -p ${nginx_ca_path}
  run_cmd openssl req -x509 -new -newkey rsa:2048 -sha256 -nodes -out "${nginx_ca_file}.crt" -keyout "${nginx_ca_file}.key" -subj "/C=CN/ST=ShenZhen/L=ShenZhen/O=Apktool Inc./OU=Web Security/CN=acronymor.com"

  run_cmd sed -i "s/server.port=.*$/server.port=${app_port}/g" config/application*.properties
  run_cmd sed -i "s/{{app_ip}}/${app_ip}/g" "${nginx_conf_file}"
  run_cmd sed -i "s/{{app_port}}/${app_port}/g" "${nginx_conf_file}"
  run_cmd sed -i "s#{{nginx_ca_file}}#${nginx_ca_file}#g" "${nginx_conf_file}"
}

function install_nginx() {
  if [ ! -d "${nginx_etc_path}" ]; then
    run_cmd echo "${nginx_etc_path} does not exist"
    exit 1
  fi

  run_cmd cp -f "${nginx_conf_file}" ${nginx_etc_path}
}

function reload_nginx() {
  run_cmd nginx -s reload
}

function clean_nginx() {
  run_cmd rm -f "${nginx_ca_file}.*"
  run_cmd rm -f "${nginx_etc_path}/${conf_name}.conf"
}