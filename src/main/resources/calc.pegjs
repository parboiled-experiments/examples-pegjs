Expression = head:Term tail:(_ ("+" / "-") _ Term)*

Term = head:Factor tail:(_ ("*" / "/") _ Factor)*

Factor = "(" _ expr:Expression _ ")" / Integer

Integer "integer" = _ [0-9]+

_ "whitespace" = [ \t\n\r]*