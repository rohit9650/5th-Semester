%{
	int len=0;
	char word[50];
%}

%%                                                                                            

[A-Za-z]+	{if(yyleng>len){ len=yyleng; for(int i=0; i<yyleng; i++) word[i]=yytext[i]; } }	
.|"\n" {}  
%%
int main(){
	yyin=fopen("test.txt","r");
	yylex();
	printf("\n length : %d word :  %s",len, word);
	return 0;
}

int yywrap(){
	return 1;
}		
