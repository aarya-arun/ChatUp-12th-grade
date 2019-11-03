#include<iostream>
#include<fstream>
#include<windows.h>
#include<graphics.h>

using namespace std;

char user[20];
bool flag=true;

void select_user();
void input(char *,int ,int);
void input_text(char *,int ,int);
int main();

class chat
{
    
	//Function to type a message into the Chat file
    
	void type()
	{
        		char text[250];
		ofstream ofile;
		ofile.open("temp.txt");
		fflush(stdin);
		read();
		setcolor(GREEN);
		char user1[20];
		strcpy(user1,user);
		line(10,595,910,595);
		line(10,595,10,695);
		line(910,595,910,695);
		line(10,695,910,695);
		strcat(user1," :-");
		outtextxy(20,600,user1);
		setcolor(BLACK);
		input_text(text,16,620);
		ofile<<user<<" :- "<<text<<"\n\n";
		ofile.close();
		copy();
		read();
	}
	
	//Function to display the chat by reading from the Chat file
	
	void read()
	{
		cleardevice();
		char text[250];
		ifstream ifile("Chat.txt");
		int y=550;
		char buffer[250];
		for(int j=0;j<249;j++)
		buffer[j]=' ';
		buffer[249]='\0';
		outtextxy(0,600,buffer);
		outtextxy(0,620,buffer);
		outtextxy(0,640,buffer);
		outtextxy(0,660,buffer);
		while(!ifile.eof())
		{
			setcolor(BLUE);
			fflush(stdin);
			ifile.getline(text,250,'\n');
			int i;
			for(i=0;text[i]!='\0';i++);

			if(i>159)
			{
				char arr[81];
				for(int j=0;j<81;j++)
					arr[j]='\0';
				for(int j=0;j<80;j++)
					arr[j]=text[j+160];
				arr[80]='\0';
				outtextxy(0,y,buffer);
				outtextxy(40,y,arr);
				y-=20;
			}

			if(i>79)
			{
				char arr[81];
				for(int j=0;j<81;j++)
					arr[j]='\0';
				for(int j=0;j<80;j++)
					arr[j]=text[j+80];
				arr[80]='\0';
				outtextxy(0,y,buffer);
				outtextxy(40,y,arr);
				y-=20;
			}

			if(i>0)
			{
				char arr[81];
				for(int j=0;j<81;j++)
					arr[j]='\0';
				for(int j=0;j<80;j++)
					arr[j]=text[j];
				arr[80]='\0';
				outtextxy(0,y,buffer);
				outtextxy(10,y,arr);
				y-=20;
			}

			outtextxy(0,y,buffer);
			y-=5;
			char user1[20];
			setcolor(BLACK);
			strcpy(user1,user);
			strcat(user1," :-");
			line(10,595,910,595);
			line(10,595,10,695);
			line(910,595,910,695);
			line(10,695,910,695);
			outtextxy(20,600,user1);
			setcolor(GREEN);
       			outtextxy(840,10,"Menu");
        			setcolor(BLACK);
		}
		ifile.close();
	}
	
	//Function to delete the chat when the user chooses to do so
	
	void del()
	{
		ofstream ofile("Check.txt");
		ofile<<"deleted";
		ofile.close();
		cleardevice();
		remove("Chat.txt");
		setcolor(RED);
		outtextxy(335,230,"Chat has been deleted......");
		setcolor(BLACK);
		ofstream ofile1("Chat.txt");
		ofile1.close();
	}
	
	//Function to handle the layout of the text in the file to get desired display on the graphics screen
	
	void copy()
	{
         		char text[250];
         		ofstream ofile("temp.txt",ios::app);
         		ifstream ifile("Chat.txt");
         
         		while(!ifile.eof())
         		{
                            		fflush(stdin);
                            		ifile.getline(text,250,'\n');
                            		ofile<<text<<"\n";
         		}
         		ifile.close();
         		ofile.close();
         		remove("Chat.txt");
         		ofstream ofile1("Chat.txt",ios::app);
         		ifstream ifile1("temp.txt");
         
         		while(!ifile1.eof())
         		{
                            		fflush(stdin);
                           		ifile1.getline(text,250,'\n');
                            		ofile1<<text<<"\n";
         		}
         		ifile1.close();
         		ofile1.close();
    	}
         
	public:

    	//Function to decide which function has to be performed based on the position of the mouse pointer
	
	void loop()
	{
		do
		{
			if(!ismouseclick(WM_LBUTTONDOWN))
            			{
                                             	ifstream ifile("Check.txt");
char arr[10];
ifile>>arr;

				if(strcmp(arr,"deleted")==0)
				{
					remove("Check.txt");
					cleardevice();
				}

				ifile.close();
				read();
                                             	delay(200);
            			}

            			else
            			{
               			 int x,y;
                			getmouseclick(WM_LBUTTONDOWN,x,y);

                			if(y>595)
                			{
                         				clearmouseclick(WM_LBUTTONDOWN);
                         				type();
                			}

                			if(y<40&&x>820)
                			{
                    				cleardevice();
                    				func();
                			}

                			else
                			read();
                			clearmouseclick(WM_LBUTTONDOWN);
            			}
            		}while(1);
	}
	
	//Function to take the user choice and perform the desired task through a menu
	
	void func()
	{
		//dislays the menu to the user
		settextstyle(3,0,5);
		setcolor(BLUE);
		outtextxy(10,10,user);
		setcolor(BLACK);
		settextstyle(3,0,1);
		outtextxy(335,80,"Please enter your choice.");
            		outtextxy(350,120,"1: Type");
            		outtextxy(350,145,"2: Delete");
            		outtextxy(350,170,"3: Logout");
            		outtextxy(350,195,"4: Exit");
            
		char choice;
		choice=getch();
			
		switch(choice)
		{
			case '1':
				cleardevice();
				loop();
				break;
			case '2':
				del();
				func();
				break;
			case '3':
				cleardevice();
				for(int i=0;i<20;i++)
				user[i]='\0';
				main();
				break;
			case '4':
				exit(0);
			default:
				outtextxy(335,230,"Invalid choice. Enter again.");
				func();
		}
	}
}
u1;


int main()  //MAIN function
{
   	 if(flag)
	{
		initwindow(930,700,"Chat");
		flag=false;
	}
	setbkcolor(WHITE);
	cleardevice();
	settextstyle(6,0,10);
	setcolor(GREEN);
	outtextxy(150,300,"ChatUP");
	setcolor(BLACK);
	settextstyle(3,0,3);
	char ch=getch();
	cleardevice();

	//To print the directions for using the chat

	setcolor(RED);
	outtextxy(10,10,"This is a program to simulate a chat on c++");
	setcolor(GREEN);
	settextstyle(3,0,1);
	outtextxy(10,60,"Directions to use:");
	setcolor(BLACK);
	outtextxy(10,85,"1: Please select your choice by entering the number corresponding to the function you want to perform.");
	outtextxy(10,110,"2: The chat keeps refreshing automatically.");
	outtextxy(10,135,"3: To type, click on the type bar so that it becomes green.");
	outtextxy(10,160,"4: Once you start typing, the chat is not refreshed till you submit your text or go back to refresh mode.");
	outtextxy(10,185,"5: To return back from type mode, click on the chat area.");
	outtextxy(10,210,"6: Once you return back to the refresh mode, the type bar turns white again.");
	outtextxy(10,235,"7: To exit the chat, click on the MENU option on the right top of the screen.");
	outtextxy(10,260,"8: Text message should not be longer than 240 characters.");
	outtextxy(10,300,"Press any key to continue.................");
	
	ch=getch();
	cleardevice();
	select_user();
	system("pause");
}

//Function to take in the user name of from the user to provide identification

void select_user()
{
	outtextxy(320,300,"Please enter the username");
	input(user,320,330);
	cleardevice();
	u1.func();

	/*if(strcmp(user,"user1")==0)
	{
		setcolor(GREEN);
        		outtextxy(320,50,"You have logged in as user1.");
        		setcolor(WHITE);
		u1.func();
	}

	else if(strcmp(user,"user2")==0)
	{
		setcolor(GREEN);
        		outtextxy(320,50,"You have logged in as user2.");
        		setcolor(WHITE);
		u1.func();
	}

	else
	{
		outtextxy(370,360,"Wrong user");
		main();
	}*/
}

//Function to help take user name from the graphics window

void input(char* buffer,int x, int y)
{
	char ch,dummy[]={"                                                     "};
	in:
outtextxy(x,y,dummy);
	int i=0;
	do
	{
         		ch=getch();

        		if(ch==8&&i>=1) //handling the backspacing
         		{
                  		buffer[--i]='\0';
                  		outtextxy(x,y,dummy);
                 		 outtextxy(x,y,buffer);
        		 }
         
        		 if(ch==13)
        		 {
                   		return ; //return to the calling function once the user presses enter
        		 }
         
         		if(isprint(ch))
         		{
                        		//setting the limiter to the length of the user name

	          		if(i>17)
                       		 {
                                		outtextxy(280,360,"Username too long. Please enter again.");
                                		for(int i=0;i<20;i++)
                                		buffer[i]='\0';
				goto in;
                        		}
                        
                        		else
                        		{
                            			buffer[i++]=ch;
                            			outtextxy(x,y,buffer);
                        		}
        		 }
         
   	 }while(1);
}

//Function to help take the text message typed by the user from the graphics window.

void input_text(char* buffer,int x, int y)
{
    	char ch,arr[2],dummy[200];
    	for(int i=0;i<250;i++)
    	buffer[i]='\0';
    	for(int i=0;i<199;i++)
    	dummy[i]=' ';
    	dummy[199]='\0';
	int i=0;
	
	do
	{
         		if(!ismouseclick(WM_LBUTTONDOWN))
         		{
                                                 int j=0;
                	}

               	else
                	{
                    		int a,b;
                    		getmouseclick(WM_LBUTTONDOWN,a,b);
                    		if(b<595&&(b>40&&a<820))
                    		u1.loop();
                	}
                	ch=getch();
         
         		if(ch==8&&i>=1) //handling the backspacing
         		{
                  		buffer[--i]='\0';
                  		outtextxy(20,y,dummy);
                  		outtextxy(20,y+25,dummy);

                  		if(i>79)
                                            {
				char arr[81];
				for(int j=0;j<81;j++)
				arr[j]='\0';
				for(int j=0;j<80;j++)
				arr[j]=buffer[j];
				arr[80]='\0';
				outtextxy(20,y,arr);
                                                          for(int j=0;j<81;j++)
				arr[j]='\0';
	                                           for(int j=0;j<80;j++)
				arr[j]=buffer[j+80];
				arr[80]='\0';
				outtextxy(20,y+25,arr);
			  }

			  else
			  {
				outtextxy(20,y,buffer);
			  }
                             }

                  	if(ch==13&&i>0) //return to the calling function once the user presses enter
       		{
              			return ;
        	 	}
         
      	   	if(ch==13&&i==0) //calls the function if the user presses enter without typing anything in type mode
        	 	{
                        		u1.loop();
        	 	}
                        
        	 	if(ch==9) //call the function loop if the user chooses to exit the type mode
        		{
                  	  	u1.loop();
        	 	}

        	 	if(isprint(ch))
        		{
                        		//setting limiter the length of the text typed to 240 characters
	         		int j;
                        		for(j=0;user[j]!='\0';j++);
	         		if(i>(239-j-4))
                        		{
                                		setcolor(RED);
				outtextxy(100,600,"Text length limit reached!!!");						setcolor(BLACK);
                        		}

                        		else
                        		{
buffer[i++]=ch;
                                    		if(i>0)
				{
					char arr[81];
					for(int j=0;j<81;j++)
					arr[j]='\0';
					for(int j=0;j<80;j++)
					arr[j]=buffer[j];
					arr[80]='\0';
					outtextxy(20,y,arr);
				}

				if(i>79)
                                            	{
					char arr[81];
                                                    		for(int j=0;j<81;j++)
arr[j]='\0';
for(int j=0;j<80;j++)
arr[j]=buffer[j+80];
				                arr[80]='\0';
				               outtextxy(20,y+20,arr);
}

if(i>159)
{
char arr[81];
for(int j=0;j<81;j++)
arr[j]='\0';
	                                                	for(int j=0;j<80;j++)
arr[j]=buffer[j+160];
				               arr[80]='\0';
				               outtextxy(20,y+40,arr);
				}
                   		}
        	 	}
    	}while(1);
}
