#!/usr/bin/env sh

set -e -u

mysqld_safe --datadir='/var/lib/mysql' &
sleep 2
mysqladmin create chaos-loris

ln -fs $PWD/maven $HOME/.m2

cd chaos-loris
./mvnw -q package

mysqladmin shutdown
