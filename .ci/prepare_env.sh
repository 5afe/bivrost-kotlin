#!/bin/bash
# fail if any commands fails
set -e

git branch

# strip the first char as that should always be "v" (as tags should be in the format "vX.X.X")
description="$(git describe --tags)"
export LIBRARY_VERSION=${description:1}
export RELEASE_NOTES=$TRAVIS_COMMIT_MESSAGE