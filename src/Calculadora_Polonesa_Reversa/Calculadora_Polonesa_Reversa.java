
package Calculadora_Polonesa_Reversa;

import java.io.IOException;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Scanner;

public class Calculadora_Polonesa_Reversa {

    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        
        String entrada = sc.nextLine();
        String valorAux = "";
      
        char operacao = ' ';
        
        boolean achouOperacao = false;
        
        Integer respostas = 0; // armazena a resposta
        
        ArrayList<Integer> valores = new ArrayList();    // armazena os valores que achar na equação    
        ArrayList<Integer> valoresArmazena = new ArrayList(); // armazena os valores que achar mas não serão usados no cálculo
        
        for( int i = 0; i < entrada.length(); i++ )
        {
            char aux = entrada.charAt(i);  // pega o caractere naquela posição             

            switch( aux )
            {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    valorAux += aux; // adiciona o caractere do valor no valorAux
                    achouOperacao = false;
                    break;
                case ' ':
                    if( valorAux != "" ) // se tiver um valor dentro do valorAux
                    {
                        Integer inteiro;
                        inteiro = Integer.parseInt( valorAux ); // transforma em inteiro
                        valores.add( inteiro );
                    }
                    valorAux = "";                 
                    break;
                default:    
                    if( valorAux != "" ) // se tiver um valor dentro do valorAux
                    {
                        Integer inteiro;
                        inteiro = Integer.parseInt( valorAux ); // transforma em inteiro
                        valores.add( inteiro );
                    }
                    valorAux = "";
                    achouOperacao = true;
                    operacao = aux; // pega a operação pra usar depois
                    break;
            }

            if ( achouOperacao )
            {
                if( !valores.isEmpty() )
                {   
                    if( valores.size() == 1 ) // se encontrar apenas um valor pra fazer a operação
                    {
                        Integer inteiro;
                        
                        if ( operacao == 's' ) // raiz quadrada
                        {
                            respostas = realizaRaizQuadrada( valores );
                        }
                        else // fatorial
                        {
                            respostas = realizaFatorial( valores );                        
                        }                       
                    }
                    else if( valores.size() == 2 ) // se encontrar dois valores pra fazer a operação
                    { 
                        if( operacao != 's' && operacao != 'f' ) // se for diferente de raiz ou fatorial que só usam 1 valor
                        {                        
                            if( operacao == '+' ) // adição
                            {
                                respostas = realizaSoma( valores );                                                      
                            }
                           
                            else if( operacao == '-' ) // subtração
                            {
                                respostas = realizaSubtracao( valores );
                            }
                            else if( operacao == '*' ) // multiplicação
                            {
                                respostas = realizaMultiplicacao( valores );                               
                            }
                            else if( operacao == '/' ) // divisão
                            {
                                respostas = realizaDivisao( valores );
                            }
                            else // potencia
                            {
                                respostas = realizaPotencia( valores );      
                            }
                        }
                        else // se for operação de raiz ou fatorial
                        {
                            if ( operacao == 's' ) // raiz quadrada
                            {
                                respostas = realizaRaizQuadrada( valores );                                   
                            }
                            else // fatorial
                            {
                                respostas = realizaFatorial( valores );
                            }
                            
                            for( int j = 0; j < valores.size() - 1; j++ ) // armazena os valores que não foram usados no cálculo
                            {
                                valoresArmazena.add( valores.get( j ) );
                            }
                        }
                    }
                    else // se encontrar mais de 2 valores pra fazer a operação
                    {
                        if( operacao != 's' && operacao != 'f' )
                        {
                            if( operacao == '+' ) // adição
                            {
                                respostas = realizaSoma( valores );                                                             
                            }
                            else if( operacao == '-' ) // subtração
                            {
                                respostas = realizaSubtracao( valores );
                            }
                            else if( operacao == '*' ) // multiplicação
                            {
                                respostas = realizaMultiplicacao( valores );
                            }
                            else if( operacao == '/' ) // divisão
                            {
                                respostas = realizaDivisao( valores );                               
                            }
                            else // potencia
                            {
                                respostas = realizaPotencia( valores );
                            }

                            for( int j = 0; j < valores.size() - 2; j++ ) // armazena os valores que não foram usados no cálculo
                            {
                                valoresArmazena.add( valores.get( j ) );
                            }
                        }
                        else
                        {
                            if ( operacao == 's' ) // raiz quadrada
                            {
                                respostas = realizaRaizQuadrada( valores ); 
                            }
                            else // fatorial
                            {
                                respostas = realizaFatorial( valores );                                                             
                            }
                            
                            for( int j = 0; j < valores.size() - 1; j++ ) // armazena os valores que não foram usados no cálculo
                            {
                                valoresArmazena.add( valores.get( j ) );
                            }
                        }
                    }
                    
                    valores = new ArrayList();
                }
                             
                char entradaAux[] = new char[100]; 
                entrada.getChars( i+1, entrada.length(), entradaAux, 0 ); // pega a parte da string depois da operação encontrada e joga no entradaAux               
                
                String entrada2 = String.valueOf( entradaAux ); // transforma em string
                entrada2 = entrada2.trim();
                
                if ( !entrada2.isEmpty() ) // se não tiver mais operações na string, ele finaliza o programa
                {              
                    entrada2 = respostas + " " + entrada2; // 'joga' a resposta para o início da operação
                                   
                    for( int j = 0; j < valoresArmazena.size(); j++ )
                    {
                        entrada2 = valoresArmazena.get( j ) + " " + entrada2; // 'joga' os valores armazenados que não foram usados para o cálculo para o início da equação
                    }

                    respostas = 0;
                    valores = new ArrayList();
                    valoresArmazena = new ArrayList();
                    achouOperacao = false;
                    entrada = entrada2;
                    i = -1;
                }
                else
                {                    
                    i = entrada.length();
                }   
            }
        }  
        
        System.out.println( "A resposta é: " + respostas ); // printa a resposta   
        
    } 
    
    public static int realizaSoma( ArrayList<Integer> valores ) 
    {
        Integer inteiro;
        
        inteiro = valores.get( valores.size() - 2 ) + valores.get( valores.size() - 1 );
                                    
        return inteiro;
    }
    
    public static int realizaSubtracao( ArrayList<Integer> valores )
    {
        Integer inteiro;
        
        inteiro= valores.get( valores.size() - 2 ) - valores.get( valores.size() - 1 );
               
        return inteiro;
    }
    
    public static int realizaMultiplicacao( ArrayList<Integer> valores ) 
    {
        Integer inteiro;
        
        inteiro = valores.get( valores.size() - 2 ) * valores.get( valores.size() - 1 );
                                      
        return inteiro;
    }
    
    public static int realizaDivisao( ArrayList<Integer> valores )
    {
        Integer inteiro;
        
        inteiro = valores.get( valores.size() - 2 ) / valores.get( valores.size() - 1 );
              
        return inteiro;
    }
    
    public static int realizaFatorial( ArrayList<Integer> valores )
    {
        Integer inteiro;
        
        int x = valores.get( valores.size() - 1 );
        
        inteiro = x;
        
        while ( x > 1 )
        {
            inteiro = inteiro *( x - 1 ); 
            x--;
        }
        
        return inteiro;
    }
    
    public static int realizaPotencia( ArrayList<Integer> valores )
    {
        Integer inteiro;
        
        double a = valores.get( valores.size() - 2 );
        double b = valores.get( valores.size() - 1 );

        inteiro = (int)pow( a, b );
               
        return inteiro;
    }
    
    public static int realizaRaizQuadrada( ArrayList<Integer> valores ) 
    {
        Integer inteiro;
        
        double resposta2 = sqrt( (double)valores.get( valores.size() - 1 ) );
        
        inteiro = (int)resposta2;

        return inteiro;
    }
}
