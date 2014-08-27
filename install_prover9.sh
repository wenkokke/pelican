#!/bin/bash

# set commands to verbose
set -x

# move to a temporary directory
TMPDIR=`mktemp -d -t prover9`
trap "rm -rf $TMPDIR" EXIT
cd $TMPDIR

# download and extract prover9 and patches
wget -q http://www.cs.unm.edu/~mccune/mace4/download/LADR-2009-11A.tar.gz >/dev/null
tar xfz LADR-2009-11A.tar.gz >/dev/null
wget -q https://gist.githubusercontent.com/pepijnkokke/8cd47c96991a1474ebc3/raw/37a92f2d718f3b3aaedd349824677b8f5d838e6e/prover9_makefile.patch >/dev/null
wget -q https://gist.githubusercontent.com/pepijnkokke/e4ab0514b9e8579ddf28/raw/5c2c7a19c85c56c40bd37e098fdbbc6ed233fd95/prover9_add_server.patch >/dev/null

# patch prover9
cd LADR-2009-11A
patch -p0 < ../prover9_makefile.patch >/dev/null
patch -p0 < ../prover9_add_server.patch >/dev/null

# build prover9
make all >/dev/null

# install prover9
mkdir -p "${PREFIX}"
mv ./bin/* "${PREFIX}"
