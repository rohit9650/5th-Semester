%{
	int len=0;
	char word;
%}

%%                                                                                            

[A-Za-z]	{   switch(*yytext){
					case 'z' : *yytext = 'c'; break;
					case 'y' : *yytext = 'b'; break;
					case 'x' : *yytext = 'a'; break;
					case 'X' : *yytext = 'A'; break;
					case 'Y' : *yytext = 'B'; break;
					case 'Z' : *yytext = 'C'; break;
					default  : *yytext += 3; break;
				} 
				printf("%s",yytext);
			}	
%%
int main(){
	yyin=fopen("test.txt","r");
	yylex();
	
	return 0;
}

int yywrap(){
	return 1;
}
