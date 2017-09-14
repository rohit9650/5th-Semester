%{
	char arr[100];int size=0;
%}

%%

"(" {arr[size++]=*yytext;}
")" { 	size--;

		while(arr[size]!='('){ 
			printf("%d",size);
			if((arr[size]>=65&&arr[size]<=90)||(arr[size]>=97&&arr[size]<=122)){
				 size--;
				 
				 if(arr[size]=='('){
				 	arr[size]='x';
				 	size++;
				 	break;
				 }	
				 if(arr[size]=='+'||arr[size]=='-'||arr[size]=='*'||arr[size]=='/'){
					size--;
					
					if((arr[size]>=65&&arr[size]<=90)||(arr[size]>=97&&arr[size]<=122)){
				 		if(arr[size-1]=='+'||arr[size-1]=='-'||arr[size-1]=='*'||arr[size-1]=='/')
							size++; 
					}
					else{
						printf("%s","Invalid");	exit(0);
					}
				 }
				 else{
				 	 printf("%s","Invalid");exit(0);
				} 
			}
			else{
				printf("%s","Invalid");
				exit(0);	 
			}
			printf("%c",arr[size]);
			size--;	
		}
		if(arr[size]=='('){
			arr[size]='x';
			size++;
		}
	}
[a-zA-Z_] {arr[size++]=*yytext;} 
[\*\+-\/] {arr[size++]=*yytext;}                                                                                           

%%

int main(){
	yyin=fopen("arith.txt","r");	
	yylex();
	if(size!=1)
		printf("%s","Invalid");
	return 0;
}

int yywrap(){
	return 1;
}		
