{-# LANGUAGE FlexibleContexts, Rank2Types #-}

import Control.Monad (forM,forM_)
import Data.Char (toLower)
import Data.List (elemIndex)
import Data.Maybe (maybe)
import Text.ParserCombinators.UU ((<$>),(<*>),(<*),(*>),(<|>),(<<|>),(<??>),pure,pMany,pSome,opt)
import Text.ParserCombinators.UU.Utils (runParser,pLetter,pDigit,pAnySym)
import Text.ParserCombinators.UU.BasicInstances (Parser,pSym,pMunch)

main :: IO ()
main = do contents <- getContents
          let parse = runParser "entry" pEntry
          let values = lines contents
          forM_ values $ \value ->
            if hasBird value
              then putStrLn $ (toLaTeX . parse $ value)
              else putStrLn value
  where
  hasBird :: String -> Bool
  hasBird ('>':_) = True
  hasBird _ = False

class LaTeX a where
  toLaTeX :: a -> String

data Tag  = Tag String deriving (Eq)
data Name = N1 String
          | N2 String String
          deriving (Eq)

-- |Data structure for lambda terms.
data Term
  = Abstraction Symbol Term
  | Application Term Term
  | Variable Symbol
  deriving (Eq)

data Entry   = Entry Tag Term deriving (Eq)
data Symbol  = Symbol Name Type deriving (Eq)
data Type    = E | T | Type :>: Type deriving (Eq)

instance Show  Tag    where show    (Tag t)  = t
instance LaTeX Tag    where toLaTeX (Tag t)  = latexTag t
instance Show  Name   where show    (N1 n) = n
                            show    (N2 n i) = n++i
instance LaTeX Name   where toLaTeX (N1 n) = wrapText n
                            toLaTeX (N2 n i) = wrapText n++"^{"++i++"}"
instance Show  Symbol where show    (Symbol n t) = show n++"_"++show t
instance LaTeX Symbol where toLaTeX (Symbol n t) = toLaTeX n++"_{"++toLaTeX t++"}"
instance Show  Type   where show    = showTP show
instance LaTeX Type   where toLaTeX = showTP toLaTeX
instance Show  Term   where show    = showLT show show asciiCtx
instance LaTeX Term   where toLaTeX = showLT toLaTeX toLaTeX latexCtx
instance Show  Entry  where show    (Entry n t) = show n++" "++show t
instance LaTeX Entry  where toLaTeX (Entry n t) = toLaTeX n++" \\tabto*{2cm} $"++toLaTeX t++"$\n"

latexTag :: String -> String
latexTag str = maybe plain mathm split
  where
  -- formatting of the tag string
  plain = "\\textbf{"++str++"}"
  mathm = \(a,b) -> "\\textbf{"++a++"}$_{"++b++"}$"
  
  -- splitting of the tag string
  split = do idx <- elemIndex '_' str
             let (x,_:y) = splitAt idx str
             return (x,y)
             
wrapText :: String -> String
wrapText t = if length t <= 1 then t else "\\text{"++t++"}"

emptyCtx = Ctx (const "") (const "") "" "" "" "" "" "" "" "" "" "" ""
data Context = Ctx {

  forTerm    :: Term -> String,
  forSymbol  :: Symbol -> String,

  getLambda  :: String,
  getSep     :: String,
  getApp     :: String,
  getExists  :: String,
  getForAll  :: String,
  getAnd     :: String,
  getOr      :: String,
  getImplies :: String,
  getNeg     :: String,
  getEq      :: String,
  getIota    :: String
  
  }

latexCtx :: Context
latexCtx = emptyCtx {
  getLambda  = "\\lambda ", 
  getSep     = ".",
  getApp     = "\\; ",
  getExists  = "\\exists ",
  getForAll  = "\\forall ",
  getAnd     = "\\land ",
  getOr      = "\\lor ",
  getImplies = "\\to ",
  getNeg     = "\\lnot ",
  getEq      = "\\equiv ",
  getIota    = "\\iota "
  }
  
asciiCtx :: Context
asciiCtx = emptyCtx {
  getLambda  = "\\", 
  getSep     = ".",
  getApp     = " ",
  getExists  = "exists ",
  getForAll  = "forall ",
  getAnd     = " /\\ ",
  getOr      = " \\/ ",
  getImplies = " -> ",
  getNeg     = "-",
  getEq      = "=",
  getIota    = "iota "
  }
  
unicodeCtx :: Context
unicodeCtx = emptyCtx {
  getLambda  = "λ",
  getSep     = ".",
  getApp     = " ",
  getExists  = "∃",
  getForAll  = "∀",
  getAnd     = "∧",
  getOr      = "∨",
  getImplies = "→",
  getNeg     = "¬",
  getEq      = "≡",
  getIota    = "ɩ"
  }
  
-- |Generic show function for semantic types.
showTP :: (Type -> String) -> Type -> String
showTP _ E = "e"
showTP _ T = "t"
showTP p (a@(_ :>: _) :>: b) = (parens . p $ a) ++ p b
showTP p (a :>: b) = p a ++ p b

-- |Generic show function for lambda terms.
showLT :: (Symbol -> String) -> (Term -> String) -> Context -> Term -> String
showLT o p ctx (Application (Variable (Symbol (N1 "NOT") _)) x)
  = (getNeg ctx) ++ wrap o p x
showLT o p ctx (Application (Variable (Symbol (N1 "IOTA") _)) x)
  = (getIota ctx) ++ wrap o p x
showLT o p ctx (Application (Variable (Symbol (N1 "EXISTS") _)) (Abstraction s x))
  = (getExists ctx) ++ o s ++ (getSep ctx) ++ p x
showLT o p ctx (Application (Variable (Symbol (N1 "FORALL") _)) (Abstraction s x)) 
  = (getForAll ctx) ++ o s ++ (getSep ctx) ++ p x
showLT o p ctx (Application (Application (Variable (Symbol (N1 "EQ") _)) x) y)
  = wrap o p x ++ (getEq ctx)      ++ wrap o p y
showLT o p ctx (Application (Application (Variable (Symbol (N1 "AND") _)) x) y)
  = wrap o p x ++ (getAnd ctx)     ++ wrap o p y
showLT o p ctx (Application (Application (Variable (Symbol (N1 "OR") _)) x) y)
  = wrap o p x ++ (getOr ctx)      ++ wrap o p y
showLT o p ctx (Application (Application (Variable (Symbol (N1 "IMPLIES") _)) x) y)
  = wrap o p x ++ (getImplies ctx) ++ wrap o p y
  
showLT o p ctx (Abstraction s x) = (getLambda ctx) ++ o s ++ (getSep ctx) ++ p x
showLT o p ctx (Application f x@(Variable _)) = p f ++ (getApp ctx) ++ p x
showLT o p ctx (Application f x)              = p f ++ (getApp ctx) ++ wrap o p x
showLT o p ctx (Variable s)                   = o s

-- |Wrap non-variables in parentheses.
wrap :: (Symbol -> String) -> (Term -> String) -> Term -> String
wrap o _ (Variable s) = o s
wrap _ p t = parens . p $ t

-- |Wrap a string in parentheses.
parens :: String -> String
parens str = "(" ++ str ++ ")"

-- |Parse a parenthesised string.
pParens :: Parser a -> Parser a
pParens p = pSym '(' *> p <* pSym ')'

-- |Parse a function symbol.
pSymbol :: Parser Symbol
pSymbol = Symbol <$> pName <* pColon <*> pType
  where
  pColon :: Parser Char
  pColon = pSym ':'

-- |Parse a function name.
pTag :: Parser Tag
pTag = Tag <$> ((:) <$> pLetter <*> pMany pOther)
  where
  pOther = pLetter <<|> pDigit <<|> pUS
  pUS = pSym '_'
  
-- |Parse a function name.
pName :: Parser Name
pName = (N1 <$> pSome pLetter) <??> (nplus <$> pSome pDigit)

nplus :: String -> Name -> Name
nplus i (N1 n) = N2 n i

-- |Parse a semantic et-type.
pType :: Parser Type
pType = pFunction <|> pAtomic
  where
  pAtomic :: Parser Type
  pAtomic = pure E <* pSym 'e'
       <<|> pure T <* pSym 't'
       <<|> pParens pFunction
  
  pFunction :: Parser Type
  pFunction = (:>:) <$> pAtomic <*> pType

-- |Parse a lambda term.
pTerm :: Parser Term
pTerm = let

  pAtomic :: Parser Term
  pAtomic = Variable    <$> pSymbol
       <<|> Abstraction <$> (pLambda *> pSymbol <* pSep) <*> pTerm
       <<|> pParens pTerm
    where
    pLambda =           pSym '\\' <* pSpace
    pSep    = pSpace *> pSym '.'  <* pSpace

  in foldl Application <$> pAtomic <*> pMany (pSpace1 *> pAtomic)
    
-- |Parse a lexicon entry.
pEntry :: Parser Entry
pEntry = Entry <$> (pBird *> pTag <* pSpace1) <*> pTerm
  where
  pBird :: Parser Char
  pBird = pSym '>' <* pSpace1

-- |Parse any sequence of many spaces.
pSpace :: Parser String
pSpace = pMany (pSym ' ')

-- |Parse any sequence of at least one space.
pSpace1 :: Parser String
pSpace1 = pSome (pSym ' ')

-- |Parse a newline.
pNewLine :: Parser Char
pNewLine = pAnySym "\n\r"