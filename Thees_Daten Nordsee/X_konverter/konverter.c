#include <stdio.h>
#include <stdlib.h>
 
int main() {
	double	x, y;
	char	bez[10];
	FILE	*fi, *fo;

	fi = fopen("C_Punkte.txt","r");
	fo = fopen("C_Punkte_getauscht.txt","w");
	if ( fi != NULL ) {
		fscanf(fi,"%s %s %s",bez,bez,bez);
		fprintf(fo,"ID      X            Y\n");
		while ( fscanf(fi,"%s %lf %lf",bez,&y,&x)!=EOF ) {
			fprintf(fo,"%s  %11.8f  %11.8f\n",bez,x,y);
		}
		fclose(fi);
		fclose(fo);
	}
	else {
		printf("Datei konnte nicht geöffnet werden!\n");
	}

}
