{-# LANGUAGE Rank2Types #-}
{-# LANGUAGE TupleSections #-}
{-# LANGUAGE FlexibleContexts #-}
module Parsing where

import Base
import qualified StdLib as Std
import Data.Maybe (catMaybes,maybeToList)
import Text.ParserCombinators.UU
import Text.ParserCombinators.UU.BasicInstances
import Text.ParserCombinators.UU.Utils

-- |Parses a lexicon in the extended syntax.
pLx :: Parser Lx
pLx = Lx_Lx <$ pSpaces <*> pMany pSt

-- |Parses statements in the extended lexicon syntax.
pSt :: Parser St
pSt = pStTy <|> pStTm
  where
  pStTy = St_Ty <$> pAn <*> pVar <* pSymbol ":" <*> pTy
  pStTm = St_Tm <$> pAn <*> pVar <* pSymbol "=" <*> pTm

-- |Parses entry annotations which have replaced bird tags.
pAn :: Parser An
pAn = pPubl <|> pPriv <|> pAuto
  where
  pPubl = An_Publ <$ pSymbol "+"
  pPriv = An_Priv <$ pSymbol "-"
  pAuto = An_Auto <$ pSymbol "@"

-- |Parses optional types.
pMbTy :: Parser MbTy
pMbTy = pMaybe (pSymbol ":" *> pTy)

-- |Parses soft types.
pSfTy :: Parser SfTy
pSfTy = maybeToList <$> pMbTy

-- |Parses types in the extended lexicon syntax.
pTy :: Parser Ty
pTy = pAll <|> pApp2
  where
  pAll  = uncurry (foldr Ty_All) <$ pSymbol "!" <*> pTyVars
  pApp2 = foldr1 Ty_App <$> pList1Sep (pSymbol "->") pApp1
  pApp1 = foldr1 Ty_App <$> pList1Sep (pure []) pAtom
  pAtom = Ty_Var <$> pTyVar <|> pParens pTy

-- |Parses a type variable in the extended lexicon syntax.
pTyVar :: Parser TyVar
pTyVar = lexeme $ pLower

-- |Parses type variables in the extended lexicon syntax.
pTyVars :: Parser (Ty,[TyVar])
pTyVars = flip (,) <$> pList1Sep pSpaces pTyVar <* pDot <*> pTy

-- |Parses terms in the extended lexicon syntax.
pTm :: Parser Tm
pTm = pLambda <|> pIota <|> pForall <|> pExists <|> pTerm
  where

  -- |Parses simple terms.
  pTerm      = pEquiv
  pEquiv     = pOp Std.eq      (pSymbol "==")  pImpl
  pImpl      = pOp Std.implies (pSymbol "=>")  pDisj
  pDisj      = pOp Std.or      (pSymbol "\\/") pConj
  pConj      = pOp Std.and     (pSymbol "/\\") pApp
  pOp f op e = foldl1 (Tm_App . Tm_App f) <$> pList1Sep op e
  pApp       = foldl1 Tm_App <$> pList1Sep pSpaces (pNeg <<|> pAtom)
  pNeg       = Tm_App Std.not <$ pSymbol "~" <*> pAtom
  pAtom      = uTm_Var <$> pIdent <|> pParens pTm

  -- |Parses quantifying terms.
  pForall    = quantify Std.forall <$ pSymbol "!"  <*> pIdents
  pExists    = quantify Std.exists <$ pSymbol "?"  <*> pIdents
  pIota      = quantify Std.iota   <$ pSymbol "i"  <*> pIdents
  pLambda    = uncurry (foldr uTm_Lam) <$ pSymbol "\\" <*> pIdents
  quantify f = uncurry (foldr $ (Tm_App f .) . uTm_Lam)

-- |Shorthand for @uncurry Tm_Lam@.  
uTm_Lam = uncurry Tm_Lam

-- |Shorthand for @uncurry Tm_Var@.
uTm_Var = uncurry Tm_Var

-- |Parses identifiers in the extended lexicon syntax.
pVar :: Parser Var
pVar = lexeme $ (:) <$> pLetter <*> pMany (pLetter <|> pDigit <|> pSym '_')

-- |Parses an explicitly typed identifier in the extended lexicon syntax.
pIdent :: Parser (Var,MbTy)
pIdent = (,) <$> pVar <*> pMbTy

-- |Parses a sequence of typed identifiers.
pIdents :: Parser (Tm,[(Var,MbTy)])
pIdents = flip (,) <$> pList1Sep pSpace pIdent <* pDot <*> pTm

-- |Parses an explicitly typed identifier in the extended lexicon syntax.
pSfTyIdent :: Parser (Var,SfTy)
pSfTyIdent = (,) <$> pVar <*> pSfTy

-- |Parses a sequence of typed identifiers.
pSfTyIdents :: Parser (Tm,[(Var,SfTy)])
pSfTyIdents = flip (,) <$> pList1Sep pSpace pSfTyIdent <* pDot <*> pTm

-- |Parses a single space.
pSpace :: Parser Char
pSpace = pSym ' '
