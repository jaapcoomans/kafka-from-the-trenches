#!/usr/bin/env bash

SCRIPT_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

docker-compose -f ${SCRIPT_HOME}/docker-compose.yml exec kafka-broker kafka-console-consumer --topic order_status_changed --bootstrap-server localhost:9092
