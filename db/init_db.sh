#!/bin/bash

set -e

# Open the PostgreSQL shell with full admin rights,
RUN_TO_CREATE_DB="psql -U postgres"

# Create the admin as well as the database.
$RUN_TO_CREATE_DB <<CREATE
create user cmsc495 with login encrypted password 'cmsc495';
create database recordsdb owner cmsc495;
\connect recordsdb;
alter user cmsc495 with SUPERUSER;
CREATE

# Run the PostgreSQL shell again, as the user just created, 
# and populate the new database with the SQL dump file:
psql -U cmsc495 -f ./recordsdb.sql recordsdb