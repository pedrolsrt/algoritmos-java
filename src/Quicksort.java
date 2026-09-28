import java.util.Comparator;

public class Quicksort<T> implements IOrdenador<T> {

    @Override
    public void ordenar(T[] vetor, Comparator<T> comparador) {
        if (vetor.length > 1) {
            quicksort(vetor, 0, vetor.length - 1, comparador);
        }
    }

    private void quicksort(T[] vetor, int esquerda, int direita, Comparator<T> comparador) {
        int i = esquerda;
        int j = direita;
        T pivo = vetor[(esquerda + direita) / 2];

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
            quicksort(vetor, esquerda, j, comparador);
        }

        if (i < direita) {
            quicksort(vetor, i, direita, comparador);
        }
    }
}
