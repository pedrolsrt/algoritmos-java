/*
EXEMPLO COMPLETO BASE - ORDENACAO POLIMORFICA COM COMPARATOR

USE ESTE ARQUIVO COMO PRIMEIRA OPCAO QUANDO O ENUNCIADO PEDIR:
- ordenacao polimorfica;
- ordenar objetos por um ou mais atributos;
- Comparator;
- Selection Sort, Bubble Sort, Insertion Sort, Merge Sort, Heap Sort ou Quick Sort.

PARA ADAPTAR NA ATIVIDADE, NORMALMENTE VOCE SO PRECISA:
1. Trocar Produto pelo nome da classe pedida (Aluno, Livro, Funcionario etc.).
2. Trocar codigo, nome e preco pelos atributos do enunciado.
3. Ajustar construtor, getters, setters e toString.
4. Criar os Comparator dos atributos pedidos.
5. Na linha "Ordenador<Produto> ordenador = ...", escolher o algoritmo solicitado.
6. Na chamada "ordenador.ordenar(...)", escolher o criterio solicitado.

TROCA RAPIDA DO ALGORITMO:
new Selecao<>()
new Bolha<>()
new Insercao<>()
new Mergesort<>()
new Heapsort<>()
new Quicksort<>()

ORDEM DECRESCENTE:
use porPreco.reversed(), porNome.reversed() etc.

O arquivo e autocontido: todas as classes necessarias estao aqui.
Depois desta explicacao, o codigo segue corrido, sem comentarios internos.
*/

import java.util.Arrays;
import java.util.Comparator;

interface Ordenador<T> {
    void ordenar(T[] vetor, Comparator<? super T> comparador);
}

class Selecao<T> implements Ordenador<T> {
    @Override
    public void ordenar(T[] vetor, Comparator<? super T> comparador) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                if (comparador.compare(vetor[j], vetor[menor]) < 0) {
                    menor = j;
                }
            }
            T temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }
}

class Bolha<T> implements Ordenador<T> {
    @Override
    public void ordenar(T[] vetor, Comparator<? super T> comparador) {
        for (int fim = vetor.length - 1; fim > 0; fim--) {
            boolean trocou = false;
            for (int i = 0; i < fim; i++) {
                if (comparador.compare(vetor[i], vetor[i + 1]) > 0) {
                    T temp = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = temp;
                    trocou = true;
                }
            }
            if (!trocou) {
                break;
            }
        }
    }
}

class Insercao<T> implements Ordenador<T> {
    @Override
    public void ordenar(T[] vetor, Comparator<? super T> comparador) {
        for (int i = 1; i < vetor.length; i++) {
            T chave = vetor[i];
            int j = i - 1;
            while (j >= 0 && comparador.compare(vetor[j], chave) > 0) {
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j + 1] = chave;
        }
    }
}

class Mergesort<T> implements Ordenador<T> {
    @Override
    public void ordenar(T[] vetor, Comparator<? super T> comparador) {
        if (vetor.length < 2) {
            return;
        }
        T[] auxiliar = Arrays.copyOf(vetor, vetor.length);
        mergeSort(vetor, auxiliar, 0, vetor.length - 1, comparador);
    }

    private void mergeSort(T[] vetor, T[] auxiliar, int inicio, int fim,
                           Comparator<? super T> comparador) {
        if (inicio >= fim) {
            return;
        }
        int meio = inicio + (fim - inicio) / 2;
        mergeSort(vetor, auxiliar, inicio, meio, comparador);
        mergeSort(vetor, auxiliar, meio + 1, fim, comparador);
        intercalar(vetor, auxiliar, inicio, meio, fim, comparador);
    }

    private void intercalar(T[] vetor, T[] auxiliar, int inicio, int meio, int fim,
                            Comparator<? super T> comparador) {
        for (int i = inicio; i <= fim; i++) {
            auxiliar[i] = vetor[i];
        }

        int i = inicio;
        int j = meio + 1;
        int k = inicio;

        while (i <= meio && j <= fim) {
            if (comparador.compare(auxiliar[i], auxiliar[j]) <= 0) {
                vetor[k++] = auxiliar[i++];
            } else {
                vetor[k++] = auxiliar[j++];
            }
        }

        while (i <= meio) {
            vetor[k++] = auxiliar[i++];
        }

        while (j <= fim) {
            vetor[k++] = auxiliar[j++];
        }
    }
}

class Heapsort<T> implements Ordenador<T> {
    @Override
    public void ordenar(T[] vetor, Comparator<? super T> comparador) {
        int n = vetor.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i, comparador);
        }

        for (int fim = n - 1; fim > 0; fim--) {
            T temp = vetor[0];
            vetor[0] = vetor[fim];
            vetor[fim] = temp;
            heapify(vetor, fim, 0, comparador);
        }
    }

    private void heapify(T[] vetor, int tamanho, int raiz,
                         Comparator<? super T> comparador) {
        int maior = raiz;
        int esquerda = 2 * raiz + 1;
        int direita = 2 * raiz + 2;

        if (esquerda < tamanho &&
            comparador.compare(vetor[esquerda], vetor[maior]) > 0) {
            maior = esquerda;
        }

        if (direita < tamanho &&
            comparador.compare(vetor[direita], vetor[maior]) > 0) {
            maior = direita;
        }

        if (maior != raiz) {
            T temp = vetor[raiz];
            vetor[raiz] = vetor[maior];
            vetor[maior] = temp;
            heapify(vetor, tamanho, maior, comparador);
        }
    }
}

class Quicksort<T> implements Ordenador<T> {
    @Override
    public void ordenar(T[] vetor, Comparator<? super T> comparador) {
        if (vetor.length > 1) {
            quickSort(vetor, 0, vetor.length - 1, comparador);
        }
    }

    private void quickSort(T[] vetor, int esquerda, int direita,
                           Comparator<? super T> comparador) {
        int i = esquerda;
        int j = direita;
        T pivo = vetor[esquerda + (direita - esquerda) / 2];

        while (i <= j) {
            while (comparador.compare(vetor[i], pivo) < 0) {
                i++;
            }

            while (comparador.compare(vetor[j], pivo) > 0) {
                j--;
            }

            if (i <= j) {
                T temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
                i++;
                j--;
            }
        }

        if (esquerda < j) {
            quickSort(vetor, esquerda, j, comparador);
        }

        if (i < direita) {
            quickSort(vetor, i, direita, comparador);
        }
    }
}

class Produto {
    private int codigo;
    private String nome;
    private double preco;

    public Produto(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " - R$ " + String.format("%.2f", preco);
    }
}

public class ExemploCompletoBase {
    public static void main(String[] args) {
        Produto[] produtos = {
            new Produto(3, "Mouse", 89.90),
            new Produto(1, "Teclado", 149.90),
            new Produto(4, "Monitor", 999.90),
            new Produto(2, "Headset", 199.90)
        };

        Comparator<Produto> porCodigo =
            Comparator.comparingInt(Produto::getCodigo);

        Comparator<Produto> porNome =
            Comparator.comparing(Produto::getNome, String.CASE_INSENSITIVE_ORDER);

        Comparator<Produto> porPreco =
            Comparator.comparingDouble(Produto::getPreco);

        Ordenador<Produto> ordenador = new Selecao<>();

        ordenador.ordenar(produtos, porPreco);

        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }
}
