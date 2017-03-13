import java.util.Scanner;
/*AOJ 1181*/

public class Main {
	
	//盤面fild[高さ][縦][横]
	//縦横
	static int fild[][][] = new int[101][200][200];
	static int height = 1;//サイコロの落とす高さ
	
	public static void main(String[] arg) {
		Scanner scan = new Scanner(System.in);
		
		//fildの一番下の段を平らに
		for(int i = 0; i < 200; i++){
			for(int j = 0; j < 200; j++){
				fild[0][i][j] = -1;
			}
		}	
		
		while(true){
			
		//高さの初期化	
		height = 1;
		//サイコロの数の設定（この値が0のとき終了）
		int dNum = scan.nextInt(); 
		if(dNum == 0) break;
		
		//答えを格納する
	    int ans[] = new int[7];
		
		for(int i = 0; i < dNum; i++){
		//サイコロの上面topと手前面frontの数字の設定
		int top, front;
		top = scan.nextInt();
		front = scan.nextInt();
		
		//サイコロの生成
		Dice dice = new Dice(top, front);
		Rolling(dice, height, 100, 100);

		}
		
		//数字の集計
		for(int h = 100; h > 0; h--){
			for(int x = 0; x < 200; x++){
				for(int y = 0; y < 200; y++){
				if(fild[h][x][y] != 0) {
					ans[(fild[h][x][y])]++;//上を向いている数字+1
					for(int l = h; l > 0; l--){
						fild[l][x][y] = 0;//その下にある数字を0に
					}
				}
		}

				}
			}
		
		//答え出力
		for(int i = 1; i < 6; i++) {
    		System.out.print(ans[i] + " ");
    	}
    	System.out.println(ans[6]);
		
		}
	}

	static void  Rolling(Dice dice, int h, int x, int y){
			
		//下にブロックがない！
			//if(fild[h-1][x][y] == 0) Rolling(dice, h-1, x, y);
		
			//東西南北の下側にサイコロがあるかの判定 0.南 1.西 2.東 3.北
			boolean[] f_dist = new boolean[4];
			if(fild[h-1][x][y-1] == 0) f_dist[0] = true;
			if(fild[h-1][x-1][y] == 0) f_dist[1] = true;
			if(fild[h-1][x+1][y] == 0) f_dist[2] = true;
			if(fild[h-1][x][y+1] == 0) f_dist[3] = true; 
			
			int max = 3;
			int d_roll = -1;
			
			for(int i =0; i < 4; i++ ) {
				//下にサイコロがあるかの判定
				if(f_dist[i]){
					//側面が４から６であるかの判定
					if(dice.num[i+1] > max ) {
						max = dice.num[i+1];
						d_roll = i;
					}
				}
			}
			
			//サイコロを転がして落とす
			if(d_roll == 0) {
				y--;
				dice.South();
			}
			else if(d_roll == 1) {
				x--;
				dice.West();
			}
			else if(d_roll == 2) {
				x++;
				dice.East();
			}
			else if(d_roll == 3) {
				y++;
				dice.Nouth();
			}
			else {
				if(h == height) height ++;
				fild[h][x][y] = dice.num[0];
				return;
			}
			
			//サイコロの上にのせる処理
			int brank = 0;
			while(fild[h-1-brank][x][y] == 0){
				brank++;
			}
			Rolling(dice, h-brank, x, y);
	}
}
