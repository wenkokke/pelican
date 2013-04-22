

-- UUAGC 0.9.42.2 (D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.)
module AG where

{-# LINE 3 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}

import UU.Pretty
{-# LINE 10 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
{-# LINE 22 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Base.ag" #-}

type TyVar = Char
{-# LINE 14 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}

{-# LINE 32 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Base.ag" #-}

type Var = String
{-# LINE 19 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}

{-# LINE 38 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}

instance PP Lx where pp lx = pp_Syn_Lx (wrap_Lx (sem_Lx lx) Inh_Lx)
instance PP St where pp st = pp_Syn_St (wrap_St (sem_St st) Inh_St)
instance PP Ty where pp ty = pp_Syn_Ty (wrap_Ty (sem_Ty ty) Inh_Ty)
instance PP Tm where pp tm = pp_Syn_Tm (wrap_Tm (sem_Tm tm) Inh_Tm)
instance Show Lx where show = show . pp
instance Show St where show = show . pp
instance Show Ty where show = show . pp
instance Show Tm where show = show . pp
{-# LINE 31 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
-- An ----------------------------------------------------------
data An = An_Publ
        | An_Priv
        | An_Auto
        deriving ( Eq)
-- cata
sem_An :: An ->
          T_An
sem_An (An_Publ) =
    (sem_An_Publ)
sem_An (An_Priv) =
    (sem_An_Priv)
sem_An (An_Auto) =
    (sem_An_Auto)
-- semantic domain
type T_An = ( PP_Doc)
data Inh_An = Inh_An {}
data Syn_An = Syn_An {pp_Syn_An :: PP_Doc}
wrap_An :: T_An ->
           Inh_An ->
           Syn_An
wrap_An sem (Inh_An) =
    (let ( _lhsOpp) = sem
     in  (Syn_An _lhsOpp))
sem_An_Publ :: T_An
sem_An_Publ =
    (let _lhsOpp :: PP_Doc
         _pp =
             ({-# LINE 15 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              text "+"
              {-# LINE 62 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 67 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
     in  ( _lhsOpp))
sem_An_Priv :: T_An
sem_An_Priv =
    (let _lhsOpp :: PP_Doc
         _pp =
             ({-# LINE 16 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              text "-"
              {-# LINE 76 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 81 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
     in  ( _lhsOpp))
sem_An_Auto :: T_An
sem_An_Auto =
    (let _lhsOpp :: PP_Doc
         _pp =
             ({-# LINE 17 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              text "@"
              {-# LINE 90 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 95 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
     in  ( _lhsOpp))
-- Lx ----------------------------------------------------------
data Lx = Lx_Lx (Ss)
        deriving ( Eq)
-- cata
sem_Lx :: Lx ->
          T_Lx
sem_Lx (Lx_Lx _ss) =
    (sem_Lx_Lx (sem_Ss _ss))
-- semantic domain
type T_Lx = ( PP_Doc)
data Inh_Lx = Inh_Lx {}
data Syn_Lx = Syn_Lx {pp_Syn_Lx :: PP_Doc}
wrap_Lx :: T_Lx ->
           Inh_Lx ->
           Syn_Lx
wrap_Lx sem (Inh_Lx) =
    (let ( _lhsOpp) = sem
     in  (Syn_Lx _lhsOpp))
sem_Lx_Lx :: T_Ss ->
             T_Lx
sem_Lx_Lx ss_ =
    (let _lhsOpp :: PP_Doc
         _ssIpp :: PP_Doc
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _ssIpp
              {-# LINE 124 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         ( _ssIpp) =
             ss_
     in  ( _lhsOpp))
-- Ss ----------------------------------------------------------
type Ss = [St]
-- cata
sem_Ss :: Ss ->
          T_Ss
sem_Ss list =
    (Prelude.foldr sem_Ss_Cons sem_Ss_Nil (Prelude.map sem_St list))
-- semantic domain
type T_Ss = ( PP_Doc)
data Inh_Ss = Inh_Ss {}
data Syn_Ss = Syn_Ss {pp_Syn_Ss :: PP_Doc}
wrap_Ss :: T_Ss ->
           Inh_Ss ->
           Syn_Ss
wrap_Ss sem (Inh_Ss) =
    (let ( _lhsOpp) = sem
     in  (Syn_Ss _lhsOpp))
sem_Ss_Cons :: T_St ->
               T_Ss ->
               T_Ss
sem_Ss_Cons hd_ tl_ =
    (let _lhsOpp :: PP_Doc
         _hdIpp :: PP_Doc
         _tlIpp :: PP_Doc
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _hdIpp >-< _tlIpp
              {-# LINE 156 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         ( _hdIpp) =
             hd_
         ( _tlIpp) =
             tl_
     in  ( _lhsOpp))
sem_Ss_Nil :: T_Ss
sem_Ss_Nil =
    (let _lhsOpp :: PP_Doc
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              empty
              {-# LINE 169 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
     in  ( _lhsOpp))
-- St ----------------------------------------------------------
data St = St_Ty (An) (Var) (Ty)
        | St_Tm (An) (Var) (Tm)
        deriving ( Eq)
-- cata
sem_St :: St ->
          T_St
sem_St (St_Ty _an _ident _t1) =
    (sem_St_Ty (sem_An _an) _ident (sem_Ty _t1))
sem_St (St_Tm _an _ident _t1) =
    (sem_St_Tm (sem_An _an) _ident (sem_Tm _t1))
-- semantic domain
type T_St = ( PP_Doc)
data Inh_St = Inh_St {}
data Syn_St = Syn_St {pp_Syn_St :: PP_Doc}
wrap_St :: T_St ->
           Inh_St ->
           Syn_St
wrap_St sem (Inh_St) =
    (let ( _lhsOpp) = sem
     in  (Syn_St _lhsOpp))
sem_St_Ty :: T_An ->
             Var ->
             T_Ty ->
             T_St
sem_St_Ty an_ ident_ t1_ =
    (let _lhsOpp :: PP_Doc
         _anIpp :: PP_Doc
         _t1Ipp :: PP_Doc
         _t1Ipp_parens :: PP_Doc
         _pp =
             ({-# LINE 11 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _anIpp >#< text ident_ >#< text ":" >#< _t1Ipp
              {-# LINE 205 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 210 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         ( _anIpp) =
             an_
         ( _t1Ipp,_t1Ipp_parens) =
             t1_
     in  ( _lhsOpp))
sem_St_Tm :: T_An ->
             Var ->
             T_Tm ->
             T_St
sem_St_Tm an_ ident_ t1_ =
    (let _lhsOpp :: PP_Doc
         _anIpp :: PP_Doc
         _t1Ipp :: PP_Doc
         _t1Ipp_parens :: PP_Doc
         _pp =
             ({-# LINE 12 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _anIpp >#< text ident_ >#< text "=" >#< _t1Ipp
              {-# LINE 229 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 234 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         ( _anIpp) =
             an_
         ( _t1Ipp,_t1Ipp_parens) =
             t1_
     in  ( _lhsOpp))
-- Tm ----------------------------------------------------------
data Tm = Tm_Var (Var)
        | Tm_App (Tm) (Tm)
        | Tm_Lam (Var) (Tm)
        deriving ( Eq)
-- cata
sem_Tm :: Tm ->
          T_Tm
sem_Tm (Tm_Var _ident) =
    (sem_Tm_Var _ident)
sem_Tm (Tm_App _t1 _t2) =
    (sem_Tm_App (sem_Tm _t1) (sem_Tm _t2))
sem_Tm (Tm_Lam _ident _t1) =
    (sem_Tm_Lam _ident (sem_Tm _t1))
-- semantic domain
type T_Tm = ( PP_Doc,PP_Doc)
data Inh_Tm = Inh_Tm {}
data Syn_Tm = Syn_Tm {pp_Syn_Tm :: PP_Doc,pp_parens_Syn_Tm :: PP_Doc}
wrap_Tm :: T_Tm ->
           Inh_Tm ->
           Syn_Tm
wrap_Tm sem (Inh_Tm) =
    (let ( _lhsOpp,_lhsOpp_parens) = sem
     in  (Syn_Tm _lhsOpp _lhsOpp_parens))
sem_Tm_Var :: Var ->
              T_Tm
sem_Tm_Var ident_ =
    (let _lhsOpp :: PP_Doc
         _lhsOpp_parens :: PP_Doc
         _pp =
             ({-# LINE 29 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              text ident_
              {-# LINE 273 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _pp_parens =
             ({-# LINE 30 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 278 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 283 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp_parens =
             ({-# LINE 20 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp_parens
              {-# LINE 288 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
     in  ( _lhsOpp,_lhsOpp_parens))
sem_Tm_App :: T_Tm ->
              T_Tm ->
              T_Tm
sem_Tm_App t1_ t2_ =
    (let _lhsOpp :: PP_Doc
         _lhsOpp_parens :: PP_Doc
         _t1Ipp :: PP_Doc
         _t1Ipp_parens :: PP_Doc
         _t2Ipp :: PP_Doc
         _t2Ipp_parens :: PP_Doc
         _pp =
             ({-# LINE 31 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _t1Ipp_parens >#< _t2Ipp_parens
              {-# LINE 304 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _pp_parens =
             ({-# LINE 32 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              pp_parens _pp
              {-# LINE 309 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 314 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp_parens =
             ({-# LINE 20 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp_parens
              {-# LINE 319 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         ( _t1Ipp,_t1Ipp_parens) =
             t1_
         ( _t2Ipp,_t2Ipp_parens) =
             t2_
     in  ( _lhsOpp,_lhsOpp_parens))
sem_Tm_Lam :: Var ->
              T_Tm ->
              T_Tm
sem_Tm_Lam ident_ t1_ =
    (let _lhsOpp :: PP_Doc
         _lhsOpp_parens :: PP_Doc
         _t1Ipp :: PP_Doc
         _t1Ipp_parens :: PP_Doc
         _pp =
             ({-# LINE 33 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              text "\\" >|< text ident_ >|< text "." >|< _t1Ipp
              {-# LINE 337 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _pp_parens =
             ({-# LINE 34 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              pp_parens _pp
              {-# LINE 342 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 347 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp_parens =
             ({-# LINE 20 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp_parens
              {-# LINE 352 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         ( _t1Ipp,_t1Ipp_parens) =
             t1_
     in  ( _lhsOpp,_lhsOpp_parens))
-- Ty ----------------------------------------------------------
data Ty = Ty_Var (TyVar)
        | Ty_App (Ty) (Ty)
        deriving ( Eq)
-- cata
sem_Ty :: Ty ->
          T_Ty
sem_Ty (Ty_Var _ident) =
    (sem_Ty_Var _ident)
sem_Ty (Ty_App _t1 _t2) =
    (sem_Ty_App (sem_Ty _t1) (sem_Ty _t2))
-- semantic domain
type T_Ty = ( PP_Doc,PP_Doc)
data Inh_Ty = Inh_Ty {}
data Syn_Ty = Syn_Ty {pp_Syn_Ty :: PP_Doc,pp_parens_Syn_Ty :: PP_Doc}
wrap_Ty :: T_Ty ->
           Inh_Ty ->
           Syn_Ty
wrap_Ty sem (Inh_Ty) =
    (let ( _lhsOpp,_lhsOpp_parens) = sem
     in  (Syn_Ty _lhsOpp _lhsOpp_parens))
sem_Ty_Var :: TyVar ->
              T_Ty
sem_Ty_Var ident_ =
    (let _lhsOpp :: PP_Doc
         _lhsOpp_parens :: PP_Doc
         _pp =
             ({-# LINE 23 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              text [ident_]
              {-# LINE 386 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _pp_parens =
             ({-# LINE 24 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 391 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 396 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp_parens =
             ({-# LINE 20 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp_parens
              {-# LINE 401 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
     in  ( _lhsOpp,_lhsOpp_parens))
sem_Ty_App :: T_Ty ->
              T_Ty ->
              T_Ty
sem_Ty_App t1_ t2_ =
    (let _lhsOpp :: PP_Doc
         _lhsOpp_parens :: PP_Doc
         _t1Ipp :: PP_Doc
         _t1Ipp_parens :: PP_Doc
         _t2Ipp :: PP_Doc
         _t2Ipp_parens :: PP_Doc
         _pp =
             ({-# LINE 25 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _t1Ipp_parens >|< _t2Ipp
              {-# LINE 417 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _pp_parens =
             ({-# LINE 26 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              pp_parens _pp
              {-# LINE 422 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp =
             ({-# LINE 8 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp
              {-# LINE 427 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         _lhsOpp_parens =
             ({-# LINE 20 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs\\AG/Printing.ag" #-}
              _pp_parens
              {-# LINE 432 "D:/Projects/SemAnTE/pipeline/rhogog/src/main/hs/AG.hs" #-}
              )
         ( _t1Ipp,_t1Ipp_parens) =
             t1_
         ( _t2Ipp,_t2Ipp_parens) =
             t2_
     in  ( _lhsOpp,_lhsOpp_parens))