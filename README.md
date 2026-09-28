# Códigos em Java

Base simples com exemplos de algoritmos de ordenação usando generics e Comparator.

## Arquivos

- `IOrdenador.java`: interface comum dos algoritmos.
- `Selecao.java`: Selection Sort.
- `Bolha.java`: Bubble Sort.
- `Insercao.java`: Insertion Sort.
- `Mergesort.java`: Merge Sort.
- `Heapsort.java`: Heap Sort.
- `Quicksort.java`: Quick Sort.
- `Produto.java`: classe de exemplo.
- `Main.java`: exemplo de uso e troca rápida de algoritmo/critério.

## Trocar o algoritmo

No `Main.java`, altere apenas a criação do objeto:

```java
IOrdenador<Produto> ordenador = new Quicksort<>();
```

Exemplos:

```java
new Selecao<>();
new Bolha<>();
new Insercao<>();
new Mergesort<>();
new Heapsort<>();
new Quicksort<>();
```

## Trocar o critério

```java
ordenador.ordenar(produtos, porCodigo);
ordenador.ordenar(produtos, porNome);
ordenador.ordenar(produtos, porPreco);
```

Para ordem decrescente:

```java
ordenador.ordenar(produtos, porPreco.reversed());
```
