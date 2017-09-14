%{
#include "y.tab.h"
int yylval;
%}

%%
[a-zA-Z] {yylval=*yytext;return ID;}
[(*/+\n)-] {return *yytext;}
%%
int yywrap(){
	return 1;
}
