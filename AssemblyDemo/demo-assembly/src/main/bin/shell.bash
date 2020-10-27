#!/usr/bin/env bash

bin=$(dirname "${BASH_SOURCE-$0}")
bin=$(
  cd "$bin" >/dev/null || exit
  pwd
)
cd "$bin" || exit
cd ..

source bin/env.bash
source bin/init.bash
source bin/cmd.bash
source bin/app.bash
source bin/nginx.bash
source bin/mysql.bash
source bin/service.bash

cmd_parse "${@}"

case "${cmd_args[type]}" in
"mysql")
  case "${cmd_args[operation]}" in
  "init")
    init_mysql "${cmd_args[username]}" "${cmd_args[password]}"
    ;;
  "install")
    install_mysql "${cmd_args[host]}" "${cmd_args[port]}" "${cmd_args[username]}" "${cmd_args[password]}"
    ;;
  "clean")
    clean_mysql "${cmd_args[host]}" "${cmd_args[port]}" "${cmd_args[username]}" "${cmd_args[password]}"
    ;;
  *)
    run_cmd echo "${cmd_args[operation]} is not supported."
    ;;
  esac
  ;;
"nginx")
  case "${cmd_args[operation]}" in
  "init")
    init_nginx "${cmd_args[host]}" "${cmd_args[port]}"
    ;;
  "install")
    install_nginx
    ;;
  "restart")
    reload_nginx
    ;;
  "clean")
    clean_nginx
    ;;
  *)
    run_cmd echo "${cmd_args[operation]} is not supported."
    ;;
  esac
  ;;
"app")
  case "${cmd_args[operation]}" in
  "init")
    init_app "${cmd_args[host]}" "${cmd_args[port]}" "${cmd_args[username]}" "${cmd_args[password]}"
    ;;
  "start")
    start_app
    ;;
  "stop")
    stop_app
    ;;
  "restart")
    stop_app
    start_app
    ;;
  *)
    run_cmd echo "${cmd_args[operation]} is not supported."
    ;;
  esac
  ;;
"service")
  case "${cmd_args[operation]}" in
  "init")
    init_service
    ;;
  "install")
    install_service
    ;;
  "start")
    start_service
    ;;
  "stop")
    stop_service
    ;;
  "restart")
    reload_service
    ;;
  "clean")
    clean_service
    ;;
  *)
    run_cmd echo "${cmd_args[operation]} is not supported."
    ;;
  esac
  ;;
*)
  run_cmd echo "${cmd_args[type]} is not supported."
  ;;
esac

: <<'!'
# usage
bash bin/shell.bash -t mysql -o init -u appUsername -w appPassword
bash bin/shell.bash -t mysql -o install -h localhost -p 3306 -u root -w rootPassword
bash bin/shell.bash -t mysql -o clean -h localhost -p 3306 -u root -w rootPassword
bash bin/shell.bash -t nginx -o init -h localhost -p 20817
bash bin/shell.bash -t nginx -o install
bash bin/shell.bash -t nginx -o restart
bash bin/shell.bash -t nginx -o clean
bash bin/shell.bash -t app -o init -h localhost -p 3306 -u appUsername -w appPassword
bash bin/shell.bash -t app -o start
bash bin/shell.bash -t app -o stop
bash bin/shell.bash -t app -o restart
bash bin/shell.bash -t service -o init
bash bin/shell.bash -t service -o install
bash bin/shell.bash -t service -o start
bash bin/shell.bash -t service -o stop
bash bin/shell.bash -t service -o restart
bash bin/shell.bash -t service -o clean
!