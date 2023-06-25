package unicamp.ic.mc322.seguradora.test;

import java.util.LinkedList;

public class ExemploLinkedList {
   public static void main(String[] args) {
      // Criando uma nova LinkedList
      LinkedList<String> lista = new LinkedList<>();

      // Adicionando elementos à lista
      lista.add("Maçã");
      lista.add("Banana");
      lista.add("Laranja");
      lista.add("Abacaxi");

      // Imprimindo os elementos da lista
      System.out.println("Elementos da lista: " + lista);

      // Adicionando um elemento no início da lista
      lista.addFirst("Limão");

      // Adicionando um elemento no final da lista
      lista.addLast("Manga");

      // Removendo um elemento do início da lista
      lista.removeFirst();

      // Removendo um elemento do final da lista
      lista.removeLast();

      // Imprimindo os elementos da lista após as modificações
      System.out.println("Elementos da lista após as modificações: " + lista);
   }
}
