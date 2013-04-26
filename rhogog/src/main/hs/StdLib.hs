module StdLib where

import Prelude hiding (or,and)
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

-- |Complex semantic types.
levels :: IntMap [Ty]
levels = foldl level empty [0..5]

level :: IntMap [Ty] -> Int -> IntMap [Ty]
level m 0 = insert 0 [e,t] m
level m n = insert n [a ~> b | a <- d, b <- d] m where d = fold (++) [] m

-- |Encoding of universal quantifier in lambda calculus.
forall  :: Tm
forall   = Tm_Var "FORALL"  (Just et_t)

-- |Encoding of existential quantifier in lambda calculus.
exists  :: Tm 
exists   = Tm_Var "EXISTS"  (Just et_t)

-- |Encoding of iota function in lambda calculus.
iota    :: Tm
iota     = Tm_Var "IOTA"    (Just et_e)

-- |Encoding of material implication in lambda calculus.
implies :: Tm 
implies  = Tm_Var "IMPLIES" (Just ttt)

-- |Encoding of equality relation in lambda calculus.
eq      :: Tm 
eq       = Tm_Var "EQ"      (Just eet)

-- |Encoding of conjunction in lambda calculus.
and     :: Tm 
and      = Tm_Var "AND"     (Just ttt)

-- |Encoding of disjunction in lambda calculus.
or      :: Tm 
or       = Tm_Var "OR"      (Just ttt)

-- |Encoding of negation in lambda calculus.
not     :: Tm
not      = Tm_Var "NOT"     (Just tt)