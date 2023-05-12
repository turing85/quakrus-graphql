#!/bin/bash

set -e
set -u

function create_user_and_database() {
	local database=${1}
	local pg_user="${POSTGRES_USER}"
	echo "  Creating user and database '${database}'"
	mkdir -p "/var/lib/postgresql/tablespaces/${database}"
	psql -v ON_ERROR_STOP=1 --username "${pg_user}" << EOF
    CREATE USER ${database} PASSWORD '${database}';
    CREATE DATABASE ${database};
    -- \c ${database};
    -- GRANT ALL PRIVILEGES ON SCHEMA public TO ${database} GRANTED BY ${pg_user};
EOF
}

if [ -n "${POSTGRES_MULTIPLE_DATABASES}" ]; then
	echo "Multiple database creation requested: ${POSTGRES_MULTIPLE_DATABASES}"
	for db in $(echo "${POSTGRES_MULTIPLE_DATABASES}" | tr ',' ' '); do
		create_user_and_database "${db}"
	done
	echo "Multiple databases created"
fi