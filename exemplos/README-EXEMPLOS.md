# Exemplos completos para adaptação

## ExemploCompletoProduto.java
Modelo completo com:
- interface genérica;
- polimorfismo;
- Comparator;
- Selection Sort;
- classe Produto;
- ordenação por código, nome ou preço;
- main pronto para executar.

Adaptação típica:
- Produto -> Aluno / Livro / Pessoa / Funcionário;
- codigo, nome, preco -> atributos do enunciado;
- porPreco -> critério pedido;
- Selecao -> algoritmo pedido.

## ExemploCompletoAluno.java
Modelo completo com:
- interface genérica;
- Insertion Sort;
- classe Aluno;
- ordenação por matrícula, nome ou nota;
- main pronto.

## Dica de prova
Se o enunciado pedir outro algoritmo, prefira reutilizar as classes já existentes em `src`:
- Selecao
- Bolha
- Insercao
- Mergesort
- Heapsort
- Quicksort

A parte que normalmente muda é:
1. nome da classe de domínio;
2. atributos;
3. Comparator;
4. algoritmo instanciado.
