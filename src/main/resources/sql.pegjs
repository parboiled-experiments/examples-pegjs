sql_stmt = select_stmt

select_stmt
  = _ "SELECT"i __ _columnExprs 
    __ "FROM"i __ select_expr 
    ( __ ("AS"i __ )? _tableName)?
    ( __ "WHERE"i __ _columnExpr __ _op __ _columnExpr)? _ 

select_expr = _tableName / '(' _ select_stmt _ ')'
_tableName = _w

_columnExprs = _columnExpr ( _ ',' _ _columnExpr )*
_columnExpr = '*' / _columnValue
_columnValue = _columnName / _l
_columnName = _w
  
_op = [=><]
_l = "'" _w "'"  / '"' _w '"' / _d
_w = [A-Za-z][A-Za-z0-9]*
_d = [0-9]+
__ = [ \t\r\n]+
_ = [ \t\r\n]* 