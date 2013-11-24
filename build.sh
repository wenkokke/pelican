#!/bin/bash

SEMANTE_HOME="$HOME/.semante"

# Install Buildr
gem install buildr --no-ri --no-rdoc

# Install Prover9
wget http://www.cs.unm.edu/~mccune/mace4/download/LADR-2009-11A.tar.gz
tar xvfz LADR-2009-11A.tar.gz
cd LADR-2009-11A
make all
make test1
make test2
make test3
mkdir -p "${SEMANTE_HOME}/ladr"
mv ./bin/* "${SEMANTE_HOME}/ladr"
cd ..

# Install Lexicon
mv ./lexicon/src/main/resources/default.lex "${SEMANTE_HOME}/default.lexicon"

# Configure SemAnTE
cat << 'EOF' > "${SEMANTE_HOME}/settings.yml"
SemAnTE:
  Lexicon:
    Default:  '${SEMANTE_HOME}/default.lexicon'
  Prover:
    Location: '${SEMANTE_HOME}/ladr'
EOF

# Build Pelican Dependencies
buildr install-deps
