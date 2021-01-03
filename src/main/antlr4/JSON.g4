grammar JSON;
obj   : '{' pair (',' pair)* '}'
      | '{' '}' ;
pair  : STRING ':' value;
arr   : '[' value (',' value)* ']'
      | '[' ']' ;
value : text | number | obj | arr ;
text  : STRING ;
number: '-'? INT ;
STRING: '"' [a-z]+ '"' ;
INT   : [0-9]+ ;
