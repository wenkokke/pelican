> N     WORD:et

simple implementation of transitive nouns.

> N     WORD:((et)t)et

the below definitions deal with noun-clusters up to a size of 3 nouns.
it assumes the annotation is placed on the rightmost node, but at the moment this is not yet checked.
since this introduces a bad ambiguety into the lexicon this should be resolved as soon as possible,
most likely by turning it into a series of annotations $NC_2$, $NC_3$, $NC_4$, etc... for noun clusters of sizes
two, three and four respectively.

> NC2   \A:et.\GQ:(et)(et)t.\B:et.EXISTS:(et)t (\x:e.AND:ttt (GQ:(et)(et)t WORD:et (\z:e.AND:ttt (B:et z:e) (OF:eet z:e x:e))) (A:et x:e))
> NC3   \A1:et.\A2:et.\GQ:(et)(et)t.\B:et.EXISTS:(et)t (\x1:e.EXISTS:(et)t (\x2:e.AND:ttt (AND:ttt (GQ:(et)(et)t WORD:et (\z:e.AND:ttt (B:et z:e) (OF:eet z:e x1:e))) (A1:et x1:e)) (AND:ttt (OF:eet x1:e x2:e) (A2:et x2:e))))

the $NP_D$ annotation is now deprecated, to be replaced by the $NP$ annotation.
the reason for this is that the $NP_I$ annotation was completely useless, as we decided not to annotate nodes.

> NP_D  \A:et.A:et WORD:e
> NP    \A:et.A:et WORD:e

the below definitions deal with np-clusters up to a size of 3 nps. as for noun clusters, it is assumed that
the clustering annotation is placed on the right-most node.

> NPC2  \P:(et)t.\A:et.AND:ttt (A:et WORD:e) (P:(et)t A:et)
> NPC3  \P1:(et)t.\P2:(et)t.\A:et.AND:ttt (A:et WORD:e) (AND:ttt (P1:(et)t A:et) (P2:(et)t A:et))

> V_1   WORD:et

variable names for [sub,ob1] are [z,x] respectively.

> V_2   \Obj:(et)t.\Sub:(et)t.Obj:(et)t (\x:e.Sub:(et)t (\z:e.WORD:eet z:e x:e))

below definition allows for quantifier movement.

> V_2   \Obj:(et)t.\Sub:(et)t.Sub:(et)t (\z:e.Obj:(et)t (\x:e.WORD:eet z:e x:e))

below definition allows for predicative usage of verbs.

> V_2   \Obj:(et)t.\z:e.Obj:(et)t (\x:e.WORD:eet z:e x:e)

variable names for [sub,ob1,ob2] are [z,x,y] respectively.

> V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Sub:(et)t (\z:e.Obj1:(et)t (\x:e.Obj2:(et)t (\y:e.WORD:eeet z:e x:e y:e)))

below definitions allow for quantifier movement.

> V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Sub:(et)t (\z:e.Obj2:(et)t (\y:e.Obj1:(et)t (\x:e.WORD:eeet z:e x:e y:e)))
> V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Obj1:(et)t (\x:e.Sub:(et)t (\z:e.Obj2:(et)t (\y:e.WORD:eeet z:e x:e y:e)))
> V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Obj2:(et)t (\y:e.Sub:(et)t (\z:e.Obj1:(et)t (\x:e.WORD:eeet z:e x:e y:e)))
> V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Obj1:(et)t (\x:e.Obj2:(et)t (\y:e.Sub:(et)t (\z:e.WORD:eeet z:e x:e y:e)))
> V_3   \Obj1:(et)t.\Obj2:(et)t.\Sub:(et)t.Obj2:(et)t (\y:e.Obj1:(et)t (\x:e.Sub:(et)t (\z:e.WORD:eeet z:e x:e y:e)))

below definition allows for predicative usage of verbs.

> V_3   \Obj1:(et)t.\Obj2:(et)t.\z:e.Obj1:(et)t (\x:e.Obj2:(et)t (\y:e.WORD:eeet z:e x:e y:e))

> MI    \A:et.\x:e.AND:ttt (A:et x:e) (WORD:et x:e)
> MI    \P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t WORD:et)
> MR    \A:et.\x:e.AND:ttt (A:et x:e) (WORD:(et)et A:et x:e)
> MR    \P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t (WORD:(et)et A:et))

below definition is a true lovecraftian horror, and should be killed with fire as soon as a new solution allows it.

> MR    \P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t (WORD:(et)et A:et))

below definition is equivalent to MI.

> ADJ_I \A:et.\x:e.AND:ttt (A:et x:e) (WORD:et x:e)

below definition is equivalent to MR.

> ADJ_R \A:et.\x:e.WORD:(et)et A:et x:e

below definition is equality IS.

> IS    \x:e.\y:e.EQ:eet x:e y:e
> IS    \Sub:(et)t.\y:e.Sub:(et)t (\x:e.EQ:eet x:e y:e)
> IS    \x:e.\Obj:(et)t.Obj:(et)t (\y:e.EQ:eet x:e y:e)
> IS    \Sub:(et)t.\Obj:(et)t.Sub:(et)t (\x:e.Obj:(et)t (\y:e.EQ:eet x:e y:e))
> IS    \Sub:(et)t.\Obj:(et)t.Sub:(et)t (\x:e.Obj:(et)t (\y:e.EQ:eet x:e y:e))

below definition is predicative IS.

> IS    \M:(et)et.\x:e.M:(et)et (\y:e.T:t) x:e

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

> WHO_R \B:et.\A:et.\x:e.AND:ttt (A:et x:e) (B:et x:e)

below definition is equivalent to MA_PR.

> WHO_A \B:et.\P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t B:et)
> WHO_A \Q:(et)t.\P:(et)t.\A:et.EXISTS:(et)t (\x:e.AND:ttt (P:(et)t A:et) (AND:ttt (P:(et)t (EQ:eet x:e)) (Q:(et)t (EQ:eet x:e))))

> AND   AND:ttt
> AND   \A:et.\B:et.\x:e.AND:ttt (A:et x:e) (B:et x:e)
> AND   \P:(et)t.\Q:(et)t.\A:et.AND:ttt (P:(et)t A:et) (Q:(et)t A:et)

below definitions are pr... whatevers.

> TO    \P:(et)t.P:(et)t
> P_R   \P:(et)t.\A:et.\x:e.AND:ttt (A:et x:e) (P:(et)t (\y:e.WORD:eet x:e y:e))
% P_R   \P:(et)t.\A:et.\GQ:(et)(et)t.\B:et.AND:ttt (GQ:(et)(et)t A:et B:et) (GQ:(et)(et)t A:et (\x:e.P:(et)t (\y:e.WORD:eet x:e y:e)))

simple lexical binding of numbers as quantifiers, barring numerical inferences.

> NUMBER WORD:(et)t

dates are implemented as a special case of $NP$s, which includes date clusters.

> DATE   \A:et.A:et WORD:e
> DATEC2 \P:(et)t.\A:et.AND:ttt (A:et WORD:e) (P:(et)t A:et)
> DATEC3 \P1:(et)t.\P2:(et)t.\A:et.AND:ttt (A:et WORD:e) (AND:ttt (P1:(et)t A:et) (P2:(et)t A:et))

below definition is auxilliary verbs.

> V_AUX \x:e.x:e
> V_AUX \A:et.A:et
> V_AUX \P:(et)t.P:(et)t
> V_AUX \GQ:(et)(et)t.GQ:(et)(et)t
> V_AUX \M:(et)et.M:(et)et

below definition is equal to an identity function.

> IGNORE \x:e.x:e
> IGNORE \A:et.A:et
> IGNORE \P:(et)t.P:(et)t
> IGNORE \GQ:(et)(et)t.GQ:(et)(et)t
> IGNORE \M:(et)et.M:(et)et