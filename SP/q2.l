%{
	int word=0,space=0,newl=0,character=0;
%}

%%                                                                                            

"\n" {newl++;}
[ \t] {space++;}
[a-zA-Z0-9]+/[ \t\n] {word++; character += yyleng;}

.|"\n" {}  
%%
int main(){
	yyin=fopen("test.txt","r");
	yylex();
	printf("\ncount of word is : %d ,charcter is : %d, spaces is : %d, line is : %d",word,character,space,newl);
	return 0;
}

int yywrap(){
	return 1;
}		


