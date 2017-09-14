%{
	int i=0;
%}

%%                                                                                            

"int"	{}
"printf"	{}
"double" {}
"main"	{}
[ \t\n,]+[a-zA-Z_]+[0-9]*/[ \t]*[;,=]	{printf("%s",yytext);i++;}	
.|"\n" {}  
%%
int main(){
	yyin=fopen("test.txt","r");
	yylex();
	printf("\ncount of identifier is %d",i);
	return 0;
}

int yywrap(){
	return 1;
}		
