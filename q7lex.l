%{
#include "y.tab.h"
int yylval;
%}

%%
[0-9] {yylval=atoi(yytext);return ID;}
[*/+\n-] {return *yytext;}
%%
int yywrap(){
	return 1;
}
