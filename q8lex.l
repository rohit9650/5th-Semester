%{
#include "y.tab.h"
int yylval;
%}

%%
"a" {return *yytext;}
"b" {return *yytext;}
"\n" {return *yytext;}
%%
int yywrap(){
	return 1;
}
