package com.othello.desdemona.app;

/**
 * Created by jesse on 5/8/14.
 */
public class Game {
    //spaces can contain a 'b' 'w' 'p' or 'n'
    //'b' black 'w' white 'p' poss_move 'n' null
    public char spaces[][][];
    public char grid[];
    private boolean b_turn;
    public boolean no_winner;


    public Game(){//done
        spaces = new char[8][8][1];
        grid = new char[64];
        clear();
        spaces[3][3][0]='b';
        spaces[4][4][0]='b';
        spaces[3][4][0]='w';
        spaces[4][3][0]='w';
        b_turn = true;
        no_winner =true;
        get_poss_moves();
       /* System.out.print("Black, please place a piece.\n\n");

        for(int i=7;i>=0;i--, System.out.print("\n"))
            for(int j=0;j<8;j++)
                System.out.print(spaces[j][i][0]);
        System.out.print("\n");*/


    }

    private void clear(){//done
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                spaces[j][i][0]='n';
            }
        }
    }

    private void swap_turn(){//done
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(spaces[j][i][0]!='b'&&
                        spaces[j][i][0]!='w'){
                    spaces[j][i][0]='n';
                }
            }
        }
        if(b_turn){
            b_turn = false;
        }else{b_turn = true;}
        get_poss_moves();
    }

    private void get_poss_moves(){//done
        if(b_turn){
			/*input black's possible moves*/
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(spaces[j][i][0]!='b' && spaces[j][i][0]!='w'){
						/*check for valid move*/
                        if (valid_move(j, i)){
                            spaces[j][i][0]='p';
                        }
                    }
                }
            }



        }else{
			/*input white's possible moves*/
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(spaces[j][i][0]=='n'){
						/*check for valid move*/
                        if (valid_move(j, i)){
                            spaces[j][i][0]='p';
                        }
                    }
                }
            }
        }
        setGrid();
    }

    private boolean place_piece(int getx, int gety){//done
        boolean have_flipped = false;
        if(spaces[getx][gety][0]=='p'){
			/*do flip command for given spaces*/


            //see who's turn it is
            char player;

            if(b_turn){
                player = 'b';
            }else{
                player = 'w';
            }

				/*check all directions*/
            for (int xinc=-1; xinc<=1; xinc++){
                for (int yinc=-1; yinc<=1; yinc++){

			    	/*make sure we're actually checking a direction*/
                    if ((xinc != 0 || yinc != 0)){

			    	/*make sure we don't go out of the bounds of the matrix 'spaces'*/
                        if ((0<getx && getx<7)||(getx==0&&xinc>=0)||(getx==7&&xinc<=0)){
                            if ((0<gety && gety<7)||(gety==0&&yinc>=0)||(gety==7&&yinc<=0)){

			    		/*x and y define another space we reference*/
                                int x, y;
                                x = getx+xinc; y = gety+yinc;

			    		/*make sure we don't 'flip' spaces*/
                                if((spaces[x][y][0]=='b')||(spaces[x][y][0]=='w')){

			    		/*loop until we hit a player token or a wall*/
                                    while(!((spaces[x][y][0]==player)
                                            ||(((x+xinc)>7)||((y+yinc)>7)
                                            ||((x+xinc)<0)||((y+yinc)<0))))
                                    {x += xinc; y += yinc;}

			    		/*make sure the reference space has a player piece on it*/
                                    if((spaces[x][y][0]==player)){

			    			/*remember the space we ended the first loop on*/
                                        int count=0;
                                        int rx = x, ry = y;

			    			/*loop back to be sure no holes are present in the line, and to make
			    			 * sure that it's not the adjacent space*/
                                        while(((spaces[x][y][0] !='n')&&(spaces[rx][ry][0])==player)&&
                                                ((x!=getx)||(y!=gety)))
                                        {count++; x-= xinc; y-= yinc;}

			    			/*checks that the results of the last loop are kosher*/
                                        if (((x==getx)&&(y==gety))&&(count>1)){

			    				/*loop back again and flip the pieces between*/
                                            boolean still_flipping = true;
                                            boolean flipped_yet =false;


                                            while(still_flipping)  {
                                                if((rx==getx)&&(ry==gety)){
                                                    still_flipping = false;}
                                                else if(!flipped_yet){
                                                    rx-=xinc; ry-=yinc;
                                                    flipped_yet=true;
                                                    continue;}
                                                else{do_flip(rx,ry);rx-=xinc; ry-=yinc;}
                                            }

                                            spaces[getx][gety][0]=player;
                                            have_flipped = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
			/*if flipped work*/
            return have_flipped;
			/*if space initially chosen is not marked as possible*/
        }else{return false;}

    }

    private void do_flip(int x, int y){//done
        if (spaces[x][y][0]=='b'){
            spaces[x][y][0]='w';
        }else{spaces[x][y][0]='b';}
    }

    private boolean valid_move(int getx, int gety){//done

        //make sure it's in a null space
        if (spaces[getx][gety][0]!='n'){return false;}

        //see who's turn it is
        char player;

        if(b_turn){
            player = 'b';
        }else{
            player = 'w';
        }

		/*check all directions*/
        for (int xinc=-1; xinc<=1; xinc++){
            for (int yinc=-1; yinc<=1; yinc++){

		    	/*make sure we're actually checking a direction*/
                if ((xinc != 0 || yinc != 0)){

		    	/*make sure we don't go out of the bounds of the matrix 'spaces'*/
                    if ((0<getx && getx<7)||(getx==0&&xinc>=0)||(getx==7&&xinc<=0)){
                        if ((0<gety && gety<7)||(gety==0&&yinc>=0)||(gety==7&&yinc<=0)){

		    	/*x and y define another space we reference*/
                            int x, y;
                            x = getx+xinc; y = gety+yinc;

			      /*make sure we don't 'flip' spaces*/
                            if((spaces[x][y][0]=='b')||(spaces[x][y][0]=='w')){

			    	  /*loop until we hit a player token or a wall*/
                                while(!((spaces[x][y][0]==player)
                                        ||(((x+xinc)>7)||((y+yinc)>7)
                                        ||((x+xinc)<0)||((y+yinc)<0))))
                                {x += xinc; y += yinc;}

			    	  /*make sure the reference space has a piece on it: might change this 'n' to player*/
                                if(!(spaces[x][y][0]=='n')){

			    		  /*set count*/
                                    int count=0;
                                    int rx = x, ry = y;

			    		  /*loop back to be sure no holes are present in the line, and to make sure
			    			 * that it's not the adjacent space*/
                                    while(((spaces[x][y][0] !='n')&&(spaces[rx][ry][0])==player)&&
                                            ((x!=getx)||(y!=gety)))
                                    {count++; x-= xinc; y-= yinc;}

			    		  /*checks that the results of the last loop are kosher*/
                                    if (((x==getx)&&(y==gety))&&(count>1)){return true;}
                                }
                            }
                        }
                    }
                }
            }
        }
		/*if no directions work*/
        return false;

    }

    public boolean do_move(int x, int y){//done
        if(place_piece(x,y)){
            swap_turn();
            // setGrid();
            return true;
        }else{return false;}

        /*else{System.out.print("Not a valid move\n");}

        if(b_turn){System.out.print("Black, please place a piece.\n");}
        else{System.out.print("White, please place a piece.\n");}

        /*System.out.print("\n");

        for(int i=7;i>=0;i--, System.out.print("\n"))
            for(int j=0;j<8;j++)
                System.out.print(spaces[j][i][0]);*/
    }

    public void setGrid(){

        int count=0;
        for(int i=7 ;i>=0;i--)
            for(int j=0;j<8;j++,count++)
                grid[count]=spaces[j][i][0];
    }

    public int blackScore(){
        int count=0;
        for(int i=7 ;i>=0;i--)
            for(int j=0;j<8;j++)
                if (spaces[j][i][0]=='b')
                    count++;

        return count;
    }

    public int whiteScore(){
        int count=0;
        for(int i=7 ;i>=0;i--)
            for(int j=0;j<8;j++)
                if (spaces[j][i][0] == 'w')
                    count++;

        return count;
    }

    public boolean isWinner(){
        int count=0;
        for(int i=7 ;i>=0;i--)
            for(int j=0;j<8;j++)
                if (spaces[j][i][0] == 'p')
                    count++;

        if (count == 0)
            swap_turn();
        else return false;

        for(int i=7 ;i>=0;i--)
            for(int j=0;j<8;j++)
                if (spaces[j][i][0] == 'p')
                    count++;

        if (count ==0) {
            no_winner = false;
            return true;
        }else return false;
    }


}