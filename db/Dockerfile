FROM postgres

RUN apt-get update && apt-get install -y dos2unix

# Copy over the database SQL dump.
COPY recordsdb.sql recordsdb.sql

# Copy the initialization scrip to /docker-entrypoint-initdb.d.
# Any scripts in this directory will be automatically run after startup.
COPY init_db.sh /docker-entrypoint-initdb.d/init_db.sh

# Convert script's encoding from Windows to Unix, and make it executable.
RUN dos2unix /docker-entrypoint-initdb.d/init_db.sh && chmod +x /docker-entrypoint-initdb.d/init_db.sh
