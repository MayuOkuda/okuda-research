import java.util.Arrays;

class Dice {
    
    int num[]; //サイコロの数
    int top;
    
    Dice(int top, int front){
    	this.num = InputNum(top, front);
        this.top = num[0];	
    }
    
    //サイコロを東向きに転がす
    void East(){
        int[] arrayNum ={2, 1, 5, 0, 4, 3};
        Temp(arrayNum);
    }
    //サイコロを西向きに転がす
    void West(){
    	int[] arrayNum ={3, 1, 0, 5, 4, 2};
    	Temp(arrayNum);
    }
    //サイコロを南向きに転がす
    void South(){
    	int[] arrayNum ={4, 0, 2, 3, 5, 1};
    	Temp(arrayNum);
    }
    //サイコロを北向きに転がす
    void Nouth(){
    	int[] arrayNum ={1, 5, 2, 3, 0, 4};
    	Temp(arrayNum);
    }
    
    //サイコロの数字を表示　0.上面 1.手前 2.右 3.左 4.奥 5.下
    void Print(){
    	for(int i = 0; i < 5; i++) {
    		System.out.print(this.num[i] + " ");
    	}
    	System.out.println(this.num[5]);
    }
    
    
    void Temp(int[] arrayNum){
    	int[] temp = new int[6];
    	int j = 0;
    	for(int i = 0; i < 6; i++) {
    		temp[j] = this.num[arrayNum[i]];
    		j++;

    	}
    	this.num = temp;	
    }
    
    int[] InputNum(int top, int front){
    	
    	int[] num = new int[6]; 
    	int[] sortNum = {top, front, 7-top, 7-front};
    	Arrays.sort(sortNum);
    	
    	num[0] = top;
    	num[1] = front;
    	num[4] = 7 - front;
    	num[5] = 7 - top;
    	
    	int[] case1 = {1,2,5,6};
    	int[] case2 = {1,3,4,6};
    	int[] case3 = {2,3,4,5};
 
    	if( Arrays.equals(sortNum, case1)){
    		if ( (top == 1 && front == 2) ||
    			 (top == 2 && front == 6) ||
    	         (top == 6 && front == 5) ||
    	         (top == 5 && front == 1) ) {
    		      	num[2] = 7 - 3;
    		      	num[3] = 3;
    			}
    		else{
    		num[2] = 3;
    		num[3] = 7 - 3;
    		}
    	}
    	else if(Arrays.equals(sortNum, case2)){
    		if ( (top == 1 && front == 3) ||
       			 (top == 3 && front == 6) ||
       	         (top == 6 && front == 4) ||
       	         (top == 4 && front == 1) ) {
       		      	num[2] = 7 - 5;
       		      	num[3] = 5;
       			}
       		else{
       		num[2] = 5;
       		num[3] = 7 - 5;
       		}
    	}
    	else if(Arrays.equals(sortNum, case3)){
    		if ( (top == 2 && front == 3) ||
       			 (top == 3 && front == 5) ||
       	         (top == 5 && front == 4) ||
       	         (top == 4 && front == 2) ) {
       		      	num[2] = 7 - 1;
       		      	num[3] = 1;
       			}
       		else{
       		num[2] = 1;
       		num[3] = 7 - 1;
       		}
    		
    	}
    	else{System.out.println("error");}
    	return num;
    }  

    
}

