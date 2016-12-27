grammar JSON;

json:   object
    ;
    
object
    :   '{' pair (',' pair)* '}'    # AnObject
    |   '{' '}'                     # EmptyObject
    ;
    	
array
    :   '[' object (',' object)* ']'  # ArrayOfObject
    |   '[' ']'                     # EmptyArray
    ;

pair:   STRING ':' value ;

value
    :   STRING		# String
    |   NUMBER		# number
    |   object  	# ObjectValue
    |   array  		# ArrayValue
    |   'true'		# btrue
    |   'false'		# bfalse
    |   'null'		# ptrNull
    ;

LCURLY : '{' ;
LBRACK : '[' ;

STRING :  '"' (ESC | ~["\\])* '"' ;

fragment ESC :   '\\' (["\\/bfnrt] | UNICODE) ;
fragment UNICODE : 'u' HEX HEX HEX HEX ;
fragment HEX : [0-9a-fA-F] ;

NUMBER
    :   '-'? INT '.' INT EXP?   // 1.35, 1.35E-9, 0.3, -4.5
    |   '-'? INT EXP            // 1e10 -3e4
    |   '-'? INT                // -3, 45
    ;
fragment INT :   '0' | '1'..'9' '0'..'9'* ; // no leading zeros
fragment EXP :   [Ee] [+\-]? INT ; // \- since - means "range" inside [...]

WS  :   [ \t\n\r]+ -> skip ; // lETS   
