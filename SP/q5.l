%{
	int line=2;int a=1;
%}

%%                
^"\n" { fprintf(yyout,"%s",yytext);}                                                                            
"\n" { fprintf(yyout,"\n%d",line);line++;}

%%

int main(){
	yyin=fopen("test.txt","r");
	yyout=fopen("paste.txt","w");
	fprintf(yyout,"%d\t",a);
	yylex();
	return 0;
}

int yywrap(){
	return 1;
}		
