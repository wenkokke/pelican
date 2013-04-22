{-# LANGUAGE Rank2Types #-}
{-# LANGUAGE FlexibleContexts #-}
module Parsing where

import Base
import Data.Maybe (catMaybes)
import Text.ParserCombinators.UU
import Text.ParserCombinators.UU.BasicInstances
import Text.ParserCombinators.UU.Utils

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
pTy = pApp <|> pVar
  where
  pVar = lexeme $ Ty_Var <$> pLower <<|> pParens pTy
  pApp = Ty_App <$> pVar <??> pSym <*> pTy
  pSym = flip const <$> pSymbol "->"

-- |Parses terms in the extended lexicon syntax.
pTm :: Parser Tm
pTm = pLambda <<|> pIota <<|> pForall <<|> pExists <<|> pTerm
  where
  pVar        = lexeme $ Tm_Var <$> pIdent <<|> pParens pTm
  pApp        = foldl1 Tm_App <$> pList1Sep pSpaces pVar
  pOp x op e  = foldl1 (Tm_App . Tm_App (Tm_Var x)) <$> pList1Sep op e
  pConj       = pOp "AND"     (pSymbol "/\\") pApp
  pDisj       = pOp "OR"      (pSymbol "\\/") pConj
  pImpl       = pOp "IMPLIES" (pSymbol "->")  pDisj
  pEquiv      = pOp "EQ"      (pSymbol "==")  pImpl
  pTerm       = pEquiv
  
  pIdents     :: Parser (Tm,[Var])
  pIdents     = flip (,) <$> pList1Sep pSpaces pIdent <* pDot <*> pTm
  absapp      :: Var -> (Tm,[Var]) -> Tm
  absapp f    = uncurry (foldr (\x -> Tm_App (Tm_Var f) . Tm_Lam x))
  pForall     :: Parser Tm
  pForall     = absapp "FORALL" <$ pSymbol "!"  <*> pIdents
  pExists     = absapp "EXISTS" <$ pSymbol "?"  <*> pIdents
  pIota       = absapp "IOTA"   <$ pSymbol "i"  <*> pIdents
  pLambda     = uncurry (foldr Tm_Lam) <$ pSymbol "\\" <*> pIdents
  
-- |Parses identifiers in the extended lexicon syntax.
pIdent :: Parser Var
pIdent = (:) <$> pLetter <*> pMany (pLetter <|> pDigit <|> pSym '_')
