
%{
void yyerror(char *);
#include "y.tab.h"
#include<stdio.h> 
int yylex(),num_a=0,num_b=0;

%}

%token ID

%%

P : P E '\n' {printf("%s","valid");}
  |
  ;

E : 'a' F 'b' { }
  ; 
F: 'a' F 'b' { }
 | 
 ;
  
%%

int main(){
	yyparse();
	return 0;
}
void yyerror(char *a){
	fprintf(stderr,"%s",a);
}
