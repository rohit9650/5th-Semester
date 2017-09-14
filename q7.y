
%{
void yyerror(char *);
#include "y.tab.h"
#include<stdio.h> 
int yylex();

%}

%token ID

%left '+' '-'
%left '*' '/'

%%

P : P E '\n' {printf("Answer : %d",$2);}
  |
  ;

E: E '+' E {$$ = $1 + $3;}
 | E '-' E {$$ = $1 - $3;}
 | E '*' E {$$ = $1 * $3;}
 | E '/' E {$$ = $1 / $3;}
 | ID  {$$  =$1;}
 ;
%%

int main(){
	yyparse();
	return 0;
}
void yyerror(char *a){
	fprintf(stderr,"%s",a);
}
