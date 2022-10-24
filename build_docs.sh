#!/bin/bash -x

date
whoami
pwd
git --version
java -version

git config --global user.email "git@github.com"
git config --global user.name "build.sh"

cid=$(git rev-parse HEAD)

git checkout docs --
git rebase master
mkdir -p docs

./gradlew clean

./gradlew run -q >./docs/index.html

git add docs
git status
git commit -m "auto build of $cid"
git push -f
