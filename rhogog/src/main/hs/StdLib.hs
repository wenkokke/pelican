module StdLib where

import Base
import Control.Monad (join)
import Data.IntMap (IntMap,(!),insert,empty,fold)

-- |Semantic type combination.
(~>) :: Ty -> Ty -> Ty
(~>) = Ty_App

-- |Basic semantic types.
e,t :: Ty
e = Ty_Var 'e'
t = Ty_Var 't'

-- |Linear semantic types.
et    = e ~> t
eet   = e ~> et
eeet  = e ~> eet
ee    = e ~> e
tt    = t ~> t
ttt   = t ~> tt
et_t  = et ~> t
et_e  = et ~> e
et_et = et ~> et

-- |Complex semantic types.
levels :: IntMap [Ty]
levels = foldl level empty [0..5]

level :: IntMap [Ty] -> Int -> IntMap [Ty]
level m 0 = insert 0 [e,t] m
level m n = insert n [a ~> b | a <- d, b <- d] m where d = fold (++) [] m

-- |Function definition function.
def :: Var -> Ty -> Tm
def x ty = Tm_Var (Ident_Ident x $ Just ty)

-- |Encoding of universal quantifier in lambda calculus.
forall  :: Tm
forall   = def "FORALL" et_t

-- |Encoding of existential quantifier in lambda calculus.
exists  :: Tm 
exists   = def "EXISTS" et_t

-- |Encoding of iota function in lambda calculus.
iota    :: Tm
iota     = def "IOTA" et_e

-- |Encoding of material implication in lambda calculus.
implies :: Tm 
implies  = def "IMPLIES" ttt

-- |Encoding of equality relation in lambda calculus.
eq      :: Tm 
eq       = def "EQ" eet

-- |Encoding of conjunction in lambda calculus.
and     :: Tm 
and      = def "AND" ttt

-- |Encoding of disjunction in lambda calculus.
or      :: Tm 
or       = def "OR" ttt

-- |Encoding of negation in lambda calculus.
not     :: Tm
not      = def "NOT" tt