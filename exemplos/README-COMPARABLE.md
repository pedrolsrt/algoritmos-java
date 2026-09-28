# Exemplo completo com Comparable

Este exemplo mostra uma segunda forma comum de fazer ordenação genérica em Java.

## Quando usar

Use este modelo quando o enunciado pedir ou sugerir:

- `Comparable`
- `compareTo`
- ordem natural
- classe capaz de comparar seus próprios objetos
- ordenação genérica sem `Comparator`

## Diferença principal

Com `Comparator`, o critério fica fora da classe:

```java
Comparator<Aluno> porNota =
    Comparator.comparingDouble(Aluno::getNota);
```

Com `Comparable`, o critério fica dentro da própria classe:

```java
@Override
public int compareTo(Aluno outro) {
    return Double.compare(this.nota, outro.nota);
}
```

## O que adaptar na prova

Se o enunciado pedir outra entidade:

- `Aluno` -> `Produto`, `Livro`, `Pessoa`, `Funcionario`
- `matricula`, `nome`, `nota` -> atributos pedidos
- `compareTo` -> critério de ordenação pedido

## Trocas rápidas no compareTo

Por matrícula:

```java
return Integer.compare(this.matricula, outro.matricula);
```

Por nome:

```java
return this.nome.compareToIgnoreCase(outro.nome);
```

Por nota decrescente:

```java
return Double.compare(outro.nota, this.nota);
```
