#!/bin/bash

SEMANTE_HOME="$HOME/.semante"
mkdir -p "$SEMANTE_HOME"

PREFIX="$SEMANTE_HOME/bin" ./install_prover9.sh

cp ./lexicon/src/main/resources/default.lexicon "${SEMANTE_HOME}/default.lexicon"
cp ./lexicon/src/main/resources/legacy.lexicon "${SEMANTE_HOME}/legacy.lexicon"

# Configure SemAnTE
cat <<EOF > "${SEMANTE_HOME}/default.yml"
SemAnTE:
  Lexicon:
    Default:  '${SEMANTE_HOME}/default.lexicon'
  Prover:
    Location: '${SEMANTE_HOME}/bin'
  Tracer:
    Pipeline: 'false'
    PredCalc: 'false'
    Prover:   'false'
    Smasher:  'false'
EOF
cat <<EOF > "${SEMANTE_HOME}/legacy.yml"
SemAnTE:
  Lexicon:
    Default:  '${SEMANTE_HOME}/legacy.lexicon'
  Prover:
    Location: '${SEMANTE_HOME}/bin'
  Tracer:
    Pipeline: 'false'
    PredCalc: 'false'
    Prover:   'false'
    Smasher:  'false'
EOF
