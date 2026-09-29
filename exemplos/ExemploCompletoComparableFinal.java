/*
EXEMPLO COMPLETO BASE - ORDENACAO POLIMORFICA COM COMPARABLE

USE ESTE ARQUIVO QUANDO O ENUNCIADO PEDIR OU CITAR:
- Comparable;
- compareTo;
- ordem natural;
- objetos que devem saber comparar a si proprios;
- Selection Sort, Bubble Sort, Insertion Sort, Merge Sort, Heap Sort ou Quick Sort.

PARA ADAPTAR NA ATIVIDADE, NORMALMENTE VOCE SO PRECISA:
1. Trocar Aluno pelo nome da classe pedida.
2. Trocar matricula, nome e nota pelos atributos do enunciado.
3. Ajustar construtor, getters, setters e toString.
4. Alterar APENAS o compareTo para definir a ordem natural solicitada.
5. Na linha "OrdenadorNatural<Aluno> ordenador = ...", escolher o algoritmo pedido.

EXEMPLOS PARA O compareTo:
nota crescente:
return Double.compare(this.nota, outro.nota);

nota decrescente:
return Double.compare(outro.nota, this.nota);

matricula crescente:
return Integer.compare(this.matricula, outro.matricula);

nome crescente:
return this.nome.compareToIgnoreCase(outro.nome);

TROCA RAPIDA DO ALGORITMO:
new SelecaoNatural<>()
new BolhaNatural<>()
new InsercaoNatural<>()
new MergesortNatural<>()
new HeapsortNatural<>()
new QuicksortNatural<>()

O arquivo e autocontido: todas as classes necessarias estao aqui.
Depois desta explicacao, o codigo segue corrido, sem comentarios internos.
*/

import java.util.Arrays;

interface OrdenadorNatural<T extends Comparable<? super T>> {
    void ordenar(T[] vetor);
}

class SelecaoNatural<T extends Comparable<? super T>>
        implements OrdenadorNatural<T> {
    @Override
    public void ordenar(T[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[j].compareTo(vetor[menor]) < 0) {
                    menor = j;
                }
            }
            T temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }
}

class BolhaNatural<T extends Comparable<? super T>>
        implements OrdenadorNatural<T> {
    @Override
    public void ordenar(T[] vetor) {
        for (int fim = vetor.length - 1; fim > 0; fim--) {
            boolean trocou = false;
            for (int i = 0; i < fim; i++) {
                if (vetor[i].compareTo(vetor[i + 1]) > 0) {
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

class InsercaoNatural<T extends Comparable<? super T>>
        implements OrdenadorNatural<T> {
    @Override
    public void ordenar(T[] vetor) {
        for (int i = 1; i < vetor.length; i++) {
            T chave = vetor[i];
            int j = i - 1;
            while (j >= 0 && vetor[j].compareTo(chave) > 0) {
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j + 1] = chave;
        }
    }
}

class MergesortNatural<T extends Comparable<? super T>>
        implements OrdenadorNatural<T> {
    @Override
    public void ordenar(T[] vetor) {
        if (vetor.length < 2) {
            return;
        }
        T[] auxiliar = Arrays.copyOf(vetor, vetor.length);
        mergeSort(vetor, auxiliar, 0, vetor.length - 1);
    }

    private void mergeSort(T[] vetor, T[] auxiliar, int inicio, int fim) {
        if (inicio >= fim) {
            return;
        }
        int meio = inicio + (fim - inicio) / 2;
        mergeSort(vetor, auxiliar, inicio, meio);
        mergeSort(vetor, auxiliar, meio + 1, fim);
        intercalar(vetor, auxiliar, inicio, meio, fim);
    }

    private void intercalar(T[] vetor, T[] auxiliar,
                            int inicio, int meio, int fim) {
        for (int i = inicio; i <= fim; i++) {
            auxiliar[i] = vetor[i];
        }

        int i = inicio;
        int j = meio + 1;
        int k = inicio;

        while (i <= meio && j <= fim) {
            if (auxiliar[i].compareTo(auxiliar[j]) <= 0) {
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

class HeapsortNatural<T extends Comparable<? super T>>
        implements OrdenadorNatural<T> {
    @Override
    public void ordenar(T[] vetor) {
        int n = vetor.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i);
        }

        for (int fim = n - 1; fim > 0; fim--) {
            T temp = vetor[0];
            vetor[0] = vetor[fim];
            vetor[fim] = temp;
            heapify(vetor, fim, 0);
        }
    }

    private void heapify(T[] vetor, int tamanho, int raiz) {
        int maior = raiz;
        int esquerda = 2 * raiz + 1;
        int direita = 2 * raiz + 2;

        if (esquerda < tamanho &&
            vetor[esquerda].compareTo(vetor[maior]) > 0) {
            maior = esquerda;
        }

        if (direita < tamanho &&
            vetor[direita].compareTo(vetor[maior]) > 0) {
            maior = direita;
        }

        if (maior != raiz) {
            T temp = vetor[raiz];
            vetor[raiz] = vetor[maior];
            vetor[maior] = temp;
            heapify(vetor, tamanho, maior);
        }
    }
}

class QuicksortNatural<T extends Comparable<? super T>>
        implements OrdenadorNatural<T> {
    @Override
    public void ordenar(T[] vetor) {
        if (vetor.length > 1) {
            quickSort(vetor, 0, vetor.length - 1);
        }
    }

    private void quickSort(T[] vetor, int esquerda, int direita) {
        int i = esquerda;
        int j = direita;
        T pivo = vetor[esquerda + (direita - esquerda) / 2];

        while (i <= j) {
            while (vetor[i].compareTo(pivo) < 0) {
                i++;
            }

            while (vetor[j].compareTo(pivo) > 0) {
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
            quickSort(vetor, esquerda, j);
        }

        if (i < direita) {
            quickSort(vetor, i, direita);
        }
    }
}

class Aluno implements Comparable<Aluno> {
    private int matricula;
    private String nome;
    private double nota;

    public Aluno(int matricula, String nome, double nota) {
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public int compareTo(Aluno outro) {
        return Double.compare(this.nota, outro.nota);
    }

    @Override
    public String toString() {
        return matricula + " - " + nome + " - Nota: " + nota;
    }
}

public class ExemploCompletoComparableFinal {
    public static void main(String[] args) {
        Aluno[] alunos = {
            new Aluno(103, "Pedro", 8.5),
            new Aluno(101, "Ana", 9.2),
            new Aluno(104, "Carlos", 6.8),
            new Aluno(102, "Bruna", 7.9)
        };

        OrdenadorNatural<Aluno> ordenador = new SelecaoNatural<>();

        ordenador.ordenar(alunos);

        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
}
