#!/bin/sh

projet=$1
dir=`date +%Y-%m-%d`-$projet

cp -a template $dir
git add $dir
cd $dir
lein deps
