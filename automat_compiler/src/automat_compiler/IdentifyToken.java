/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automat_compiler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
      
      
     public static ArrayList<String> aux;
    public static ArrayList identi;
    public static ArrayList delimiters;
    public static ArrayList verAritmeticos;
    public static ArrayList verLogicos;
    public static ArrayList palReservadas;
    public static ArrayList tokens;
    HashMap<String, Integer> tok;

     
    public void inicializaArrays() {
        
        aux = new ArrayList();
        identi = new ArrayList();
        delimiters = new ArrayList();
        verAritmeticos = new ArrayList();
        verLogicos = new ArrayList();
        palReservadas = new ArrayList();
        tok = new HashMap<>();
        tokens = new ArrayList();        
       
        delimiters.add("(");
        delimiters.add(")");
        delimiters.add(";");
        delimiters.add(".");
        delimiters.add(",");

        verAritmeticos.add("+");
        verAritmeticos.add("-");
        verAritmeticos.add("/");
        verAritmeticos.add("*");
        verAritmeticos.add("=");

        verLogicos.add("==");
        verLogicos.add("<");
        verLogicos.add(">");
        verLogicos.add("!=");
        verLogicos.add("<=");
        verLogicos.add(">=");
        
        palReservadas.add("CONST");
        palReservadas.add("VAR");
        palReservadas.add("PROCED");
        palReservadas.add("BEGIN");
        palReservadas.add("END");
        palReservadas.add("WRITE");
        palReservadas.add("READ");
        palReservadas.add("CALL");
        palReservadas.add("IF");
        palReservadas.add("THEN");
        palReservadas.add("WHILE");
        palReservadas.add("DO");
        palReservadas.add("FOR");
        palReservadas.add("TO");
        palReservadas.add("DTO");

        tok.put("CONST", 100);
        tok.put("VAR", 101);
        tok.put("PROCED", 102);
        tok.put("BEGIN", 103);
        tok.put("END", 104);
        tok.put("WHILE", 105);
        tok.put("WRITE", 106);
        tok.put("CALL", 107);
        tok.put("READ", 108);
        tok.put("THEN", 109);
        tok.put("DO", 110);
        tok.put("IF", 111);
        tok.put("FOR", 112);
        tok.put("TO", 113);
        tok.put("DTO", 114);
        tok.put("=", 200);
        tok.put("+", 201);
        tok.put("-", 202);
        tok.put("*", 203);
        tok.put("/", 204);
        tok.put("==", 210);
        tok.put("!=", 211);
        tok.put("<", 212);
        tok.put(">", 213);
        tok.put("<=", 214);
        tok.put(">=", 215);
        tok.put(".", 220);
        tok.put(",", 221);
        tok.put(";", 222);
        tok.put("(", 223);
        tok.put(")", 224);
    }
     public ArrayList Array()
        {
            return tokens;
        }

        public ArrayList arrValues()
        {
            return identi;
        }
    
    public void metodoChido() {
        inicializaArrays();
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
                identi.add(cad);
                tokens.add(getToken(cad));
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
                identi.add(cad);
                tokens.add(getToken(cad));
                continue;
            }
            switch (c) {
                case '+':
                case '-':
                case '/':
                case '*':
                    identi.add(Character.toString(c));
                    tokens.add(getToken(Character.toString(c)));
                    break;
                case '<':
                case '>':
                case '!':
                case '=':
                    cad += c;
                    i++;
                    if (i >= conteido.length()) {
                        identi.add(Character.toString(c));
                        tokens.add(getToken(Character.toString(c)));
                        break;
                    }
                    c = conteido.charAt(i);
                    if (c == ('=')) {
                        cad += c;
                        identi.add(cad);
                        tokens.add(getToken(cad));
                        break;
                    } else {
                        identi.add(cad);
                        tokens.add(getToken(cad));
                        i--;
                        break;
                    }
                case '.':
                case ',':
                case ';':
                case '(':
                case ')':
                    identi.add(Character.toString(c));
                    tokens.add(getToken(Character.toString(c)));
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
    
    public int getToken(String cadena) {
        String aux = "";
        char c;
        int token = 0;
        aux = cadena.toUpperCase();
        c = aux.charAt(0);
        if (palReservadas.contains(aux)) {
            token = tok.get(aux);
        }
        if (verAritmeticos.contains(aux)) {
            token = tok.get(aux);
        }

        if (delimiters.contains(aux)) {
            token = tok.get(aux);
        }

        if (verLogicos.contains(aux)) {
            token = tok.get(aux);
        }

        if (Character.isDigit(c)) {
            token = 300;
        }

        if (Character.isLetter(c) && !palReservadas.contains(aux)) {
            token = 350;
        }
        return token;
    }

    public void mostrarTodo() {
        char c;
        String aux = "";
    Collection<String> fin=new ArrayList<>();

        
        for (int i = 0; i < identi.size(); i++) {
            aux = identi.get(i).toString();
            aux = aux.toUpperCase();
            c = aux.charAt(0);

            if (palReservadas.contains(aux)) {
                fin.add((identi.get(i) + "  (" + i + ") (" + tokens.get(i) + ")"));
                continue;
            }
            if (verAritmeticos.contains(aux)) {
                fin.add((identi.get(i) + "  (" + i + ") (" + tokens.get(i) + ")"));
                continue;
            }

            if (delimiters.contains(aux)) {
                fin.add((identi.get(i) + "  (" + i + ") (" + tokens.get(i) + ")"));
                continue;
            }

            if (verLogicos.contains(aux)) {
                fin.add((identi.get(i) + "  (" + i + ") (" + tokens.get(i) + ")"));
                continue;
            }

            if (Character.isDigit(c)) {
                fin.add(identi.get(i) + "  (" + i + ") (300)");
                continue;
            }

            if (Character.isLetter(c) && !palReservadas.contains(aux)) {
                fin.add(identi.get(i) + "  (" + i + ") (350)");
            }
        }
      
        for (Iterator<String> iterator = fin.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            System.out.println(next);
        }
        
    }
         
}