#include<stdio.h>
#include<string.h>
void chk_label();
void chk_opcode();
void READ_LINE();
struct MOT {
	char   code[10],objcode[10];
} myMOT[3]= {
	{"LDA","00"},
	{"JMP","01"},
	{"STA","02"}
};


struct symtab {
	char symbol[10];
	int addr;
} mySymtab[10];

int startaddr,locctr,symcount=0,length;
char line[20],label[8],opcode[8],operand[8],programName[10];

void PASS1() {
	FILE *input,*inter;
	input=fopen("input.txt","r");
    fgets(line,20,input);   					// read a line from assembly code.

	READ_LINE();								// separate the line into label(word3), opcode(word2), operand(word1).

	if(strcmp(opcode,"START")==0) {

		startaddr=atoi(operand);				// start address becomes the one mentioned in first instruction.
		locctr=startaddr;						// location counter assigned with start address.
		strcpy(programName,label);				// first label is the program name.

		fgets(line,20,input);					// read second line.
	} else {									// program name and start address not defined(first line of input file).
		programName[0]='\0';
		startaddr=0;
		locctr=0;
	}

	while(strcmp(line,"END")!=0) {

		READ_LINE();

		if(label[0]!='\0'){						// label is present or not.
			chk_label();						// if label is a new label or not.
		}
		chk_opcode();							// look for opcode in MOT or POT.

		fgets(line,20,input);
	}
	fclose(input);
	printf("\nPass 1 completed, data structures filled.\n");
}

void PASS2() {
	FILE *input,*output;
	char record[30],objC[6],value[5];
	int lc=0,foundopcode,foundoperand,chk,operandaddr,recaddr=0;

	input=fopen("input.txt","r");
	output=fopen("output.xls","w");
	value[0]=objC[0]='\0';
	printf("\n\nGenerating Corresponding Object code ...\n");
	lc=startaddr;
	fgets(line,20,input);

	fprintf(output,"LOCATION \t MACHINE \t CODE \t LABEL \t  OPCODE  \t  OPERAND\n");

	READ_LINE();
	if(strcmp(opcode,"START")==0){ 					// if START opcode is encountered, simply read second line
		fgets(line,20,input);
			fprintf(output,"\n %d \t %s \t %s \t %s \t %s \t %s",lc,objC,value,label,opcode,operand);
	}

	record[0]='\0';
	while(strcmp(line,"END")!=0) {
		operandaddr=foundoperand=foundopcode=0;
		value[0]=objC[0]='\0';						// objC stores opcode's object code, value stores operand's address.
		READ_LINE();
		for(chk=0; chk<3; chk++) {
			if(strcmp(opcode,myMOT[chk].code)==0) {	//check opcode in MOT
				foundopcode=1;
				lc+=3;					            // found in MOT
				strcpy(objC,myMOT[chk].objcode);	// copy object code in objC

				if(operand[0]!='\0') {				// if operand is present in instruction
					for(chk=0; chk<symcount; chk++)	// whether it is in symbol table or not

						if(strcmp(mySymtab[chk].symbol,operand)==0) { // operand present in symbol tab
							itoa(mySymtab[chk].addr,value,10);		//change address of operand to integer and assign it.
					        foundoperand=1;
						}
					if(!foundoperand)strcat(objC,"err");		// operand present in instruction but absent in symtab
				}
			}
		}
		if(!foundopcode) {			// check opcode in POT
			if(strcmp(opcode,"BYTE")==0 || strcmp(opcode,"WORD")||strcmp(opcode,"RESB")) {
				strcat(objC,"POT Opcode"); lc+=atoi(operand);
			}
		}
		fprintf(output,"\n %d \t %s \t %s \t %s \t %s \t %s",lc,objC,value,label,opcode,operand);
		fgets(line,20,input);
	}
	fclose(input);
	fclose(output);
	printf("\nPass 2 completed, code generated, check output.xls for machine code.");

}
//=================================================

void READ_LINE() {
	char buff[8],word1[8],word2[8],word3[8];
	int i,j=0,count=0;
	label[0]=opcode[0]=operand[0]=word1[0]=word2[0]=word3[0]='\0';
	for(i=0; line[i]!='\0'; i++) {
		if(line[i]!=' ')buff[j++]=line[i];
		else {
			buff[j]='\0';
			strcpy(word3,word2);
			strcpy(word2,word1);
			strcpy(word1,buff);
			j=0;
			count++;
		}
	}
	buff[j-1]='\0';
	strcpy(word3,word2);
	strcpy(word2,word1);
	strcpy(word1,buff);
	switch(count) {
		case 0:
			strcpy(opcode,word1);
			break;
		case 1: {
			strcpy(opcode,word2);
			strcpy(operand,word1);
		}
		break;
		case 2: {
			strcpy(label,word3);
			strcpy(opcode,word2);
			strcpy(operand,word1);
		}
		break;
	}
}

void chk_label() {
	int k,dupsym=0;
	for(k=0; k<symcount; k++)					// label name already used.
		if(!strcmp(label,mySymtab[k].symbol)) {
			mySymtab[k].addr=-1;				// nullify old label, which I forgot; should be an error, I guess.
			dupsym=1;
			break;
		}
	if(!dupsym) {								// new label.
		strcpy(mySymtab[symcount].symbol,label);// insert it into symbol table.
		mySymtab[symcount++].addr=locctr;		// symbol count incremented ,  address of label= location counter.
	}
}

void chk_opcode() {

	int k=0,found=0;
	for(k=0; k<3; k++)


		if(strcmp(opcode,myMOT[k].code)==0) {

			locctr+=3;							// Every opcode is of size 3

			found=1;							// opcode found flag set to true
			break;
		}
	if(!found) {								// if opcode not found in MOT,look in POT
		if(!strcmp( opcode,"WORD")) locctr+=3;
		else if (!strcmp(opcode,"RESW"))locctr+=(3*atoi(operand));
		else if(!strcmp(opcode,"RESB"))locctr+=atoi(operand);
	}

}

int main() {

	int i;
	PASS1();
	printf("\n The symbol table is - ");
	printf("\n Symbol 	Address");
	for(i=0;i<symcount;i++)
		printf("\n %s          %d",mySymtab[i].symbol,mySymtab[i].addr);
	length=locctr-startaddr;
	PASS2();
	getch();
}


