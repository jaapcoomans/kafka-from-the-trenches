#!/usr/bin/env bash

SCRIPT_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

docker-compose -f ${SCRIPT_HOME}/docker-compose/kafka/docker-compose.yml down
