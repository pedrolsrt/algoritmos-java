import java.util.Arrays;
import java.util.Comparator;

public class Mergesort<T> implements IOrdenador<T> {

    @Override
    public void ordenar(T[] vetor, Comparator<T> comparador) {
        if (vetor.length < 2) {
            return;
        }

        T[] auxiliar = Arrays.copyOf(vetor, vetor.length);
        ordenar(vetor, auxiliar, 0, vetor.length - 1, comparador);
    }

    private void ordenar(T[] vetor, T[] auxiliar, int inicio, int fim,
                         Comparator<T> comparador) {
        if (inicio >= fim) {
            return;
        }

        int meio = (inicio + fim) / 2;

        ordenar(vetor, auxiliar, inicio, meio, comparador);
        ordenar(vetor, auxiliar, meio + 1, fim, comparador);
        intercalar(vetor, auxiliar, inicio, meio, fim, comparador);
    }

    private void intercalar(T[] vetor, T[] auxiliar, int inicio, int meio, int fim,
                            Comparator<T> comparador) {
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
