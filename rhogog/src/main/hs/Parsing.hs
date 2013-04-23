{-# LANGUAGE Rank2Types #-}
{-# LANGUAGE FlexibleContexts #-}
module Parsing where

import Base
import Data.Maybe (catMaybes)
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
  pStTy = St_Ty <$> pAn <*> lexeme pIdent <* pSymbol ":" <*> pTy
  pStTm = St_Tm <$> pAn <*> lexeme pIdent <* pSymbol "=" <*> pTm

-- |Parses entry annotations which have replaced bird tags.
pAn :: Parser An
pAn = pPubl <<|> pPriv <<|> pAuto
  where
  pPubl = An_Publ <$ pSymbol "+"
  pPriv = An_Priv <$ pSymbol "-"
  pAuto = An_Auto <$ pSymbol "@"

-- |Parses types in the extended lexicon syntax.
pTy :: Parser Ty
pTy = pAll <<|> pApp2
  where
  pVar  = Ty_Var <$> pTyVar <<|> pParens pTy
  pApp1 = foldr1 Ty_App <$> pList1Sep (pure []) pVar
  pApp2 = foldr1 Ty_App <$> pList1Sep (pSymbol "->") pApp1
  pAll  = uncurry (foldr Ty_All) <$ pSymbol "!" <*> pTyVars
  
-- |Parses a type variable in the extended lexicon syntax.
pTyVar :: Parser TyVar
pTyVar = lexeme $ pLower

-- |Parses type variables in the extended lexicon syntax.
pTyVars :: Parser (Ty,[TyVar])
pTyVars = flip (,) <$> pList1Sep pSpaces pTyVar <* pDot <*> pTy

-- |Parses terms in the extended lexicon syntax.
pTm :: Parser Tm
pTm = pLambda <<|> pIota <<|> pForall <<|> pExists <<|> pTerm
  where
  
  -- |Parses simple terms.
  pTerm      = pEquiv
  pImpl      = pOp "IMPLIES" (pSymbol "->")  pDisj
  pEquiv     = pOp "EQ"      (pSymbol "==")  pImpl
  pDisj      = pOp "OR"      (pSymbol "\\/") pConj
  pConj      = pOp "AND"     (pSymbol "/\\") pApp
  pOp x op e = foldl1 (Tm_App . Tm_App (Tm_Var x)) <$> pList1Sep op e
  pApp       = foldl1 Tm_App <$> pList1Sep pSpaces pVar
  pVar       = lexeme $ Tm_Var <$> pIdent <<|> pParens pTm
  
  -- |Parses quantifying terms.
  pForall    = quantify "FORALL" <$ pSymbol "!"  <*> pTyIdents
  pExists    = quantify "EXISTS" <$ pSymbol "?"  <*> pTyIdents
  pIota      = quantify "IOTA"   <$ pSymbol "i"  <*> pTyIdents
  pLambda    = uncurry (foldr $ uncurry Tm_Lam) <$ pSymbol "\\" <*> pTyIdents
  quantify f = uncurry (foldr $ (Tm_App (Tm_Var f).) . uncurry Tm_Lam)
  
-- |Parses identifiers in the extended lexicon syntax.
pIdent :: Parser Var
pIdent = (:) <$> pLetter <*> pMany (pLetter <|> pDigit <|> pSym '_')

-- |Parses an explicitly typed identifier in the extended lexicon syntax.
pTyIdent :: Parser (Var,MbTy)
pTyIdent = (,) <$> pIdent <*> (pMaybe $ pSymbol ":" *> pTy)

-- |Parses a sequence of typed identifiers.
pTyIdents :: Parser (Tm,[(Var,MbTy)])
pTyIdents = flip (,) <$> pList1Sep s pTyIdent <* pDot <*> pTm
  where s = pSym ' '