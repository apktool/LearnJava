#!/bin/bash

source bin/init.bash

function usage() {
  echo "Usage: bash shell [ -t | --type (nginx|mysql)]
                        "
  exit 2
}

declare -A cmd_args
declare parse_type=unset

function cmd_parse() {
  parsed_arguments=$(getopt -a -n shell -o lt:o:h:p:u:w: --long help,type:,operation:,host:,port:,username:,password: -- "$@")
  valid_arguments=$?
  if [ "$valid_arguments" != "0" ]; then
    usage
  fi

  local operation=unset
  local type=unset
  local ip=unset
  local port=unset

  eval set -- "${parsed_arguments}"
  while :; do
    case "${1}" in
    -l | --help)
      usage
      shift 1
      ;;
    -t | --type)
      type="${2}"
      shift 2
      ;;
    -o | --operation)
      operation="${2}"
      shift 2
      ;;
    -h | --host)
      cmd_args[host]="${2}"
      shift 2
      ;;
    -p | --port)
      cmd_args[port]="${2}"
      shift 2
      ;;
    -u | --username)
      cmd_args[username]="${2}"
      shift 2
      ;;
    -w | --password)
      cmd_args[password]="${2}"
      shift 2
      ;;
    --)
      shift
      break
      ;;
    *)
      echo "Unexpected option: ${1} - this should not happen."
      usage
      ;;
    esac
  done

  case ${type} in
  "nginx")
    cmd_args[type]=${type}
    ;;
  "mysql")
    cmd_args[type]=${type}
    ;;
  "app")
    cmd_args[type]=${type}
    ;;
  "service")
    cmd_args[type]=${type}
    ;;
  "*")
    echo "Only support nginx, mysql, app, service"
    ;;
  esac

  case ${operation} in
  "init")
    cmd_args[operation]=${operation}
    ;;
  "install")
    cmd_args[operation]=${operation}
    ;;
  "start")
    cmd_args[operation]=${operation}
    ;;
  "stop")
    cmd_args[operation]=${operation}
    ;;
  "restart")
    cmd_args[operation]=${operation}
    ;;
  "clean")
    cmd_args[operation]=${operation}
    ;;
  *)
    echo "Only support init, start, stop, clean"
    usage
    ;;
  esac
}