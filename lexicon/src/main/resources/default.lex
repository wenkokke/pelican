> N     WORD:et

simple implementation of transitive nouns.

> N_2   WORD:eet
> N_2   \P:(et)t.\y:e.P:(et)t (\x:e.WORD:eet x:e y:e)

the $NP_D$ annotation is now deprecated, to be replaced by the $NP$ annotation.
the reason for this is that the $NP_I$ annotation was completely useless, as we decided not to annotate nodes.

> NP_D  \A:et.A:et WORD:e
> NP    \A:et.A:et WORD:e

> V_1   WORD:et
> V_1   \Sub:(et)t.Sub:(et)t WORD:et

variable names for [sub,ob1] are [z,x] respectively.

> V_2   \Obj:(et)t.\Sub:(et)t.Obj:(et)t (\x:e.Sub:(et)t (\z:e.WORD:eet z:e x:e))
% V_2   \Obj:(et)t.\Sub:(et)t.Sub:(et)t (\z:e.Obj:(et)t (\x:e.WORD:eet z:e x:e))

below definition allows for predicative usage of transitive verbs.

> V_2   \Obj:(et)t.\z:e.Obj:(et)t (\x:e.WORD:eet z:e x:e)

variable names for [sub,ob1,ob2] are [z,x,y] respectively.

> V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Sub:(et)t (\z:e.Obj1:(et)t (\x:e.Obj2:(et)t (\y:e.WORD:eeet z:e x:e y:e)))
% V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Sub:(et)t (\z:e.Obj2:(et)t (\y:e.Obj1:(et)t (\x:e.WORD:eeet z:e x:e y:e)))
% V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Obj1:(et)t (\x:e.Sub:(et)t (\z:e.Obj2:(et)t (\y:e.WORD:eeet z:e x:e y:e)))
% V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Obj2:(et)t (\y:e.Sub:(et)t (\z:e.Obj1:(et)t (\x:e.WORD:eeet z:e x:e y:e)))
% V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Obj1:(et)t (\x:e.Obj2:(et)t (\y:e.Sub:(et)t (\z:e.WORD:eeet z:e x:e y:e)))
% V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Obj2:(et)t (\y:e.Obj1:(et)t (\x:e.Sub:(et)t (\z:e.WORD:eeet z:e x:e y:e)))

below definition allows for predicative usage of ditransitive verbs, as in "jan, who ran, sat".

> V_3   \Obj1:(et)t.\Obj2:(et)t.\z:e.Obj1:(et)t (\x:e.Obj2:(et)t (\y:e.WORD:eeet z:e x:e y:e))

> MI    \A:et.\x:e.AND:ttt (A:et x:e) (WORD:et x:e)
> MI    \P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t WORD:et)
> MOD_I \A:et.\x:e.AND:ttt (A:et x:e) (WORD:et x:e)
> MOD_I \P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t WORD:et)

> MR    \A:et.\x:e.AND:ttt (A:et x:e) (WORD:(et)et A:et x:e)
> MR    \P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t (WORD:(et)et A:et))
> MOD_R \A:et.\x:e.AND:ttt (A:et x:e) (WORD:(et)et A:et x:e)
> MOD_R \P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t (WORD:(et)et A:et))

below definition is a true lovecraftian horror, and should be killed with fire as soon as a new solution allows it.

> MR    \P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t (WORD:(et)et A:et))

below definition is equality IS.

> IS    \x:e.\y:e.EQ:eet x:e y:e
> IS    \Sub:(et)t.\y:e.Sub:(et)t (\x:e.EQ:eet x:e y:e)
> IS    \x:e.\Obj:(et)t.Obj:(et)t (\y:e.EQ:eet x:e y:e)
> IS    \Sub:(et)t.\Obj:(et)t.Sub:(et)t (\x:e.Obj:(et)t (\y:e.EQ:eet x:e y:e))
> IS    \Sub:(et)t.\Obj:(et)t.Sub:(et)t (\x:e.Obj:(et)t (\y:e.EQ:eet x:e y:e))

below definition is predicative IS.

> IS    \PRED:et.\x:e.PRED:et x:e
> IS    \MOD:(et)et.\x:e.MOD:(et)et (\y:e.T:t) x:e

> A     \A:et.\B:et.EXISTS:(et)t (\x:e.AND:ttt (A:et x:e) (B:et x:e))
> THE   \A:et.\B:et.B:et (IOTA:(et)e A:et)
> THE   \P:(et)t.\B:et.P:(et)t B:et

below definition is equivalent to A.

> SOME  \A:et.\B:et.EXISTS:(et)t (\x:e.AND:ttt (A:et x:e) (B:et x:e))
> EVERY \A:et.\B:et.FORALL:(et)t (\x:e.IMPLIES:ttt (A:et x:e) (B:et x:e))

below definitions is a superset of what we would actually like posessives to mean,
as right now the definition is equivalent to A and SOME.

> POSS  \A:et.\B:et.EXISTS:(et)t (\x:e.AND:ttt (A:et x:e) (B:et x:e))
> OWN   \A:et.A:et

below definition is equivalent to MI.

> WHO_R \PRED:et.\A:et.\x:e.AND:ttt (A:et x:e) (PRED:et x:e)

below definition is equivalent to MA_PR.

> WHO_A \B:et.\P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t B:et)
> WHO_A \Q:(et)t.\P:(et)t.\A:et.EXISTS:(et)t (\x:e.AND:ttt (P:(et)t A:et) (AND:ttt (P:(et)t (EQ:eet x:e)) (Q:(et)t (EQ:eet x:e))))

> AND   AND:ttt
> AND   \A:et.\B:et.\x:e.AND:ttt (A:et x:e) (B:et x:e)
> AND   \P:(et)t.\Q:(et)t.\A:et.AND:ttt (P:(et)t A:et) (Q:(et)t A:et)

below definitions are pr... whatevers.

> TO    \P:(et)t.P:(et)t

  the dog of john ran
  ix. (ran(x) /\ of(x,john))

"of" for relational nouns, e.g. "The widow of John".

> OF    \P:(et)t.\R:eet.\z:e.P:(et)t (\x:e.R:eet z:e x:e)

"'s" for relational nouns, e.g. "John's wife.", use "OF".

> S     \P:(et)t.\R:eet.\z:e.P:(et)t (\x:e.R:eet z:e x:e)
> GEN   \P:(et)t.\R:eet.\z:e.P:(et)t (\x:e.R:eet z:e x:e)

"'s" for non-relational nouns, e.g. "John's dog."

> S     \P:(et)t.\Q:(et)t.\A:et.Q:(et)t (\z:e.P:(et)t (\x:e.AND:ttt (A:et z:e) (R:eet z:e x:e)))
> GEN   \P:(et)t.\Q:(et)t.\A:et.Q:(et)t (\z:e.P:(et)t (\x:e.AND:ttt (A:et z:e) (R:eet z:e x:e)))

prepositions for modifications of noun phrases, e.g. "The man in the car." 

> P_R   \P:(et)t.\Q:(et)t.\A:et.Q:(et)t (\z:e.P:(et)t (\x:e.AND:ttt (A:et z:e) (WORD:eet z:e x:e)))

prepositions for modification of predicates, e.g. "John walked in the park."

> P_R   \P:(et)t.\A:et.\z:e.AND:ttt (A:et z:e) (P:(et)t (\y:e.WORD:eet (IOTA:(et)e A:et) y:e))

% GEN   \P:(et)t.\A:et.\B:et.P:(et)t (\x:e.B:et (IOTA:(et)e (\z:e.AND:ttt (A:et z:e) (OF:eet z:e x:e))))  

simple lexical binding of numbers as quantifiers, barring numerical inferences.
therefore it is equivalent to SOME.

> NUMBER \A:et.\B:et.EXISTS:(et)t (\x:e.AND:ttt (A:et x:e) (B:et x:e))

dates are implemented as a special case of $NP$s, which includes date clusters.

> DATE   \A:et.A:et WORD:e

below definition is auxilliary verbs.

> V_AUX \A:et.A:et
> V_AUX \A:eet.A:eet
> V_AUX \A:eeet.A:eeet
> V_AUX \A:((et)t)et.A:((et)t)et
> V_AUX \A:((et)t)((et)t)t.A:((et)t)((et)t)t
> V_AUX \A:((et)t)eet.A:((et)t)eet
> V_AUX \A:((et)t)((et)t)et.A:((et)t)((et)t)et
> V_AUX \A:((et)t)((et)t)((et)t)t.A:((et)t)((et)t)((et)t)t

below definition is equal to an identity function.

> IGNORE \x:e.x:e
> IGNORE \A:et.A:et
> IGNORE \P:(et)t.P:(et)t
> IGNORE \GQ:(et)(et)t.GQ:(et)(et)t
> IGNORE \M:(et)et.M:(et)et
