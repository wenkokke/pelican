\section{Stable Annotations}

\subsection{Nouns and Noun Phrases}

Nouns are annotated as $N$ or $N_2$ depending on their transitivity.

> N_1   WORD:et
> N_2   WORD:eet
> N_2   \P:(et)t.\y:e.P:(et)t (\x:e.WORD:eet x:e y:e)

> $NC_1$ \A:et.\z:e.(WORD:(et)et A:et) z:e
> $NC_2$ \A:et.\B:et.\z:e.((WORD:(et)((et)et) A:et) B:et) z:e
> $NC_3$ \A:et.\B:et.\C:et.\z:e.(((WORD:(et)((et)((et)et)) A:et) B:et) C:et) z:e
> $NC_4$ \A:et.\B:et.\C:et.\D:et.\z:e.((((WORD:(et)((et)((et)((et)et))) A:et) B:et) C:et) D:et) z:e

Noun Phrases are represented as words of type $e$, lifted to their usual pessimistic
Montague type of $(et)t$.

> NP    \A:et.A:et WORD:e

> $NPC_1$ \P:(et)t.\A:et.A:et (WORD:((et)t)e P:(et)t)
> $NPC_2$ \P:(et)t.\Q:(et)t.\A:et.A:et ((WORD:((et)t)((et)t)e P:(et)t) Q:(et)t)
> $NPC_3$ \P:(et)t.\Q:(et)t.\R:(et)t.\A:et.A:et (((WORD:((et)t)((et)t)((et)t)e P:(et)t) Q:(et)t) R:(et)t)
> $NPC_4$ \P:(et)t.\Q:(et)t.\R:(et)t.\S:(et)t.\A:et.A:et ((((WORD:((et)t)((et)t)((et)t)((et)t)e P:(et)t) Q:(et)t) R:(et)t) S:(et)t)

\subsection{Verbs and Verb Phrases}

Verbs are annotated based on their transitivity. The ambiguous definitions allow verbs
to be lifted to accept pessimisticly typed noun phrases as their arguments. Because
of this, $V_1$ either has the type $et$ or the type $((et)t)t$.

The variable names associated with the quantifiers $Sub$, $Obj$ or $Obj^1$ and $Obj^2$ are
$z$, $x$ and $y$ respectively.

> V_1   WORD:et
> V_2   \Obj1:(et)t.\z:e.Obj1:(et)t (\x:e.WORD:eet x:e z:e)
> V_3   \Obj1:(et)t.\Obj2:(et)t.\z:e.Obj1:(et)t (\x:e.Obj2:(et)t (\y:e.WORD:eeet z:e x:e y:e))

\subsection{Modifiers}

Modifiers are annotated as $\text{MOD}_I$ or $\text{MOD}_R$ depending on whether they
are \textit{intersective} or \textit{restrictive}.

> MOD_I \A:et.\x:e.AND:ttt (A:et x:e) (WORD:et x:e)

> MOD_R \A:et.\z:e.AND:ttt (A:et z:e) ((WORD:(et)et A:et) z:e)
> MOD_R \A:eet.\x:e.\z:e.AND:ttt (A:eet z:e x:e) ((WORD:(et)et (\z:e.A:eet z:e x:e)) z:e)
> MOD_R \A:eeet.\x:e.\y:e.\z:e.AND:ttt (A:eeet z:e x:e y:e) ((WORD:(et)et (\z:e.A:eeet z:e x:e y:e)) z:e)

Two specialized versions of $\text{MOD}_I$ and $\text{MOD}_R$\footnote{
  \textbf{TODO}: provide examples in which these versions are needed.
}.

> MOD_I \P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t WORD:et)
> MOD_R \P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t (WORD:(et)et A:et))

Furthermore, $\text{WHO}_R$ returns an application of $\text{MOD}_I$.

> WHO_R \PRED:et.\A:et.\x:e.AND:ttt (A:et x:e) (PRED:et x:e)

And lastly, $\text{WHO}_A$ and $\text{MOD}_A$, which handle appositive modification.

> WHO_A \B:et.\P:(et)t.\A:et.(P:(et)t (\x:e.AND:ttt (A:et x:e) (B:et x:e)))
> MOD_A \P:(et)t.\A:et.AND:ttt (P:(et)t A:et) (P:(et)t WORD:et)

This special instance of appositive modification handles sentences such as "Jan, a man, ran",
in which a noun phrase is modified by a noun phrase. This could be solved by the insertion
of "who is", which is exactly what this instance of $\text{WHO}_A$ does.

> WHO_A \Q:(et)t.\P:(et)t.\A:et.EXISTS:(et)t (\x:e.AND:ttt (A:et x:e) (AND:ttt (P:(et)t (EQ:eet x:e)) (Q:(et)t (EQ:eet x:e))))

\subsection{Generalized Quantifiers}

The definite article introduces an application of the $\iota$-function.

> THE   \A:et.\B:et.B:et (IOTA:(et)e A:et)

Though it may look a bit strange, this version of the definite article allows
constructions such as "The John Lennon", where it is applied to something that
is already a definite\footnote{
  \textbf{TODO} perhaps this version should be renamed, to avoid accidental usage of $NP$ instead of $N$.
}.

> THE   \P:(et)t.\B:et.P:(et)t B:et

The indefinite article introduces an existential quantifier, and can be
approached through the annotation $A$ and its alias $\text{SOME}$

> A       \A:et.\B:et.EXISTS:(et)t (\x:e.AND:ttt (A:et x:e) (B:et x:e))
> SOME    \A:et.\B:et.EXISTS:(et)t (\x:e.AND:ttt (A:et x:e) (B:et x:e))

Finally, the annotation $\text{EVERY}$ introduces a univeral quantifier.

> EVERY \A:et.\B:et.FORALL:(et)t (\x:e.IMPLIES:ttt (A:et x:e) (B:et x:e))

\subsection{The $\text{AND}$ Annotation}

The $\text{AND}$ annotation implements a polymorphic conjunction, that
reduces down to usage of the first-order primitive $(\land)$.

> AND   AND:ttt
> AND   \A:et.\B:et.\x:e.AND:ttt (A:et x:e) (B:et x:e)
> AND   \P:(et)t.\Q:(et)t.\A:et.AND:ttt (P:(et)t A:et) (Q:(et)t A:et)

\subsection{The $\text{IS}$ Annotation}

The annotation $\text{IS}$ unfolds either to $\text{IS}_{EQ}$ or
$\text{IS}_{PRED}$, depending on the types.

The first definition of $\text{IS}$ creates an equality relation.

> IS      \x:e.\y:e.EQ:eet x:e y:e
> IS      \Sub:(et)t.\y:e.Sub:(et)t (\x:e.EQ:eet x:e y:e)
> IS      \x:e.\Obj:(et)t.Obj:(et)t (\y:e.EQ:eet x:e y:e)
> IS      \Sub:(et)t.\Obj:(et)t.Sub:(et)t (\x:e.Obj:(et)t (\y:e.EQ:eet x:e y:e))
> IS      \Sub:(et)t.\Obj:(et)t.Sub:(et)t (\x:e.Obj:(et)t (\y:e.EQ:eet x:e y:e))

The second definition of $\text{IS}$ is an identity function on predicates.

> IS      \PRED:et.\x:e.PRED:et x:e
> IS      \MOD:(et)et.\x:e.MOD:(et)et (\y:e.T:t) x:e

\subsection{Prepositions}

The $\text{OF}$ annotation handles "of" for relational nouns, e.g. "The widow of John".
This means that when used in sentences such as "The dog of John ran", it will raise a
type error.\footnote{
  I feel that this is the correct behaviour, as the sentence is not in fact grammatical,
  and other uses of "of" are far to complext to handle at this stage.
}

Use of this annotation will assume that the noun in the sentence is a transitive noun---at
the moment that means that it has to be explicitly annotated with with $N_2$---and apply
the transitive noun, i.e $\iota x. widow(John)(x)$.

> OF    \P:(et)t.\R:eet.\z:e.P:(et)t (\x:e.R:eet z:e x:e)

The annotation $S$ or $\text{GEN}$ is to be used for annotating "'s"; its meaning is determined
based on the type of the noun.

For relational nouns, it behaves as $\text{OF}$, e.g. "John's wife" becomes $\iota x. wife(John)(x)$.

> GEN   \P:(et)t.\R:eet.\A:et.IOTA:(et)e (\z:e.AND:ttt (P:(et)t (\x:e.R:eet x:e z:e)) (A:et z:e))

For non-relational nouns, e.g. in "John's dog", it behaves as the general $P_R$ discussed below.

> GEN   \P:(et)t.\N:et.\V:et.IOTA:(et)e (\z:e.AND:ttt (V:et z:e) (P:(et)t (\x:e.R:eet z:e x:e)))

% [John [walks [in Boston]]]
% in : NP -> N -> N
% in(walk,john,boston) /\ walk(john)

% [[The [man [in Boston]]] walks]
% in : NP -> N -> N
% ix. in(man,x,boston) /\ walk(x)

Other prepositions are denoted by triples. For instance, "John walks in Boston" is treated
as $\text{walk}(\text{John}) \land \text{in}(\text{John},\text{walk},\text{boston})$; whereas "The man
in Boston walks" is treated as $\iota x. \text{man}(x) \land \text{in}(x,\text{man},\text{boston})
\land \text{walks}(x)$.

> P_R   \P:(et)t.\A:et.\x:e.AND:ttt (A:et x:e) (P:(et)t (\y:e.WORD:e(et)et y:e A:et x:e))
> P_I   \P:(et)t.\A:et.\x:e.AND:ttt (A:et x:e) (P:(et)t (\y:e.WORD:eet y:e x:e))

Finally, there is a special-case annotation for $\text{TO}$ when used to denote the object of
a ditransitive verb, which is simply the identity function.\footnote{
  Note that this definition depends on double-object resolution, which at this moment is not
  yet performed. Therefore, it should instead alter an object such that it will bind itself to
  the correct argument-position of a ditransitive verb.
}.

> TO    \P:(et)t.P:(et)t

\subsection{Auxilliary Verbs}

At the moment, auxilliary verbs are simply interpreted as identity functions, which means
that all information contained within them is lost. This is not a problem, as we decided
that the problem of voice was out of the scope of the project.

> V_AUX \A:et.A:et
> V_AUX \A:eet.A:eet
> V_AUX \A:eeet.A:eeet
> V_AUX \A:((et)t)et.A:((et)t)et
> V_AUX \A:((et)t)((et)t)t.A:((et)t)((et)t)t
> V_AUX \A:((et)t)eet.A:((et)t)eet
> V_AUX \A:((et)t)((et)t)et.A:((et)t)((et)t)et
> V_AUX \A:((et)t)((et)t)((et)t)t.A:((et)t)((et)t)((et)t)t

\section{Experimental Annotations}

\subsection{Possessive Pronouns}

The below annotation contains only some of the meaning of an acual possesive,
as right now the definition is equivalent to the indefinite article $\text{SOME}$.

> POSS  \A:et.\B:et.EXISTS:(et)t (\x:e.AND:ttt (A:et x:e) (B:et x:e))

The accompanying word-specific annotation $\text{OWN}$ is also simply an identity
function that gets rid of the word "own" in sentences.

> OWN   \A:et.A:et

\subsection{Numbers}

Numbers are simply interpreted as existentials, which bars any numerical inferences.
The definition is equivalent to $\text{SOME}$, but may change in the future\footnote{
  \textbf{TODO} technically this annotation gives incorrect results when applied to the
  number zero, and this would be equivalent to $NONE$ instead of $SOME$.
}.

> NUMBER \A:et.\B:et.EXISTS:(et)t (\x:e.AND:ttt (A:et x:e) (B:et x:e))

\subsection{Dates}

Dates are implemented as a special case of $NP$s. Therefore any temporal inferences
based on dates are barred.

> DATE   \A:et.A:et WORD:e

\subsection{The $\text{IGNORE}$ Annotation}

The $\text{IGNORE}$ annotation is equal to an identity function. It should not be
used with the exception of experimentation and temporary solutions. It is may be
subject to removal at any time.

> IGNORE \x:e.x:e
> IGNORE \A:et.A:et
> IGNORE \P:(et)t.P:(et)t
> IGNORE \GQ:(et)(et)t.GQ:(et)(et)t
> IGNORE \M:(et)et.M:(et)et
