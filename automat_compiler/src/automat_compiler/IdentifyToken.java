/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automat_compiler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


/**
 *
 * @author Alma
 */

public class IdentifyToken {
      Collection<String> cadenas=new LinkedList<>();

      char[] one_by_one;
      
    public IdentifyToken(char [] one_by_one) {
        this.one_by_one=one_by_one;
    }
      
  
    public static ArrayList lexema_readed;
     public static ArrayList token_readed;
     
    public static ArrayList tokem_DB;
    public static ArrayList lexema_DB;
    
    
 

     
    public void load_lexema_token() {
        

        lexema_readed = new ArrayList();
        token_readed = new ArrayList();
        
        lexema_DB = new ArrayList();        
        tokem_DB = new ArrayList();        

         
        lexema_DB.add("CONST");
        lexema_DB.add("VAR");
        lexema_DB.add("PROCEDURE");
        lexema_DB.add("BEGIN");
        lexema_DB.add("END");
        lexema_DB.add("UNTIL");
        lexema_DB.add("WRITE");
        lexema_DB.add("CALL");
        lexema_DB.add("READ");
        lexema_DB.add("THEN");
        lexema_DB.add("DO");
        lexema_DB.add("IF");     
        
        tokem_DB.add(100);
        tokem_DB.add(101);
        tokem_DB.add(102);
        tokem_DB.add(103);
        tokem_DB.add(104);
        tokem_DB.add(105);
        tokem_DB.add(106);
        tokem_DB.add(107);
        tokem_DB.add(108);
        tokem_DB.add(109);
        tokem_DB.add(110);
        tokem_DB.add(111);
    
        lexema_DB.add("="); 
        lexema_DB.add("+");
        lexema_DB.add("-");
        lexema_DB.add("*");
        lexema_DB.add("/");
        
        tokem_DB.add(200);
        tokem_DB.add(201);
        tokem_DB.add(202);
        tokem_DB.add(203);
        tokem_DB.add(204);
        
        lexema_DB.add("==");
        lexema_DB.add("!=");
        lexema_DB.add("<");
        lexema_DB.add(">");
        lexema_DB.add("<=");
        lexema_DB.add(">=");
       
        tokem_DB.add(300);
        tokem_DB.add(301);
        tokem_DB.add(302);
        tokem_DB.add(303);
        tokem_DB.add(304);
        tokem_DB.add(305);
        
        lexema_DB.add(".");
        lexema_DB.add(",");
        lexema_DB.add(";");
        lexema_DB.add("(");
        lexema_DB.add(")");
        
        tokem_DB.add(400);
        tokem_DB.add(401);
        tokem_DB.add(402);
        tokem_DB.add(403);
        tokem_DB.add(404);
    }

    
    public void catch_lexema() {
        load_lexema_token();
        int i = 0;
        Character c;
        String cad;
        String conteido = (String.valueOf(one_by_one));

        //try{
        while (i < conteido.length()) {
            c = conteido.charAt(i);
            cad = "";

            //NUMEROS
            if (Character.isDigit(c)) {
                while (Character.isDigit(c)) {
                    cad += c;
                    i++;
                    if (i >= conteido.length()) {
                        break;
                    }
                    c = conteido.charAt(i);
                }
                lexema_readed.add(cad);
                token_readed.add(getToken(cad));
                continue;
            }

            //LEXEMAS
            if (Character.isLetter(c)) {
                while (Character.isLetterOrDigit(c) || c == ('_')) {
                    cad += c;
                    i++;
                    if (i >= conteido.length()) {
                        break;
                    }
                    c = conteido.charAt(i);
                }
                lexema_readed.add(cad);
                token_readed.add(getToken(cad));
                continue;
            }
            switch (c) {
                case '+':
                case '-':
                case '/':
                case '*':
                    lexema_readed.add(Character.toString(c));
                    token_readed.add(getToken(Character.toString(c)));
                    break;
                case '<':
                case '>':
                case '!':
                case '=':
                    cad += c;
                    i++;
                    if (i >= conteido.length()) {
                        lexema_readed.add(Character.toString(c));
                        token_readed.add(getToken(Character.toString(c)));
                        break;
                    }
                    c = conteido.charAt(i);
                    if (c == ('=')) {
                        cad += c;
                        lexema_readed.add(cad);
                        token_readed.add(getToken(cad));
                        break;
                    } else {
                        lexema_readed.add(cad);
                        token_readed.add(getToken(cad));
                        i--;
                        break;
                    }
                case '.':
                case ',':
                case ';':
                case '(':
                case ')':
                    lexema_readed.add(Character.toString(c));
                    token_readed.add(getToken(Character.toString(c)));
                    break;
                case '\n':
                case '\t':
                case ' ':
                    break;

                default:
                    break;
            }
            i++;
        }    
    }
    
    public String getToken(String cadena) {
        String aux = "";
        char c;
        String token = "";
        int position;
        
        aux = cadena.toUpperCase();
        c = aux.charAt(0);
        if (lexema_DB.contains(aux)) {
            position = lexema_DB.indexOf(aux);
            token = tokem_DB.get(position).toString();
        }else  if (Character.isLetter(c) && !lexema_DB.contains(aux)) {
            token = "600";
        }
        if (Character.isDigit(c)) {
            token = "500";
        }
    return token;
    }

    public Collection almacenar_para_imprimir() {
        char c;
        String aux = "";
    Collection<String> fin=new ArrayList<>();

        
        for (int i = 0; i < lexema_readed.size(); i++) {
            aux = lexema_readed.get(i).toString();
            aux = aux.toUpperCase();
            c = aux.charAt(0);

            if (lexema_DB.contains(aux)) {
                fin.add((lexema_readed.get(i) + "    (" + token_readed.get(i) + ")"));
                continue;
            }else if (Character.isLetter(c) && !lexema_DB.contains(aux)) {
                fin.add(lexema_readed.get(i) + "     (600)");
            }
            if (Character.isDigit(c)) {
                fin.add(lexema_readed.get(i) + "    (500)");
                continue;
            }           
        }
          return fin;   
    }        
}