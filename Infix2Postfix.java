/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;
import javax.swing.*;

public class Infix2Postfix {
    
    private Stack stack;
    String infix="",postfix="",id,pToken;
    int pc=0; //parentheses count
    int move=1; 
    GUI gui1=new GUI();
    
    public Infix2Postfix(String infix){
        this.infix=infix;
        stack=new Stack();
    }
    
    public String Convert(){
        String c;
        for(int i=0;i<infix.length();i++){
            char ch=infix.charAt(i);
            if(Character.isDigit(ch)){
                c=ch+"";
                while(i<infix.length()-2&&Character.isDigit(infix.charAt(i+1))){
                    c=c+infix.charAt(i+1);
                    i++;
                }
                int x=Integer.parseInt(c);
                postfix=postfix+x;
                pToken=x+"";
            }
            else if(Character.isLetter(ch)){
                id=ch+"";
                while(i<infix.length()-22&&Character.isLetter(infix.charAt(i+1))||Character.isDigit(infix.charAt(i+1))){
                    i++;
                    id=id+infix.charAt(i+1);
                }
                pToken=id;
                postfix=postfix+id;
            }
            else{
                pToken=ch+"";
                switch(ch){
                    case'+':
                        case'-':
                            checkOperand(ch,1);
                            break;
                    case'*':
                        case'/':
                            checkOperand(ch,2);
                            break;
                    case'^':
                        checkOperand(ch,3);
                        break;
                    case'(':
                        stack.push(ch);
                        pc++;
                        break;
                    case')':
                        gotParen(ch);
                        pc--;
                        break; 
                }
            }
            
          print();
          move++;
        }
          while (!stack.isEmpty()){ 
          postfix=postfix+stack.pop();
          print();
          move++;
      } System.out.println("\nThe Evaluation of Postfix Expression : " + evaluatePostfix(postfix) + "\n");
      
      if(pc!=0){
          System.out.println("parantes not matched");
          System.exit(0);
      }
        
        
        return postfix;
    }
    public void checkOperand(char curOp,int prc1){
        while(!stack.isEmpty()){
            char topOp=(char)stack.pop();
            if(topOp=='('){
                stack.push(topOp);
                break;
            }
            else{
                int prc2=0;
                if(topOp=='+'||topOp=='-')
                    prc2=1;
                else if(topOp=='*'||topOp=='/')
                    prc2=2;
                else if(topOp=='^')
                    prc2=3;
                if(prc2<prc1){
                    stack.push(topOp);
                    break;
                }else
                    postfix=postfix+topOp;
                
            }
        }
        stack.push(curOp);
    }
    
    public void gotParen(char ch){
        while(!stack.isEmpty()){
            char chx=(char)stack.pop();
            if(chx=='(')
                break;
            else
                postfix=postfix+chx;
        }
    }
    public void print(){
        String m=move+" ";
          System.out.println("Move \t"+m);
          System.out.println("Token \t"+pToken);
          String st1=stack.toString();
          System.out.println("Stack \t"+st1);
          System.out.println("Output \t"+postfix);
          System.out.println("------------------------------------");
         
    }
    static int evaluatePostfix(String exp) 
    { 
        //create a stack 
        Stack<Integer> stack=new Stack<>(); 
          
        // Scan all characters one by one 
        for(int i=0;i<exp.length();i++) 
        { 
            char c=exp.charAt(i); 
              
            // If the scanned character is an operand (number here), 
            // push it to the stack. 
            if(Character.isDigit(c)) 
            stack.push(c - '0'); 
              
            //  If the scanned character is an operator, pop two 
            // elements from stack apply the operator 
            else
            { 
                int val1 = stack.pop(); 
                int val2 = stack.pop(); 
                  
                switch(c) 
                { 
                    case '+': 
                    stack.push(val2+val1); 
                    break; 
                      
                    case '-': 
                    stack.push(val2- val1); 
                    break; 
                      
                    case '/': 
                    stack.push(val2/val1); 
                    break; 
                      
                    case '*': 
                    stack.push(val2*val1); 
                    break; 
              } 
            } 
        } 
        return stack.pop();     
    }
    
            
    
}

